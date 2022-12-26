package com.select.choice.domain.post.domain.service.impl;

import com.select.choice.domain.image.data.dto.ImageUploadDto;
import com.select.choice.domain.image.service.ImageService;
import com.select.choice.domain.post.domain.data.dto.CreatePostDto;
import com.select.choice.domain.post.domain.data.dto.PostDto;
import com.select.choice.domain.post.domain.data.entity.Post;
import com.select.choice.domain.post.domain.exception.PostNotFoundException;
import com.select.choice.domain.post.domain.repository.PostRepository;
import com.select.choice.domain.post.domain.service.PostService;
import com.select.choice.domain.post.domain.util.PostConverter;
import com.select.choice.domain.user.entity.User;
import com.select.choice.domain.user.facade.UserFacade;
import com.select.choice.global.error.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostConverter postConverter;
    private final ImageService imageService;
    private final UserFacade userFacade;

    @Override
    public List<PostDto> getAllPostList() {
        List<Post> list = postRepository.findAll();
        return postConverter.toPostDto(list);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDto> getBestPostList() {
        List<Post> list = postRepository.getBestPostList();
        return postConverter.toPostDto(list);
    }
    @Transactional
    @Override
    public void createPost(CreatePostDto postDto, MultipartFile image) throws IOException {
        User user = userFacade.currentUser();
        Post post = postConverter.toEntity(postDto, user);
        if(!image.isEmpty()) {
            ImageUploadDto imageUploadDto = imageService.uploadImage(image);
            post.updateThumbnail(imageUploadDto.getImageUrl());
        }
        postRepository.save(post);
    }

    @Override
    public void deletePost(Long postIdx) {
        Post post = postRepository.findById(postIdx).orElseThrow(()->new PostNotFoundException(ErrorCode.POST_NOT_FOUND));
        postRepository.delete(post);
    }

}

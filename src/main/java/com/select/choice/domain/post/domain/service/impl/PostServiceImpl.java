package com.select.choice.domain.post.domain.service.impl;

import com.select.choice.domain.image.service.ImageService;
import com.select.choice.domain.post.domain.data.dto.CreatePostDto;
import com.select.choice.domain.post.domain.data.dto.PostDto;
import com.select.choice.domain.post.domain.data.entity.Post;
import com.select.choice.domain.post.domain.repository.PostRepository;
import com.select.choice.domain.post.domain.request.CreatePostRequestDto;
import com.select.choice.domain.post.domain.service.PostService;
import com.select.choice.domain.post.domain.util.PostConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostConverter postConverter;
    private final ImageService imageService;

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
    public void createPost(CreatePostDto postDto) {
        Post post = postConverter.toEntity(postDto);
        postRepository.save(post);

    }

}

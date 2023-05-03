package com.select.choice.domain.post.service.impl;

import com.select.choice.domain.post.domain.entity.Post;
import com.select.choice.domain.post.domain.repository.PostRepository;
import com.select.choice.domain.post.presentation.data.dto.PostDto;
import com.select.choice.domain.post.presentation.data.dto.WebPostDto;
import com.select.choice.domain.post.service.GetPopularPostsService;
import com.select.choice.domain.post.util.PostConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetPopularPostServiceImpl implements GetPopularPostsService {
    private final PostRepository postRepository;
    private final PostConverter postConverter;

    @Override
    public List<PostDto> getPopularPosts(Pageable pageable) {
        List<Post> list = postRepository.getPopularPosts(pageable);
        return postConverter.toBestPostDto(list);
    }

    @Override
    public List<WebPostDto> getPopularPostList(Pageable pageable) {
        List<Post> list = postRepository.getPopularPosts(pageable);
        return postConverter.toBestPostDtoList(list);
    }
}
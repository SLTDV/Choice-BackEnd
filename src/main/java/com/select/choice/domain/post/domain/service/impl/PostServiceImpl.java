package com.select.choice.domain.post.domain.service.impl;

import com.select.choice.domain.post.domain.data.dto.PostDto;
import com.select.choice.domain.post.domain.data.entity.Post;
import com.select.choice.domain.post.domain.repository.PostRepository;
import com.select.choice.domain.post.domain.service.PostService;
import com.select.choice.domain.post.domain.util.PostConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostConverter postConverter;

    @Override
    public List<PostDto> getAllPostList() {
        List<Post> list = postRepository.findAll();
        return postConverter.toPostDto(list);
    }

    @Override
    public List<PostDto> getBestPostList() {
        return null;
    }
}

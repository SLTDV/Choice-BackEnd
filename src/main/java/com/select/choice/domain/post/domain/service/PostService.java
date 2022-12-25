package com.select.choice.domain.post.domain.service;


import com.select.choice.domain.post.domain.data.dto.CreatePostDto;
import com.select.choice.domain.post.domain.data.dto.PostDto;
import com.select.choice.domain.post.domain.request.CreatePostRequestDto;

import java.util.List;

public interface PostService {

    List<PostDto> getAllPostList();
    List<PostDto> getBestPostList();
    void createPost(List<CreatePostDto> createPostRequestDtoList);
}

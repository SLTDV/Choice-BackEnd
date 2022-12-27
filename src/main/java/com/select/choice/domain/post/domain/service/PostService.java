package com.select.choice.domain.post.domain.service;


import com.select.choice.domain.post.domain.data.dto.AddCountDto;
import com.select.choice.domain.post.domain.data.dto.CreatePostDto;
import com.select.choice.domain.post.domain.data.dto.PostDto;

import java.util.List;

public interface PostService {

    List<PostDto> getAllPostList();
    List<PostDto> getBestPostList();
    void createPost(CreatePostDto createPostDto);

    void deletePost(Long postIdx);

    void addCount(AddCountDto addCountDto, Long postIdx);
}

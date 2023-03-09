package com.select.choice.domain.post.service;


import com.select.choice.domain.post.presentation.data.dto.AddCountDto;
import com.select.choice.domain.post.presentation.data.dto.CreatePostDto;
import com.select.choice.domain.post.presentation.data.response.AddCountResponse;
import com.select.choice.domain.post.presentation.data.response.PostDetailResponse;
import com.select.choice.domain.post.presentation.data.response.PostListResponse;

import java.util.List;

public interface PostService {

    List<PostListResponse> getAllPostList();
    List<PostListResponse> getBestPostList();
    void createPost(CreatePostDto createPostDto);
    PostDetailResponse aggregateDetail(Long postIdx);

    void deletePost(Long postIdx);

    AddCountResponse addCount(AddCountDto addCountDto, Long postIdx);
}

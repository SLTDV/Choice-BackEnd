package com.select.choice.domain.post.domain.service;


import com.select.choice.domain.post.domain.data.dto.*;
import com.select.choice.domain.post.domain.data.response.AddCountResponse;
import com.select.choice.domain.post.domain.data.response.PostDetailResponse;

import java.util.List;

public interface PostService {

    List<PostListDto> getAllPostList();
    List<PostListDto> getBestPostList();
    void createPost(CreatePostDto createPostDto);
    PostDetailResponse aggregateDetail(Long postIdx);

    void deletePost(Long postIdx);

    AddCountResponse addCount(AddCountDto addCountDto, Long postIdx);
}

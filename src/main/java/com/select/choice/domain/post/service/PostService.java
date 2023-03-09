package com.select.choice.domain.post.service;


import com.select.choice.domain.post.presentation.data.dto.*;
import com.select.choice.domain.post.presentation.data.response.AddCountResponse;
import com.select.choice.domain.post.presentation.data.response.PostDetailResponse;

import java.util.List;

public interface PostService {

    List<PostDto> getAllPostList();
    List<PostDto> getBestPostList();
    void createPost(CreatePostDto createPostDto);
    PostDetailDto aggregateDetail(Long postIdx);

    void deletePost(Long postIdx);

    VoteCountDto addCount(AddCountDto addCountDto, Long postIdx);
}

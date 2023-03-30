package com.select.choice.domain.post.service;


import com.select.choice.domain.post.presentation.data.dto.*;

import java.util.List;

public interface PostService {

    List<PostDto> getAllPostList();
    List<PostDto> getBestPostList();
    void createPost(CreatePostDto createPostDto);
    PostDetailDto aggregateDetail(Long postIdx);
    void deletePost(Long postIdx);
    VoteCountDto voteCount(AddCountDto addCountDto, Long postIdx);
    List<WebVerPostDto> getPost();
}

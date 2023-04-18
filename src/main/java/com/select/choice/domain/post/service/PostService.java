package com.select.choice.domain.post.service;


import com.select.choice.domain.post.presentation.data.dto.*;
import com.select.choice.domain.post.presentation.data.dto.TodayPostListDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    List<PostDto> getAllPostList(Pageable pageable);
    List<PostDto> getBestPostList(Pageable pageable);
    void createPost(CreatePostDto createPostDto);
    PostDetailDto aggregateDetail(Long postIdx);
    void deletePost(Long postIdx);
    VoteCountDto voteCount(AddCountDto addCountDto, Long postIdx);
    List<WebVerPostDto> getPost(Pageable pageable);
    List<WebVerPostDto> getBestPost(Pageable pageable);
    TodayPostListDto getTodayPostList();
    WebVerPostDetailDto getPostDetail(Long postIdx);
}

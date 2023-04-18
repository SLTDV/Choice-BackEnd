package com.select.choice.domain.post.util;

import com.select.choice.domain.post.presentation.data.dto.CommentDetailDto;
import com.select.choice.domain.post.domain.entity.PostVotingState;
import com.select.choice.domain.post.presentation.data.dto.*;
import com.select.choice.domain.post.presentation.data.request.CreatePostRequest;
import com.select.choice.domain.post.presentation.data.response.*;
import com.select.choice.domain.post.domain.entity.Post;
import com.select.choice.domain.post.presentation.data.request.AddCountRequest;
import com.select.choice.domain.user.domain.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostConverter {
    List<PostResponse> toResponse(List<PostDto> dto);
    List<PostDto> toBestPostDto(List<Post> post);
    List<PostDto> toDto(List<Post> postList);
    Post toEntity(CreatePostDto dto, User user);
    PostDetailResponse toResponse(PostDetailDto postDetailDto);
    CreatePostDto toDto(CreatePostRequest createPostRequestDto);
    AddCountDto toDto(AddCountRequest addCountRequest);
    PostDetailDto toDto(List<CommentDetailDto> commentDetailDtoList , Post post);
    VoteCountResponse toResponse(VoteCountDto voteCountDto);
    VoteCountDto toDto(Integer firstVotingCount, Integer secondVotingCount);
    PostVotingState toEntity(User user, Post post);
    List<WebVerPostDto> toPostDto(List<Post> list);
    List<WebVerPostResponse> toPostResponse(List<WebVerPostDto> webVerPostDtoList);
    List<WebVerPostDto> toBestPostDtoList(List<Post> list);
    TodayPostDto toTodayPostDto(Post post);
    TodayPostListDto toTodayPostListDto(List<TodayPostDto> todayPosts);
    TodayPostResponse toTodayPostResponse(TodayPostDto todayPostDto);
    TodayPostListResponse toTodayPostListResponse(List<TodayPostResponse> todayPostListResponses);
    WebVerPostDetailDto toPostDetailDto(List<CommentDetailDto> commentDetailDtoList, Post post);
    WebVerPostDetailResponse toResponse(WebVerPostDetailDto dto);
    PostListDto toDto(List<Post> list, Pageable pageable);
    PostListResponse toResponse(List<PostResponse> postResponses, int pageNumber);
}

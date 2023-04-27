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
    VoteOptionDto toDto(AddCountRequest addCountRequest);
    PostDetailDto toDto(List<CommentDetailDto> commentDetailDtoList , Post post, Pageable pageable);
    VoteForPostResponse toResponse(VoteForPostDto voteCountDto);
    VoteForPostDto toDto(Integer firstVotingCount, Integer secondVotingCount);
    PostVotingState toEntity(User user, Post post);
    List<WebPostDto> toPostDto(List<Post> list);
    List<WebPostResponse> toPostResponse(List<WebPostDto> webVerPostDtoList);
    List<WebPostDto> toBestPostDtoList(List<Post> list);
    TodayPostDto toTodayPostDto(Post post);
    TodayPostListDto toTodayPostListDto(List<TodayPostDto> todayPosts);
    TodayPostResponse toTodayPostResponse(TodayPostDto todayPostDto);
    TodayPostListResponse toTodayPostListResponse(List<TodayPostResponse> todayPostListResponses);
    WebPostDetailDto toPostDetailDto(List<CommentDetailDto> commentDetailDtoList, Post post, Pageable pageable, User user);
    WebPostDetailResponse toResponse(WebPostDetailDto dto);
    PostListDto toDto(List<Post> list, Pageable pageable);
    PostListResponse toResponse(List<PostResponse> postResponses, int pageNumber);
    WebPostListResponse toWebResponse(List<WebPostResponse> webVerPostResponseList, int pageNumber);
}

package com.select.choice.domain.post.util;

import com.select.choice.domain.comment.presentation.data.dto.CommentDetailDto;
import com.select.choice.domain.post.presentation.data.dto.*;
import com.select.choice.domain.post.presentation.data.request.CreatePostRequest;
import com.select.choice.domain.post.presentation.data.response.VoteCountResponse;
import com.select.choice.domain.post.presentation.data.response.PostDetailResponse;
import com.select.choice.domain.post.domain.entity.Post;
import com.select.choice.domain.post.presentation.data.request.AddCountRequest;
import com.select.choice.domain.post.presentation.data.response.PostResponse;
import com.select.choice.domain.user.domain.entity.User;

import java.util.List;

public interface PostConverter {
    List<PostResponse> toResponse(List<PostDto> dto);
    List<PostResponse> toBesetPostResponse(List<PostDto> dto);
    List<PostDto> toBestPostDto(List<Post> post);
    List<PostDto> toDto(List<Post> postList);
    Post toEntity(CreatePostDto dto, User user);
    PostDetailResponse toResponse(PostDetailDto postDetailDto);
    CreatePostDto toDto(CreatePostRequest createPostRequestDto);
    AddCountDto toDto(AddCountRequest addCountRequest);
    PostDetailDto toDto(List<CommentDetailDto> commentDetailDtoList , User user);
    VoteCountResponse toResponse(VoteCountDto voteCountDto);
    VoteCountDto toDto(Integer firstVotingCount, Integer secondVotingCount);
}

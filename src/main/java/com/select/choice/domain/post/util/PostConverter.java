package com.select.choice.domain.post.util;

import com.select.choice.domain.comment.presentation.data.dto.CommentDetailDto;
import com.select.choice.domain.post.presentation.data.dto.AddCountDto;
import com.select.choice.domain.post.presentation.data.dto.CreatePostDto;
import com.select.choice.domain.post.presentation.data.dto.PostDetailDto;
import com.select.choice.domain.post.presentation.data.dto.AllPostListDto;
import com.select.choice.domain.post.presentation.data.request.CreatePostRequestDto;
import com.select.choice.domain.post.presentation.data.response.AddCountResponse;
import com.select.choice.domain.post.presentation.data.response.PostDetailResponse;
import com.select.choice.domain.post.presentation.data.response.AllPostListResponse;
import com.select.choice.domain.post.domain.entity.Post;
import com.select.choice.domain.post.presentation.data.request.AddCountRequest;
import com.select.choice.domain.user.data.entity.User;

import java.util.List;

public interface PostConverter {
    List<AllPostListResponse> toResponse(List<AllPostListDto> dto);
    List<AllPostListDto> toBestPostDto(List<Post> post);
    List<AllPostListDto> toPostListDto(List<Post> postList);
    Post toEntity(CreatePostDto dto, User user);
    PostDetailResponse toResponse(PostDetailDto postDetailDto);
    CreatePostDto toDto(CreatePostRequestDto createPostRequestDto);
    AddCountDto toDto(AddCountRequest addCountRequest);
    PostDetailDto toDto(List<CommentDetailDto> commentDetailDtoList , User user);

    AddCountResponse toResponse(Integer firstVotingCount, Integer secondVotingCount);
}

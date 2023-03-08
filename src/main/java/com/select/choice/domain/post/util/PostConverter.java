package com.select.choice.domain.post.util;

import com.select.choice.domain.comment.presentation.data.dto.CommentDetailDto;
import com.select.choice.domain.post.data.dto.AddCountDto;
import com.select.choice.domain.post.data.dto.CreatePostDto;
import com.select.choice.domain.post.data.dto.PostDetailDto;
import com.select.choice.domain.post.data.dto.PostListDto;
import com.select.choice.domain.post.data.request.CreatePostRequestDto;
import com.select.choice.domain.post.data.response.AddCountResponse;
import com.select.choice.domain.post.data.response.PostDetailResponse;
import com.select.choice.domain.post.data.response.PostListResponse;
import com.select.choice.domain.post.data.entity.Post;
import com.select.choice.domain.post.data.request.AddCountRequest;
import com.select.choice.domain.user.data.entity.User;

import java.util.List;

public interface PostConverter {
    List<PostListResponse> toResponse(List<PostListDto> dto);
    List<PostListDto> toBestPostDto(List<Post> post);
    List<PostListDto> toPostListDto(List<Post> postList);
    Post toEntity(CreatePostDto dto, User user);
    PostDetailResponse toResponse(PostDetailDto postDetailDto);
    CreatePostDto toDto(CreatePostRequestDto createPostRequestDto);
    AddCountDto toDto(AddCountRequest addCountRequest);
    PostDetailDto toDto(List<CommentDetailDto> commentDetailDtoList , User user);

    AddCountResponse toResponse(Integer firstVotingCount, Integer secondVotingCount);
}

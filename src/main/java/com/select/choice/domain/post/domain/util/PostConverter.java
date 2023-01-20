package com.select.choice.domain.post.domain.util;

import com.select.choice.domain.comment.domain.data.dto.CommentDetailDto;
import com.select.choice.domain.post.domain.data.dto.*;
import com.select.choice.domain.post.domain.data.entity.Post;
import com.select.choice.domain.post.domain.data.response.AddCountResponse;
import com.select.choice.domain.post.domain.data.response.PostDetailResponse;
import com.select.choice.domain.post.domain.data.request.AddCountRequest;
import com.select.choice.domain.post.domain.data.response.PostListResponse;
import com.select.choice.domain.post.domain.data.request.CreatePostRequestDto;
import com.select.choice.domain.user.domain.data.entity.User;

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

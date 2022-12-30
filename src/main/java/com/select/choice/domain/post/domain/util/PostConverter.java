package com.select.choice.domain.post.domain.util;

import com.select.choice.domain.comment.domain.data.dto.CommentDetailDto;
import com.select.choice.domain.post.domain.data.dto.*;
import com.select.choice.domain.post.domain.data.entity.Post;
import com.select.choice.domain.post.domain.data.response.AddCountResponse;
import com.select.choice.domain.post.domain.data.response.PostDetailResponse;
import com.select.choice.domain.post.domain.data.request.AddCountRequest;
import com.select.choice.domain.post.domain.data.response.PostResponse;
import com.select.choice.domain.post.domain.data.request.CreatePostRequestDto;
import com.select.choice.domain.user.domain.data.entity.User;

import java.util.List;

public interface PostConverter {
    List<PostResponse> toResponse(List<PostListDto> dto);
    List<PostListDto> toBestPostDto(List<Post> post);
    List<PostListDto> toPostListDto(List<Post> postList);
    Post toEntity(CreatePostDto dto, User user);
    PostDetailResponse toDetailResponse(PostDetailDto postDetailDto);
    CreatePostDto toCreatePost(CreatePostRequestDto createPostRequestDto);
    AddCountDto toAddCountDto(AddCountRequest addCountRequest);
    PostDetailDto postDetailDto(List<CommentDetailDto> commentDetailDtoList , User user);

    AddCountResponse toAddCountResponse(Integer firstVotingCount, Integer secondVotingCount);
}

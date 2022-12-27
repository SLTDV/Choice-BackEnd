package com.select.choice.domain.post.domain.util;

import com.select.choice.domain.post.domain.data.dto.AddCountDto;
import com.select.choice.domain.post.domain.data.dto.CreatePostDto;
import com.select.choice.domain.post.domain.data.dto.PostDetailDto;
import com.select.choice.domain.post.domain.data.dto.PostDto;
import com.select.choice.domain.post.domain.data.entity.Post;
import com.select.choice.domain.post.domain.data.request.AddCountRequest;
import com.select.choice.domain.post.domain.data.response.PostDetailResponse;
import com.select.choice.domain.post.domain.data.response.PostResponse;
import com.select.choice.domain.post.domain.data.request.CreatePostRequestDto;
import com.select.choice.domain.user.domain.data.entity.User;

import java.util.List;

public interface PostConverter {
    List<PostResponse> toResponse(List<PostDto> dto);
    List<PostDto>toPostDto(List<Post>post);
    List<PostDto>toBestPostDto(List<Post>post);
    Post toEntity(CreatePostDto dto, User user);
    PostDetailResponse toDetailResponse(PostDetailDto postDetailDto);
    CreatePostDto toCreatePost(CreatePostRequestDto createPostRequestDto);

    AddCountDto toAddCountDto(AddCountRequest addCountRequest);
}

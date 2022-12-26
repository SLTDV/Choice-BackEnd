package com.select.choice.domain.post.domain.util;

import com.select.choice.domain.post.domain.data.dto.CreatePostDto;
import com.select.choice.domain.post.domain.data.dto.PostDto;
import com.select.choice.domain.post.domain.data.entity.Post;
import com.select.choice.domain.post.domain.data.response.PostResponse;
import com.select.choice.domain.post.domain.data.request.CreatePostRequestDto;
import com.select.choice.domain.user.entity.User;

import java.util.List;

public interface PostConverter {
    List<PostResponse> toResponse(List<PostDto> dto);
    List<PostDto>toPostDto(List<Post>post);
    Post toEntity(CreatePostDto dto, User user);

    CreatePostDto toCreatePost(CreatePostRequestDto createPostRequestDto);
}

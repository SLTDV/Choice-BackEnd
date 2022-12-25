package com.select.choice.domain.post.domain.util;

import com.select.choice.domain.post.domain.data.dto.CreatePostDto;
import com.select.choice.domain.post.domain.data.dto.PostDto;
import com.select.choice.domain.post.domain.data.entity.Post;
import com.select.choice.domain.post.domain.data.reponse.PostResponse;
import com.select.choice.domain.post.domain.request.CreatePostRequestDto;

import java.util.List;

public interface PostConverter {
    List<PostResponse> toResponse(List<PostDto> dto);
    List<PostDto>toPostDto(List<Post>post);
    Post toEntity(CreatePostDto dto);

    List<CreatePostDto> toCreatePost(List<CreatePostRequestDto>createPostRequestDtos);
}

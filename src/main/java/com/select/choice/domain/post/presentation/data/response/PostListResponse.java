package com.select.choice.domain.post.presentation.data.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class PostListResponse {
    private final Integer page;
    private final Integer size;
    private final List<PostResponse> posts;
}

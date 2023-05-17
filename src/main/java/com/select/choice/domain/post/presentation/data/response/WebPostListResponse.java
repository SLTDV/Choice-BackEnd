package com.select.choice.domain.post.presentation.data.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class WebPostListResponse {
    private final Integer page;
    private final List<WebPostResponse> postList;
}

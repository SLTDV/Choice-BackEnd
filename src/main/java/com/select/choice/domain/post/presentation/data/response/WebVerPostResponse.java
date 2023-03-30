package com.select.choice.domain.post.presentation.data.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class WebVerPostResponse {
    private final Long idx;
    private final String imageUrl;
    private final String title;
    private final int participants;
    private final int commentCount;
}

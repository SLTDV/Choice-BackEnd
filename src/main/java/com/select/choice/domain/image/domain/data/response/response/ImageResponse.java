package com.select.choice.domain.image.domain.data.response.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Builder
public class ImageResponse {
    private final String imageUrl;
}

package com.select.choice.domain.image.presentation.data.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class ImageResponse {
    private final String imageUrl;
}

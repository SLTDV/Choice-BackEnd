package com.select.choice.domain.image.presentation.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class ImageUploadDto {
    private final String imageUrl;
}

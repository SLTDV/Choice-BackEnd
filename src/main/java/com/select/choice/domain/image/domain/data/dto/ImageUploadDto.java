package com.select.choice.domain.image.domain.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Builder
public class ImageUploadDto {
    private final String imageUrl;
}
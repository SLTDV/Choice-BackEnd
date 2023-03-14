package com.select.choice.domain.image.util;

import com.select.choice.domain.image.presentation.data.dto.ImageUploadDto;
import com.select.choice.domain.image.presentation.data.response.ImageResponse;
import com.select.choice.domain.image.presentation.data.response.ProfileImageResponse;

public interface ImageConverter {
    ImageResponse toResponse(ImageUploadDto dto, ImageUploadDto uploadDto);
    ImageUploadDto toDto(String firstUploadImageUrl);
    ProfileImageResponse toResponse(ImageUploadDto dto);
}

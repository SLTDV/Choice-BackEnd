package com.select.choice.domain.image.domain.util;

import com.select.choice.domain.image.domain.data.dto.ImageUploadDto;
import com.select.choice.domain.image.domain.data.response.ImageResponse;

public interface ImageConverter {
    ImageResponse toResponse(ImageUploadDto imageUploadDto);
    ImageUploadDto toDto(String uploadImageUrl);
}

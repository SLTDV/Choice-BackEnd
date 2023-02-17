package com.select.choice.domain.image.util;

import com.select.choice.domain.image.data.dto.ImageUploadDto;
import com.select.choice.domain.image.data.response.ImageResponse;

public interface ImageConverter {
    ImageResponse toResponse(ImageUploadDto imageUploadDto);
    ImageUploadDto toDto(String uploadImageUrl);
}

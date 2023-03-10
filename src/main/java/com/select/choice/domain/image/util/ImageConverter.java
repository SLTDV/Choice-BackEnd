package com.select.choice.domain.image.util;

import com.select.choice.domain.image.presentation.data.dto.ImageUploadDto;
import com.select.choice.domain.image.presentation.data.response.ImageResponse;

public interface ImageConverter {
    ImageResponse toResponse(ImageUploadDto dto, ImageUploadDto uploadDto);
    ImageUploadDto toDto(String firstUploadImageUrl);

}

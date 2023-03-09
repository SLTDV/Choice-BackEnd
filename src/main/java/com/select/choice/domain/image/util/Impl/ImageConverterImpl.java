package com.select.choice.domain.image.util.Impl;

import com.select.choice.domain.image.presentation.data.dto.ImageUploadDto;
import com.select.choice.domain.image.presentation.data.response.ImageResponse;
import com.select.choice.domain.image.util.ImageConverter;
import org.springframework.stereotype.Component;

@Component
public class ImageConverterImpl implements ImageConverter {

    @Override
    public ImageResponse toResponse(ImageUploadDto dto, ImageUploadDto uploadDto) {
        return ImageResponse.builder()
                .firstUploadImageUrl(dto.getUploadImageUrl())
                .secondUploadImageUrl(uploadDto.getUploadImageUrl())
                .build();
    }

    @Override
    public ImageUploadDto toDto(String firstUploadImageUrl) {
        return ImageUploadDto.builder()
                .uploadImageUrl(firstUploadImageUrl)
                .build();
    }

}

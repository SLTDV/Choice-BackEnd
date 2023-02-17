package com.select.choice.domain.image.util.Impl;

import com.select.choice.domain.image.data.dto.ImageUploadDto;
import com.select.choice.domain.image.data.response.ImageResponse;
import com.select.choice.domain.image.util.ImageConverter;
import org.springframework.stereotype.Component;

@Component
public class ImageConverterImpl implements ImageConverter {

    @Override
    public ImageResponse toResponse(ImageUploadDto imageUploadDto) {
        String dtoImageUrl = imageUploadDto.getImageUrl();

        return ImageResponse.builder()
                .imageUrl(dtoImageUrl)
                .build();
    }

    @Override
    public ImageUploadDto toDto(String uploadImageUrl) {
        return ImageUploadDto.builder()
                .imageUrl(uploadImageUrl)
                .build();
    }
}

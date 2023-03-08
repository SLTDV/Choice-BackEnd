package com.select.choice.domain.image.util.Impl;

import com.select.choice.domain.image.presentation.data.dto.ImageUploadDto;
import com.select.choice.domain.image.presentation.data.response.ImageResponse;
import com.select.choice.domain.image.util.ImageConverter;
import org.springframework.stereotype.Component;

@Component
public class ImageConverterImpl implements ImageConverter {

    @Override
    public ImageResponse toResponse(ImageUploadDto imageUploadDto) {
        return ImageResponse.builder()
                .firstUploadImageUrl(imageUploadDto.getFirstUploadImageUrl())
                .secondUploadImageUrl(imageUploadDto.getSecondUploadImageUrl())
                .build();
    }

    @Override
    public ImageUploadDto toDto(String firstUploadImageUrl, String secondUploadImageUrl) {
        return ImageUploadDto.builder()
                .firstUploadImageUrl(firstUploadImageUrl)
                .secondUploadImageUrl(secondUploadImageUrl)
                .build();
    }

}

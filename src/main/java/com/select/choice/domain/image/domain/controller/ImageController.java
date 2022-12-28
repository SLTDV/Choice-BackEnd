package com.select.choice.domain.image.domain.controller;


import com.select.choice.domain.image.domain.data.dto.ImageUploadDto;
import com.select.choice.domain.image.domain.data.response.ImageResponse;
import com.select.choice.domain.image.domain.service.ImageService;
import com.select.choice.domain.image.domain.util.ImageConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RequiredArgsConstructor
@RestController
@RequestMapping("/image")
public class ImageController {
    private final ImageService imageService;
    private final ImageConverter imageConverter;

    /*
    기능: 이미지 업로드
    담당자: 노혁
     */
    @PostMapping
    public ResponseEntity<ImageResponse> imageUpload(
            @RequestPart(value = "file", required = false)MultipartFile image) throws IOException {
        ImageUploadDto imageUploadDto = imageService.uploadImage(image);
        ImageResponse imageResponse = imageConverter.toResponse(imageUploadDto);
        return new ResponseEntity<>(imageResponse,HttpStatus.OK);
    }
}

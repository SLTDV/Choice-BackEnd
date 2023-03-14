package com.select.choice.domain.image.presentation;


import com.select.choice.domain.image.presentation.data.dto.ImageUploadDto;
import com.select.choice.domain.image.presentation.data.response.ImageResponse;
import com.select.choice.domain.image.presentation.data.response.ProfileImageResponse;
import com.select.choice.domain.image.service.ImageService;
import com.select.choice.domain.image.util.ImageConverter;
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
            @RequestPart(value = "firstImage")MultipartFile firstImage,
            @RequestPart(value = "secondImage")MultipartFile secondImage) throws IOException {
        ImageUploadDto dto = imageService.uploadImage(firstImage);
        ImageUploadDto uploadDto = imageService.uploadImage(secondImage);
        ImageResponse imageResponse = imageConverter.toResponse(dto, uploadDto);
        return new ResponseEntity<>(imageResponse, HttpStatus.OK);
    }

    /*
    기능: 프로필 이미지 업로드
    담당자: 노혁
     */
    @PostMapping("/profile")
    public ResponseEntity<ProfileImageResponse> profileImageUpload(
            @RequestPart(value = "profileImage")MultipartFile profileImage) throws IOException {
        ImageUploadDto dto = imageService.uploadImage(profileImage);
        ProfileImageResponse profileImageResponse = imageConverter.toResponse(dto);
        return new ResponseEntity<>(profileImageResponse, HttpStatus.OK);
    }

}

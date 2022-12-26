package com.select.choice.domain.image.controller;


import com.select.choice.domain.image.data.dto.ImageUploadDto;
import com.select.choice.domain.image.service.ImageService;
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
    @PostMapping()
    public ResponseEntity<String> imageUpload(@RequestPart(value = "file", required = false)MultipartFile image) throws IOException {
        ImageUploadDto imageUploadDto = imageService.uploadImage(image);
        return new ResponseEntity<>(imageUploadDto.getImageUrl(),HttpStatus.OK);
    }
}

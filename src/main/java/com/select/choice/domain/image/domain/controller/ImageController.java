package com.select.choice.domain.image.domain.controller;


import com.select.choice.domain.image.domain.data.response.ImageResponse;
import com.select.choice.domain.image.domain.service.ImageService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<ImageResponse> imageUpload(@RequestPart(value = "file", required = false)MultipartFile image) throws IOException {
        String imageUrl = imageService.uploadImage(image);
        ImageResponse imageResponse = new ImageResponse(imageUrl);
        return ResponseEntity.ok(imageResponse);
    }
}

package com.select.choice.domain.image.domain.service;

import com.select.choice.domain.image.domain.data.dto.ImageUploadDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    ImageUploadDto uploadImage(MultipartFile image) throws IOException;
}
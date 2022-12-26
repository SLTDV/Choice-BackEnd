package com.select.choice.domain.image.service;

import com.select.choice.domain.image.data.dto.ImageUploadDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    ImageUploadDto uploadImage(MultipartFile multipartFile) throws IOException;
}

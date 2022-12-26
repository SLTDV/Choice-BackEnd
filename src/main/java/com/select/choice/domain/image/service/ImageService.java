package com.select.choice.domain.image.service;

import com.select.choice.domain.image.data.dto.ImageUploadDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    //String형으로 imageUrl을 반환함
    String uploadImage(MultipartFile image) throws IOException;
}

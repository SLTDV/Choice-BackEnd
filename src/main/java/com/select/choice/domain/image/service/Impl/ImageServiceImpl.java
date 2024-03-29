package com.select.choice.domain.image.service.Impl;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.select.choice.domain.image.exception.ConvertMultipartFileException;
import com.select.choice.domain.image.presentation.data.dto.ImageUploadDto;
import com.select.choice.domain.image.service.ImageService;
import com.select.choice.domain.image.util.ImageConverter;
import com.select.choice.global.error.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class ImageServiceImpl implements ImageService {
    private final AmazonS3Client amazonS3Client;
    private final ImageConverter imageConverter;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    // MultipartFile을 전달받아 File로 전환한 후 S3에 업로드
    public ImageUploadDto uploadImage(MultipartFile file) throws IOException {
        File uploadFile = convert(file)
                .orElseThrow(() -> new ConvertMultipartFileException(ErrorCode.CONVERT_MULTIPART_FILE));
        return upload(uploadFile);
    }

    private ImageUploadDto upload(File file) {
        String fileUploadName = "images" + "/" + file.getName();

        String firstUploadImageUrl = putS3(file, fileUploadName);

        removeNewFile(file);  // 로컬에 생성된 File 삭제 (MultipartFile -> File 전환 하며 로컬에 파일 생성됨)

        return imageConverter.toDto(firstUploadImageUrl);      // 업로드된 파일의 S3 URL 주소 반환
    }

    private String putS3(File uploadFile, String fileName) {
        amazonS3Client.putObject(
                new PutObjectRequest(bucket, fileName, uploadFile)
                        .withCannedAcl(CannedAccessControlList.PublicRead)	// PublicRead 권한으로 업로드 됨
        );
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    private void removeNewFile(File targetFile) {
        targetFile.delete();
    }

    private Optional<File> convert(MultipartFile file) throws  IOException {
        File convertFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        if(convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }
}

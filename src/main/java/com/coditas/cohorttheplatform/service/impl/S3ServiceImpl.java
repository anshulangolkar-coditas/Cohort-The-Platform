package com.coditas.cohorttheplatform.service.impl;

import com.coditas.cohorttheplatform.exception.ExceptionMessages;
import com.coditas.cohorttheplatform.exception.InvalidFileException;
import com.coditas.cohorttheplatform.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class S3ServiceImpl implements S3Service {

    @Value("${aws.s3.bucket}")
    private String bucket;

    private final S3Client s3Client;

    @Override
    public String uploadFile(MultipartFile file) {

        validateFile(file);

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        try {
            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(bucket)
                    .key(fileName)
                    .contentType(file.getContentType())
                    .build();

      s3Client.putObject(request, RequestBody.fromBytes(file.getBytes()));

            return fileName;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void validateFile(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new InvalidFileException(ExceptionMessages.FILE_NULL);
        }

        if (!ALLOWED_CONTENT_TYPES.contains(file.getContentType())) {
            throw new InvalidFileException(ExceptionMessages.FILE_UPLOAD_EXTENSIONS);
        }

        long maxSize = 10 * 1024 * 1024;

        if (file.getSize() > maxSize) {
            throw new InvalidFileException(ExceptionMessages.FILE_UPLOAD_LIMIT);
        }
    }

    private static final Set<String> ALLOWED_CONTENT_TYPES = Set.of("application/pdf",
            "application/zip",
            "application/txt",
            "application/x-zip-compressed",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
            "application/msword",
            "application/vnd.openxmlformats-officedocument.presentationml.presentation",
            "application/vnd.ms-powerpoint");

}


package com.coditas.cohorttheplatform.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface S3Service {

    String uploadFile(MultipartFile file);

    String generatePresignedUrl(String fileKey);


}

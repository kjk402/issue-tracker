package com.bas.issuetracker.web.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class S3FileUploader {

    private final AmazonS3 amazonS3;

    @Value(("${aws.s3.image.bucket}"))
    private String bucket;

    public S3FileUploader(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public String upload(MultipartFile multipartFile) throws IOException {
        String fileName = multipartFile.getOriginalFilename();
        PutObjectRequest putObjectRequest = new PutObjectRequest(
                bucket,
                fileName,
                multipartFile.getInputStream(), null)
                .withCannedAcl(CannedAccessControlList.PublicRead);
        amazonS3.putObject(putObjectRequest);
        return fileName;
    }
}

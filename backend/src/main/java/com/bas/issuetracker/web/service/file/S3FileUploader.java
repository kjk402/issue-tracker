package com.bas.issuetracker.web.service.file;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.bas.issuetracker.web.exceptions.FileUploadFailedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

import static com.bas.issuetracker.web.exceptions.FileUploadFailedException.*;

@Service
public class S3FileUploader {

    private final AmazonS3 amazonS3;
    private final FileExtentionVerifier fileExtentionVerifier;

    @Value(("${aws.s3.image.bucket}"))
    private String bucket;

    public S3FileUploader(AmazonS3 amazonS3, FileExtentionVerifier fileExtentionVerifier) {
        this.amazonS3 = amazonS3;
        this.fileExtentionVerifier = fileExtentionVerifier;
    }

    public String upload(MultipartFile multipartFile) throws IOException {
        String extention = extractExtention(multipartFile.getOriginalFilename());
        String newName = createNewFileName(extention);
        verifyExtention(extention);
        PutObjectRequest putObjectRequest = new PutObjectRequest(
                bucket,
                newName,
                multipartFile.getInputStream(),
                new ObjectMetadata())
                .withCannedAcl(CannedAccessControlList.PublicRead);
        amazonS3.putObject(putObjectRequest);
        return newName;
    }

    private void verifyExtention(String extention) {
        if (!fileExtentionVerifier.isAvailable(extention)) {
            throw new FileUploadFailedException(INVALID_FILE_EXTENTION);
        }
    }

    private String extractExtention(String fileName) {
        if (fileName == null) {
            throw new FileUploadFailedException(NO_FILE_NAME);
        }
        String[] splitedName = fileName.split("\\.");
        if (splitedName.length < 2) {
            throw new FileUploadFailedException(INVALID_FILE_NAME);
        }
        return splitedName[splitedName.length - 1];
    }

    private String createNewFileName(String extention) {
        return UUID.randomUUID() + "." + extention;
    }
}

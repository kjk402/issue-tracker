package com.bas.issuetracker.web.controller;

import com.bas.issuetracker.web.service.file.S3FileUploader;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Api(tags = {"Image API"})
@RequestMapping("/images")
@RestController
public class ImageController {

    private final S3FileUploader fileUploader;

    public ImageController(S3FileUploader fileUploader) {
        this.fileUploader = fileUploader;
    }

    @PostMapping
    public void uploadImage(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        String uploadedFileUrl = fileUploader.upload(multipartFile);
        /*@Todo
        *   파일의 URL을 가지고 디비에 저장하는 기능을 구현해야함*/
    }

}

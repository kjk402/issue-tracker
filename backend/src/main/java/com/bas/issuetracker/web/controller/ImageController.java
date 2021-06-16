package com.bas.issuetracker.web.controller;

import com.bas.issuetracker.web.dto.image.ImageDTO;
import com.bas.issuetracker.web.service.file.S3FileUploader;
import com.bas.issuetracker.web.service.image.ImageService;
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
    private final ImageService imageService;

    public ImageController(S3FileUploader fileUploader, ImageService imageService) {
        this.fileUploader = fileUploader;
        this.imageService = imageService;
    }

    @PostMapping
    public ImageDTO uploadImage(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        String uploadedFileUrl = fileUploader.upload(multipartFile);
        return imageService.createImage(uploadedFileUrl);
    }

}

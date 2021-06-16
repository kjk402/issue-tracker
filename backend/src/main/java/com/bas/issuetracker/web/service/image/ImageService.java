package com.bas.issuetracker.web.service.image;

import com.bas.issuetracker.web.domain.image.Image;
import com.bas.issuetracker.web.domain.image.ImageRepository;
import com.bas.issuetracker.web.dto.image.ImageDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ImageService {

    private final ImageRepository imageRepository;
    private final ImageDtoConverter imageDtoConverter;

    public ImageService(ImageRepository imageRepository, ImageDtoConverter imageDtoConverter) {
        this.imageRepository = imageRepository;
        this.imageDtoConverter = imageDtoConverter;
    }

    @Transactional
    public ImageDTO createImage(String imageUrl) {
        Image image = new Image(imageUrl);
        imageRepository.create(image);
        return imageDtoConverter.imageToImageDTO(image);
    }

    public void showImageOfIssue() {

    }

    @Transactional
    public void updateImage() {

    }
}

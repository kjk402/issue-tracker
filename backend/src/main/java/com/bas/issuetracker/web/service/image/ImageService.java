package com.bas.issuetracker.web.service.image;

import com.bas.issuetracker.web.domain.image.Image;
import com.bas.issuetracker.web.domain.image.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ImageService {

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Transactional
    public Image createImage() {
        return null;
    }

    public void showImageOfIssue() {

    }

    @Transactional
    public void updateImage() {

    }
}

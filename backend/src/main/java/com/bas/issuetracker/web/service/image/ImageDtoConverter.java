package com.bas.issuetracker.web.service.image;

import com.bas.issuetracker.web.domain.image.Image;
import com.bas.issuetracker.web.dto.image.ImageDTO;
import org.springframework.stereotype.Service;

@Service
public class ImageDtoConverter {
    public ImageDTO imageToImageDTO(Image image) {
        return new ImageDTO(image.getId(), image.getImageUrl());
    }
}

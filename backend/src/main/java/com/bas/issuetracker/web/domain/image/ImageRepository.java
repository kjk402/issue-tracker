package com.bas.issuetracker.web.domain.image;

import java.util.List;

public interface ImageRepository {
    Image create(Image image);

    Image updateIssueId(Image image, int issueId);

    List<Image> findAllByIssueId(int issueId);
}

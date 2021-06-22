package com.bas.issuetracker.web.domain.image;

import java.util.List;

public interface ImageRepository {
    Image create(Image image);

    Image updateIssue(Image image, int issueId);

    List<Image> findAllByIssueId(int issueId);

    void updateIssueId(int imageId, int issueId);
}

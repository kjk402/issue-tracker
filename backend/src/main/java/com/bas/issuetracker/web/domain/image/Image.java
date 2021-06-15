package com.bas.issuetracker.web.domain.image;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Image {
    private int id;
    private int issueId;
    private String imageUrl;

    public void updateId(int id) {
        this.id = id;
    }

    public void updateIssueId(int issueId) {
        this.issueId = issueId;
    }
}

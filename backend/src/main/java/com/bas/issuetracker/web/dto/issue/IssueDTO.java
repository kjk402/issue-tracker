package com.bas.issuetracker.web.dto.issue;

import java.time.LocalDateTime;

public class IssueDTO {
    private int id;
    private String title;
    private int isOpen;
    private int commentCount;
    private LocalDateTime lastModifiedDateTime;

    public IssueDTO(int id, String title, int isOpen, int commentCount, LocalDateTime lastModifiedDateTime) {
        this.id = id;
        this.title = title;
        this.isOpen = isOpen;
        this.commentCount = commentCount;
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getIsOpen() {
        return isOpen;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public LocalDateTime getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }
}

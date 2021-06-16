package com.bas.issuetracker.web.dto.issue;

import java.time.LocalDateTime;

public class IssueInfo {
    private int issueId;
    private String title;
    private boolean isOpen;
    private int commentCount;
    private LocalDateTime lastModifiedDateTime;

    public IssueInfo(int issueId, String title, boolean isOpen, int commentCount, LocalDateTime lastModifiedDateTime) {
        this.issueId = issueId;
        this.title = title;
        this.isOpen = isOpen;
        this.commentCount = commentCount;
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public int getIssueId() {
        return issueId;
    }

    public String getTitle() {
        return title;
    }

    public boolean getIsOpen() {
        return isOpen;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public LocalDateTime getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }
}

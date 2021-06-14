package com.bas.issuetracker.web.domain.comment;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class Comment {
    private int id;
    private String content;
    private int issueId;
    private int authorId;
    private boolean deletable;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime lastModifiedDateTime;

    public Comment(int id, String content, int issueId, int authorId, boolean deletable) {
        this.id = id;
        this.content = content;
        this.issueId = issueId;
        this.authorId = authorId;
        this.deletable = deletable;
        this.lastModifiedDateTime = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public int getIssueId() {
        return issueId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public boolean isDeletable() {
        return deletable;
    }

    public LocalDateTime getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

}

package com.bas.issuetracker.web.dto.comment;

import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class CommentRequest {
    private int issueId;
    private String content;

    public CommentRequest(int issueId, String content) {
        this.issueId = issueId;
        this.content = content;
    }

    public int getIssueId() {
        return issueId;
    }

    public String getContent() {
        return content;
    }
}

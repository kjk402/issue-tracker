package com.bas.issuetracker.web.dto.comment;

import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class CommentUpdateRequest {
    private int commentId;
    private String content;

    public CommentUpdateRequest(int commentId, String content) {
        this.commentId = commentId;
        this.content = content;
    }

    public int getCommentId() {
        return commentId;
    }

    public String getContent() {
        return content;
    }

}

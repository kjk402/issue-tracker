package com.bas.issuetracker.web.dto.comment;

import com.bas.issuetracker.web.dto.issue.UserDTO;

import java.time.LocalDateTime;

public class CommentDTO {
    private int commentId;
    private String content;
    private LocalDateTime lastModifiedDateTime;
    private UserDTO userDTO;

    public CommentDTO(int commentId, String content, LocalDateTime lastModifiedDateTime, UserDTO userDTO) {
        this.commentId = commentId;
        this.content = content;
        this.lastModifiedDateTime = lastModifiedDateTime;
        this.userDTO = userDTO;
    }

    public int getCommentId() {
        return commentId;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

}

package com.bas.issuetracker.web.dto.comment;

import com.bas.issuetracker.web.dto.issue.UserDTO;

import java.time.LocalDateTime;

public class CommentDTO {
    private String content;
    private LocalDateTime lastModifiedDateTime;
    private UserDTO userDTO;

    public CommentDTO(String content, LocalDateTime lastModifiedDateTime, UserDTO userDTO) {
        this.content = content;
        this.lastModifiedDateTime = lastModifiedDateTime;
        this.userDTO = userDTO;
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

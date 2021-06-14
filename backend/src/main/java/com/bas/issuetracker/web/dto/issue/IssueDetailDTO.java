package com.bas.issuetracker.web.dto.issue;

import com.bas.issuetracker.web.dto.comment.CommentDTO;

import java.util.List;

public class IssueDetailDTO {
    private IssueListDTO issueListDTO;
    private List<CommentDTO> commentDTOS;

    public IssueDetailDTO(IssueListDTO issueListDTO, List<CommentDTO> commentDTOS) {
        this.issueListDTO = issueListDTO;
        this.commentDTOS = commentDTOS;
    }

    public IssueListDTO getIssueListDTO() {
        return issueListDTO;
    }

    public List<CommentDTO> getCommentDTOS() {
        return commentDTOS;
    }

}

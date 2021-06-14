package com.bas.issuetracker.web.dto.issue;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class IssueResponseDTO {
    private IssueRequestDTO issueRequestDTO;


    public IssueResponseDTO(IssueRequestDTO issueRequestDTO) {
        this.issueRequestDTO = issueRequestDTO;

    }

    public IssueRequestDTO getIssueRequestDTO() {
        return issueRequestDTO;
    }

}

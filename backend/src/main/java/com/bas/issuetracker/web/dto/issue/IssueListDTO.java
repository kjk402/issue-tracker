package com.bas.issuetracker.web.dto.issue;

import java.util.List;

public class IssueListDTO {
    private List<IssuePreviewDTO> issueList;

    public IssueListDTO(List<IssuePreviewDTO> issueList) {
        this.issueList = issueList;
    }

    public List<IssuePreviewDTO> getIssueList() {
        return issueList;
    }

}

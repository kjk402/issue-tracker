package com.bas.issuetracker.web.dto.issue;

public class IssueDTO {
    private IssueInfo issueInfo;
    private UserDTO userDTO;

    public IssueDTO(IssueInfo issueInfo, UserDTO userDTO) {
        this.issueInfo = issueInfo;
        this.userDTO = userDTO;
    }

    public IssueInfo getIssueInfo() {
        return issueInfo;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

}

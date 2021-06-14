package com.bas.issuetracker.web.dto.issue;

public class IssueListDTO {
    private IssueDTO issueDTO;
    private UserDTO userDTO;

    public IssueListDTO(IssueDTO issueDTO, UserDTO userDTO) {
        this.issueDTO = issueDTO;
        this.userDTO = userDTO;
    }

    public IssueDTO getIssueDTO() {
        return issueDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

}

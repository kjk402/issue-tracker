package com.bas.issuetracker.web.dto.issue;

import java.util.List;

public class AssignedDTO {
    private List<UserDTO> assignedList;

    public AssignedDTO(List<UserDTO> assignedList) {
        this.assignedList = assignedList;
    }

    public List<UserDTO> getAssignedList() {
        return assignedList;
    }

}


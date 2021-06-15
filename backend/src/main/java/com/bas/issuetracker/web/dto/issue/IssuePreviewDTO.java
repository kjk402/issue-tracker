package com.bas.issuetracker.web.dto.issue;

import com.bas.issuetracker.web.dto.comment.CommentDTO;
import com.bas.issuetracker.web.dto.label.LabelPreview;
import com.bas.issuetracker.web.dto.milestone.MilestoneInIssue;

import java.util.List;

public class IssuePreviewDTO {
    private IssueDTO issueDTO;
    private List<UserDTO> assignedList;
    private List<LabelPreview> labelList;
    private MilestoneInIssue milestone;

    public IssuePreviewDTO(IssueDTO issueDTO, List<UserDTO> assignedList, List<LabelPreview> labelList, MilestoneInIssue milestone) {
        this.issueDTO = issueDTO;
        this.assignedList = assignedList;
        this.labelList = labelList;
        this.milestone = milestone;
    }

    public IssueDTO getIssueDTO() {
        return issueDTO;
    }

    public List<UserDTO> getAssignedList() {
        return assignedList;
    }

    public List<LabelPreview> getLabelList() {
        return labelList;
    }

    public MilestoneInIssue getMilestone() {
        return milestone;
    }

}

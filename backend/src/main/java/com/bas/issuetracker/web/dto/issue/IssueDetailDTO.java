package com.bas.issuetracker.web.dto.issue;

import com.bas.issuetracker.web.dto.comment.CommentDTO;
import com.bas.issuetracker.web.dto.label.LabelPreview;
import com.bas.issuetracker.web.dto.milestone.MilestoneInIssue;

import java.util.List;

public class IssueDetailDTO {
    private IssueDTO issueDTO;
    private List<CommentDTO> commentDTO;
    private List<UserDTO> assignedList;
    private List<LabelPreview> labelList;
    private MilestoneInIssue milestone;

    public IssueDetailDTO(IssueDTO issueDTO, List<CommentDTO> commentDTO, List<UserDTO> assignedList, List<LabelPreview> labelList, MilestoneInIssue milestone) {
        this.issueDTO = issueDTO;
        this.commentDTO = commentDTO;
        this.assignedList = assignedList;
        this.labelList = labelList;
        this.milestone = milestone;
    }

    public IssueDTO getIssueDTO() {
        return issueDTO;
    }

    public List<CommentDTO> getCommentDTO() {
        return commentDTO;
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
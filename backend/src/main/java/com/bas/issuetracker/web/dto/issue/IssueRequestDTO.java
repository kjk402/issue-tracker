package com.bas.issuetracker.web.dto.issue;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public class IssueRequestDTO {
    private String title;
    private String comment;
    private List<Integer> milestoneId;
    private List<Integer> labelIds;
    private List<Integer> assignedUserIds;

    public IssueRequestDTO(String title, String comment, List<Integer> milestoneId, List<Integer> labelIds, List<Integer> assignedUserIds) {
        this.title = title;
        this.comment = comment;
        this.milestoneId = milestoneId;
        this.labelIds = labelIds;
        this.assignedUserIds = assignedUserIds;
    }

    public String getTitle() {
        return title;
    }

    public String getComment() {
        return comment;
    }

    public Integer getMilestoneId() {
        if (milestoneId.isEmpty()) {
            return null;
        }
        return milestoneId.get(0);
    }

    public List<Integer> getLabelIds() {
        return labelIds;
    }

    public List<Integer> getAssignedUserIds() {
        return assignedUserIds;
    }
}


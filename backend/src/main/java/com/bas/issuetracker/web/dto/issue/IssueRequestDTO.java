package com.bas.issuetracker.web.dto.issue;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public class IssueRequestDTO {
    private String title;
    private String comment;
    private int milestoneId;
//    private List<Integer> labelIds;

    public IssueRequestDTO(String title, String comment, int milestoneId) {
        this.title = title;
        this.comment = comment;
        this.milestoneId = milestoneId;
    }

    public String getTitle() {
        return title;
    }

    public String getComment() {
        return comment;
    }

    public int getMilestoneId() {
        return milestoneId;
    }

}

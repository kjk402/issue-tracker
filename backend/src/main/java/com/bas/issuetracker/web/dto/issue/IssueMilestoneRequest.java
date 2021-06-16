package com.bas.issuetracker.web.dto.issue;

public class IssueMilestoneRequest {
    private int issueId;
    private int milestoneId;

    public IssueMilestoneRequest(int issueId, int milestoneId) {
        this.issueId = issueId;
        this.milestoneId = milestoneId;
    }

    public int getIssueId() {
        return issueId;
    }

    public int getMilestoneId() {
        return milestoneId;
    }

}

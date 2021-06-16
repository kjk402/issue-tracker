package com.bas.issuetracker.web.dto.milestone;

public class MilestoneInIssue {
    private int milestoneId;
    private String title;
    private boolean isOpen;

    public MilestoneInIssue(int milestoneId, String title, boolean isOpen) {
        this.milestoneId = milestoneId;
        this.title = title;
        this.isOpen = isOpen;
    }

    public int getMilestoneId() {
        return milestoneId;
    }

    public String getTitle() {
        return title;
    }

    public boolean isOpen() {
        return isOpen;
    }
}

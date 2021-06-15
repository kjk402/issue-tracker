package com.bas.issuetracker.web.dto.milestone;

public class MilestoneInIssue {
    private int id;
    private String title;
    private boolean isOpen;

    public MilestoneInIssue(int id, String title, boolean isOpen) {
        this.id = id;
        this.title = title;
        this.isOpen = isOpen;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isOpen() {
        return isOpen;
    }
}

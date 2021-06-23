package com.bas.issuetracker.web.dto.issue;

public class IssueInMilestone {
    private int id;
    private String title;
    private boolean isOpen;

    public IssueInMilestone(int id, String title, boolean isOpen) {
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

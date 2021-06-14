package com.bas.issuetracker.web.dto.issue;

public class IssueTitleRequest {
    private int issueId;
    private String title;

    public IssueTitleRequest(int issueId, String title) {
        this.issueId = issueId;
        this.title = title;
    }

    public int getIssueId() {
        return issueId;
    }

    public String getTitle() {
        return title;
    }

}

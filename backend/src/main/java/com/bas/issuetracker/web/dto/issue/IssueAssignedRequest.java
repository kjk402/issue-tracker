package com.bas.issuetracker.web.dto.issue;

import java.util.List;

public class IssueAssignedRequest {
    private int issueId;
    private List<Integer> userIds;

    public IssueAssignedRequest(int issueId, List<Integer> userIds) {
        this.issueId = issueId;
        this.userIds = userIds;
    }

    public int getIssueId() {
        return issueId;
    }

    public List<Integer> getUserIds() {
        return userIds;
    }

}

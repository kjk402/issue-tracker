package com.bas.issuetracker.web.dto.issue;

import java.util.List;

public class IssueLabelRequest {
    private int issueId;
    private List<Integer> labelIds;

    public IssueLabelRequest(int issueId, List<Integer> labelIds) {
        this.issueId = issueId;
        this.labelIds = labelIds;
    }

    public int getIssueId() {
        return issueId;
    }

    public List<Integer> getLabelIds() {
        return labelIds;
    }

}

package com.bas.issuetracker.web.dto.issue;

import java.util.List;

public class IssueOpenCloseRequest {
    private List<Integer> issueIds;
    private String stateToChange;

    public IssueOpenCloseRequest(List<Integer> issueIds, String stateToChange) {
        this.issueIds = issueIds;
        this.stateToChange = stateToChange;
    }

    public List<Integer> getIssueIds() {
        return issueIds;
    }

    public String getStateToChange() {
        return stateToChange;
    }

}

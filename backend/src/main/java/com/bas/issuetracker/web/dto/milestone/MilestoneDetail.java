package com.bas.issuetracker.web.dto.milestone;

import com.bas.issuetracker.web.dto.issue.IssueInMilestone;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class MilestoneDetail {
    private int milestoneId;
    private String title;
    private String description;
    private LocalDateTime lastModifiedDateTime;
    private LocalDate dueToDate;
    private boolean isOpen;
    private List<IssueInMilestone> openedIssues;
    private List<IssueInMilestone> closedIssues;


}

package com.bas.issuetracker.web.service.milestone;

import com.bas.issuetracker.web.domain.issue.Issue;
import com.bas.issuetracker.web.domain.milestone.Milestone;
import com.bas.issuetracker.web.dto.issue.IssueInMilestone;
import com.bas.issuetracker.web.dto.milestone.MilestoneDetail;
import com.bas.issuetracker.web.dto.milestone.MilestoneMetadata;
import com.bas.issuetracker.web.dto.milestone.MilestonePreview;
import com.bas.issuetracker.web.dto.milestone.MilestonePreviews;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MilestoneDtoConverter {
    public Milestone metadataToMilestone(MilestoneMetadata metadata) {
        return Milestone.builder()
                .title(metadata.getTitle())
                .description(metadata.getDescription())
                .dueToDate(metadata.getDueToDate())
                .build();
    }

    public MilestonePreview milestoneToMilestonePreview(Milestone milestone) {
        return MilestonePreview.builder()
                .milestoneId(milestone.getId())
                .title(milestone.getTitle())
                .description(milestone.getDescription())
                .lastModifiedDateTime(milestone.getLastModifiedDateTime())
                .dueToDate(milestone.getDueToDate())
                .isOpen(milestone.isOpen())
                .build();
    }

    public MilestonePreviews MilestonesToMilestonePreviews(List<Milestone> milestones) {
        List<MilestonePreview> previews = milestones.stream()
                .map(this::milestoneToMilestonePreview)
                .collect(Collectors.toList());
        return new MilestonePreviews(previews);
    }

    public IssueInMilestone issueToIssueInMilestone(Issue issue) {
        return new IssueInMilestone(issue.getId(), issue.getTitle(), issue.isOpen());
    }

    public MilestoneDetail MilestoneToMilestoneDetail(Milestone milestone, List<IssueInMilestone> issues) {
        List<IssueInMilestone> openedIssues = new ArrayList<>();
        List<IssueInMilestone> closedIssues = new ArrayList<>();

        for (IssueInMilestone issue : issues) {
            if (issue.isOpen()) {
                openedIssues.add(issue);
            } else {
                closedIssues.add(issue);
            }
        }

        return MilestoneDetail.builder()
                .milestoneId(milestone.getId())
                .title(milestone.getTitle())
                .description(milestone.getDescription())
                .lastModifiedDateTime(milestone.getLastModifiedDateTime())
                .dueToDate(milestone.getDueToDate())
                .isOpen(milestone.isOpen())
                .openedIssues(openedIssues)
                .closedIssues(closedIssues)
                .build();
    }
}

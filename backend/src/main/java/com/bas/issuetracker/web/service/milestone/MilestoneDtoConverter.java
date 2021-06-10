package com.bas.issuetracker.web.service.milestone;

import com.bas.issuetracker.web.domain.milestone.Milestone;
import com.bas.issuetracker.web.dto.MilestoneMetadata;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MilestoneDtoConverter {
    public Milestone metadataToMilestone(MilestoneMetadata metadata) {
        return Milestone.builder()
                .title(metadata.getTitle())
                .description(metadata.getDescription())
                .dueToDate(metadata.getDueToDate())
                .build();
    }
}

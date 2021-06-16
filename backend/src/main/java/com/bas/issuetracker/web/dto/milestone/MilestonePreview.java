package com.bas.issuetracker.web.dto.milestone;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class MilestonePreview {
    private int milestoneId;
    private String title;
    private String description;
    private LocalDateTime lastModifiedDateTime;
    private LocalDate dueToDate;
    private boolean isOpen;
}

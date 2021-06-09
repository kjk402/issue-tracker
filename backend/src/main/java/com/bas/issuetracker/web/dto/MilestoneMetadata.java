package com.bas.issuetracker.web.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class MilestoneMetadata {
    private String title;
    private String description;
    private LocalDate dueToDate;
}

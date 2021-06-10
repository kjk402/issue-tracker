package com.bas.issuetracker.web.domain.milestone;

import com.bas.issuetracker.web.dto.MilestoneMetadata;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class Milestone {
    private int id;
    private String title;
    private String description;
    private LocalDateTime lastModifiedDateTime;
    private LocalDate dueToDate;
    private boolean isOpen;

    public void updateId(int id) {
        this.id = id;
    }

    public void updateMetadata(MilestoneMetadata metadata) {
        this.title = metadata.getTitle();
        this.description = metadata.getDescription();
        this.dueToDate = metadata.getDueToDate();
    }

    public void touch() {
        this.lastModifiedDateTime = LocalDateTime.now().withNano(0);
    }

    public void open() {
        this.isOpen = true;
    }

    public void close() {
        this.isOpen = false;
    }
}

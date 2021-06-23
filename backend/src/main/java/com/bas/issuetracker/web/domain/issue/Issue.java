package com.bas.issuetracker.web.domain.issue;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Setter
public class Issue {
    private int id;
    private String title;
    private boolean isOpen;
    private int authorId;
    private int milestoneId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime lastModifiedDateTime;

    public Issue(String title, boolean isOpen, int authorId, int milestoneId, LocalDateTime lastModifiedDateTime) {
        this.title = title;
        this.isOpen = isOpen;
        this.authorId = authorId;
        this.milestoneId = milestoneId;
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public Issue(){}

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public int getAuthorId() {
        return authorId;
    }

    public int getMilestoneId() {
        return milestoneId;
    }

    public LocalDateTime getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

}

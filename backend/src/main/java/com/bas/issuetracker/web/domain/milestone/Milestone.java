package com.bas.issuetracker.web.domain.milestone;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime lastModifiedDateTime;
    private LocalDate dueToDate;

    public void updateId(int id) {
        this.id = id;
    }
}

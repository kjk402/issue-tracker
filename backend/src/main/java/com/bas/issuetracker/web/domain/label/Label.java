package com.bas.issuetracker.web.domain.label;

import com.bas.issuetracker.web.dto.label.LabelMetadata;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Label {
    private int id;
    private String title;
    private String description;
    private String color;

    public void updateId(int id) {
        this.id = id;
    }

    public void updateMetadata(LabelMetadata metadata) {
        this.title = metadata.getTitle();
        this.description = metadata.getDescription();
        this.color = metadata.getColor();
    }
}

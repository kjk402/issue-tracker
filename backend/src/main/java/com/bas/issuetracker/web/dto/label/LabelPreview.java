package com.bas.issuetracker.web.dto.label;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LabelPreview {
    private int id;
    private String title;
    private String description;
    private String color;

    public LabelPreview(int id, String title, String description, String color) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.color = color;
    }
}

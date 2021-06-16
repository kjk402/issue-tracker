package com.bas.issuetracker.web.dto.label;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LabelPreview {
    private int labelId;
    private String title;
    private String description;
    private String color;

    public LabelPreview(int labelId, String title, String description, String color) {
        this.labelId = labelId;
        this.title = title;
        this.description = description;
        this.color = color;
    }

}

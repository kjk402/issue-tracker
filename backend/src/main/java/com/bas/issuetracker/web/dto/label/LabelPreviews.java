package com.bas.issuetracker.web.dto.label;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class LabelPreviews {
    private List<LabelPreview> labels;
}

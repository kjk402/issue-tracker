package com.bas.issuetracker.web.service.label;

import com.bas.issuetracker.web.domain.label.Label;
import com.bas.issuetracker.web.dto.label.LabelMetadata;
import com.bas.issuetracker.web.dto.label.LabelPreview;
import com.bas.issuetracker.web.dto.label.LabelPreviews;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LabelDtoConverter {
    public Label labelMetadataToLabel(LabelMetadata metadata) {
        return Label.builder()
                .title(metadata.getTitle())
                .description(metadata.getDescription())
                .color(metadata.getColor())
                .build();
    }

    public LabelPreview labelToLabelPreview(Label label) {
        return LabelPreview.builder()
                .id(label.getId())
                .title(label.getTitle())
                .description(label.getDescription())
                .color(label.getColor())
                .build();
    }

    public LabelPreviews labelsToLabelPreviews(List<Label> labels) {
        List<LabelPreview> previews = labels.stream()
                .map(this::labelToLabelPreview)
                .collect(Collectors.toList());
        return new LabelPreviews(previews);
    }
}

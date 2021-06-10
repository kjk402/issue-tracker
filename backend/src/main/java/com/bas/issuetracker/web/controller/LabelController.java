package com.bas.issuetracker.web.controller;

import com.bas.issuetracker.web.domain.label.Label;
import com.bas.issuetracker.web.dto.label.LabelMetadata;
import com.bas.issuetracker.web.dto.label.LabelPreview;
import com.bas.issuetracker.web.dto.label.LabelPreviews;
import com.bas.issuetracker.web.service.label.LabelDtoConverter;
import com.bas.issuetracker.web.service.label.LabelService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/labels")
public class LabelController {

    private final LabelService labelService;
    private final LabelDtoConverter labelDtoConverter;

    public LabelController(LabelService labelService, LabelDtoConverter labelDtoConverter) {
        this.labelService = labelService;
        this.labelDtoConverter = labelDtoConverter;
    }

    @PostMapping
    public LabelPreview createLabel(@RequestBody @Valid LabelMetadata metadata) {
        Label label = labelService.createLabel(metadata);
        return labelDtoConverter.labelToLabelPreview(label);
    }

    @GetMapping
    public LabelPreviews showLabels() {
        return labelService.showLabels();
    }

    @PutMapping("/{labelId}")
    public void updateLabel(@PathVariable int labelId,
                                @RequestBody @Valid LabelMetadata metadata) {
        labelService.updateLabel(labelId, metadata);
    }

    @DeleteMapping("/{labelId}")
    public void deleteLabel(@PathVariable int labelId) {
        labelService.deleteLabel(labelId);
    }
}

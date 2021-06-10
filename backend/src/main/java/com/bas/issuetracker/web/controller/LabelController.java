package com.bas.issuetracker.web.controller;

import com.bas.issuetracker.web.domain.label.Label;
import com.bas.issuetracker.web.dto.label.LabelMetadata;
import com.bas.issuetracker.web.dto.label.LabelPreview;
import com.bas.issuetracker.web.dto.label.LabelPreviews;
import com.bas.issuetracker.web.service.label.LabelDtoConverter;
import com.bas.issuetracker.web.service.label.LabelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"Label Controller"})
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
    @ApiOperation(value = "레이블 생성", notes = "새로운 레이블을 생성합니다.")
    public LabelPreview createLabel(@RequestBody @Valid LabelMetadata metadata) {
        Label label = labelService.createLabel(metadata);
        return labelDtoConverter.labelToLabelPreview(label);
    }

    @GetMapping
    @ApiOperation(value = "레이블 목록조회", notes = "레이블 목록을 조회합니다.")
    public LabelPreviews showLabels() {
        return labelService.showLabels();
    }

    @PutMapping("/{labelId}")
    @ApiOperation(value = "레이블 수정", notes = "레이블을 수정합니다")
    public void updateLabel(@PathVariable int labelId,
                                @RequestBody @Valid LabelMetadata metadata) {
        labelService.updateLabel(labelId, metadata);
    }

    @DeleteMapping("/{labelId}")
    @ApiOperation(value = "레이블 삭제", notes = "레이블을 삭제합니다")
    public void deleteLabel(@PathVariable int labelId) {
        labelService.deleteLabel(labelId);
    }
}

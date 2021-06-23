package com.bas.issuetracker.web.controller;

import com.bas.issuetracker.web.domain.milestone.Milestone;
import com.bas.issuetracker.web.dto.milestone.*;
import com.bas.issuetracker.web.service.milestone.MilestoneDtoConverter;
import com.bas.issuetracker.web.service.milestone.MilestoneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"Milestone API"}, description = "마일스톤 API")
@RestController
@RequestMapping("/milestones")
public class MilestoneController {

    private final MilestoneService milestoneService;
    private final MilestoneDtoConverter milestoneDtoConverter;

    public MilestoneController(MilestoneService milestoneService, MilestoneDtoConverter milestoneDtoConverter) {
        this.milestoneService = milestoneService;
        this.milestoneDtoConverter = milestoneDtoConverter;
    }

    @PostMapping
    @ApiOperation(value = "마일스톤 생성", notes = "새로운 마일스톤을 생성합니다.")
    public MilestonePreview createMilestone(@RequestBody @Valid MilestoneMetadata metadata) {
        Milestone milestone = milestoneService.createMilestone(metadata);
        return milestoneDtoConverter.milestoneToMilestonePreview(milestone);
    }

    @GetMapping
    @ApiOperation(value = "마일스톤 목록조회", notes = "마일스톤을 목록을 조회합니다.")
    public MilestoneDetails showMilestones() {
        return milestoneService.showMilestones();
    }

    @PatchMapping("/{milestoneId}")
    @ApiOperation(value = "마일스톤 수정", notes = "마일스톤을 수정합니다.")
    public void updateMilestone(@PathVariable int milestoneId,
                                @RequestBody @Valid MilestoneMetadata metadata) {
        milestoneService.updateMilestone(milestoneId, metadata);
    }

    @PatchMapping("/{milestoneId}/state")
    @ApiOperation(value = "마일스톤 상태변경", notes = "마일스톤을 열거나 닫습니다")
    public void updateMilestoneState(@PathVariable int milestoneId,
                                     @RequestBody @Valid MilestoneState state) {
        if (state.isOpen()) {
            milestoneService.openMilestone(milestoneId);
        } else {
            milestoneService.closeMilestone(milestoneId);
        }
    }

    @DeleteMapping("/{milestoneId}")
    @ApiOperation(value = "마일스톤 삭제", notes = "마일스톤을 삭제합니다")
    public void deleteMilestone(@PathVariable int milestoneId) {
        milestoneService.deleteMilestone(milestoneId);
    }
}

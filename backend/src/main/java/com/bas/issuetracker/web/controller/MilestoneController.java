package com.bas.issuetracker.web.controller;

import com.bas.issuetracker.web.domain.milestone.Milestone;
import com.bas.issuetracker.web.dto.MilestoneMetadata;
import com.bas.issuetracker.web.dto.MilestonePreview;
import com.bas.issuetracker.web.dto.MilestonePreviews;
import com.bas.issuetracker.web.dto.MilestoneState;
import com.bas.issuetracker.web.service.milestone.MilestoneDtoConverter;
import com.bas.issuetracker.web.service.milestone.MilestoneService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public MilestonePreview createMilestone(@RequestBody @Valid MilestoneMetadata metadata) {
        Milestone milestone = milestoneService.createMilestone(metadata);
        return milestoneDtoConverter.milestoneToMilestonePreview(milestone);
    }

    @GetMapping
    public MilestonePreviews showMilestones() {
        return milestoneService.showMilestones();
    }

    @PatchMapping("/{milestoneId}")
    public void updateMilestone(@PathVariable int milestoneId,
                                @RequestBody @Valid MilestoneMetadata metadata) {
        milestoneService.updateMilestone(milestoneId, metadata);
    }

    @PatchMapping("/{milestoneId}/state")
    public void updateMilestoneState(@PathVariable int milestoneId,
                                     @RequestBody @Valid MilestoneState state) {
        if (state.isOpen()) {
            milestoneService.openMilestone(milestoneId);
        } else {
            milestoneService.closeMilestone(milestoneId);
        }
    }

    @DeleteMapping("/{milestoneId}")
    public void deleteMilestone(@PathVariable int milestoneId) {
        milestoneService.deleteMilestone(milestoneId);
    }
}

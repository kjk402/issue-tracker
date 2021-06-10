package com.bas.issuetracker.web.controller;

import com.bas.issuetracker.web.domain.milestone.Milestone;
import com.bas.issuetracker.web.dto.MilestoneMetadata;
import com.bas.issuetracker.web.dto.MilestonePreview;
import com.bas.issuetracker.web.service.milestone.MilestoneDtoConverter;
import com.bas.issuetracker.web.service.milestone.MilestoneService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public MilestonePreview createMilestone(@RequestBody MilestoneMetadata metadata) {
        Milestone milestone = milestoneService.createMilestone(metadata);
        return milestoneDtoConverter.milestoneToMilestonePreview(milestone);
    }
}

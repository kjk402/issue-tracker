package com.bas.issuetracker.web.dto.milestone;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MilestonePreviews {
    private List<MilestonePreview> milestones;
}

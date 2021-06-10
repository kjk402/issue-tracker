package com.bas.issuetracker.web.service.milestone;

import com.bas.issuetracker.web.domain.milestone.Milestone;
import com.bas.issuetracker.web.domain.milestone.MilestoneRepository;
import com.bas.issuetracker.web.dto.MilestoneMetadata;
import com.bas.issuetracker.web.dto.MilestonePreviews;
import com.bas.issuetracker.web.exceptions.notfound.MilestoneNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.bas.issuetracker.web.exceptions.notfound.MilestoneNotFoundException.MILESTONE_NOT_FOUND;

@Service
@Transactional(readOnly = true)
public class MilestoneService {

    private final MilestoneRepository milestoneRepository;
    private final MilestoneDtoConverter milestoneDtoConverter;

    public MilestoneService(MilestoneRepository milestoneRepository, MilestoneDtoConverter milestoneDtoConverter) {
        this.milestoneRepository = milestoneRepository;
        this.milestoneDtoConverter = milestoneDtoConverter;
    }

    @Transactional
    public Milestone createMilestone(MilestoneMetadata metadata) {
        Milestone milestone = milestoneDtoConverter.metadataToMilestone(metadata);
        milestone.open();
        milestone.touch();
        return milestoneRepository.create(milestone);
    }

    public Milestone findMilestone(int milestoneId) {
        return milestoneRepository.findById(milestoneId)
                .orElseThrow(() -> new MilestoneNotFoundException(MILESTONE_NOT_FOUND, milestoneId));
    }

    public MilestonePreviews showMilestones() {
        List<Milestone> milestones = milestoneRepository.findAll();
        return milestoneDtoConverter.MilestonesToMilestonePreviews(milestones);
    }

    @Transactional
    public void updateMetadata(int milestoneId, MilestoneMetadata metadata) {
        Milestone milestone = findMilestone(milestoneId);
        milestone.updateMetadata(metadata);
        milestoneRepository.update(milestone);
    }

    @Transactional
    public void openMetadata(int milestoneId) {
        Milestone milestone = findMilestone(milestoneId);
        milestone.open();
        milestoneRepository.update(milestone);
    }

    @Transactional
    public void closeMetadata(int milestoneId) {
        Milestone milestone = findMilestone(milestoneId);
        milestone.close();
        milestoneRepository.update(milestone);
    }
}

package com.bas.issuetracker.web.service.milestone;

import com.bas.issuetracker.web.dao.IssueDAO;
import com.bas.issuetracker.web.domain.milestone.Milestone;
import com.bas.issuetracker.web.domain.milestone.MilestoneRepository;
import com.bas.issuetracker.web.dto.issue.IssueInMilestone;
import com.bas.issuetracker.web.dto.milestone.*;
import com.bas.issuetracker.web.exceptions.IssueException;
import com.bas.issuetracker.web.exceptions.notfound.MilestoneNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.bas.issuetracker.web.exceptions.notfound.MilestoneNotFoundException.MILESTONE_NOT_FOUND;

@Service
@Transactional(readOnly = true)
public class MilestoneService {

    private final MilestoneRepository milestoneRepository;
    private final MilestoneDtoConverter milestoneDtoConverter;
    private final MilestoneDAO milestoneDAO;
    private final IssueDAO issueDAO;

    public MilestoneService(MilestoneRepository milestoneRepository, MilestoneDtoConverter milestoneDtoConverter, MilestoneDAO milestoneDAO, IssueDAO issueDAO) {
        this.milestoneRepository = milestoneRepository;
        this.milestoneDtoConverter = milestoneDtoConverter;
        this.milestoneDAO = milestoneDAO;
        this.issueDAO = issueDAO;
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

    public MilestoneDetails showMilestones() {
        List<Milestone> milestones = milestoneRepository.findAll();
        List<MilestoneDetail> milestoneDetails = new ArrayList<>();
        for (Milestone milestone : milestones) {
            List<IssueInMilestone> issues = issueDAO.findIssuesByMilestoneId(milestone.getId());
            MilestoneDetail milestoneDetail = milestoneDtoConverter.MilestoneToMilestoneDetail(milestone, issues);
            milestoneDetails.add(milestoneDetail);
        }
        return new MilestoneDetails(milestoneDetails);
    }

    @Transactional
    public void updateMilestone(int milestoneId, MilestoneMetadata metadata) {
        Milestone milestone = findMilestone(milestoneId);
        milestone.updateMetadata(metadata);
        milestoneRepository.update(milestone);
    }

    @Transactional
    public void openMilestone(int milestoneId) {
        Milestone milestone = findMilestone(milestoneId);
        milestone.open();
        milestoneRepository.update(milestone);
    }

    @Transactional
    public void closeMilestone(int milestoneId) {
        Milestone milestone = findMilestone(milestoneId);
        milestone.close();
        milestoneRepository.update(milestone);
    }

    @Transactional
    public void deleteMilestone(int milestoneId) {
        findMilestone(milestoneId);
        milestoneRepository.delete(milestoneId);
    }

    public MilestoneInIssue findMilestoneByIssueId(int issueId) {
        return milestoneDAO.findMilestoneByIssueId(issueId);
    }

}

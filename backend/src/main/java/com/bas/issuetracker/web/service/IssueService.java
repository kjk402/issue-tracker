package com.bas.issuetracker.web.service;

import com.bas.issuetracker.web.dao.IssueDAO;
import com.bas.issuetracker.web.dto.comment.CommentDTO;
import com.bas.issuetracker.web.dto.issue.*;
import com.bas.issuetracker.web.dto.label.LabelPreview;
import com.bas.issuetracker.web.dto.milestone.MilestoneInIssue;
import com.bas.issuetracker.web.dto.search.SearchFilter;
import com.bas.issuetracker.web.exceptions.IssueException;
import com.bas.issuetracker.web.service.label.LabelService;
import com.bas.issuetracker.web.service.milestone.MilestoneService;
import com.bas.issuetracker.web.service.users.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IssueService {

    private final IssueDAO issueDAO;
    private final CommentService commentService;
    private final LabelService labelService;
    private final UserService userService;
    private final MilestoneService milestoneService;

    public IssueService(IssueDAO issueDAO, CommentService commentService, LabelService labelService, UserService userService, MilestoneService milestoneService) {
        this.issueDAO = issueDAO;
        this.commentService = commentService;
        this.labelService = labelService;
        this.userService = userService;
        this.milestoneService = milestoneService;
    }

    public IssueDetailDTO showIssueDetail(int issueId) {
        List<CommentDTO> commentDTOS = commentService.showCommentsByIssueId(issueId);
        List<UserDTO> assignedList = userService.findUserByAssignedIssue(issueId);
        List<LabelPreview> labelList = labelService.findLabelsByIssueId(issueId);
        MilestoneInIssue milestone = milestoneService.findMilestoneByIssueId(issueId);
        return new IssueDetailDTO(issueDAO.findIssueById(issueId).orElseThrow(() -> new IssueException("이슈없습니다.")), commentDTOS, assignedList, labelList, milestone);
    }

    public IssueListDTO showIssueList(List<Integer> issueIds) {
        List<IssuePreviewDTO> issuePreviewDTOS = new ArrayList<>();
        for (int issueId : issueIds) {
            issuePreviewDTOS.add(showIssuePreview(issueId));
        }
        return new IssueListDTO(issuePreviewDTOS);
    }

    public IssuePreviewDTO showIssuePreview(int issueId) {
        List<UserDTO> assignedList = userService.findUserByAssignedIssue(issueId);
        List<LabelPreview> labelList = labelService.findLabelsByIssueId(issueId);
        MilestoneInIssue milestone = milestoneService.findMilestoneByIssueId(issueId);
        return new IssuePreviewDTO(issueDAO.findIssueById(issueId).orElseThrow(() -> new IssueException("이슈없습니다.")), assignedList, labelList, milestone);
    }

    public List<Integer> searchIssuesByFilter(SearchFilter filterData, int userId) {
        return issueDAO.findIssuesByFilter(filterData, userId);
    }

    public void createIssue(int userId, IssueRequestDTO issueRequestDTO) {
        issueDAO.saveIssueAndComment(userId, issueRequestDTO);
    }

    public void changeTitleOfIssue(IssueTitleRequest issueTitleRequest) {
        issueDAO.changeTitleOfIssue(issueTitleRequest.getIssueId(), issueTitleRequest.getTitle());
    }

    public void changeStateOfIssue(IssueOpenCloseRequest issueOpenCloseRequest) {
        List<Integer> issueIds = issueOpenCloseRequest.getIssueIds();
        String state = issueOpenCloseRequest.getStateToChange();
        boolean isOpen = !state.equals("close");
        issueDAO.changeStateOfIssue(issueIds, isOpen);
    }

    public void applyLabelToIssue(IssueLabelRequest issueLabelRequest) {
        int issueId = issueLabelRequest.getIssueId();
        List<Integer> labelIds = issueLabelRequest.getLabelIds();
        issueDAO.deleteLabelToIssue(issueId);
        issueDAO.applyLabelsToIssue(issueId, labelIds);
    }

    public void applyAssignedToIssue(IssueAssignedRequest issueAssignedRequest) {
        int issueId = issueAssignedRequest.getIssueId();
        List<Integer> userIds = issueAssignedRequest.getUserIds();
        issueDAO.deleteAssignedToIssue(issueId);
        issueDAO.applyAssignedToIssue(issueId, userIds);
    }

    public void applyMilestoneToIssue(IssueMilestoneRequest issueMilestoneRequest) {
        int issueId = issueMilestoneRequest.getIssueId();
        int milestoneId = issueMilestoneRequest.getMilestoneId();
        issueDAO.updateMilestoneToIssue(issueId, milestoneId);
    }

}

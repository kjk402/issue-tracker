package com.bas.issuetracker.web.service;

import com.bas.issuetracker.web.dao.IssueDAO;
import com.bas.issuetracker.web.dto.comment.CommentDTO;
import com.bas.issuetracker.web.dto.issue.*;
import com.bas.issuetracker.web.dto.search.SearchFilterData;
import com.bas.issuetracker.web.exceptions.IssueException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueService {

    private final IssueDAO issueDAO;
    private final CommentService commentService;

    public IssueService(IssueDAO issueDAO, CommentService commentService) {
        this.issueDAO = issueDAO;
        this.commentService = commentService;
    }

    public IssueDetailDTO showIssueDetail(int issueId) {
        List<CommentDTO> commentDTOS = commentService.showCommentsByIssueId(issueId);
        return new IssueDetailDTO(issueDAO.findIssueById(issueId).orElseThrow(() -> new IssueException("이슈없습니다.")), commentDTOS);
    }

    public List<IssueListDTO> showIssuesByOpenOrClose(String openOrClose) {
        int booleanOpenOrClose = 0;
        if (openOrClose.equals("open")) {
            booleanOpenOrClose = 1;
        }
        return issueDAO.findIssuesByOpenOrClose(booleanOpenOrClose);
    }

    public List<Integer> searchIssuesByFilter(SearchFilterData filterData, int userId) {
        return issueDAO.findIssuesByFilter(filterData, userId);
    }

    public void createIssue(int userId, IssueRequestDTO issueRequestDTO) {
        int issueId = issueDAO.saveIssueAndComment(userId, issueRequestDTO);

    }

    public void changeTitleOfIssue(IssueTitleRequest issueTitleRequest) {
        issueDAO.changeTitleOfIssue(issueTitleRequest.getIssueId(), issueTitleRequest.getTitle());
    }

    public void changeStateOfIssue(IssueOpenCloseRequest issueOpenCloseRequest) {
        List<Integer> issueIds = issueOpenCloseRequest.getIssueIds();
        String state = issueOpenCloseRequest.getStateToChange();
        boolean isOpen = true;
        if (state.equals("close")) {
            isOpen = false;
        }
        issueDAO.changeStateOfIssue(issueIds, isOpen);
    }
}

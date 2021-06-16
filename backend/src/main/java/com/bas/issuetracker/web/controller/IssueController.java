package com.bas.issuetracker.web.controller;


import com.bas.issuetracker.web.config.annotation.CertifiedUser;
import com.bas.issuetracker.web.dto.issue.*;
import com.bas.issuetracker.web.dto.search.SearchFilter;
import com.bas.issuetracker.web.service.IssueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"Issue API"}, description = "이슈 API")
@RestController
@RequestMapping("/issues")
public class IssueController {

    private final IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping
    @ApiOperation(value = "이슈 리스트 보기", notes = "검색필터로 이슈 목록 조회")
    public IssueListDTO showIssueList(
            @ApiParam(value = "검색필터", example = "is:open") @RequestParam String searchFilter,
            @ApiParam(required = false, hidden = true) @CertifiedUser int userId
    ) {
        SearchFilter filter = SearchFilter.parse(searchFilter);
        List<Integer> issueIds = issueService.searchIssuesByFilter(filter, userId);
        return issueService.showIssueList(issueIds);
    }

    @GetMapping("/detail")
    @ApiOperation(value = "이슈 상세 보기", notes = "이슈 아이디로 상세보기")
    public IssueDetailDTO showIssueDetail(@ApiParam("이슈 식별자") @RequestParam int issueId) {
        return issueService.showIssueDetail(issueId);
    }

    @PostMapping
    @ApiOperation(value = "이슈 생성", notes = "새로운 이슈를 생성합니다.")
    public void makeIssue(
            @ApiParam(required = false, hidden = true) @CertifiedUser int userId, @RequestBody IssueRequestDTO issueRequestDTO) {
        issueService.createIssue(userId, issueRequestDTO);
    }

    @PatchMapping("/transfer-title")
    @ApiOperation(value = "이슈 title 변경", notes = "이슈 title 변경")
    public void changeTitleOfIssue(@RequestBody IssueTitleRequest issueTitleRequest) {
        issueService.changeTitleOfIssue(issueTitleRequest);
    }

    @PatchMapping("/transfer-state")
    @ApiOperation(value = "이슈 상태 변경", notes = "open or close")
    public void changeStateOfIssue(@RequestBody IssueOpenCloseRequest issueOpenCloseRequest) {
        issueService.changeStateOfIssue(issueOpenCloseRequest);
    }

    @PostMapping("/assigned")
    @ApiOperation(value = "담당자 적용하기", notes = "이슈에 담당자 적용하기")
    public void applyAssignedToIssue(@RequestBody IssueAssignedRequest issueAssignedRequest) {
        issueService.applyAssignedToIssue(issueAssignedRequest);
    }

    @PostMapping("/label")
    @ApiOperation(value = "라벨 적용하기", notes = "이슈에 라벨 적용하기")
    public void applyLabelToIssue(@RequestBody IssueLabelRequest issueLabelRequest) {
        issueService.applyLabelToIssue(issueLabelRequest);
    }

    @PostMapping("/milestone")
    @ApiOperation(value = "마일스톤 적용하기", notes = "이슈에 마일스톤 적용하기")
    public void applyMilestoneToIssue(@RequestBody IssueMilestoneRequest issueMilestoneRequest) {
        issueService.applyMilestoneToIssue(issueMilestoneRequest);
    }

}

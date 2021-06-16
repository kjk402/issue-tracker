package com.bas.issuetracker.web.service;

import com.bas.issuetracker.web.dto.search.SearchFilter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class IssueServiceTest {

    @Autowired
    private IssueService issueService;

    @Test
    @DisplayName("필터로 검색할 수 있어야 합니다")
    void testFindIssuesByFilter() {
        testFilterSearchByTestcase("is:open", new Integer[]{1, 2, 3, 5, 6, 7});
        testFilterSearchByTestcase("is:open author:@me", new Integer[]{1, 2});
        testFilterSearchByTestcase("is:open assignee:@me", new Integer[]{1, 2, 3, 7});
        testFilterSearchByTestcase("is:open comment:@me", new Integer[]{1, 2, 3});
        testFilterSearchByTestcase("is:open author:@me assignee:@me comment:@me", new Integer[]{1, 2});
    }

    private void testFilterSearchByTestcase(String testcase, Integer[] expected) {
        SearchFilter searchFilter = SearchFilter.parse(testcase);
        List<Integer> issueIds = issueService.searchIssuesByFilter(searchFilter, 1);
        assertThat(issueIds).contains(expected);
    }
}

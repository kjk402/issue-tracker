package com.bas.issuetracker.web.dto.search;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.bas.issuetracker.web.config.constants.SearchFilterConstants.*;
import static org.assertj.core.api.Assertions.assertThat;

class SearchFilterDataTest {

    @Test
    @DisplayName("문자열 검색필터를 객체로 변환할 수 있어야 합니다")
    void testParse() {
        String[] testcases = {
                OPENED_ISSUE,
                CLOSED_ISSUE,
                OPENED_ISSUE + " " + AUTHOR_IS_ME,
                OPENED_ISSUE + " " + ASSIGNEE_IS_ME,
                OPENED_ISSUE + " " + COMMENT_BY_ME,
                CLOSED_ISSUE + " " + AUTHOR_IS_ME,
                CLOSED_ISSUE + " " + ASSIGNEE_IS_ME,
                CLOSED_ISSUE + " " + COMMENT_BY_ME,
                OPENED_ISSUE + " " + AUTHOR_IS_ME + " " + ASSIGNEE_IS_ME + " " + COMMENT_BY_ME,
        };
        SearchFilterData[] expecteds = {
                new SearchFilterData(true, false, false, false),
                new SearchFilterData(false, false, false, false),
                new SearchFilterData(true, true, false, false),
                new SearchFilterData(true, false, true, false),
                new SearchFilterData(true, false, false, true),
                new SearchFilterData(false, true, false, false),
                new SearchFilterData(false, false, true, false),
                new SearchFilterData(false, false, false, true),
                new SearchFilterData(true, true, true, true),
        };
        testParseWithTestcase(testcases, expecteds);
    }

    private void testParseWithTestcase(String[] testcases, SearchFilterData[] expecteds) {
        for (int i = 0; i < testcases.length; i++) {
            String testcase = testcases[i];
            SearchFilterData expected = expecteds[i];
            SearchFilterData searchFilterData = SearchFilterData.parse(testcase);
            assertThat(searchFilterData.equals(expected)).isTrue();
        }
    }
}

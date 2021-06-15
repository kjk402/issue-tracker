package com.bas.issuetracker.web.dto.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

import static com.bas.issuetracker.web.config.constants.SearchFilterConstants.*;

@Getter
@Builder
@AllArgsConstructor
public class SearchFilterData {

    private boolean isOpen = true;
    private boolean authorIsMe = false;
    private boolean assigneeIsMe = false;
    private boolean commentByMe = false;

    public static SearchFilterData parse(String stringFilter) {
        String[] filters = stringFilter.split(" ");
        SearchFilterDataBuilder builder = new SearchFilterDataBuilder();
        for (String filter : filters) {
            switch (filter) {
                case OPENED_ISSUE :
                    builder.isOpen(true);
                    break;
                case CLOSED_ISSUE :
                    builder.isOpen(false);
                    break;
                case AUTHOR_IS_ME :
                    builder.authorIsMe(true);
                    break;
                case ASSIGNEE_IS_ME :
                    builder.assigneeIsMe(true);
                    break;
                case COMMENT_BY_ME :
                    builder.commentByMe(true);
                    break;
            }
        }
        return builder.build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchFilterData that = (SearchFilterData) o;
        return isOpen == that.isOpen && authorIsMe == that.authorIsMe && assigneeIsMe == that.assigneeIsMe && commentByMe == that.commentByMe;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isOpen, authorIsMe, assigneeIsMe, commentByMe);
    }
}

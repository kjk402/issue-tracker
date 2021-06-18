package com.bas.issuetracker.web.dao;

import com.bas.issuetracker.web.dto.issue.IssueInfo;
import com.bas.issuetracker.web.dto.issue.IssueDTO;
import com.bas.issuetracker.web.dto.issue.IssueRequestDTO;
import com.bas.issuetracker.web.dto.issue.UserDTO;
import com.bas.issuetracker.web.dto.search.SearchFilter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.bas.issuetracker.web.queries.IssueQueryKt.*;

@Repository
public class IssueDAO {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final CommentDAO commentDAO;

    public IssueDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate, CommentDAO commentDAO) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.commentDAO = commentDAO;
    }

    public Optional<IssueDTO> findIssueById(int issueId) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("issue_id", issueId);
        List<IssueDTO> issueDTOS = namedParameterJdbcTemplate.query(SELECT_ISSUE_DETAIL, sqlParameterSource, (rs, rowNum) ->
                new IssueDTO(new IssueInfo(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getBoolean("is_open"),
                        rs.getInt("comment_count"),
                        rs.getTimestamp("last_modified_date_time").toLocalDateTime()),
                        new UserDTO(
                                rs.getInt("user_id"),
                                rs.getString("nickname"),
                                rs.getString("name"),
                                rs.getString("profile_image")
                        )));
        return issueDTOS.stream().findFirst();
    }

    public List<Integer> findIssuesByFilter(SearchFilter filter, int searcherId) {
        MapSqlParameterSource parameter = new MapSqlParameterSource()
                .addValue("is_open", filter.isOpen());
        StringBuilder query = new StringBuilder()
                .append(SEARCH_ISSUES_BY_FILTER_BODY);
        appendAssigneeIsMe(query, filter, parameter, searcherId);
        appendCommentByMe(query, filter, parameter, searcherId);
        query.append(FILTER_PART_IS_OPENED);
        appendAuthorIsMe(query, filter, parameter, searcherId);
        query.append(FILTER_PART_END_OF_QUERY);

        return executeFindIssuesByFilterQuery(query.toString(), parameter);
    }

    private List<Integer> executeFindIssuesByFilterQuery(String query, MapSqlParameterSource parameter) {
        return namedParameterJdbcTemplate.query(query, parameter, (rs, rowNum) -> rs.getInt("i.id"));
    }

    private void appendAssigneeIsMe(StringBuilder query, SearchFilter filter, MapSqlParameterSource parameter, int myId) {
        if (filter.isAssigneeIsMe()) {
            query.append(FILTER_PART_ASSIGNED_BY_ME);
            parameter.addValue("assigned_user_id", myId);
        }
    }

    private void appendCommentByMe(StringBuilder query, SearchFilter filter, MapSqlParameterSource parameter, int myId) {
        if (filter.isCommentByMe()) {
            query.append(FILTER_PART_COMMENT_BY_ME);
            parameter.addValue("comment_author_id", myId);
        }
    }

    private void appendAuthorIsMe(StringBuilder query, SearchFilter filter, MapSqlParameterSource parameter, int myId) {
        if (filter.isAuthorIsMe()) {
            query.append(FILTER_PART_ISSUE_AUTHOR);
            parameter.addValue("issue_author_id", myId);
        }
    }

    public int saveIssueAndComment(int userId, IssueRequestDTO issueRequestDTO) {
        int issueId = createIssue(userId, issueRequestDTO);
        commentDAO.createComment(userId, issueId, issueRequestDTO.getComment(), false);
        applyLabelsToIssue(issueId, issueRequestDTO.getLabelIds());
        applyAssignedToIssue(issueId, issueRequestDTO.getAssignedUserIds());
        return issueId;
    }

    public int createIssue(int userId, IssueRequestDTO issueRequestDTO) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("title", issueRequestDTO.getTitle())
                .addValue("author_id", userId)
                .addValue("is_open", true)
                .addValue("milestone_id", issueRequestDTO.getMilestoneId())
                .addValue("last_modified_date_time", LocalDateTime.now());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(CREATE_ISSUE, sqlParameterSource, keyHolder, new String[]{"ID"});
        return keyHolder.getKey().intValue();
    }

    public void changeTitleOfIssue(int issueId, String title) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("title", title)
                .addValue("issue_id", issueId);
        namedParameterJdbcTemplate.update(UPDATE_ISSUE, sqlParameterSource);
    }

    public void changeStateOfIssue(List<Integer> issueIds, boolean state) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("state", state)
                .addValue("issue_ids", issueIds);
        namedParameterJdbcTemplate.update(OPEN_OR_CLOSE_ISSUE, sqlParameterSource);
    }

    public void deleteLabelToIssue(int issueId) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("issue_id", issueId);
        namedParameterJdbcTemplate.update(DISCONNECT_LABEL, sqlParameterSource);
    }

    public void applyLabelsToIssue(int issueId, List<Integer> labelIds) {
        for (int labelId : labelIds) {
            SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                    .addValue("issue_id", issueId)
                    .addValue("label_id", labelId);
            namedParameterJdbcTemplate.update(CONNECT_LABEL, sqlParameterSource);
        }
    }

    public void updateMilestoneToIssue(int issueId, int milestoneId) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("milestone_id", milestoneId)
                .addValue("issue_id", issueId);
        namedParameterJdbcTemplate.update(UPDATE_MILESTONE_ISSUE, sqlParameterSource);
    }

    public void deleteAssignedToIssue(int issueId) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("issue_id", issueId);
        namedParameterJdbcTemplate.update(DISCONNECT_ASSIGNED, sqlParameterSource);
    }

    public void applyAssignedToIssue(int issueId, List<Integer> userIds) {
        for (int userId : userIds) {
            SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                    .addValue("issue_id", issueId)
                    .addValue("user_id", userId);
            namedParameterJdbcTemplate.update(CONNECT_ASSIGNED, sqlParameterSource);
        }
    }

}

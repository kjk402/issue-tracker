package com.bas.issuetracker.web.dao;

import com.bas.issuetracker.web.dto.issue.IssueDTO;
import com.bas.issuetracker.web.dto.issue.IssueListDTO;
import com.bas.issuetracker.web.dto.issue.IssueRequestDTO;
import com.bas.issuetracker.web.dto.issue.UserDTO;
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

    public Optional<IssueListDTO> findIssueById(int issueId) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("issue_id", issueId);
        List<IssueListDTO> issueListDTOS = namedParameterJdbcTemplate.query(SELECT_ISSUE_DETAIL, sqlParameterSource, (rs, rowNum) ->
                new IssueListDTO(new IssueDTO(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("is_open"),
                        rs.getInt("comment_count"),
                        rs.getTimestamp("last_modified_date_time").toLocalDateTime()),
                        new UserDTO(
                                rs.getString("nickname"),
                                rs.getString("name"),
                                rs.getString("profile_image")
                        )));
        return issueListDTOS.stream().findFirst();
    }

    public List<IssueListDTO> findIssuesByOpenOrClose(int openOrClose) {
        List<IssueListDTO> issueListDTOS = new ArrayList<>();
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("open_or_close", openOrClose);
        namedParameterJdbcTemplate.query(SELECT_MULTIPLE_ISSUE, sqlParameterSource, (rs, rowMum) ->
                issueListDTOS.add(new IssueListDTO(
                        new IssueDTO(rs.getInt("id"),
                                rs.getString("title"),
                                rs.getInt("is_open"),
                                rs.getInt("comment_count"),
                                rs.getTimestamp("last_modified_date_time").toLocalDateTime()),
                        new UserDTO(
                                rs.getString("nickname"),
                                rs.getString("name"),
                                rs.getString("profile_image")
                        )

                )));
        return issueListDTOS;
    }

    public int saveIssueAndComment(int userId, IssueRequestDTO issueRequestDTO) {
        int issueId = createIssue(userId, issueRequestDTO);
        commentDAO.createComment(userId, issueId, issueRequestDTO.getComment(), false);
        return issueId;
    }

    public int createIssue(int userId, IssueRequestDTO issueRequestDTO) {
        String sql = "INSERT INTO issue (title, author_id, is_open, last_modified_date_time)" +
                "VALUES (:title, :author_id, :is_open, :last_modified_date_time)";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("title", issueRequestDTO.getTitle())
                .addValue("author_id", userId)
                .addValue("is_open", true)
                .addValue("last_modified_date_time", LocalDateTime.now());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, sqlParameterSource, keyHolder, new String[]{"ID"});
        return keyHolder.getKey().intValue();
    }

    public void changeTitleOfIssue(int issueId, String title) {
        String sql = "UPDATE issue SET title = :title WHERE id = :issue_id";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("title", title)
                .addValue("issue_id", issueId);
        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }

    public void changeStateOfIssue(List<Integer> issueIds, boolean state) {
        String sql = "UPDATE issue SET is_open = :state WHERE id IN (:issue_ids)";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("state", state)
                .addValue("issue_ids", issueIds);
        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }

}

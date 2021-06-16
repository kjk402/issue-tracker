package com.bas.issuetracker.web.dao;

import com.bas.issuetracker.web.dto.comment.CommentDTO;
import com.bas.issuetracker.web.dto.issue.UserDTO;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.bas.issuetracker.web.queries.CommentQueryKt.*;
@Repository
public class CommentDAO {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CommentDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<CommentDTO> showCommentsByIssueId(int issueId) {
        List<CommentDTO> commentDTOS = new ArrayList<>();
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("issue_id", issueId);
        namedParameterJdbcTemplate.query(SELECT_COMMENT_BY_ISSUE_ID, sqlParameterSource, (rs, rowNum) ->
                commentDTOS.add(new CommentDTO(
                        rs.getInt("id"),
                        rs.getString("content"),
                        rs.getTimestamp("last_modified_date_time").toLocalDateTime(),
                        new UserDTO(
                                rs.getInt("user_id"),
                                rs.getString("nickname"),
                                rs.getString("name"),
                                rs.getString("profile_image")
                        )
                )));
        return commentDTOS;
    }

    public void createComment(int userId, int issueId, String content, boolean deletable) {
        String sql = "INSERT INTO comment (content, issue_id, author_id, deletable)" +
                "VALUES (:content, :issue_id, :author_id, :deletable)";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("content", content)
                .addValue("issue_id", issueId)
                .addValue("author_id", userId)
                .addValue("deletable", deletable);
        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }

    public void updateComment(int commentId, String content) {
        String sql = "UPDATE comment SET content =:content WHERE id =:comment_id";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("content", content)
                .addValue("comment_id", commentId);
        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }

    public int findDeletableOfCommentById(int commentId) {
        String sql = "SELECT deletable FROM comment WHERE id =:comment_id";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("comment_id", commentId);
        return namedParameterJdbcTemplate.queryForObject(sql, sqlParameterSource, Integer.class);
    }

    public void deleteComment(int commentId) {
        String sql = "DELETE c FROM comment c WHERE c.id=:comment_id AND c.deletable = 1";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("comment_id", commentId);
        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }

}

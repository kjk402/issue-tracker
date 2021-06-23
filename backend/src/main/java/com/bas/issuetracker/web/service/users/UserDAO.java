package com.bas.issuetracker.web.service.users;

import com.bas.issuetracker.web.domain.user.OAuthAuthenticater;
import com.bas.issuetracker.web.domain.user.User;
import com.bas.issuetracker.web.domain.user.UserRepository;
import com.bas.issuetracker.web.dto.issue.UserDTO;
import com.bas.issuetracker.web.service.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.bas.issuetracker.web.queries.UserStatementKt.*;

@Slf4j
@Service
public class UserDAO implements UserRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final UserMapper userMapper;

    public UserDAO(NamedParameterJdbcTemplate jdbcTemplate, UserMapper userMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userMapper = userMapper;
    }

    @Override
    public User save(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("nickname", user.getNickname())
                .addValue("name", user.getName())
                .addValue("profile_image", user.getProfileImage())
                .addValue("access_token", user.getAccessToken())
                .addValue("oauth_id", user.getOauthId())
                .addValue("email", user.getEmail())
                .addValue("authenticated_by", user.getAuthenticatedBy().name());
        jdbcTemplate.update(com.bas.issuetracker.web.queries.UserStatementKt.SAVE_USER, mapSqlParameterSource, keyHolder);
        user.updateId(Objects.requireNonNull(keyHolder.getKey()).intValue());
        return user;
    }

    @Override
    public Optional<User> findUserByAuthenticatedByAndOauthId(OAuthAuthenticater oAuthAuthenticater, String oauthId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("authenticated_by", oAuthAuthenticater.name())
                .addValue("oauth_id", oauthId);
        try {
            User user = jdbcTemplate.queryForObject(com.bas.issuetracker.web.queries.UserStatementKt.FIND_USER_BY_AUTHENTICATED_BY_AND_OAUTH_ID, mapSqlParameterSource, userMapper);
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void updateAccessToken(int id, String newToken) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("access_token", newToken);
        jdbcTemplate.update(com.bas.issuetracker.web.queries.UserStatementKt.UPDATE_TOKEN, mapSqlParameterSource);
    }

    @Override
    public Optional<User> findUserById(int userId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("id", userId);
        try {
            User user = jdbcTemplate.queryForObject(com.bas.issuetracker.web.queries.UserStatementKt.FIND_USER_BY_ID, mapSqlParameterSource, userMapper);
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<UserDTO> findAllUsers() {
        List<UserDTO> userDTOS = new ArrayList<>();
        jdbcTemplate.query(FIND_ALL_USERS, (rs, rowNum)->
                userDTOS.add(new UserDTO(
                        rs.getInt("user_id"),
                        rs.getString("nickname"),
                        rs.getString("name"),
                        rs.getString("profile_image")
                )));
        return userDTOS;
    }

    public List<UserDTO> findUsersByAssignedIssue(int issueId) {
        List<UserDTO> userDTOS = new ArrayList<>();
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("issue_id", issueId);
        jdbcTemplate.query(FIND_USERS_BY_ASSIGNED_ID, sqlParameterSource, (rs, rowNum)->
                userDTOS.add(new UserDTO(
                        rs.getInt("user_id"),
                        rs.getString("nickname"),
                        rs.getString("name"),
                        rs.getString("profile_image")
                )));
        return userDTOS;
    }

}

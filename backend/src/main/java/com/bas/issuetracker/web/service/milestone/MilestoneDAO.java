package com.bas.issuetracker.web.service.milestone;

import com.bas.issuetracker.web.domain.milestone.Milestone;
import com.bas.issuetracker.web.domain.milestone.MilestoneRepository;
import com.bas.issuetracker.web.service.mapper.MilestoneMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class MilestoneDAO implements MilestoneRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final MilestoneMapper milestoneMapper;

    public MilestoneDAO(NamedParameterJdbcTemplate jdbcTemplate, MilestoneMapper milestoneMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.milestoneMapper = milestoneMapper;
    }

    @Override
    public Milestone create(Milestone milestone) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("title", milestone.getTitle())
                .addValue("description", milestone.getDescription())
                .addValue("last_modified_date_time", milestone.getLastModifiedDateTime())
                .addValue("due_to_date", milestone.getDueToDate())
                .addValue("is_open", milestone.isOpen());
        jdbcTemplate.update(com.bas.issuetracker.web.queries.MilestoneStatementKt.CREATE_MILESTONE, mapSqlParameterSource, keyHolder);
        milestone.updateId(Objects.requireNonNull(keyHolder.getKey()).intValue());
        return milestone;
    }

    @Override
    public Optional<Milestone> findById(int id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        try {
            Milestone milestone = jdbcTemplate.queryForObject(com.bas.issuetracker.web.queries.MilestoneStatementKt.FIND_MILESTONE, mapSqlParameterSource, milestoneMapper);
            return Optional.ofNullable(milestone);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Milestone> findAll() {
        return jdbcTemplate.query(com.bas.issuetracker.web.queries.MilestoneStatementKt.FIND_MILESTONES, new MapSqlParameterSource(), milestoneMapper);
    }

    @Override
    public void update(Milestone milestone) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("id", milestone.getId())
                .addValue("title", milestone.getTitle())
                .addValue("description", milestone.getDescription())
                .addValue("due_to_date", milestone.getDueToDate())
                .addValue("is_open", milestone.isOpen());
        jdbcTemplate.update(com.bas.issuetracker.web.queries.MilestoneStatementKt.UPDATE_MILESTONE, mapSqlParameterSource);
    }

    @Override
    public void delete(int id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        jdbcTemplate.update(com.bas.issuetracker.web.queries.MilestoneStatementKt.DELETE_MILESTONE, mapSqlParameterSource);
    }
}

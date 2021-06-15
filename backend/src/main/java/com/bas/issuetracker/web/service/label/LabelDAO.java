package com.bas.issuetracker.web.service.label;

import com.bas.issuetracker.web.domain.label.Label;
import com.bas.issuetracker.web.domain.label.LabelRepository;
import com.bas.issuetracker.web.service.mapper.LabelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class LabelDAO implements LabelRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final LabelMapper labelMapper;

    public LabelDAO(NamedParameterJdbcTemplate jdbcTemplate, LabelMapper labelMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.labelMapper = labelMapper;
    }

    @Override
    public Label create(Label label) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("title", label.getTitle())
                .addValue("description", label.getDescription())
                .addValue("color", label.getColor());
        jdbcTemplate.update(com.bas.issuetracker.web.queries.LabelStatementKt.CREATE_LABEL, mapSqlParameterSource, keyHolder);
        label.updateId(Objects.requireNonNull(keyHolder.getKey()).intValue());
        return label;
    }

    @Override
    public Optional<Label> findById(int id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        try {
            Label label = jdbcTemplate.queryForObject(com.bas.issuetracker.web.queries.LabelStatementKt.FIND_LABEL, mapSqlParameterSource, labelMapper);
            return Optional.ofNullable(label);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Label> findAll() {
        return jdbcTemplate.query(com.bas.issuetracker.web.queries.LabelStatementKt.FIND_LABELS, new MapSqlParameterSource(), labelMapper);
    }

    @Override
    public void update(Label label) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("id", label.getId())
                .addValue("title", label.getTitle())
                .addValue("description", label.getDescription())
                .addValue("color", label.getColor());
        jdbcTemplate.update(com.bas.issuetracker.web.queries.LabelStatementKt.UPDATE_LABEL, mapSqlParameterSource);
    }

    @Override
    public void delete(int id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        jdbcTemplate.update(com.bas.issuetracker.web.queries.LabelStatementKt.DELETE_LABEL, mapSqlParameterSource);
    }
}

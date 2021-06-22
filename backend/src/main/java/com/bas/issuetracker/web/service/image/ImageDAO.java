package com.bas.issuetracker.web.service.image;

import com.bas.issuetracker.web.domain.image.Image;
import com.bas.issuetracker.web.domain.image.ImageRepository;
import com.bas.issuetracker.web.service.mapper.ImageMapper;
import com.bas.issuetracker.web.service.mapper.LabelMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.bas.issuetracker.web.queries.ImageStatementKt.*;

@Service
@Transactional(readOnly = true)
public class ImageDAO implements ImageRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final ImageMapper imageMapper;

    public ImageDAO(NamedParameterJdbcTemplate jdbcTemplate, ImageMapper imageMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.imageMapper = imageMapper;
    }

    @Override
    public Image create(Image image) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("image_url", image.getImageUrl());
        jdbcTemplate.update(CREATE_IMAGE, mapSqlParameterSource, keyHolder);
        image.updateId(Objects.requireNonNull(keyHolder.getKey()).intValue());
        return image;
    }

    @Override
    public Image updateIssue(Image image, int issueId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("image_url", image.getImageUrl())
                .addValue("issue_id", image.getIssueId());
        jdbcTemplate.update(UPDATE_IMAGE, mapSqlParameterSource);
        return image;
    }

    @Override
    public List<Image> findAllByIssueId(int issueId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("issue_id", issueId);
        return jdbcTemplate.query(FIND_IMAGES_BY_ISSUE_ID, mapSqlParameterSource, imageMapper);
    }

    @Override
    public void updateIssueId(int imageId, int issueId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("image_id", imageId)
                .addValue("issue_id", issueId);
        jdbcTemplate.update(UPDATE_ISSUE_ID, mapSqlParameterSource);
    }
}

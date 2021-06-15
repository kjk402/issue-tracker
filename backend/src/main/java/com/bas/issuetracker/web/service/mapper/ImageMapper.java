package com.bas.issuetracker.web.service.mapper;

import com.bas.issuetracker.web.domain.image.Image;
import com.bas.issuetracker.web.domain.label.Label;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class ImageMapper implements RowMapper<Image> {
    @Override
    public Image mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Image(
                rs.getInt("id"),
                rs.getInt("issue_id"),
                rs.getString("image_url")
        );
    }
}

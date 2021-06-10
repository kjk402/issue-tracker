package com.bas.issuetracker.web.service.mapper;

import com.bas.issuetracker.web.domain.label.Label;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class LabelMapper implements RowMapper<Label> {
    @Override
    public Label mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Label.builder()
                .id(rs.getInt("id"))
                .title(rs.getString("title"))
                .description(rs.getString("description"))
                .color(rs.getString("color"))
                .build();
    }
}

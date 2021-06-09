package com.bas.issuetracker.web.service.mapper;

import com.bas.issuetracker.web.domain.milestone.Milestone;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;

@Service
public class MilestoneMapper implements RowMapper<Milestone> {
    @Override
    public Milestone mapRow(ResultSet rs, int rowNum) throws SQLException {

        return Milestone.builder()
                .id(rs.getInt("id"))
                .title(rs.getString("title"))
                .description(rs.getString("description"))
                .lastModifiedDateTime(rs.getTimestamp("last_modified_date_time").toLocalDateTime())
                .dueToDate(rs.getTimestamp("due_to_date").toLocalDateTime().toLocalDate())
                .build();
    }
}

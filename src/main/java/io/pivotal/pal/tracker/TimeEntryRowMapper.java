package io.pivotal.pal.tracker;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TimeEntryRowMapper implements RowMapper<TimeEntry> {
    @Override
    public TimeEntry mapRow(ResultSet rs, int rowNum) throws SQLException {
        TimeEntry timeEntry = new TimeEntry();
            timeEntry.setId(rs.getLong(1));
            timeEntry.setProjectId(rs.getLong(2));
            timeEntry.setUserId(rs.getLong(3));
            timeEntry.setDate(rs.getDate(4).toLocalDate());
            timeEntry.setHours(rs.getInt(5));
        return timeEntry;
    }
}

package io.pivotal.pal.tracker;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcTimeEntryRepository implements TimeEntryRepository {

    private JdbcTemplate jdbcTemplate;
    public JdbcTimeEntryRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String INSERT_SQL = "INSERT into time_entries(project_id, user_id, date, hours) values(?,?,?,?)";

        jdbcTemplate.update( connection -> {
                PreparedStatement ps = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, timeEntry.getProjectId());
                ps.setLong(2, timeEntry.getUserId());
                ps.setDate(3, Date.valueOf(timeEntry.getDate()));
                ps.setInt(4, timeEntry.getHours());
                return ps;
        }, keyHolder);

        timeEntry.setId(keyHolder.getKey().longValue());
        return timeEntry;
    }

    @Override
    public TimeEntry find(long id) {
        String SELECT_SQL = "SELECT * FROM time_entries WHERE id = ?";
            try {
                TimeEntry timeEntry = jdbcTemplate.queryForObject(SELECT_SQL, new Object[]{id}, new TimeEntryRowMapper());
                return timeEntry;
            } catch (EmptyResultDataAccessException e){
                return null;
            }

    }

    @Override
    public void delete(long id) {
        String DELETE_SQL="DELETE FROM time_entries where id=?";
        jdbcTemplate.update(DELETE_SQL, id);
    }

    @Override
    public List<TimeEntry> list() {
        String SELECT_SQL = "SELECT * FROM time_entries";

        List<TimeEntry> timeEntries = jdbcTemplate.query(SELECT_SQL, new TimeEntryRowMapper());

        return timeEntries;
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {

        String UPDATE_SQL = "UPDATE  time_entries" +
                " SET project_id=?, user_id=?, date=?, hours=? " +
                " WHERE id=?";

        jdbcTemplate.update( connection -> {
            PreparedStatement ps = connection.prepareStatement(UPDATE_SQL);
            ps.setLong(1, timeEntry.getProjectId());
            ps.setLong(2, timeEntry.getUserId());
            ps.setDate(3, Date.valueOf(timeEntry.getDate()));
            ps.setInt(4, timeEntry.getHours());
            ps.setLong(5,id);
            return ps;
        });

        return find(id);
    }
}

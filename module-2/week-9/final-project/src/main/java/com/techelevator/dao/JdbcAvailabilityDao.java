package com.techelevator.dao;

import com.techelevator.model.Availability;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class JdbcAvailabilityDao implements AvailabilityDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcAvailabilityDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public JdbcAvailabilityDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Availability getAvailabilityById(int doctorId) {
        return null;
    }
}

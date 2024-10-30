package com.techelevator.dao;

import com.techelevator.model.DoctorFacility;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class JdbcDoctorFacilityDao implements DoctorFacilityDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcDoctorFacilityDao (DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public JdbcDoctorFacilityDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<DoctorFacility> getDoctorsAndFacilities() {
        return null;
    }
}

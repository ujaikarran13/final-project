package com.techelevator.dao;

import com.techelevator.model.Facility;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class JdbcFacilityDao implements FacilityDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcFacilityDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Facility getFacilityById(int facilityId) {
        return null;
    }

    @Override
    public List<Facility> getFacilities() {
        return null;
    }

    @Override
    public List<Facility> getFacilitiesByAddress() {
        return null;
    }

    @Override
    public List<Facility> getFacilitiesByOfficeHours() {
        return null;
    }

    @Override
    public Facility updateFacility(Facility updatedFacility) {
        return null;
    }
}

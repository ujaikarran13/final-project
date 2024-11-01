package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Availability;
import com.techelevator.model.Facility;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcFacilityDao implements FacilityDao{
private final JdbcTemplate jdbcTemplate;

public JdbcFacilityDao(JdbcTemplate jdbcTemplate){
    this.jdbcTemplate = jdbcTemplate;
}


    @Override
    public Facility getFacilitiesByID(int facilityId) {
        Facility facility = null;


        String sql = "SELECT * FROM facilities WHERE facility_id = ? ";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, facilityId);
            if (results.next()) {
                facility = mapRowToFacility(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataAccessException e){
            throw new DaoException("Database access error", e);
        }

        return facility;
    }


    @Override
    public Facility updateFacility(Facility modifiedFacility) {
        Facility updatedFacility = null;

        String sql = "UPDATE facilities SET facility_name=?, address=?, phone_number=?, cost_per_hour=? WHERE facility_id=?;";

        try {
            int rowsAffected = jdbcTemplate.update(sql, modifiedFacility.getFacilityName(), modifiedFacility.getAddress(), modifiedFacility.getPhoneNumber(),
                    modifiedFacility.getCostPerHour(), modifiedFacility.getFacilityId());
            if (rowsAffected == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            }
            updatedFacility = getFacilitiesByID(modifiedFacility.getFacilityId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation " + e.getMessage(), e);
        }

        return updatedFacility;

    }


    private Facility mapRowToFacility(SqlRowSet results) {

        Facility facility = new Facility();
        facility.setFacilityId(results.getInt("facility_id"));
        facility.setFacilityName(results.getString("facility_name"));
        facility.setAddress(results.getString("address"));
        facility.setPhoneNumber(results.getString("phone_number"));
        facility.setCostPerHour(results.getInt("cost_per_hour"));
        return facility;

    }
}


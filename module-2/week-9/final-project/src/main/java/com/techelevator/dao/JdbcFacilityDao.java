package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Facility;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcOperations;
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
    public List<Facility> getFacilities() {
        List<Facility> facilities = new ArrayList<>();


        String sql = "SELECT * FROM facilities ";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Facility facility = mapRowToFacility(results);
                facilities.add(facility);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return facilities;
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

    @Override
    public Facility addFacilities(Facility facility) {
        String sql = "INSERT INTO facilities (facility_name, address, phone_number, cost_per_hour) VALUES (?, ?, ?, ?)";
        try {
            int rowsAffected = jdbcTemplate.update(sql, facility.getFacilityName(), facility.getAddress(),
                    facility.getPhoneNumber(), facility.getCostPerHour());
            if (rowsAffected > 0) {
                return getFacilitiesByID(facility.getFacilityId());  // Return the newly added facility by ID
            } else {
                throw new DaoException("Failed to insert the facility");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataAccessException e) {
            throw new DaoException("Database access error", e);
        }
    }

    @Override
    public String getPhoneNumberForFacility(int facilityId) {
        String sql = "SELECT phone_number FROM facilities WHERE facility_id = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{facilityId}, String.class);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataAccessException e) {
            throw new DaoException("Database access error", e);
        }
    }

    @Override
    public List<String> getAllNumbers() {
        List<String> phoneNumbers = new ArrayList<>();

        String sql = "SELECT phone_number FROM facilities";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                phoneNumbers.add(results.getString("phone_number"));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataAccessException e) {
            throw new DaoException("Database access error", e);
        }

        return phoneNumbers;
    }

    @Override
    public Facility searchFacilities(String name) {
        Facility facility = null;

        String sql = "SELECT * FROM facilities WHERE facility_name = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, name);
            if (results.next()) {
                facility = mapRowToFacility(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataAccessException e) {
            throw new DaoException("Database access error", e);
        }

        return facility;
    }
    @Override
    public boolean deleteFacilities(int facilityId) {
        String sql = "DELETE FROM facilities WHERE facility_id = ?";

        try {
            int rowsAffected = jdbcTemplate.update(sql, facilityId);
            return rowsAffected > 0;
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataAccessException e) {
            throw new DaoException("Database access error", e);
        }
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



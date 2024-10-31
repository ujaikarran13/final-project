package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Facility;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcFacilityDao implements FacilityDao{

    private final String FACILITY_SELECT = "SELECT f.facility_ID, f.address, f.cost_per_hour, f.office_hours, f.phone_number FROM facility f";
    private final JdbcTemplate jdbcTemplate;

    public JdbcFacilityDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Facility getFacilityById(int facilityId) {
        Facility facility = null;
        String sql = "SELECT * FROM Facilities WHERE facility_ID = ?";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, facilityId);
            if (results.next()) {
                facility = mapRowToFacility(results);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return facility;

    }



    @Override
    public List<Facility> getFacilities() {
        List<Facility> allFacilities = new ArrayList<>();
        String sql = FACILITY_SELECT;

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()){
            Facility facilityResult = mapRowToFacility((results));
            allFacilities.add(facilityResult);
        }
        return allFacilities;
    }

    @Override
    public List<Facility> getFacilitiesByAddress(String address) {
        List<Facility> allFacilities = new ArrayList<>();
        String sql = FACILITY_SELECT + " WHERE f.address ILIKE ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, "%" + address + "%");
        while (results.next()){
            Facility facilityResult = mapRowToFacility(results);
            allFacilities.add(facilityResult);
        }
        return allFacilities;
    }

    @Override
    public List<Facility> getFacilitiesByOfficeHours(String officeHours) {
        List<Facility> allFacilities = new ArrayList<>();
        String sql = FACILITY_SELECT + " WHERE f.office_hours ILIKE ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, "%" + officeHours + "%");
        while (results.next()){
            Facility facilityResult = mapRowToFacility(results);
            allFacilities.add(facilityResult);
        }
        return allFacilities;
    }


    @Override
    public Facility updateFacility(Facility updatedfacility) {
        try {
            String sql = "Update Facilities SET address = ?, phone_number = ?, office_hours = ?, cost_per_hour = ? " +
                    "WHERE facility_ID = ?";
            jdbcTemplate.update(sql, updatedfacility.getAddress(), updatedfacility.getPhoneNumber(), updatedfacility.getOfficeHours(),
                    updatedfacility.getCostPerHour(), updatedfacility.getFacilityId());
            return updatedfacility;
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Failed to update facility due to data integrity violation", e);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Failed to update facility due to connection issues", e);
        } catch (Exception e) {
            throw new DaoException("Failed to update facility", e);
        }
    }
    private Facility mapRowToFacility(SqlRowSet rs) {
        Facility facility = new Facility();
        facility.setFacilityId(rs.getInt("facility_ID"));
        facility.setAddress(rs.getString("address"));
        facility.setCostPerHour(rs.getInt("cost_per_hour"));
        facility.setOfficeHours(rs.getString("office_hours"));
        facility.setPhoneNumber(rs.getString("phone_number"));
        return facility;
    }
}

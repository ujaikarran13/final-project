package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Facility;
import com.techelevator.model.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
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
        Facility facility = null;
        String sql = "SELECT * FROM Facilities WHERE FacilityID = ?";

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
    public Facility updateFacility(Facility facility) {
        try {
            String sql = "Update Facilities SET Address = ?, PhoneNumber = ?, OfficeHours = ?, CostPerHour = ? " +
                    "WHERE FacilityID = ?";
            jdbcTemplate.update(sql, facility.getAddress(), facility.getPhoneNumber(), facility.getOfficeHours(),
                    facility.getCostPerHour(), facility.getFacilityId());
            return facility;
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
        facility.setFacilityId(rs.getInt("FacilityID"));
        facility.setAddress(rs.getString("Address"));
        facility.setCostPerHour(rs.getInt("CostPerHour"));
        facility.setOfficeHours(rs.getString("OfficeHours"));
        facility.setPhoneNumber(rs.getString("PhoneNumber"));
        return facility;
    }
}

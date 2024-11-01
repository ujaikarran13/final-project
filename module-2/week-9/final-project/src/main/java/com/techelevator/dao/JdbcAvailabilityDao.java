package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Availability;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAvailabilityDao implements AvailabilityDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcAvailabilityDao (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Availability> getAvailability() {
        List<Availability> availabilities = new ArrayList<>();


        String sql = "SELECT * FROM availability " +
                "ORDER BY availability_id;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Availability availability = mapRowToAvailability(results);
                availabilities.add(availability);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return availabilities;
    }


    @Override
    public Availability getAvailabilityByID(int availabilityId) {
        Availability availability = null;


        String sql = "SELECT * FROM availability WHERE availability_id = ? ";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, availabilityId);
            if (results.next()) {
                availability = mapRowToAvailability(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataAccessException e){
            throw new DaoException("Database access error", e);
        }

        return availability;
    }
    @Override
    public Availability updateAvailability(Availability modifiedAvailability){
        Availability updatedAvailability = null;

        String sql = "UPDATE availability SET doctor_id=?, day_of_week=?, start_time=?, end_time=? WHERE availability_id=?;";

        try {
            int rowsAffected = jdbcTemplate.update(sql, modifiedAvailability.getDoctorId(), modifiedAvailability.getDayOfWeek(), modifiedAvailability.getStartTime(), modifiedAvailability.getEndTime(), modifiedAvailability.getAvailabilityId());
        if (rowsAffected == 0){
            throw new DaoException("Zero rows affected, expected at least one");
        }
        updatedAvailability = getAvailabilityByID(modifiedAvailability.getAvailabilityId());
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation", e);
        }
        return updatedAvailability;
    }



    private Availability mapRowToAvailability(SqlRowSet results) {

        Availability availability = new Availability();
        availability.setAvailabilityId(results.getInt("availability_id"));
        availability.setDoctorId(results.getInt("doctor_id"));
        availability.setDayOfWeek(results.getString("day_of_week"));
        availability.setStartTime(results.getString("start_time"));
        availability.setEndTime(results.getString("end_time"));
        return availability;

    }
}

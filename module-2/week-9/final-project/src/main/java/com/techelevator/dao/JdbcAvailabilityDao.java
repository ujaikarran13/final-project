package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Availability;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;

public class JdbcAvailabilityDao implements AvailabilityDao {

    private final String AVAILABILITY_SELECT = "SELECT a.availability_id, a.doctor_id, a.day_of_week, a.start_time, a.end_time";

    private final JdbcTemplate jdbcTemplate;

    public JdbcAvailabilityDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public JdbcAvailabilityDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Availability getDoctorAvailabilityById(int doctorId) {

        Availability availability = null;
        String sql = "SELECT * FROM Availability WHERE doctor_id = ?";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, doctorId);
            if (results.next()) {
                availability = mapRowToAvailability(results);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return availability;
    }
    @Override
    public Availability getDoctorAvailabilityByDayOfWeek(String dayOfWeek) {

        Availability availability = null;
        String sql = "SELECT * FROM Availability WHERE day_of_week = ?";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, dayOfWeek);
            if (results.next()) {
                availability = mapRowToAvailability(results);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return availability;
    }
    @Override
    public Availability updateDoctorAvailability(Availability updatedAvailability) {
        try {
            String sql = "Update Availability SET day_of_week = ?, start_time = ?, end_time = ? " +
                    "WHERE availability_id = ? AND doctor_id = ? ";
            jdbcTemplate.update(sql, updatedAvailability.getDayOfWeek(), updatedAvailability.getStartTime(), updatedAvailability.getEndTime(),
                    updatedAvailability.getDoctorId(), updatedAvailability.getAvailabilityId());
            return updatedAvailability;
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Failed to update facility due to data integrity violation", e);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Failed to update facility due to connection issues", e);
        } catch (Exception e) {
            throw new DaoException("Failed to update facility", e);
        }
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

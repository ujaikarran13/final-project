package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Campground;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcCampgroundDao implements CampgroundDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcCampgroundDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Campground getCampgroundById(int campgroundId) {
        Campground campground = null;
        String sql = "SELECT campground_id, park_id, name, open_from_mm, open_to_mm, daily_fee " +
                "FROM campground WHERE campground_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, campgroundId);
            if (results.next()) {
                campground = mapRowToCampground(results);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database");
        }
        return campground;
    }

    @Override
    public List<Campground> getCampgroundsByParkId(int parkId) {
        List<Campground> campgrounds = new ArrayList<>();
        String sql = "SELECT campground_id, park_id, name, open_from_mm, open_to_mm, daily_fee " +
                "FROM campground WHERE park_id = ? ORDER BY name";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, parkId);
            while (results.next()) {
                Campground campground = mapRowToCampground(results);
                campgrounds.add(campground);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database");
        }
        return campgrounds;
    }

    private Campground mapRowToCampground(SqlRowSet results) {
        Campground camp = new Campground();
        camp.setCampgroundId(results.getInt("campground_id"));
        camp.setParkId(results.getInt("park_id"));
        camp.setName(results.getString("name"));
        camp.setOpenFromMonth(results.getInt("open_from_mm"));
        camp.setOpenToMonth(results.getInt("open_to_mm"));
        camp.setDailyFee(results.getBigDecimal("daily_fee"));
        return camp;
    }
}

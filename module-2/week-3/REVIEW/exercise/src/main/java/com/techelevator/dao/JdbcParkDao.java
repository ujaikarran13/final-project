package com.techelevator.dao;

import com.techelevator.model.Campground;
import com.techelevator.model.Park;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcParkDao implements ParkDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcParkDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Park getParkById(int parkId) {

        return null;
    }

    @Override
    public List<Park> getParks() {

        return new ArrayList<>();
    }

    @Override
    public Park createPark(Park park) {

        return null;
    }

    @Override
    public Park updatePark(Park park) {

        return null;
    }

    @Override
    public int deleteParkById(int parkId) {

        return -1;
    }
}

package com.techelevator.dao;

import com.techelevator.model.Park;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

public class JdbcParkDaoTests extends BaseDaoTests {

    private JdbcParkDao jdbcParkDao;

    @Before
    public void setup() {

        jdbcParkDao = new JdbcParkDao(dataSource);
    }


    // Your tests go here !!!!!


    // Convenience method in lieu of a Park constructor with all the fields as parameters.
    // Similar to mapRowToPark() in JdbcParkDao.
    private static Park mapValuesToPark(int parkId, String name, String location, LocalDate establishDate,
        int area, int visitors, String description) {

        Park park = new Park();
        park.setParkId(parkId);
        park.setName(name);
        park.setLocation(location);
        park.setEstablishDate(establishDate);
        park.setArea(area);
        park.setVisitors(visitors);
        park.setDescription(description);
        return park;
    }

    private void assertParksMatch(Park expected, Park actual) {

        Assert.assertEquals(expected.getParkId(), actual.getParkId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getLocation(), actual.getLocation());
        Assert.assertEquals(expected.getEstablishDate(), actual.getEstablishDate());
        Assert.assertEquals(expected.getArea(), actual.getArea());
        Assert.assertEquals(expected.getVisitors(), actual.getVisitors());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
    }
}

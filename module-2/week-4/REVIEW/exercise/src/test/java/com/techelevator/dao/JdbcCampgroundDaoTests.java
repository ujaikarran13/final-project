package com.techelevator.dao;

import com.techelevator.model.Campground;
import org.junit.Assert;
import org.junit.Before;

import java.math.BigDecimal;

public class JdbcCampgroundDaoTests extends BaseDaoTests {

    private JdbcCampgroundDao jdbcCampgroundDao;

    @Before
    public void setup() {

        jdbcCampgroundDao = new JdbcCampgroundDao(dataSource);
    }


    // Your tests go here !!!!!


    // Convenience method in lieu of a Campground constructor with all the fields as parameters.
    // Similar to mapRowToCampground() in JdbcCampgroundDao.
    private static Campground mapValuesToCampground(int campgroundId, int parkId, String name, int openFromMonth,
        int openToMonth, BigDecimal dailyFee) {

        Campground campground = new Campground();
        campground.setCampgroundId(campgroundId);
        campground.setParkId(parkId);
        campground.setName(name);
        campground.setOpenFromMonth(openFromMonth);
        campground.setOpenToMonth(openToMonth);
        campground.setDailyFee(dailyFee);
        return campground;
    }

    private void assertCampgroundsMatch(Campground expected, Campground actual) {

        Assert.assertEquals(expected.getCampgroundId(), actual.getCampgroundId());
        Assert.assertEquals(expected.getParkId(), actual.getParkId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getOpenFromMonth(), actual.getOpenFromMonth());
        Assert.assertEquals(expected.getOpenToMonth(), actual.getOpenToMonth());
        Assert.assertEquals(expected.getDailyFee(), actual.getDailyFee());
    }
}

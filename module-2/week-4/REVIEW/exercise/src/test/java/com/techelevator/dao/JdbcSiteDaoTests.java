package com.techelevator.dao;

import com.techelevator.model.Site;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class JdbcSiteDaoTests extends BaseDaoTests {

    private JdbcSiteDao jdbcSiteDao;

    @Before
    public void setup() {

        jdbcSiteDao = new JdbcSiteDao(dataSource);
    }


    // Your tests go here !!!!!


    // Convenience method in lieu of a Site constructor with all the fields as parameters.
    // Similar to mapRowToSite() in JdbcSiteDao.
    private static Site mapValuesToSite(int siteId, int campgroundId, int siteNumber, int maxOccupancy,
        boolean accessible, int maxRvLength, boolean utilities) {

        Site site = new Site();
        site.setSiteId(siteId);
        site.setCampgroundId(campgroundId);
        site.setSiteNumber(siteNumber);
        site.setMaxOccupancy(maxOccupancy);
        site.setAccessible(accessible);
        site.setMaxRvLength(maxRvLength);
        site.setUtilities(utilities);
        return site;
    }

    private void assertSitesMatch(Site expected, Site actual) {

        Assert.assertEquals(expected.getSiteId(), actual.getSiteId());
        Assert.assertEquals(expected.getCampgroundId(), actual.getCampgroundId());
        Assert.assertEquals(expected.getSiteNumber(), actual.getSiteNumber());
        Assert.assertEquals(expected.getMaxOccupancy(), actual.getMaxOccupancy());
        Assert.assertEquals(expected.isAccessible(), actual.isAccessible());
        Assert.assertEquals(expected.getMaxRvLength(), actual.getMaxRvLength());
        Assert.assertEquals(expected.isUtilities(), actual.isUtilities());
    }
}

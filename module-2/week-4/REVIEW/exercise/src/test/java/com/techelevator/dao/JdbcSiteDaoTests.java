package com.techelevator.dao;

import com.techelevator.model.Campground;
import com.techelevator.model.Site;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class JdbcSiteDaoTests extends BaseDaoTests {

    private JdbcSiteDao jdbcSiteDao;

    @Before
    public void setup() {

        jdbcSiteDao = new JdbcSiteDao(dataSource);
    }

    @Test
    public void getSiteById(){
        Site expectedSite1 = mapValuesToSite(1, 1, 1, 6, false, 0, false);
        Site expectedSite2 = mapValuesToSite(2, 1, 2, 10, false, 0, false);
        Site expectedSite3 = mapValuesToSite(3, 1, 3, 20, true, 0, false);

        Site actualSite1 = jdbcSiteDao.getSiteById(expectedSite1.getSiteId());
        Site actualSite2 = jdbcSiteDao.getSiteById(expectedSite2.getSiteId());
        Site actualSite3 = jdbcSiteDao.getSiteById(expectedSite3.getSiteId());

        assertSitesMatch(expectedSite1, actualSite1);
        assertSitesMatch(expectedSite2, actualSite2);
        assertSitesMatch(expectedSite3, actualSite3);
        Assert.assertNull(jdbcSiteDao.getSiteById(70));

    }

@Test
public void getSitesByCampgroundId(){

        List<Site> sitesCampground5 = new ArrayList<>();
        List<Site> sitesCampground6 = new ArrayList<>();

        Site site1 = mapValuesToSite(45, 5, 1,35, true, 0, false);
        Site site2 = mapValuesToSite(46, 6, 1, 55, true, 0, false);

        sitesCampground5.add(site1);
        sitesCampground6.add(site2);

        List<Site> actualSite1 = jdbcSiteDao.getSitesByCampgroundId(5);
        List<Site> actualSite2 = jdbcSiteDao.getSitesByCampgroundId(6);
        Assert.assertEquals(sitesCampground5.size(), actualSite1.size());
        assertSitesMatch(sitesCampground5.get(0), actualSite1.get(0));
        Assert.assertEquals(sitesCampground6.size(), actualSite2.size());
        assertSitesMatch(sitesCampground6.get(0), actualSite2.get(0));
        Assert.assertEquals(0, jdbcSiteDao.getSitesByCampgroundId(9));

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

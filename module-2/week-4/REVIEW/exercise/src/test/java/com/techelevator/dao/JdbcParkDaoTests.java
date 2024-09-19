package com.techelevator.dao;

import com.techelevator.model.Park;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcParkDaoTests extends BaseDaoTests {

    private JdbcParkDao jdbcParkDao;

    @Before
    public void setup() {

        jdbcParkDao = new JdbcParkDao(dataSource);
    }

@Test
public void getParkById () {
        Park park = mapValuesToPark(1, "Acadia", "Maine", LocalDate.parse("1919-02-26"), 47389, 2563129, "Covering most of Mount Desert Island and other coastal islands, Acadia features the tallest mountain on the Atlantic coast of the United States, granite peaks, ocean shoreline, woodlands, and lakes. There are freshwater, estuary, forest, and intertidal habitats.");
        Park park1 = jdbcParkDao.getParkById(park.getParkId());
        assertParksMatch(park, park1);
}

@Test
public void getParks(){
    List<Park> parks = new ArrayList<>();
    Park park1 = mapValuesToPark(1, "Acadia", "Maine", LocalDate.parse("1919-02-26"), 47389, 2563129, "Covering most of Mount Desert Island and other coastal islands, Acadia features the tallest mountain on the Atlantic coast of the United States, granite peaks, ocean shoreline, woodlands, and lakes. There are freshwater, estuary, forest, and intertidal habitats.");
    Park park2 = mapValuesToPark(2, "Arches", "Utah", LocalDate.parse("1929-04-12"), 76518, 1284767, "This site features more than 2,000 natural sandstone arches, including the famous Delicate Arch. In a desert climate, millions of years of erosion have led to these structures, and the arid ground has life-sustaining soil crust and potholes, which serve as natural water-collecting basins. Other geologic formations are stone columns, spires, fins, and towers.");
    Park park3 = mapValuesToPark(3, "Cuyahoga Valley", "Ohio", LocalDate.parse("2000-10-11"), 32860, 2189849, "This park along the Cuyahoga River has waterfalls, hills, trails, and exhibits on early rural living. The Ohio and Erie Canal Towpath Trail follows the Ohio and Erie Canal, where mules towed canal boats. The park has numerous historic homes, bridges, and structures, and also offers a scenic train ride.");

    parks.add(park1);
    parks.add(park2);
    parks.add(park3);

    List<Park> actualParks = jdbcParkDao.getParks();
    Assert.assertEquals(parks.size(), actualParks.size());
    Assert.assertNotEquals(0, actualParks.size());
    assertParksMatch(parks.get(0), actualParks.get(0));
    assertParksMatch(parks.get(1), actualParks.get(1));
    assertParksMatch(parks.get(2), actualParks.get(2));

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

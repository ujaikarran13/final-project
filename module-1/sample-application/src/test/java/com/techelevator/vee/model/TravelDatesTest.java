package com.techelevator.vee.model;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class TravelDatesTest {

    @Test(expected = IllegalArgumentException.class )
    public void whenDepartsIsNull_Throw() {
        // arrange
        final TravelDates travelDates = new TravelDates();

        // act
        travelDates.setDepartAndReturn(null, null);

    }

    @Test(expected = IllegalArgumentException.class )
    public void whenReturnDateIsNull_Throw() {
        // arrange
        final TravelDates travelDates = new TravelDates();

        // act
        travelDates.setDepartAndReturn(LocalDate.now(), null);

    }

    //Example: unit test expecting an exception to be thrown
    @Test(expected = IllegalArgumentException.class )
    public void whenReturnDateIsBeforeDepartDate_Throw() {
        // arrange
        final TravelDates travelDates = new TravelDates();

        // act
        travelDates.setDepartAndReturn(LocalDate.now().plusDays(1), LocalDate.now());
    }

    @Test
    public void canCalculateDuration() {
        // arrange
        final int duration = 3;
        final LocalDate departs = LocalDate.now();
        final LocalDate returns = departs.plusDays(duration);
        final TravelDates travelDates = new TravelDates();
        travelDates.setDepartAndReturn(departs, returns);

        // act
        final int actualDuration = travelDates.getNumberOfDays();

        // assert
        Assert.assertEquals("duration is wrong", duration, actualDuration);
    }

    @Test
    public void canRenderAsAString() {
        // arrange
        final int duration = 3;
        final LocalDate departs = LocalDate.now();
        final LocalDate returns = departs.plusDays(duration);
        final TravelDates travelDates = new TravelDates();
        travelDates.setDepartAndReturn(departs, returns);

        // act
        final String asString = travelDates.toString();

        // assert
        final String expected = String.format("%s - %s",
                departs.format(TravelDates.MONTH_DAY_YEAR_FORMAT),
                returns.format(TravelDates.MONTH_DAY_YEAR_FORMAT));
        Assert.assertEquals("string representation is wrong", expected, asString);
    }


    @Test
    public void whenSerialized_canDeserialized() {
        // arrange
        final LocalDate departs = LocalDate.now();
        final LocalDate returns = departs.plusDays(1);
        final TravelDates original = new TravelDates();
        original.setDepartAndReturn(departs, returns);

        // act
        final TravelDates copy = new TravelDates();
        copy.initializeFromXml(original.getInnerXml());

        // assert
        Assert.assertEquals("copy is wrong", original.toString(), copy.toString());
    }

}

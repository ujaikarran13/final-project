package com.techelevator.vee.model;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LodgingTest {

    private final TravelDates duration = new TravelDates();

    public LodgingTest() {
        final LocalDate departDate = LocalDate.now().plusDays(1);
        final LocalDate returnDate = departDate.plusDays(2);
        this.duration.setDepartAndReturn(departDate, returnDate);
    }

    @Test
    public void whenNoCostPerNight_TotalIsZero() {
        // arrange
        final Lodging lodging = new Lodging(this.duration);

        // act
        final BigDecimal totalCost = lodging.getTotalCost();

        // assert
        Assert.assertEquals("the final cost should be zero", BigDecimal.ZERO, totalCost);
    }

    @Test
    public void whenNightlyCostIsSet_TotalIsCalculated() {
        // arrange
        final Lodging lodging = new Lodging(this.duration, "unit test lodging", BigDecimal.TEN);

        // act
        final BigDecimal totalCost = lodging.getTotalCost();

        // assert
        final BigDecimal expected = BigDecimal.TEN.multiply(BigDecimal.valueOf(duration.getNumberOfDays()));
        Assert.assertEquals("the final cost should be zero", expected, totalCost);
    }

    @Test
    public void whenRetrievingDetails_allPartsArePresent() {
        // arrange
        final Lodging lodging = new Lodging(this.duration, "unit test lodging", BigDecimal.TEN);

        // act
        final String detailDescription = lodging.getCostDetails();

        // assert
        final String expected = String.format("%d nights at $%s per night.",
                this.duration.getNumberOfDays(),
                lodging.getCostPerNight());
        Assert.assertEquals("detail description is wrong", expected, detailDescription);
    }

    @Test
    public void whenSerialized_canDeserialized() {
        // arrange
        final Lodging lodging = new Lodging(this.duration, "unit test lodging", BigDecimal.TEN);

        // act
        final Lodging copy = new Lodging(this.duration);
        copy.initializeFromXml(lodging.getInnerXml());

        // assert
        Assert.assertEquals("description is wrong", lodging.getDescription(), copy.getDescription());
        Assert.assertEquals("cost/night is wrong", lodging.getCostPerNight(), copy.getCostPerNight());
    }
}

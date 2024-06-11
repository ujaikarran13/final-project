package com.techelevator.vee.model.transportation;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CarTransportationTest {

    @Test
    public void whenUsingDefaults_totalCostIsZero() {
        // arrange
        final CarTransportation car = new CarTransportation();

        // act
        final BigDecimal totalCost = car.getTotalCost();

        // assert
        Assert.assertEquals("total cost is wrong", BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP), totalCost);
    }

    @Test
    public void whenValuesArePresent_calculateTotalCost() {
        // arrange
        final int totalMiles = 13;
        final BigDecimal fuelCost = BigDecimal.valueOf(3.99);
        final CarTransportation car = new CarTransportation(totalMiles, fuelCost);

        // act
        final BigDecimal totalCost = car.getTotalCost();

        // assert
        final BigDecimal expected = fuelCost.multiply(BigDecimal.valueOf(totalMiles / car.mpgEstimate))
                .setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("total cost is wrong", expected, totalCost);
    }

    @Test
    public void whenValuesArePresent_theyArePresentInTheCostDetails() {
        // arrange
        final double totalMiles = 13;
        final BigDecimal fuelCost = BigDecimal.valueOf(3.99);
        final CarTransportation car = new CarTransportation(totalMiles, fuelCost);

        // act
        final String costDetails = car.getCostDetails();

        // assert
        final String expected = String.format("Driving %.1f miles at %.1f miles per gallon. Fuel costs $%s per gallon.",
                totalMiles,
                car.mpgEstimate,
                fuelCost);
        Assert.assertEquals("cost detail is wrong", expected, costDetails);

    }

    @Test
    public void whenSerialized_canDeserialize() {
        // arrange
        final double totalMiles = 13;
        final BigDecimal fuelCost = BigDecimal.valueOf(3.99);
        final CarTransportation original = new CarTransportation(totalMiles, fuelCost);
        original.setDescription("unit-test-description");

        // act
        final CarTransportation copy = new CarTransportation();
        copy.initializeFromXml(original.getInnerXml());

        // assert
        Assert.assertEquals("mpg estimate is wrong", original.getMpgEstimate(), copy.getMpgEstimate(), 0.01);
        Assert.assertEquals("total miles is wrong", original.getMiles(), copy.getMiles(), 0.01);
        Assert.assertEquals("fuel cost is wrong", original.fuelCostPerGallon, copy.fuelCostPerGallon);
    }
}

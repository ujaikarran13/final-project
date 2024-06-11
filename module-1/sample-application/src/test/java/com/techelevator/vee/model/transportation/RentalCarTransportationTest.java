package com.techelevator.vee.model.transportation;

import com.techelevator.vee.model.TravelDates;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class RentalCarTransportationTest {

    @Test
    public void whenCarIsFreeWithUnlimitedMileage_thenCostIsFuel() {
        // arrange
        final int daysInTrip = 2;
        final TravelDates rentalPeriod = createForDuration(daysInTrip);
        final int totalMiles = 100;
        final BigDecimal fuelCostPerGallon = BigDecimal.ZERO;
        final CarTransportation fuel = new CarTransportation(totalMiles, fuelCostPerGallon);
        final BigDecimal carCost = BigDecimal.ZERO;
        final RentalCarTransportation rentalCarTransportation = new RentalCarTransportation(rentalPeriod, carCost, fuel);
        rentalCarTransportation.setUnlimitedMiles(true);

        // act
        final BigDecimal totalCost = rentalCarTransportation.getTotalCost();

        // assert
        Assert.assertEquals("total cost is wrong", fuel.getTotalCost(), totalCost);
    }

    @Test
    public void withUnlimitedMileageAndFreeFuel_costIsDailyCarRate() {
        // arrange
        final int daysInTrip = 2;
        final TravelDates rentalPeriod = createForDuration(daysInTrip);

        final int totalMiles = 100;
        final BigDecimal fuelCostPerGallon = BigDecimal.ZERO;
        final CarTransportation fuel = new CarTransportation(totalMiles, fuelCostPerGallon);

        final BigDecimal carCostPerDay = BigDecimal.TEN;
        final RentalCarTransportation rentalCarTransportation = new RentalCarTransportation(rentalPeriod, carCostPerDay, fuel);
        rentalCarTransportation.setUnlimitedMiles(true);

        // act
        final BigDecimal totalCost = rentalCarTransportation.getTotalCost();

        // assert
        final BigDecimal expectedCost = carCostPerDay
                .multiply(BigDecimal.valueOf(rentalPeriod.getNumberOfDays()))
                .setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("total cost is wrong", expectedCost, totalCost);
    }

    @Test
    public void whenCarAndFuelAreFree_costIsMileageOverAllowance() {
        // arrange
        final int daysInTrip = 2;
        final TravelDates rentalPeriod = createForDuration(daysInTrip);

        final int totalMiles = 200;
        final BigDecimal fuelCostPerGallon = BigDecimal.ZERO;
        final CarTransportation fuel = new CarTransportation(totalMiles, fuelCostPerGallon);

        final BigDecimal carCostPerDay = BigDecimal.ZERO;
        final int dailyMileageAllowance = 75;
        BigDecimal costPerExtraMile = BigDecimal.ONE;
        final RentalCarTransportation rentalCarTransportation = new RentalCarTransportation(rentalPeriod, carCostPerDay, fuel);
        rentalCarTransportation.setUnlimitedMiles(false);
        rentalCarTransportation.setMilesAllowedPerDay(dailyMileageAllowance);
        rentalCarTransportation.setCostPerExtraMile(costPerExtraMile);

        // act
        final BigDecimal totalCost = rentalCarTransportation.getTotalCost();

        // assert
        final BigDecimal expectedCost =
                costPerExtraMile.multiply(BigDecimal.valueOf(totalMiles - (dailyMileageAllowance*daysInTrip)))
                        .setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("total cost is wrong", expectedCost, totalCost);
    }

    @Test
    public void costDetailsIncludeComponents() {
        // arrange
        final int daysInTrip = 2;
        final TravelDates rentalPeriod = createForDuration(daysInTrip);

        final int totalMiles = 200;
        final BigDecimal fuelCostPerGallon = BigDecimal.ZERO;
        final CarTransportation fuel = new CarTransportation(totalMiles, fuelCostPerGallon);

        final BigDecimal carCostPerDay = BigDecimal.valueOf(200);
        final int dailyMileageAllowance = 75;
        BigDecimal costPerExtraMile = BigDecimal.ONE;
        final RentalCarTransportation rentalCarTransportation = new RentalCarTransportation(rentalPeriod, carCostPerDay, fuel);
        rentalCarTransportation.setUnlimitedMiles(false);
        rentalCarTransportation.setMilesAllowedPerDay(dailyMileageAllowance);
        rentalCarTransportation.setCostPerExtraMile(costPerExtraMile);

        // act
        final String details = rentalCarTransportation.getCostDetails();

        // assert
        // and %.1f extra miles at $%s per mile
        Assert.assertTrue("car details are wrong",
                details.contains(String.format("Rental car for %d days at $%s per day",
                        rentalPeriod.getNumberOfDays(),
                        rentalCarTransportation.getCostPerDay())));

        final double mileageOverage = rentalCarTransportation.getMiles()
                - (rentalCarTransportation.getMilesAllowedPerDay() * rentalPeriod.getNumberOfDays());
        Assert.assertTrue("mileage details are wrong",
                details.contains( String.format("and %.1f extra miles at $%s per mile",
                mileageOverage,
                rentalCarTransportation.getCostPerExtraMile())));
    }

    @Test
    public void whenSerialized_canDeserialize() {
        // arrange
        final int daysInTrip = 2;
        final TravelDates rentalPeriod = createForDuration(daysInTrip);

        final int totalMiles = 200;
        final BigDecimal fuelCostPerGallon = BigDecimal.ZERO;
        final CarTransportation fuel = new CarTransportation(totalMiles, fuelCostPerGallon);

        final BigDecimal carCostPerDay = BigDecimal.ZERO;
        final int dailyMileageAllowance = 75;
        BigDecimal costPerExtraMile = BigDecimal.ONE;
        final RentalCarTransportation original = new RentalCarTransportation(rentalPeriod, carCostPerDay, fuel);
        original.setUnlimitedMiles(false);
        original.setMilesAllowedPerDay(dailyMileageAllowance);
        original.setCostPerExtraMile(costPerExtraMile);
        original.setDescription("serialization unit test");

        // act
        final RentalCarTransportation copy = new RentalCarTransportation(rentalPeriod);
        copy.initializeFromXml(original.getInnerXml());

        // assert
        Assert.assertEquals("costPerDay is wrong", original.getCostPerDay(), copy.getCostPerDay());
        Assert.assertEquals("unlimited mileage indicator is wrong", original.getUnlimitedMiles(), copy.getUnlimitedMiles());
        Assert.assertEquals("mileage allowance is wrong", original.getMilesAllowedPerDay(), copy.getMilesAllowedPerDay());
        Assert.assertEquals("mileage overage cost is wrong", original.getCostPerExtraMile(), copy.getCostPerExtraMile());
    }


    private TravelDates createForDuration(int duration) {
        final LocalDate departureDate = LocalDate.now().plusDays(1);
        final LocalDate returnDate = departureDate.plusDays(duration);
        final TravelDates dates = new TravelDates();
        dates.setDepartAndReturn(departureDate, returnDate);
        return dates;
    }
}

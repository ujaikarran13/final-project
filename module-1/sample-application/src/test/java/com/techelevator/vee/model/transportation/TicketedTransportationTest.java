package com.techelevator.vee.model.transportation;

import com.techelevator.vee.model.TravelDates;
import com.techelevator.vee.model.TravelGroup;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class TicketedTransportationTest {

    @Test
    public void whenTravelersArePresent_IncludeInCost() {
        // arrange
        final TravelDates travelDates = createForDuration(1);
        final int adultCount = 1;
        final int childCount = 1;
        final int seniorCount = 1;
        final TravelGroup travelers = new TravelGroup(adultCount, childCount, seniorCount);
        final TicketedTransportation ticketedTransportation = new TicketedTransportation(travelDates, travelers);
        final BigDecimal costPerAdult = BigDecimal.valueOf(100);
        final BigDecimal costPerSenior = BigDecimal.TEN;
        final BigDecimal costPerChild = BigDecimal.ONE;
        ticketedTransportation.setCostPerAdult(costPerAdult);
        ticketedTransportation.setCostPerSenior(costPerSenior);
        ticketedTransportation.setCostPerChild(costPerChild);

        // act
        final BigDecimal totalCost = ticketedTransportation.getTotalCost();

        // assert
        final BigDecimal expectedCost =
                BigDecimal.ZERO
                        .add(costPerAdult.multiply(BigDecimal.valueOf(adultCount)))
                        .add(costPerSenior.multiply(BigDecimal.valueOf(seniorCount)))
                        .add(costPerChild.multiply(BigDecimal.valueOf(childCount)));
        Assert.assertEquals("total cost is wrong", expectedCost, totalCost);
    }

    @Test
    public void whenARentalCarIsPresent_ItIsIncludedInCost() {
        // arrange
        final TravelDates travelDates = createForDuration(1);

        final BigDecimal rentalCarCostPerDay = BigDecimal.valueOf(100);
        final RentalCarTransportation car = new RentalCarTransportation(travelDates);
        car.setCostPerDay(rentalCarCostPerDay);

        final TravelGroup travelers = new TravelGroup(0, 0, 0);
        final TicketedTransportation ticketedTransportation = new TicketedTransportation(travelDates, travelers);
        ticketedTransportation.setRentalCar(car);

        // act
        final BigDecimal totalCost = ticketedTransportation.getTotalCost();

        // assert
        final BigDecimal expectedCost = rentalCarCostPerDay
                .multiply(BigDecimal.valueOf(travelDates.getNumberOfDays()))
                .setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("cost is wrong", expectedCost, totalCost);
    }

    @Test
    public void whenTravelersArePresent_theyAreIncludedInTheDetails() {
        // arrange
        final TravelDates travelDates = createForDuration(1);
        final int adultCount = 2;
        final int childCount = 3;
        final int seniorCount = 1;
        final TravelGroup travelers = new TravelGroup(adultCount, childCount, seniorCount);
        final TicketedTransportation ticketedTransportation = new TicketedTransportation(travelDates, travelers);
        final BigDecimal costPerAdult = BigDecimal.valueOf(100);
        final BigDecimal costPerSenior = BigDecimal.TEN;
        final BigDecimal costPerChild = BigDecimal.ONE;
        ticketedTransportation.setCostPerAdult(costPerAdult);
        ticketedTransportation.setCostPerSenior(costPerSenior);
        ticketedTransportation.setCostPerChild(costPerChild);

        // act
        final String details = ticketedTransportation.getCostDetails();

        // assert
        Assert.assertTrue("adult details are wrong",
                details.contains(String.format("%d at the adult ticket price of $%s", adultCount, costPerAdult)));

        Assert.assertTrue("child details are wrong",
                details.contains(String.format("%d at the child ticket price of $%s", childCount, costPerChild)));

        Assert.assertTrue("senior details are wrong",
                details.contains(String.format("%d at the senior ticket price of $%s", seniorCount, costPerSenior)));

    }

    @Test
    public void whenARentalCarIsPresent_ItIsIncludedInTheDetails() {
        // arrange
        final TravelDates travelDates = createForDuration(1);

        final BigDecimal rentalCarCostPerDay = BigDecimal.valueOf(100);
        final RentalCarTransportation car = new RentalCarTransportation(travelDates);
        car.setCostPerDay(rentalCarCostPerDay);

        final TravelGroup travelers = new TravelGroup(0, 0, 0);
        final TicketedTransportation ticketedTransportation = new TicketedTransportation(travelDates, travelers);
        ticketedTransportation.setRentalCar(car);

        // act
        final String details = ticketedTransportation.getCostDetails();

        // assert
        Assert.assertTrue("rental car details are wrong", details.contains(car.getCostDetails()));
    }

    @Test
    public void whenSerialized_canDeserialize() {
        // arrange
        final TravelDates travelDates = createForDuration(1);
        final int adultCount = 2;
        final int childCount = 3;
        final int seniorCount = 1;
        final TravelGroup travelers = new TravelGroup(adultCount, childCount, seniorCount);
        final TicketedTransportation original = new TicketedTransportation(travelDates, travelers);
        original.setDescription("serialization-unit-test");
        final BigDecimal costPerAdult = BigDecimal.valueOf(100);
        final BigDecimal costPerSenior = BigDecimal.TEN;
        final BigDecimal costPerChild = BigDecimal.ONE;
        original.setCostPerAdult(costPerAdult);
        original.setCostPerSenior(costPerSenior);
        original.setCostPerChild(costPerChild);

        // act
        final TicketedTransportation copy = new TicketedTransportation(travelDates, travelers);
        copy.initializeFromXml(original.getInnerXml());

        // assert
        Assert.assertEquals("adult cost is wrong", original.getCostPerAdult(), copy.getCostPerAdult());
        Assert.assertEquals("child cost is wrong", original.getCostPerChild(), copy.getCostPerChild());
        Assert.assertEquals("senior cost is wrong", original.getCostPerSenior(), copy.getCostPerSenior());

    }

    private TravelDates createForDuration(int duration) {
        final LocalDate departureDate = LocalDate.now().plusDays(1);
        final LocalDate returnDate = departureDate.plusDays(duration);
        final TravelDates dates = new TravelDates();
        dates.setDepartAndReturn(departureDate, returnDate);
        return dates;
    }
}

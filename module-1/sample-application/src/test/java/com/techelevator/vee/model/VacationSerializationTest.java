package com.techelevator.vee.model;

import com.techelevator.vee.model.transportation.CarTransportation;
import com.techelevator.vee.model.transportation.RentalCarTransportation;
import com.techelevator.vee.model.transportation.TicketedTransportation;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class VacationSerializationTest {

    @Test
    public void canSerializeWithACar() {
        // arrange
        final Vacation vacation = new Vacation();
        vacation.getDates().setDepartAndReturn(LocalDate.now(), LocalDate.now().plusDays(2));
        final CarTransportation car = new CarTransportation(100, BigDecimal.valueOf(2.99));
        car.setDescription("car");
        vacation.setTransportation(car);

        // act
        final Vacation copy = new Vacation();
        copy.initializeFromXml(vacation.getInnerXml());

        // assert
        Assert.assertEquals("wrong transportation type", CarTransportation.class, copy.getTransportation().getClass());
        Assert.assertEquals("rental car did not restore correctly", car.getInnerXml(), copy.getTransportation().getInnerXml());
    }

    @Test
    public void canSerializeWithARentalCar() {
        // arrange
        final Vacation vacation = new Vacation();
        vacation.getDates().setDepartAndReturn(LocalDate.now(), LocalDate.now().plusDays(2));
        final CarTransportation car = new CarTransportation(100, BigDecimal.valueOf(2.99));
        final RentalCarTransportation rentalCar = new RentalCarTransportation(vacation.getDates(), BigDecimal.valueOf(100), car);
        rentalCar.setDescription("rental car");
        vacation.setTransportation(rentalCar);

        // act
        final Vacation copy = new Vacation();
        copy.initializeFromXml(vacation.getInnerXml());

        // assert
        Assert.assertEquals("wrong transportation type", RentalCarTransportation.class, copy.getTransportation().getClass());
        Assert.assertEquals("rental car did not restore correctly", rentalCar.getInnerXml(), copy.getTransportation().getInnerXml());
    }

    @Test
    public void canSerializeWithTicketedTrans() {
        // arrange
        final Vacation vacation = new Vacation();
        vacation.getDates().setDepartAndReturn(LocalDate.now(), LocalDate.now().plusDays(2));
        vacation.getTravelers().setAdultCount(1);
        vacation.getTravelers().setChildCount(0);
        vacation.getTravelers().setSeniorCount(0);
        final TicketedTransportation ticketedTransportation =
                new TicketedTransportation(vacation.getDates(), vacation.getTravelers(), BigDecimal.TEN);
        ticketedTransportation.setDescription("ticketed transportation");
        vacation.setTransportation(ticketedTransportation);

        // act
        final Vacation copy = new Vacation();
        copy.initializeFromXml(vacation.getInnerXml());

        // assert
        Assert.assertEquals("wrong transportation type", TicketedTransportation.class, copy.getTransportation().getClass());
        Assert.assertEquals("transportation did not restore correctly", ticketedTransportation.getInnerXml(), copy.getTransportation().getInnerXml());
    }

}

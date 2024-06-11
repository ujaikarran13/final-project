package com.techelevator.vee.model.transportation;

import com.techelevator.vee.model.ChildAndSeniorPriceable;
import com.techelevator.vee.model.TravelDates;
import com.techelevator.vee.model.TravelGroup;
import com.techelevator.util.xml.BasicXmlBuilder;
import com.techelevator.util.xml.BasicXmlParser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * TicketedTransportation is a class that encapsulates data and calculations related to the cost
 * of traveling on a plane, train, bus, etc. based on a cost per passenger, and optionally includes
 * a rental car (to be used at the destination).
 *
 * This class implicitly implements the BasicXml and Expense interfaces (because it extends
 * Transportation which explicitly implements those interfaces).
 *
 * Creating an instance of this class requires a TravelGroup object that represents the passengers.
 * That object is not part of the TicketedTransportation XML, based on the assumption that it will
 * be serialized and deserialized by its supplier (the Vacation object). Similarly, it requires a
 * TravelDates object, to be used for the rental car (see RentalCarTransportation).
 */

public class TicketedTransportation extends Transportation implements ChildAndSeniorPriceable {

    private final TravelDates vacationDates;
    private final TravelGroup passengers;
    private BigDecimal costPerAdult = BigDecimal.ZERO;
    private BigDecimal costPerChild = BigDecimal.ZERO;
    private BigDecimal costPerSenior = BigDecimal.ZERO;

    private RentalCarTransportation rentalCar;

    public TicketedTransportation(TravelDates vacationDates, TravelGroup passengers) {
        this.vacationDates = vacationDates;
        this.passengers = passengers;
    }

    public TicketedTransportation(TravelDates vacationDates, TravelGroup passengers, BigDecimal singlePrice) {
        this(vacationDates, passengers);
        costPerAdult = singlePrice;
        costPerChild = singlePrice;
        costPerSenior = singlePrice;
    }

    @Override
    public String toString() {
        String result = super.toString();
        if (rentalCar != null) {
            result += String.format(" (including rental %s)", rentalCar.getDescription());
        }
        return result;
    }

    //region-------------------------Getters and Setters-------------------------------


    public TravelDates getVacationDates() {
        return vacationDates;
    }

    public TravelGroup getPassengers() {
        return passengers;
    }

    public BigDecimal getCostPerAdult() {
        return costPerAdult;
    }

    public void setCostPerAdult(BigDecimal costPerAdult) {
        this.costPerAdult = costPerAdult;
    }

    @Override
    public BigDecimal getCostPerChild() {
        return costPerChild;
    }

    @Override
    public void setCostPerChild(BigDecimal costPerChild) {
        this.costPerChild = costPerChild;
    }

    @Override
    public BigDecimal getCostPerSenior() {
        return costPerSenior;
    }

    @Override
    public void setCostPerSenior(BigDecimal costPerSenior) {
        this.costPerSenior = costPerSenior;
    }

    public RentalCarTransportation getRentalCar() {
        return rentalCar;
    }

    public void setRentalCar(RentalCarTransportation rentalCar) {
        this.rentalCar = rentalCar;
    }
    //endregion

    //region-------------------------Expense methods-------------------------------
    @Override
    public BigDecimal getTotalCost() {
        BigDecimal total = costPerAdult.multiply(BigDecimal.valueOf(passengers.getAdultCount()));
        total = total.add(costPerChild.multiply(BigDecimal.valueOf(passengers.getChildCount())));
        total = total.add(costPerSenior.multiply(BigDecimal.valueOf(passengers.getSeniorCount())));

        if (rentalCar != null) {
            total = total.add(rentalCar.getTotalCost());
        }
        return total;
    }

    @Override
    public String getCostDetails() {
        List<String> segments = new ArrayList<>();
        if (passengers.getAdultCount() > 0) {
            segments.add(String.format("%d at the adult ticket price of $%s", passengers.getAdultCount(), costPerAdult));
        }
        if (passengers.getChildCount() > 0) {
            segments.add(String.format("%d at the child ticket price of $%s", passengers.getChildCount(), costPerChild));
        }
        if (passengers.getSeniorCount() > 0) {
            segments.add(String.format("%d at the senior ticket price of $%s", passengers.getSeniorCount(), costPerSenior));
        }
        String result = String.join(" + ", segments) + ".";
        if (rentalCar != null) {
            result = String.format("%s\n%s", result, rentalCar.getCostDetails());
        }
        return result;
    }
    //endregion

    //region-------------------------BasicXml methods-------------------------------
    @Override
    public void initializeFromXml(String xml) {
        super.initializeFromXml(xml);

        BasicXmlParser parser = new BasicXmlParser(xml);
        costPerAdult = parser.getBigDecimalContent("adultTicketCost");
        costPerChild = parser.getBigDecimalContent("childTicketCost");
        costPerSenior = parser.getBigDecimalContent("seniorTicketCost");
        if (parser.hasElement("rentalCar")) {
            rentalCar = new RentalCarTransportation(vacationDates);
            rentalCar.initializeFromXml(parser.getStringContent("rentalCar"));
        }
    }

    @Override
    public String getInnerXml() {
        BasicXmlBuilder builder = new BasicXmlBuilder();
        builder.addElement("adultTicketCost", costPerAdult);
        builder.addElement("childTicketCost", costPerChild);
        builder.addElement("seniorTicketCost", costPerSenior);
        if (rentalCar != null) {
            builder.addElement("rentalCar", rentalCar);
        }
        return super.getInnerXml() + builder.build();
    }
    //endregion
}

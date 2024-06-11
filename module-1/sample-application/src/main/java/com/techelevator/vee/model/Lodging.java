package com.techelevator.vee.model;

import com.techelevator.util.xml.BasicXml;
import com.techelevator.util.xml.BasicXmlBuilder;
import com.techelevator.util.xml.BasicXmlParser;

import java.math.BigDecimal;

/**
 * Lodging is a class that encapsulates data and calculations related to the cost
 * of vacation accommodations, based on a cost per night.
 *
 * Creating an instance of this class requires a TravelDates object that represents the
 * reservation period. That object is not part of the Lodging XML, based on the assumption
 * that it will be serialized and deserialized by its supplier (the Vacation object).
 */
 
public class Lodging implements BasicXml, Expense {

    private final TravelDates reservationPeriod;
    private String description;
    private BigDecimal costPerNight = BigDecimal.ZERO;

    public Lodging(TravelDates reservationPeriod) {
        this.reservationPeriod = reservationPeriod;
    }

    public Lodging(TravelDates reservationPeriod, String description, BigDecimal costPerNight) {
        this.reservationPeriod = reservationPeriod;
        this.description = description;
        this.costPerNight = costPerNight;
    }

    @Override
    public String toString() {
        return "Lodging: " + description;
    }

    //region-------------------------Getters and Setters-------------------------------

    public TravelDates getReservationPeriod() {
        return reservationPeriod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCostPerNight() {
        return costPerNight;
    }

    public void setCostPerNight(BigDecimal costPerNight) {
        this.costPerNight = costPerNight;
    }
    //endregion

    //region-------------------------Expense methods-------------------------------
    @Override
    public BigDecimal getTotalCost() {
        return costPerNight.multiply(BigDecimal.valueOf(reservationPeriod.getNumberOfDays()));
    }

    @Override
    public String getCostDetails() {
        return String.format("%d nights at $%s per night.", reservationPeriod.getNumberOfDays(), costPerNight);
    }
    //endregion

    //region-------------------------BasicXml methods-------------------------------
    @Override
    public void initializeFromXml(String xml) {
        BasicXmlParser parser = new BasicXmlParser(xml);
        description = parser.getStringContent("description");
        costPerNight = parser.getBigDecimalContent("costPerNight");
    }

    @Override
    public String getInnerXml() {
        BasicXmlBuilder builder = new BasicXmlBuilder();
        builder.addElement("description", description);
        builder.addElement("costPerNight", costPerNight);
        return builder.build();
    }
    //endregion

}

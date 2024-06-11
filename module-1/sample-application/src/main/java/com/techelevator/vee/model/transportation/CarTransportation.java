package com.techelevator.vee.model.transportation;

import com.techelevator.util.xml.BasicXmlBuilder;
import com.techelevator.util.xml.BasicXmlParser;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * CarTransportation is a class that encapsulates data and calculations related to the fuel
 * cost of traveling by car.
 *
 * This class implicitly implements the BasicXml and Expense interfaces (because it extends
 * Transportation which explicitly implements those interfaces).
 */

public class CarTransportation extends Transportation {

    //Example: declaring a constant
    public static final double AVERAGE_MPG = 25.4; //EPA average for 2020

    protected double miles;
    protected BigDecimal fuelCostPerGallon = BigDecimal.ZERO;
    protected double mpgEstimate = AVERAGE_MPG;

    public CarTransportation() {}

    public CarTransportation(double miles, BigDecimal fuelCostPerGallon) {
        this.miles = miles;
        this.fuelCostPerGallon = fuelCostPerGallon;
    }

    //region-------------------------Getters and Setters-------------------------------
    public double getMpgEstimate() {
        return mpgEstimate;
    }

    public void setMpgEstimate(double mpgEstimate) {
        this.mpgEstimate = mpgEstimate;
    }

    public double getMiles() {
        return miles;
    }

    public void setMiles(double miles) {
        this.miles = miles;
    }

    public BigDecimal getFuelCostPerGallon() {
        return fuelCostPerGallon;
    }

    public void setFuelCostPerGallon(BigDecimal fuelCostPerGallon) {
        this.fuelCostPerGallon = fuelCostPerGallon;
    }
    //endregion

    //region-------------------------Expense methods-------------------------------
    @Override
    public BigDecimal getTotalCost() {
        BigDecimal gallonsOfFuel = BigDecimal.valueOf(miles / mpgEstimate);
        return fuelCostPerGallon.multiply(gallonsOfFuel).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String getCostDetails() {
        return String.format("Driving %.1f miles at %.1f miles per gallon. Fuel costs $%s per gallon.",
                miles, mpgEstimate, fuelCostPerGallon);
    }
    //endregion

    //region-------------------------BasicXml methods-------------------------------
    @Override
    public void initializeFromXml(String xml) {
        super.initializeFromXml(xml);

        BasicXmlParser parser = new BasicXmlParser(xml);
        mpgEstimate = parser.getDoubleContent("mpgEstimate");
        miles = parser.getDoubleContent("miles");
        fuelCostPerGallon = parser.getBigDecimalContent("fuelCostPerGallon");
    }

    @Override
    public String getInnerXml() {
        BasicXmlBuilder builder = new BasicXmlBuilder();
        builder.addElement("mpgEstimate", mpgEstimate);
        builder.addElement("miles", miles);
        builder.addElement("fuelCostPerGallon", fuelCostPerGallon);
        return super.getInnerXml() + builder.build();
    }
    //endregion

}

package com.techelevator.vee.model.transportation;

import com.techelevator.util.xml.BasicXmlBuilder;
import com.techelevator.util.xml.BasicXmlParser;
import com.techelevator.vee.model.TravelDates;

import java.math.BigDecimal;

/**
 * RentalCarTransportation is a class that encapsulates data and calculations related to the cost
 * of renting a car, based on a cost per day plus an optional mileage fee.
 *
 * This class implicitly implements the BasicXml and Expense interfaces (because it extends
 * CarTransportation which also implicitly implements those interfaces).
 *
 * Creating an instance of this class requires a TravelDates object that represents the
 * rental period. That object is not part of the RentalCarTransportation XML, based on the
 * assumption that it will be serialized and deserialized by its supplier (the Vacation object).
 */

public class RentalCarTransportation extends CarTransportation {

    private final TravelDates rentalPeriod;
    private BigDecimal costPerDay;
    private boolean unlimitedMiles = true;
    private int milesAllowedPerDay;
    private BigDecimal costPerExtraMile = BigDecimal.ZERO;

    public RentalCarTransportation(TravelDates rentalPeriod) {
        this.rentalPeriod = rentalPeriod;
    }

    public RentalCarTransportation(TravelDates rentalPeriod, BigDecimal costPerDay, CarTransportation car) {
        this.rentalPeriod = rentalPeriod;
        this.miles = car.miles;
        this.fuelCostPerGallon = car.fuelCostPerGallon;
        this.mpgEstimate = car.mpgEstimate;
        this.costPerDay = costPerDay;
    }

    //region-------------------------Getters and Setters-------------------------------


    public TravelDates getRentalPeriod() {
        return rentalPeriod;
    }

    public boolean isUnlimitedMiles() {
        return unlimitedMiles;
    }

    public BigDecimal getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(BigDecimal costPerDay) {
        this.costPerDay = costPerDay;
    }

    public boolean getUnlimitedMiles() {
        return unlimitedMiles;
    }

    public void setUnlimitedMiles(boolean unlimitedMiles) {
        this.unlimitedMiles = unlimitedMiles;
    }

    public int getMilesAllowedPerDay() {
        return milesAllowedPerDay;
    }

    public void setMilesAllowedPerDay(int milesAllowedPerDay) {
        this.milesAllowedPerDay = milesAllowedPerDay;
    }

    public BigDecimal getCostPerExtraMile() {
        return costPerExtraMile;
    }

    public void setCostPerExtraMile(BigDecimal costPerExtraMile) {
        this.costPerExtraMile = costPerExtraMile;
    }

    //endregion

    //region-------------------------Expense methods-------------------------------

    private double getExtraMiles() {
        return miles - (milesAllowedPerDay * rentalPeriod.getNumberOfDays());
    }

    @Override
    public BigDecimal getTotalCost() {
        //Example: calling a method being overridden
        BigDecimal fuelCost = super.getTotalCost();
        BigDecimal rentalCost = costPerDay.multiply(BigDecimal.valueOf(rentalPeriod.getNumberOfDays()));
        if (!unlimitedMiles) {
            double extraMiles = getExtraMiles();
            if (extraMiles > 0) {
                BigDecimal mileageCost = costPerExtraMile.multiply(BigDecimal.valueOf(extraMiles));
                rentalCost = rentalCost.add(mileageCost);
            }
        }
        return fuelCost.add(rentalCost);
    }

    @Override
    public String getCostDetails() {
        String details = String.format("Rental car for %d days at $%s per day",
                                        rentalPeriod.getNumberOfDays(), costPerDay);
        double extraMiles = getExtraMiles();
        //Example: complex boolean expression
        if (!unlimitedMiles && extraMiles > 0) {
            details += String.format(" and %.1f extra miles at $%s per mile", extraMiles, costPerExtraMile);
        }
        details += ".\n";
        return details + super.getCostDetails();
    }
    //endregion

    //region-------------------------BasicXml methods-------------------------------
    @Override
    public void initializeFromXml(String xml) {
        super.initializeFromXml(xml);

        BasicXmlParser parser = new BasicXmlParser(xml);
        costPerDay = parser.getBigDecimalContent("costPerDay");
        unlimitedMiles = parser.getBooleanContent("unlimitedMiles");
        if (!unlimitedMiles) {
            milesAllowedPerDay = parser.getIntegerContent("milesAllowedPerDay");
            costPerExtraMile = parser.getBigDecimalContent("costPerExtraMile");
        }
    }

    @Override
    public String getInnerXml() {
        BasicXmlBuilder builder = new BasicXmlBuilder();
        builder.addElement("costPerDay", costPerDay);
        builder.addElement("unlimitedMiles", unlimitedMiles);
        if (!unlimitedMiles) {
            builder.addElement("milesAllowedPerDay", milesAllowedPerDay);
            builder.addElement("costPerExtraMile", costPerExtraMile);
        }
        return super.getInnerXml() + builder.build();
    }
    //endregion

}

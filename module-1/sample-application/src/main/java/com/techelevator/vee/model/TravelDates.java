package com.techelevator.vee.model;

import com.techelevator.util.xml.BasicXml;
import com.techelevator.util.xml.BasicXmlBuilder;
import com.techelevator.util.xml.BasicXmlParser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * TravelDates is a class that encapsulates departure and return dates and the logic
 * necessary to ensure the return is after the departure and calculate the duration
 * of the trip.
 */

public class TravelDates implements BasicXml {
    public static final DateTimeFormatter MONTH_DAY_YEAR_FORMAT = DateTimeFormatter.ofPattern("M/d/yyyy");
    private LocalDate departOn;
    private LocalDate returnOn;

    @Override
    public String toString() {
        String result = "Unspecified departure and return dates";
        if (departOn != null && returnOn != null) {
            //Example: LocalDate formatting
            result = String.format("%s - %s", departOn.format(MONTH_DAY_YEAR_FORMAT), returnOn.format(MONTH_DAY_YEAR_FORMAT));
        }
        return result;
    }

    public void setDepartAndReturn(LocalDate departOn, LocalDate returnOn) throws IllegalArgumentException {
        //Example: LocalDate comparison
        if (departOn != null && returnOn != null && returnOn.isAfter(departOn)) {
            this.departOn = departOn;
            this.returnOn = returnOn;
        } else {
            //Example: throwing an IllegalArgumentException
            throw new IllegalArgumentException("Dates must be sequential.");
        }
    }

    public int getNumberOfDays() {
        int result = 0;
        if (departOn != null && returnOn != null) {
            //Example: counting days between LocalDates
            result = (int) departOn.until(returnOn, ChronoUnit.DAYS);
        }
        return result;
    }


    //region-------------------------BasicXml methods-------------------------------
    @Override
    public void initializeFromXml(String xml) {
        BasicXmlParser parser = new BasicXmlParser(xml);
        departOn = parser.getDateContent("departOn");
        returnOn = parser.getDateContent("returnOn");
    }

    @Override
    public String getInnerXml() {
        BasicXmlBuilder builder = new BasicXmlBuilder();
        builder.addElement("departOn", departOn);
        builder.addElement("returnOn", returnOn);
        return builder.build();
    }
    //endregion

}

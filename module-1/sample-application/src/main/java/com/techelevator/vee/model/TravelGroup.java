package com.techelevator.vee.model;

import com.techelevator.util.xml.BasicXml;
import com.techelevator.util.xml.BasicXmlBuilder;
import com.techelevator.util.xml.BasicXmlParser;

import java.util.ArrayList;
import java.util.List;

/**
 * TravelGroup is a class for keeping track of the number of travelers in three
 * categories: adult, child, and senior.
 */

public class TravelGroup implements BasicXml {

    private int adultCount = 1;
    private int childCount = 0;
    private int seniorCount = 0;

    //Example: overloading a constructor
    public TravelGroup() {}

    public TravelGroup(int adultCount, int childCount, int seniorCount) {
        this.adultCount = adultCount;
        this.childCount = childCount;
        this.seniorCount = seniorCount;
    }

    @Override
    public String toString() {
        List<String> segments = new ArrayList<>();
        //Example: independent if statements
        if (adultCount > 0) {
            segments.add(String.format("%d %s", adultCount, adultCount > 1 ? "adults" : "adult"));
        }
        if (childCount > 0) {
            segments.add(String.format("%d %s", childCount, childCount > 1 ? "children" : "child"));
        }
        if (seniorCount > 0) {
            segments.add(String.format("%d %s", seniorCount, seniorCount > 1 ? "seniors" : "senior"));
        }
        return String.join(", ", segments);
    }

    public int getTravelerCount() {
        return adultCount + childCount + seniorCount;
    }

    //region-------------------------Getters and Setters-------------------------------
    public int getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(int adultCount) {
        this.adultCount = adultCount;
    }

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }

    public int getSeniorCount() {
        return seniorCount;
    }

    public void setSeniorCount(int seniorCount) {
        this.seniorCount = seniorCount;
    }
    //endregion

    //region-------------------------BasicXml methods-------------------------------
    @Override
    public void initializeFromXml(String xml) {
        BasicXmlParser parser = new BasicXmlParser(xml);
        adultCount = parser.getIntegerContent("adultCount");
        childCount = parser.getIntegerContent("childCount");
        seniorCount = parser.getIntegerContent("seniorCount");
    }

    @Override
    public String getInnerXml() {
        BasicXmlBuilder builder = new BasicXmlBuilder();
        builder.addElement("adultCount", adultCount);
        builder.addElement("childCount", childCount);
        builder.addElement("seniorCount", seniorCount);
        return builder.build();
    }
    //endregion

}

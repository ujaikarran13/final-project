package com.techelevator.vee.model.transportation;

import com.techelevator.vee.model.Expense;
import com.techelevator.util.xml.BasicXml;
import com.techelevator.util.xml.BasicXmlBuilder;
import com.techelevator.util.xml.BasicXmlParser;

import java.math.BigDecimal;

/**
 * Transportation is a base class for all the classes that represent the costs associated
 * with getting to and from the vacation destination.
 */

public abstract class Transportation implements BasicXml, Expense {

    private String description;

    @Override
    public String toString() {
        return "Transportation: " + description;
    }

    //region-------------------------Getters and Setters-------------------------------
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //endregion

    //region-------------------------Expense methods-------------------------------
    @Override
    public abstract BigDecimal getTotalCost();

    @Override
    public abstract String getCostDetails();
    //endregion

    //region-------------------------BasicXml methods-------------------------------
    @Override
    public void initializeFromXml(String xml) {
        BasicXmlParser parser = new BasicXmlParser(xml);
        description = parser.getStringContent("description");
    }

    @Override
    public String getInnerXml() {
        return BasicXmlBuilder.createSingleElement("description", description);
    }
    //endregion

}

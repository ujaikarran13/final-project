package com.techelevator.vee.model;

import com.techelevator.util.xml.BasicXml;
import com.techelevator.util.xml.BasicXmlBuilder;
import com.techelevator.util.xml.BasicXmlParser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Activity is a class that encapsulates data and calculations related to the cost
 * of a vacation activity based on a fixed cost, a cost per participant, or both.
 *
 * Creating an instance of this class requires a TravelGroup object that represents the participants.
 * That object is not part of the Activity XML, based on the assumption that it will be serialized
 * and deserialized by its supplier (the Vacation object).
 */

public class Activity implements BasicXml, Expense, ChildAndSeniorPriceable {

    private final TravelGroup participants;
    private String description;
    private BigDecimal fixedCost = BigDecimal.ZERO;
    private BigDecimal costPerAdult = BigDecimal.ZERO;
    private BigDecimal costPerChild = BigDecimal.ZERO;
    private BigDecimal costPerSenior = BigDecimal.ZERO;

    public Activity(TravelGroup participants) {
        this.participants = participants;
    }

    public Activity(TravelGroup participants, String description) {
        this.participants = participants;
        this.description = description;
    }

    public void setCostPerPerson(BigDecimal costPerPerson) {
        costPerAdult = costPerPerson;
        costPerChild = costPerPerson;
        costPerSenior = costPerPerson;
    }

    @Override
    public String toString() {
        return description;
    }

    //region-------------------------Getters and Setters-------------------------------

    public TravelGroup getParticipants() {
        return participants;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getFixedCost() {
        return fixedCost;
    }

    public void setFixedCost(BigDecimal fixedCost) {
        this.fixedCost = fixedCost;
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
    //endregion

    //region-------------------------Expense methods-------------------------------
    @Override
    public BigDecimal getTotalCost() {
        BigDecimal total = fixedCost;
        total = total.add(costPerAdult.multiply(BigDecimal.valueOf(participants.getAdultCount())));
        total = total.add(costPerChild.multiply(BigDecimal.valueOf(participants.getChildCount())));
        total = total.add(costPerSenior.multiply(BigDecimal.valueOf(participants.getSeniorCount())));
        return total;
    }

    @Override
    public String getCostDetails() {
        List<String> segments = new ArrayList<>();
        if (!fixedCost.equals(BigDecimal.ZERO)) {
            segments.add(String.format("Fixed cost of $%s", fixedCost));
        }
        if (participants.getAdultCount() > 0 && !costPerAdult.equals(BigDecimal.ZERO)) {
            segments.add(String.format("%d at $%s each", participants.getAdultCount(), costPerAdult));
        }
        if (participants.getChildCount() > 0 && !costPerChild.equals(BigDecimal.ZERO)) {
            segments.add(String.format("%d at $%s each", participants.getChildCount(), costPerChild));
        }
        if (participants.getSeniorCount()> 0 && !costPerSenior.equals(BigDecimal.ZERO)) {
            segments.add(String.format("%d at $%s each", participants.getSeniorCount(), costPerSenior));
        }
        return String.join(" + ", segments) + ".";
    }
    //endregion

    //region-------------------------BasicXml methods-------------------------------
    @Override
    public void initializeFromXml(String xml) {
        BasicXmlParser parser = new BasicXmlParser(xml);
        description = parser.getStringContent("description");
        fixedCost = parser.getBigDecimalContent("fixedCost");
        costPerAdult = parser.getBigDecimalContent("costPerAdult");
        costPerChild = parser.getBigDecimalContent("costPerChild");
        costPerSenior = parser.getBigDecimalContent("costPerSenior");
    }

    @Override
    public String getInnerXml() {
        BasicXmlBuilder builder = new BasicXmlBuilder();
        builder.addElement("description", description);
        builder.addElement("fixedCost", fixedCost);
        builder.addElement("costPerAdult", costPerAdult);
        builder.addElement("costPerChild", costPerChild);
        builder.addElement("costPerSenior", costPerSenior);
        return builder.build();
    }
    //endregion

}

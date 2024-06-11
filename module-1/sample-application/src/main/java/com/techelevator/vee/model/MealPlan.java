package com.techelevator.vee.model;

import com.techelevator.util.xml.BasicXml;
import com.techelevator.util.xml.BasicXmlBuilder;
import com.techelevator.util.xml.BasicXmlParser;

import java.math.BigDecimal;

/**
 * MealPlan is a class that encapsulates data and calculations related to the cost
 * of eating while on vacation, based on a number of meals per day and an average cost
 * per meal per person.
 *
 * Creating an instance of this class requires a TravelDates object and a TravelGroup
 * object to represent the diners and vacation days. Those objects are not part of the
 * MealPlan XML, based on the assumption that they will be serialized and deserialized by
 * their supplier (the Vacation object).
 */

public class MealPlan implements BasicXml, Expense {

    private final TravelDates vacationDates;
    private final TravelGroup diners;
    private int mealsPerDay;
    private BigDecimal averageMealCostPerPerson = BigDecimal.ZERO;

    public MealPlan(TravelDates vacationDates, TravelGroup diners) {
        this.vacationDates = vacationDates;
        this.diners = diners;
    }

    public MealPlan(TravelDates vacationDates, TravelGroup diners, int mealsPerDay, BigDecimal averageMealCostPerPerson) {
        this(vacationDates, diners);
        this.mealsPerDay = mealsPerDay;
        this.averageMealCostPerPerson = averageMealCostPerPerson;
    }

    @Override
    public String toString() {
        return String.format("Meals per day (not included with lodging): %d", mealsPerDay);
    }

    //region-------------------------Getters and Setters-------------------------------


    public TravelDates getVacationDates() {
        return vacationDates;
    }

    public TravelGroup getDiners() {
        return diners;
    }

    public int getMealsPerDay() {
        return mealsPerDay;
    }

    public void setMealsPerDay(int mealsPerDay) {
        this.mealsPerDay = mealsPerDay;
    }

    public BigDecimal getAverageMealCostPerPerson() {
        return averageMealCostPerPerson;
    }

    public void setAverageMealCostPerPerson(BigDecimal averageMealCostPerPerson) {
        this.averageMealCostPerPerson = averageMealCostPerPerson;
    }

    //endregion

    //region-------------------------Expense methods-------------------------------
    @Override
    public BigDecimal getTotalCost() {
        //Example: primitive multiplication and BigDecimal multiplication
        int mealCount = diners.getTravelerCount() * mealsPerDay * vacationDates.getNumberOfDays();
        BigDecimal convertedMealCount = BigDecimal.valueOf(mealCount);
        BigDecimal totalCost = averageMealCostPerPerson.multiply(convertedMealCount);
        return totalCost;
    }

    @Override
    public String getCostDetails() {
        return String.format("%d diner(s) having %d meals each day at $%s per meal.",
                diners.getTravelerCount(), mealsPerDay, averageMealCostPerPerson);
    }
    //endregion

    //region-------------------------BasicXml methods-------------------------------
    @Override
    public void initializeFromXml(String xml) {
        BasicXmlParser parser = new BasicXmlParser(xml);
        mealsPerDay = parser.getIntegerContent("mealsPerDay");
        averageMealCostPerPerson = parser.getBigDecimalContent("averageMealCostPerPerson");
    }

    @Override
    public String getInnerXml() {
        BasicXmlBuilder builder = new BasicXmlBuilder();
        builder.addElement("mealsPerDay", mealsPerDay);
        builder.addElement("averageMealCostPerPerson", averageMealCostPerPerson);
        return builder.build();
    }
    //endregion

}

package com.techelevator.vee.model;

import com.techelevator.vee.model.transportation.CarTransportation;
import com.techelevator.vee.model.transportation.RentalCarTransportation;
import com.techelevator.vee.model.transportation.TicketedTransportation;
import com.techelevator.vee.model.transportation.Transportation;
import com.techelevator.util.xml.BasicXml;
import com.techelevator.util.xml.BasicXmlParser;
import com.techelevator.util.xml.BasicXmlBuilder;

import java.math.BigDecimal;
import java.util.*;

/**
 * The Vacation class is the heart of the object model for the Vacation Expense Estimator.
 * It combines instances of the other classes to represent the various aspects of a single
 * vacation.
 *
 * The instances of TravelDates and TravelGroup are passed to the other classes that need
 * them when those classes are instantiated. The implication is that there is only one
 * instance of TravelDates and TravelGroup for each vacation, which means any changes made
 * to the dates or number of travelers is automatically available to those other classes,
 * without needing to explicitly update each one.
 */

public class Vacation implements BasicXml, Expense {

    private String destination;
    private final TravelDates dates = new TravelDates();
    private final TravelGroup travelers = new TravelGroup();
    private Lodging lodging;
    private MealPlan mealPlan;
    private Transportation transportation;
    //Example: instantiating a HashMap with String keys and Activity values
    private final Map<String, Activity> activities = new HashMap<>();

    @Override
    public String toString() {
        return String.format("Vacation to %s (%s)", destination, dates);
    }

    public int getNumberOfDays() {
        return dates.getNumberOfDays();
    }

    public String getInformation() {
        //Example: instantiating an ArrayList of Strings
        List<String> info = new ArrayList<>();
        info.add("Destination: " + destination);
        info.add("Dates: " + dates);
        info.add("Travelers: " + travelers);
        if (lodging != null) {
            info.add(lodging.toString());
        } else {
            info.add("No lodging specified.");
        }
        if (mealPlan != null) {
            info.add(mealPlan.toString());
        } else {
            info.add("No meals specified.");
        }
        if (transportation != null) {
            info.add(transportation.toString());
        } else {
            info.add("No transportation specified.");
        }
        if (activities.size() > 0) {
            info.add("Activities:");
            for (String description : getActivityDescriptions()) {
                info.add("  * " + description);
            }
        } else {
            info.add("No activities added.");
        }
        return String.join("\n", info);
    }

    //Example: working with a Map
    public void addActivity(Activity activity) {
        String description = activity.getDescription();
        activities.put(description, activity);
    }

    public void removeActivity(String description) {
        activities.remove(description);
    }

    public boolean hasActivityWithDescription(String description) {
        return activities.containsKey(description);
    }

    public String[] getActivityDescriptions() {
        //Example: converting a Set to an array
        Set<String> descriptions = activities.keySet();
        return descriptions.toArray(new String[0]);
    }

    //region-------------------------Getters and Setters-------------------------------
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public TravelDates getDates() {
        return dates;
    }

    public TravelGroup getTravelers() {
        return travelers;
    }

    public Lodging getLodging() {
        return lodging;
    }

    public void setLodging(Lodging lodging) {
        this.lodging = lodging;
    }

    public MealPlan getMealPlan() {
        return mealPlan;
    }

    public void setMealPlan(MealPlan mealPlan) {
        this.mealPlan = mealPlan;
    }

    public Transportation getTransportation() {
        return transportation;
    }

    public void setTransportation(Transportation transportation) {
        this.transportation = transportation;
    }

    //endregion

    //region-------------------------Expense methods-------------------------------

    private List<Expense> getExpenses() {
        //Example: instantiating an ArrayList of Expenses
        List<Expense> expenses = new ArrayList<>();
        if (lodging != null) {
            expenses.add(lodging);
        }
        if (mealPlan != null) {
            expenses.add(mealPlan);
        }
        if (transportation != null) {
            expenses.add(transportation);
        }
        expenses.addAll(activities.values());
        return expenses;
    }

    @Override
    public BigDecimal getTotalCost() {
        //Example: BigDecimal addition
        BigDecimal total = BigDecimal.ZERO;
        for (Expense expense : getExpenses()) {
            total = total.add(expense.getTotalCost());
        }
        return total;
    }

    @Override
    public String getCostDetails() {
        //Example: using polymorphism through an interface
        String details = "";
        for (Expense expense : getExpenses()) {
            details += String.format("%s\n%s\n", expense, expense.getCostDetails());
        }
        return details;
    }
    //endregion

    //region-------------------------BasicXml methods-------------------------------
    @Override
    public void initializeFromXml(String xml) {
        BasicXmlParser parser = new BasicXmlParser(xml);
        destination = parser.getStringContent("destination");
        dates.initializeFromXml(parser.getStringContent("dates"));
        travelers.initializeFromXml(parser.getStringContent("travelers"));
        String lodgingXml = parser.getStringContent("lodging");
        if (lodgingXml != null && !lodgingXml.isBlank()) {
            lodging = new Lodging(dates);
            lodging.initializeFromXml(lodgingXml);
        }
        String mealsXml = parser.getStringContent("mealPlan");
        if (mealsXml != null && !mealsXml.isBlank()) {
            mealPlan = new MealPlan(dates, travelers);
            mealPlan.initializeFromXml(mealsXml);
        }
        initializeTransportation(parser);
        initializeActivities(parser);
    }

    private void initializeTransportation(BasicXmlParser parser) {
        String xml = parser.getStringContent("transportation-CarTransportation");
        if (xml != null && !xml.isBlank()) {
            transportation = new CarTransportation();
        } else {
            xml = parser.getStringContent("transportation-RentalCarTransportation");
            if (xml != null && !xml.isBlank()) {
                transportation = new RentalCarTransportation(dates);
            } else {
                xml = parser.getStringContent("transportation-TicketedTransportation");
                if (xml != null && !xml.isBlank()) {
                    transportation = new TicketedTransportation(dates, travelers);
                }
            }
        }
        //Example: using polymorphism through inheritance
        if (transportation != null) {
            transportation.initializeFromXml(xml);
        }
    }

    private void initializeActivities(BasicXmlParser parser) {
        int counter = 0;
        boolean finished = false;
        while (!finished) {
            String activityXml = parser.getStringContent("activity-" + counter);
            if (activityXml != null && !activityXml.isEmpty()) {
                Activity activity = new Activity(travelers);
                activity.initializeFromXml(activityXml);
                activities.put(activity.getDescription(), activity);
                counter++;
            } else {
                finished = true;
            }
        }
    }

    @Override
    public String getInnerXml() {
        BasicXmlBuilder builder = new BasicXmlBuilder();
        builder.addElement("destination", destination);
        builder.addElement("dates", dates);
        builder.addElement("travelers", travelers);
        builder.addElement("lodging", lodging);
        builder.addElement("mealPlan", mealPlan);
        if (transportation != null) {
            String tag = "transportation-" + transportation.getClass().getSimpleName();
            builder.addElement(tag, transportation);
        }
        int counter = 0;
        for (Activity activity : activities.values()) {
            builder.addElement("activity-" + counter, activity);
            counter++;
        }

        return builder.build();
    }
    //endregion

}

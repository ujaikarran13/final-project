package com.techelevator.vee.model;

import com.techelevator.util.xml.BasicXmlBuilder;
import com.techelevator.vee.model.transportation.CarTransportation;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class VacationTest {

    final Vacation vacation = new Vacation();
    final BigDecimal lodgingCostPerNight = BigDecimal.valueOf(10_000);
    final Lodging lodging;
    final BigDecimal averageMealCost = BigDecimal.valueOf(1000);
    final int expectedMealsPerDay = 3;
    final MealPlan mealPlan;
    final int expectedMileage = 100;
    final BigDecimal fuelCostPerGallon = BigDecimal.valueOf(2.99);
    final CarTransportation car;
    final Activity bumperCarsActivity;

    public VacationTest() {
        // arrange
        vacation.setDestination("vacation-unit-tests");
        vacation.getDates().setDepartAndReturn(LocalDate.now(), LocalDate.now().plusDays(2));
        vacation.getTravelers().setAdultCount(3);
        vacation.getTravelers().setChildCount(2);
        vacation.getTravelers().setSeniorCount(1);

        lodging = new Lodging(vacation.getDates(), "vacation-lodging", lodgingCostPerNight);
        vacation.setLodging(lodging);

        mealPlan = new MealPlan(vacation.getDates(), vacation.getTravelers(), expectedMealsPerDay, averageMealCost);
        vacation.setMealPlan(mealPlan);

        car = new CarTransportation(expectedMileage, fuelCostPerGallon);
        car.setDescription("vacation-transportation");
        vacation.setTransportation(car);

        bumperCarsActivity = new Activity(vacation.getTravelers(), "bumper cars");
        vacation.addActivity(bumperCarsActivity);
    }

    @Test
    public void thenTotalCostIsSumOfComponents() {
        // act
        final BigDecimal remainder = vacation.getTotalCost()
                .subtract(lodging.getTotalCost())
                .subtract(mealPlan.getTotalCost())
                .subtract(car.getTotalCost())
                .subtract(bumperCarsActivity.getCostPerAdult());

        // assert
        Assert.assertEquals("total cost is not the sum of the components",
                BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP),
                remainder);
    }

    @Test
    public void thenCostDetailsContainsAllComponents() {
        // act
        final String details = vacation.getCostDetails();

        // assert
        Assert.assertTrue("lodging details are missing", details.contains(lodging.getCostDetails()));
        Assert.assertTrue("meal-plan details are missing", details.contains(mealPlan.getCostDetails()));
        Assert.assertTrue("car details are missing", details.contains(car.getCostDetails()));
        Assert.assertTrue("activity details are missing", details.contains(bumperCarsActivity.getCostDetails()));
    }

    @Test
    public void whenSerialized_thenComponentsArePresent() {
        // arrange
        final BasicXmlBuilder builder = new BasicXmlBuilder();
        final String descriptionAsXml = builder
                .addElement("destination", vacation.getDestination())
                .build();

        // act
        final String xml = vacation.getInnerXml();

        // assert
        Assert.assertTrue("destination is wrong", xml.contains(descriptionAsXml));
        Assert.assertTrue("dates are wrong", xml.contains(vacation.getDates().getInnerXml()));
        Assert.assertTrue("travelers are wrong", xml.contains(vacation.getTravelers().getInnerXml()));
        Assert.assertTrue("lodging is wrong", xml.contains(vacation.getLodging().getInnerXml()));
        Assert.assertTrue("meal-plan is wrong", xml.contains(vacation.getMealPlan().getInnerXml()));
        Assert.assertTrue("car is wrong", xml.contains(vacation.getTransportation().getInnerXml()));
        Assert.assertTrue("activity is wrong", xml.contains(bumperCarsActivity.getInnerXml()));
    }

    @Test
    public void whenSerialized_canDeserialize() {
        // act
        final Vacation copy = new Vacation();
        copy.initializeFromXml(vacation.getInnerXml());

        // assert
        Assert.assertEquals("the serialized contents differ", vacation.getInnerXml(), copy.getInnerXml());

    }
}

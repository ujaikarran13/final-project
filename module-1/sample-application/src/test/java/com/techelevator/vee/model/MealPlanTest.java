package com.techelevator.vee.model;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MealPlanTest {

    @Test
    public void whenTheMealPlanHasNoMealCost_totalCostIsZero() {

        // arrange
        final TravelDates travelDates = createForDuration(2);
        final TravelGroup diners = new TravelGroup(1,0,0);
        final MealPlan mealPlan = new MealPlan(travelDates, diners);
        mealPlan.setMealsPerDay(1);

        // act
        final BigDecimal totalCost = mealPlan.getTotalCost();

        // assert
        Assert.assertEquals("wrong total cost", BigDecimal.ZERO, totalCost);

    }

    @Test
    public void whenTheMealPlanHasNoMeals_totalCostIsZero() {
        // arrange
        final TravelDates travelDates = createForDuration(2);
        final TravelGroup diners = new TravelGroup(1,0,0);
        final MealPlan mealPlan = new MealPlan(travelDates, diners);
        mealPlan.setAverageMealCostPerPerson(BigDecimal.ONE);

        // act
        final BigDecimal totalCost = mealPlan.getTotalCost();

        // assert
        Assert.assertEquals("wrong total cost", BigDecimal.ZERO, totalCost);

    }

    @Test
    public void whenTheMealPlanHasAllComponents_totalCostCalculated() {
        // arrange
        final TravelDates travelDates = createForDuration(2);
        final TravelGroup diners = new TravelGroup(1,0,0);
        final int mealsPerDay = 2;
        final BigDecimal averageMealCost = BigDecimal.TEN;
        final MealPlan mealPlan = new MealPlan(travelDates, diners, mealsPerDay, averageMealCost);

        // act
        final BigDecimal totalCost = mealPlan.getTotalCost();

        // assert
        final BigDecimal expectedCost = averageMealCost.multiply(BigDecimal.valueOf(
                (long) travelDates.getNumberOfDays()
                        * diners.getTravelerCount()
                        * mealsPerDay
        ));
        Assert.assertEquals("wrong total cost", expectedCost, totalCost);

    }

    @Test
    public void whenTheMealPlanHasAllComponents_canOutputDetails() {
        // arrange
        final TravelDates travelDates = createForDuration(2);
        final TravelGroup diners = new TravelGroup(1,0,0);
        final int mealsPerDay = 2;
        final BigDecimal averageMealCost = BigDecimal.TEN;
        final MealPlan mealPlan = new MealPlan(travelDates, diners, mealsPerDay, averageMealCost);

        // act
        final String costDetails = mealPlan.getCostDetails();

        // assert
        Assert.assertTrue("diner count is wrong", costDetails.contains("1 diner(s)"));
        Assert.assertTrue("meal count is wrong", costDetails.contains("having 2 meals each day"));
        Assert.assertTrue("cost is wrong", costDetails.contains("at $10 per meal"));
    }

    @Test
    public void whenSerialized_canDeserialize() {
        // arrange
        final TravelDates travelDates = createForDuration(2);
        final TravelGroup diners = new TravelGroup(1,0,0);
        final int mealsPerDay = 2;
        final BigDecimal averageMealCost = BigDecimal.TEN;
        final MealPlan original = new MealPlan(travelDates, diners, mealsPerDay, averageMealCost);

        // act
        final MealPlan copy = new MealPlan(travelDates, diners);
        copy.initializeFromXml(original.getInnerXml());

        // assert
        Assert.assertEquals("meals per day is wrong", original.getMealsPerDay(), copy.getMealsPerDay());
        Assert.assertEquals("cost per meal is wrong", original.getAverageMealCostPerPerson(), copy.getAverageMealCostPerPerson());

    }

    private TravelDates createForDuration(int days) {
        final LocalDate departDate = LocalDate.now().plusDays(1);
        final LocalDate returnDate = departDate.plusDays(days);
        final TravelDates travelDates = new TravelDates();
        travelDates.setDepartAndReturn(departDate, returnDate);

        return travelDates;
    }


}

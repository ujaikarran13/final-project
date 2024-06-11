package com.techelevator.vee;

import com.techelevator.vee.model.*;
import com.techelevator.vee.model.transportation.CarTransportation;
import com.techelevator.vee.model.transportation.RentalCarTransportation;
import com.techelevator.vee.model.transportation.TicketedTransportation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;


public class VacationExpenseEstimatorViewTest {
    /**     WARNING!!
     *      the order that items are queued mimics the
     *      prompts shown by the view. changing either one
     *      will cause tests to fail.
     */

    private ConsoleMock console;
    private VacationExpenseEstimatorView view;

    @Before
    public void setup() {
        console = new ConsoleMock();
        view = new VacationExpenseEstimatorView(console);
    }

    @Test
    public void whenActivitiesArePresent_displayThem() {
        // arrange
        String[] descriptions = {"activity 1", "activity 2"};

        // act
        view.displayActivityDescriptions(descriptions);

        // assert
        Assert.assertArrayEquals("list contents differ from expected",
                descriptions,
                console.bulletedItems.toArray());
    }

    @Test
    public void whenThereAreNoActivities_printNoActivityMessage() {
        // arrange
        String[] descriptions = new String[0];

        // act
        view.displayActivityDescriptions(descriptions);

        // assert
        Assert.assertEquals("there should be no standard message", 0, console.bulletedItems.size());
        Assert.assertEquals("there should be one error message", 1, console.capturedErrorMessages.size());
    }

    @Test
    public void givenInputFromUser_setDestination() {
        // arrange
        final String destination = "destination-1";
        console.stringPromptQueue.add(destination);
        final Vacation vacation = new Vacation();

        // act
        view.promptToUpdateDestination(vacation);

        // assert
        Assert.assertEquals("destination is wrong", destination, vacation.getDestination());
    }

    @Test
    public void  whenNoInputFromUser_leaveDestination() {
        // arrange
        final String expected = "destination-1";
        console.stringPromptQueue.add("");
        final Vacation vacation = new Vacation();
        vacation.setDestination(expected);

        // act
        view.promptToUpdateDestination(vacation);

        // assert
        Assert.assertEquals("destination is wrong", expected, vacation.getDestination());
    }

    @Test
    public void givenInputFromUser_setVacationDates() {
        // arrange
        final LocalDate departDate = LocalDate.now();
        final LocalDate returnDate = departDate.plusDays(1);
        console.localDatePromptQueue.add(departDate);
        console.localDatePromptQueue.add(returnDate);
        final Vacation vacation = new Vacation();

        // act
        view.promptToUpdateVacationDates(vacation);

        // assert
        final TravelDates actual = new TravelDates();
        actual.setDepartAndReturn(departDate, returnDate);
        Assert.assertEquals("travel dates are wrong", actual.toString(), vacation.getDates().toString());
        Assert.assertEquals("there should be no error message", 0, console.capturedErrorMessages.size() );
    }

    @Test
    public void whenDatesAreReversed_generateErrorMessage() {
        // arrange
        final LocalDate departDate = LocalDate.now();
        final LocalDate returnDate = departDate.minusDays(1);
        console.localDatePromptQueue.add(departDate);
        console.localDatePromptQueue.add(returnDate);
        final Vacation vacation = new Vacation();

        // act
        view.promptToUpdateVacationDates(vacation);

        // assert
        Assert.assertEquals("there should be one error message", 1, console.capturedErrorMessages.size() );
    }

    @Test
    public void whenUserInputIsAvailable_setTravelers() {
        // arrange
        final int adultCount = 1;
        final int childrenCount = 2;
        final int seniorCount = 3;
        console.integerPromptQueue.add(adultCount);
        console.integerPromptQueue.add(childrenCount);
        console.integerPromptQueue.add(seniorCount);
        final Vacation vacation = new Vacation();

        // act
        view.promptToUpdateTravelers(vacation);

        // assert
        Assert.assertEquals("adult count is wrong", adultCount, vacation.getTravelers().getAdultCount());
        Assert.assertEquals("children count is wrong", childrenCount, vacation.getTravelers().getChildCount());
        Assert.assertEquals("senior count is wrong", seniorCount, vacation.getTravelers().getSeniorCount());
    }

    @Test
    public void whenPartialUserInputIsAvailable_setTravelers() {
        // arrange
        final int adultCount = 1;
        console.integerPromptQueue.add(adultCount);
        console.integerPromptQueue.add(Integer.MIN_VALUE); // child count
        console.integerPromptQueue.add(Integer.MIN_VALUE); // senior count
        final Vacation vacation = new Vacation();

        // act
        view.promptToUpdateTravelers(vacation);

        // assert
        Assert.assertEquals("adult count is wrong", adultCount, vacation.getTravelers().getAdultCount());
        Assert.assertEquals("children count is wrong", 0, vacation.getTravelers().getChildCount());
        Assert.assertEquals("senior count is wrong", 0, vacation.getTravelers().getSeniorCount());
    }

    @Test
    public void whenUserInputIsAvailable_setLodging() {
        // arrange
        final String description = "hotel";
        final BigDecimal costPerNight = BigDecimal.TEN;
        console.stringPromptQueue.add(description);
        console.bigDecimalPromptQueue.add(costPerNight);
        final TravelDates period = buildForDuration(1);

        // act
        final Lodging lodging = view.promptForLodging(period);

        // assert
        Assert.assertEquals("description is wrong", description, lodging.getDescription());
        Assert.assertEquals("cost is wrong", costPerNight, lodging.getCostPerNight());
    }

    @Test
    public void whenLodgingIsSkipped_returnNull() {
        // arrange
        final String description = "";
        console.stringPromptQueue.add(description);
        final TravelDates period = new TravelDates();
        period.setDepartAndReturn(LocalDate.now(), LocalDate.now().plusDays(1));

        // act
        final Lodging lodging = view.promptForLodging(period);

        // assert
        Assert.assertNull("no lodging should be returned", lodging);
    }

    @Test
    public void whenUserInputIsAvailable_buildMealPlan() {
        // arrange
        final int mealsPerDay = 2;
        final BigDecimal costPerMealPerPerson = BigDecimal.TEN;
        final TravelDates period = buildForDuration(1);
        final TravelGroup travelers = new TravelGroup(1,2,3);
        console.integerPromptQueue.add(mealsPerDay);
        console.bigDecimalPromptQueue.add(costPerMealPerPerson);

        // act
        final MealPlan plan = view.promptForMealPlan(period, travelers);

        // assert
        Assert.assertEquals("travel period is wrong", period.toString(), plan.getVacationDates().toString());
        Assert.assertEquals("diners is wrong", travelers.toString(), plan.getDiners().toString());
        Assert.assertEquals("number of meals is wrong", mealsPerDay, plan.getMealsPerDay());
        Assert.assertEquals("cost is wrong", costPerMealPerPerson, plan.getAverageMealCostPerPerson());
    }

    @Test
    public void whenNoMealCount_returnNoPlan() {
        // arrange
        final TravelDates period = buildForDuration(1);
        final TravelGroup travelers = new TravelGroup(1,2,3);
        console.integerPromptQueue.add(Integer.MIN_VALUE);

        // act
        final MealPlan plan = view.promptForMealPlan(period, travelers);

        // assert
        Assert.assertNull("no plan should be returned", plan);
    }

    @Test
    public void whenNoMealCost_returnNoPlan() {
        // arrange
        final int mealsPerDay = 2;
        final TravelDates period = buildForDuration(1);
        final TravelGroup travelers = new TravelGroup(1,2,3);
        console.integerPromptQueue.add(mealsPerDay);
        console.bigDecimalPromptQueue.add(ConsoleMock.NULL_BIGDECIMAL);

        // act
        final MealPlan plan = view.promptForMealPlan(period, travelers);

        // assert
        Assert.assertNull("no plan should be returned", plan);
    }

    @Test
    public void whenUserInputIsAvailable_AddActivityWithFixedCost() {
        // arrange
        final Vacation vacation = new Vacation();
        final String description = "activity-description";
        final BigDecimal fixedCost = BigDecimal.TEN;
        console.stringPromptQueue.add(description);
        console.yesNoPromptQueue.add(false); // no per-person cost
        console.bigDecimalPromptQueue.add(fixedCost);

        // act
        final Activity activity = view.promptForActivity(vacation);

        // assert
        Assert.assertNotNull("an activity should be built", activity);
        Assert.assertEquals("description is wrong", description, activity.getDescription());
        Assert.assertNotNull("travelers should be linked", activity.getParticipants());
        Assert.assertEquals("cost is wrong", fixedCost, activity.getFixedCost());

    }

    @Test
    public void doNotAllowDuplicateActivityDescriptions() {
        // arrange
        final Vacation vacation = new Vacation();
        final String description = "activity-description";
        final Activity activity = new Activity(vacation.getTravelers(), description);
        vacation.addActivity(activity);
        console.stringPromptQueue.add(description);
        console.stringPromptQueue.add(""); // stop the input loop

        // act
        final Activity newActivity = view.promptForActivity(vacation);

        // assert
        Assert.assertNull("no activity should be created", newActivity);
        Assert.assertEquals("error message should be returned", 1, console.capturedErrorMessages.size());
    }

    @Test
    public void whenUserInputIsAvailable_AddActivityWithSeparateAdultChildAndSeniorCosts() {
        // arrange
        final Vacation vacation = new Vacation();
        final String description = "activity-description";
        final BigDecimal adultCost = BigDecimal.valueOf(100.00);
        final BigDecimal seniorCost = BigDecimal.TEN;
        final BigDecimal childCost = BigDecimal.ONE;

        console.stringPromptQueue.add(description);
        console.yesNoPromptQueue.add(true); // per-person cost
        console.bigDecimalPromptQueue.add(adultCost);
        console.yesNoPromptQueue.add(true); // separate child and senior costs
        console.bigDecimalPromptQueue.add(childCost);
        console.bigDecimalPromptQueue.add(seniorCost);
        console.bigDecimalPromptQueue.add(ConsoleMock.NULL_BIGDECIMAL); // no fixed costs

        // act
        final Activity activity = view.promptForActivity(vacation);

        // assert
        Assert.assertNotNull("an activity should be built", activity);
        Assert.assertEquals("adult cost is wrong", adultCost, activity.getCostPerAdult());
        Assert.assertEquals("child cost is wrong", childCost, activity.getCostPerChild());
        Assert.assertEquals("senior cost is wrong", seniorCost, activity.getCostPerSenior());
    }

    @Test
    public void whenRemovingActivities_noExistingActivitiesShowsMessage() {
        // arrange
        final String[] activityDescriptions = new String[0];

        // act
        final String result = view.promptToRemoveActivity(activityDescriptions);

        // assert
        Assert.assertNull("no description was selected", result);
        Assert.assertEquals("an error message should be displayed", 1, console.capturedErrorMessages.size());
    }

    @Test
    public void whenUserInputIsProvided_selectTheActivityToRemove() {
        // arrange
        final String activityDescription = "activity-description";
        final String[] activityDescriptions = {activityDescription};
        console.menuSelectionPromptQueue.add(activityDescription);

        // act
        final String result = view.promptToRemoveActivity(activityDescriptions);

        // assert
        Assert.assertEquals("description was selected", activityDescription, result);
        Assert.assertEquals("no error messages should be displayed", 0, console.capturedErrorMessages.size());
    }

    @Test
    public void whenUserInputIsAvailable_canAddCar() {
        // arrange
        final String expectedDescription = "Your own car";
        final double distance = 100.0;
        final double mpg = 2.50;
        final BigDecimal fuelCostPerGal = BigDecimal.ONE;
        final TravelDates period = buildForDuration(1);
        final TravelGroup travelers = new TravelGroup(1,2,3);
        final String carOption = "Driving your own car";
        console.menuSelectionPromptQueue.add(carOption);
        console.doublePromptQueue.add(distance);
        console.bigDecimalPromptQueue.add(fuelCostPerGal);
        console.doublePromptQueue.add(mpg);

        // act
        final CarTransportation car = (CarTransportation) view.promptForTransportation(period, travelers);

        // assert
        Assert.assertEquals("description is wrong", expectedDescription, car.getDescription());
        Assert.assertEquals("expected distance is wrong", distance, car.getMiles(), 0.01);
        Assert.assertEquals("fuel cost is wrong", fuelCostPerGal, car.getFuelCostPerGallon());
        Assert.assertEquals("expected mpg is wrong", mpg, car.getMpgEstimate(), 0.01);
    }

    @Test
    public void whenUserInputIsAvailable_canAddRentalCar() {
        // arrange
        final String rentalCarOption = "Renting a car";
        final String expectedDescription = "rental-car-description";
        final BigDecimal costPerDay = BigDecimal.TEN;
        final TravelDates period = buildForDuration(1);
        final TravelGroup travelers = new TravelGroup(1,2,3);
        final double distance = 100.0;
        final double mpg = 2.50;
        final BigDecimal fuelCostPerGal = BigDecimal.ONE;
        final int mileagePerDay = 25;
        final BigDecimal mileageOverageCharge = BigDecimal.ONE;
        console.menuSelectionPromptQueue.add(rentalCarOption);
        console.stringPromptQueue.add(expectedDescription);
        console.bigDecimalPromptQueue.add(costPerDay);
        console.doublePromptQueue.add(distance);
        console.bigDecimalPromptQueue.add(fuelCostPerGal);
        console.doublePromptQueue.add(mpg);
        console.yesNoPromptQueue.add(false); // no unlimited mileage
        console.integerPromptQueue.add(mileagePerDay);
        console.bigDecimalPromptQueue.add(mileageOverageCharge);

        // act
        final RentalCarTransportation rental = (RentalCarTransportation) view.promptForTransportation(period, travelers);

        // assert
        Assert.assertEquals("description is wrong", expectedDescription, rental.getDescription());
        Assert.assertEquals("cost per day is wrong", costPerDay, rental.getCostPerDay());
        Assert.assertEquals("expected distance is wrong", distance, rental.getMiles(), 0.01);
        Assert.assertEquals("fuel cost is wrong", fuelCostPerGal, rental.getFuelCostPerGallon());
        Assert.assertEquals("expected mpg is wrong", mpg, rental.getMpgEstimate(), 0.01);
        Assert.assertFalse("unlimited mileage is wrong", rental.getUnlimitedMiles());
        Assert.assertEquals("mileage allowance is wrong", mileagePerDay, rental.getMilesAllowedPerDay());
        Assert.assertEquals("mileage overage is wrong", mileageOverageCharge, rental.getCostPerExtraMile());

    }

    @Test
    public void whenUserInputIsAvailable_addTicketedTransportation() {
        // arrange
        final String ticketedTransportationOption = "By ticketed transportation (plane, train, etc.)";
        final TravelDates period = buildForDuration(1);
        final TravelGroup travelers = new TravelGroup(1,2,3);
        final String description = "ticketed-description";
        final BigDecimal adultCost = BigDecimal.TEN;
        final boolean noSeparateCostForChildrenAndSeniors = false;
        final boolean includeRentalCar = true;
        final String rentalDescription = "rental-description";
        final BigDecimal costPerDay = BigDecimal.TEN;
        final double distance = 100.0;
        final double mpg = 2.50;
        final BigDecimal fuelCostPerGal = BigDecimal.ONE;
        final boolean unlimitedMileage = true;

        console.menuSelectionPromptQueue.add(ticketedTransportationOption);
        console.stringPromptQueue.add(description);
        console.bigDecimalPromptQueue.add(adultCost);
        console.yesNoPromptQueue.add(noSeparateCostForChildrenAndSeniors);
        console.yesNoPromptQueue.add(includeRentalCar);
        console.stringPromptQueue.add(rentalDescription);
        console.bigDecimalPromptQueue.add(costPerDay);
        console.doublePromptQueue.add(distance);
        console.bigDecimalPromptQueue.add(fuelCostPerGal);
        console.doublePromptQueue.add(mpg);
        console.yesNoPromptQueue.add(unlimitedMileage); // no unlimited mileage

        // act
        final TicketedTransportation tt = (TicketedTransportation) view.promptForTransportation(period, travelers);

        // assert
        Assert.assertEquals("description is wrong", description, tt.getDescription());
        Assert.assertEquals("cost is wrong", adultCost, tt.getCostPerAdult());
        Assert.assertNotNull("rental car is not present", tt.getRentalCar());

    }

    private TravelDates buildForDuration(int duration) {
        final TravelDates period = new TravelDates();
        period.setDepartAndReturn(LocalDate.now(), LocalDate.now().plusDays(duration));
        return period;
    }
}

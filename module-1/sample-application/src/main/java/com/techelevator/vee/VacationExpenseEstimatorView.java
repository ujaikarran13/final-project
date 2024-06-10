package com.techelevator.vee;

import com.techelevator.util.BasicConsole;
import com.techelevator.vee.model.*;
import com.techelevator.vee.model.transportation.CarTransportation;
import com.techelevator.vee.model.transportation.RentalCarTransportation;
import com.techelevator.vee.model.transportation.TicketedTransportation;
import com.techelevator.vee.model.transportation.Transportation;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * VacationExpenseEstimatorView is a class that the VacationExpenseEstimatorController uses
 * for gathering vacation information from the user and presenting vacation information to
 * the user. It requires an object that implements the BasicConsole interface to handle the
 * mechanics of reading from and writing to the console.
 *
 * (The term View comes from the Model-View-Controller pattern, which you'll learn
 * more about in module 2.)
 */

public class VacationExpenseEstimatorView {

    private final BasicConsole console;

    public VacationExpenseEstimatorView(BasicConsole console) {
        this.console = console;
    }

    public String promptForFilename() {
        return console.promptForString("Please enter the filename: ");
    }

    public void printErrorMessage(String message) {
        console.printErrorMessage(message);
    }

    public String getMenuSelection(String menuTitle, String[] options) {
        console.printBanner(menuTitle);
        return console.getMenuSelection(options);
    }

    public boolean promptForYesNo(String prompt) {
        return console.promptForYesNo(prompt);
    }

    public void displayVacationInfo(Vacation vacation) {
        console.printDivider();
        console.printMessage(vacation.getInformation());
        console.printDivider();
        console.pauseOutput();
    }

    public void displayActivityDescriptions(String[] descriptions) {
        if (descriptions.length > 0) {
            console.printDivider();
            console.printBulletedItems(descriptions);
            console.printDivider();
        } else {
            console.printErrorMessage("No activities.");
        }
        console.pauseOutput();
    }

    public void displayExpenseSummary(Vacation vacation) {
        console.printBanner("Total Estimated Expense is: $" + vacation.getTotalCost());
    }

    public void displayExpenseDetails(Vacation vacation) {
        console.printDivider();
        console.printMessage(vacation.getCostDetails());
        console.printDivider();
        console.pauseOutput();
    }

    public void promptToUpdateDestination(Vacation vacation) {
        String destination = console.promptForString("Enter destination (for example, The Grand Canyon): ");
        if (!destination.isBlank()) {
            vacation.setDestination(destination);
        }
    }

    public void promptToUpdateVacationDates(Vacation vacation) {
        LocalDate newDeparture = console.promptForLocalDate("Enter a departure date: ");
        if (newDeparture != null) {
            LocalDate newReturn = console.promptForLocalDate("Enter the return date: ");
            if (newReturn != null) {
                try {
                    vacation.getDates().setDepartAndReturn(newDeparture, newReturn);
                } catch (IllegalArgumentException e) {
                    console.printErrorMessage(e.getMessage());
                }
            }
        }
    }

    public void promptToUpdateTravelers(Vacation vacation) {
        TravelGroup travelers = vacation.getTravelers();
        String prompt = String.format("Enter the number of adults (currently %d): ", travelers.getAdultCount());
        Integer count = console.promptForInteger(prompt);
        if (count != null) {
            travelers.setAdultCount(count);
        }
        prompt = String.format("Enter the number of children (currently %d): ", travelers.getChildCount());
        count = console.promptForInteger(prompt);
        if (count != null) {
            travelers.setChildCount(count);
        }
        prompt = String.format("Enter the number of seniors (currently %d): ", travelers.getSeniorCount());
        count = console.promptForInteger(prompt);
        if (count != null) {
            travelers.setSeniorCount(count);
        }
    }

    public Lodging promptForLodging(TravelDates reservationPeriod) {
        Lodging result = null;
        String description = console.promptForString("Where will you be staying (Airbnb, Hotel, Cabin, etc.)? ");
        if (!description.isBlank()) {
            BigDecimal costPerNight = console.promptForBigDecimal("What's the estimated cost per night? $");
            if (costPerNight != null) {
                result = new Lodging(reservationPeriod, description, costPerNight);
            }
        }
        return result;
    }

    public MealPlan promptForMealPlan(TravelDates vacationDates, TravelGroup diners) {
        MealPlan result = null;
        Integer mealsPerDay = console.promptForInteger("How many meals per day (not included with lodging)? ");
        if (mealsPerDay != null) {
            BigDecimal mealCost = console.promptForBigDecimal("What's the estimated average cost per meal per person? $");
            if (mealCost != null) {
                result = new MealPlan(vacationDates, diners, mealsPerDay, mealCost);
            }
        }
       return result;
    }

    public Transportation promptForTransportation(TravelDates vacationDates, TravelGroup passengers) {
        Transportation result = null;
        console.printMessage("How will you be traveling?");
        final String CAR = "Driving your own car";
        final String RENTAL_CAR = "Renting a car";
        final String TICKETED = "By ticketed transportation (plane, train, etc.)";
        final String CANCEL = "Cancel";
        final String[] MENU_OPTIONS = {CAR, RENTAL_CAR, TICKETED, CANCEL};

        String selection = console.getMenuSelection(MENU_OPTIONS);
        if (selection.equals(CAR)) {
            result = promptForCar();
            if (result != null) {
                result.setDescription("Your own car");
            }
        } else if (selection.equals(RENTAL_CAR)) {
            result = promptForRentalCar(vacationDates);
        } else if (selection.equals(TICKETED)) {
            result = promptForTicketedTransportation(vacationDates, passengers);
        }
        return result;
    }

    private CarTransportation promptForCar() {
        CarTransportation result = null;
        Double miles = console.promptForDouble("How many miles will you be traveling? ");
        if (miles != null) {
            BigDecimal fuelCost = console.promptForBigDecimal("How much do you estimate gas will cost per gallon? $");
            if (fuelCost != null) {
                result = new CarTransportation(miles, fuelCost);
                console.printMessage("Average miles per gallon is " + CarTransportation.AVERAGE_MPG + ".");
                Double mpg = console.promptForDouble("Estimated miles per gallon (leave blank to use the average): ");
                if (mpg != null) {
                    result.setMpgEstimate(mpg);
                }
            }
        }
        return result;
    }

    private RentalCarTransportation promptForRentalCar(TravelDates rentalPeriod) {
        RentalCarTransportation result = null;
        String description = console.promptForString("What kind of car will you rent (compact car, van, SUV, etc.)? ");
        if (!description.isBlank()) {
            BigDecimal costPerDay = console.promptForBigDecimal("How much do you estimate the rental charge will be per day? $");
            if (costPerDay != null) {
                CarTransportation car = promptForCar();
                if (car != null) {
                    result = new RentalCarTransportation(rentalPeriod, costPerDay, car);
                    result.setDescription(description);
                    askAboutUnlimitedMiles(result);
                }
            }
        }
        return result;
    }

    private void askAboutUnlimitedMiles(RentalCarTransportation rentalCar) {
        boolean saidYes = console.promptForYesNo("Does this rental include unlimited miles? ");
        if (!saidYes) {
            Integer milesAllowed = console.promptForInteger("How many miles are allowed per day? ");
            if (milesAllowed != null) {
                BigDecimal costPerMile = console.promptForBigDecimal("How much is charged per mile over the limit? $");
                if (costPerMile != null) {
                    rentalCar.setUnlimitedMiles(false);
                    rentalCar.setMilesAllowedPerDay(milesAllowed);
                    rentalCar.setCostPerExtraMile(costPerMile);
                }
            }
        }
    }

    private TicketedTransportation promptForTicketedTransportation(TravelDates vacationDates, TravelGroup passengers) {
        TicketedTransportation result = null;
        String description = console.promptForString("What kind of transportation will you take (Plane, Train, Bus, etc.)? ");
        if (!description.isBlank()) {
            BigDecimal adultPrice = console.promptForBigDecimal("How much is an adult ticket? $");
            if (adultPrice != null) {
                result = new TicketedTransportation(vacationDates, passengers, adultPrice);
                result.setDescription(description);
                askAboutChildAndSeniorCosts(result);
                askAboutRentalCar(result, vacationDates);
            }
        }
        return result;
    }

    private void askAboutChildAndSeniorCosts(ChildAndSeniorPriceable item) {
        boolean saidYes = console.promptForYesNo("Would you like to enter other prices for children and seniors? ");
        if (saidYes) {
            BigDecimal childPrice = console.promptForBigDecimal("What is the cost per child? $");
            if (childPrice != null) {
                item.setCostPerChild(childPrice);
            }
            BigDecimal seniorPrice = console.promptForBigDecimal("What is the cost per senior? $");
            if (seniorPrice != null) {
                item.setCostPerSenior(seniorPrice);
            }
        }
    }

    private void askAboutRentalCar(TicketedTransportation ticketedTransportation, TravelDates rentalPeriod) {
        boolean saidYes = console.promptForYesNo("Would you like to include a rental car at your destination? ");
        if (saidYes) {
            RentalCarTransportation rental = promptForRentalCar(rentalPeriod);
            if (rental != null) {
                ticketedTransportation.setRentalCar(rental);
            }
        }
    }

    public Activity promptForActivity(Vacation vacation) {
        Activity result = null;
        boolean finished = false;
        while (!finished) {
            String description = console.promptForString("Describe the activity (Beach, Amusement Park, Skiing, etc.): ");
            if (description.isBlank()) {
                finished = true;
            } else if (vacation.hasActivityWithDescription(description)) {
                console.printErrorMessage("An activity has already been added with that description.");
            } else {
                result = new Activity(vacation.getTravelers(), description);
                askAboutActivityCosts(result);
                finished = true;
            }
        }
        return result;
    }

    private void askAboutActivityCosts(Activity activity) {
        boolean hasPerPersonCost = console.promptForYesNo("Does this activity have a cost per person? ");
        if (hasPerPersonCost) {
            TravelGroup participants = activity.getParticipants();
            if (participants.getAdultCount() > 0) {
                BigDecimal adultCost = console.promptForBigDecimal("What is the cost per adult? $");
                if (adultCost != null) {
                    activity.setCostPerPerson(adultCost);
                    askAboutChildAndSeniorCosts(activity);
                }
            }
        }
        BigDecimal fixedCost = console.promptForBigDecimal("What fixed cost does this activity have? $");
        if (fixedCost != null) {
            activity.setFixedCost(fixedCost);
        }
    }

    public String promptToRemoveActivity(String[] descriptions) {
        String result = null;
        if (descriptions.length > 0) {
            console.printBanner("Select an Activity to remove");
            result = console.getMenuSelection(descriptions, true);
        } else {
            console.printErrorMessage("No activities to remove");
        }
        return result;
    }

}

package com.techelevator.vee;

import com.techelevator.util.BasicConsole;
import com.techelevator.util.BasicFileStorage;
import com.techelevator.util.exception.BasicXmlException;
import com.techelevator.util.exception.FileStorageException;
import com.techelevator.vee.model.*;
import com.techelevator.vee.model.transportation.Transportation;

/**
 * VacationExpenseEstimatorController is a class that the application creates a single
 * instance of to orchestrate all of its operations through a series of menus. It relies
 * on other classes for the details of interacting with the user and file system.
 *
 * (The term Controller comes from the Model-View-Controller pattern, which you'll learn
 * more about in module 2.)
 */

public class VacationExpenseEstimatorController {

    private final VacationExpenseEstimatorView view;
    private final VacationStorage storage;
    private Vacation currentVacation;

    public VacationExpenseEstimatorController(BasicConsole console, BasicFileStorage fileStorage) {
        view = new VacationExpenseEstimatorView(console);
        storage = new VacationStorage(fileStorage);
    }

    public void run() {
        displayStartingMenu();
    }

    private void displayStartingMenu() {
        final String NEW = "Create new Vacation";
        final String LOAD = "Load a saved Vacation";
        final String EXIT = "Exit the program";
        final String[] MENU_OPTIONS = {NEW, LOAD, EXIT};

        boolean finished = false;
        //Example: while loop
        while (!finished) {
            String selection =
                    view.getMenuSelection("Welcome to the Vacation Expense Estimator", MENU_OPTIONS);
            if (selection.equals(NEW)) {
                createNewVacation();
                displayVacationMenu();
            } else if (selection.equals(LOAD)) {
                loadExistingVacation();
                if (currentVacation != null) {
                    displayVacationMenu();
                }
            } else if (selection.equals(EXIT)) {
                finished = true;
            }
        }
    }

    private void createNewVacation() {
        currentVacation = new Vacation();
        while (currentVacation.getDestination() == null || currentVacation.getDestination().isBlank()) {
            view.promptToUpdateDestination(currentVacation);
        }
        while (currentVacation.getNumberOfDays() == 0) {
            view.promptToUpdateVacationDates(currentVacation);
        }
    }

    private void loadExistingVacation() {
        String filename = view.promptForFilename();
        if (!filename.isBlank()) {
            try {
                currentVacation = storage.readVacationFromFile(filename);
            } catch (FileStorageException e) {
                view.printErrorMessage(e.getMessage());
            }
        }
    }

    private void displayVacationMenu() {
        final String INFO = "Display vacation information";
        final String DESTINATION_AND_DATES = "Modify destination or travel dates";
        final String TRAVELERS = "Specify number of travelers";
        final String LODGING_MEALS_TRANSPORTATION = "Specify lodging, meals or transportation";
        final String ACTIVITIES = "Add or remove activities";
        final String REPORT = "Display expense report";
        final String SAVE = "Save this Vacation";
        final String DONE = "Done with this Vacation";
        final String[] MENU_OPTIONS = {INFO, DESTINATION_AND_DATES, TRAVELERS, LODGING_MEALS_TRANSPORTATION,
                                       ACTIVITIES, REPORT, SAVE, DONE};

        boolean finished = false;
        while (!finished) {
            String selection = view.getMenuSelection(currentVacation.toString(), MENU_OPTIONS);
            if (selection.equals(INFO)) {
                view.displayVacationInfo(currentVacation);
            } else if (selection.equals(DESTINATION_AND_DATES)) {
                view.promptToUpdateDestination(currentVacation);
                view.promptToUpdateVacationDates(currentVacation);
            } else if (selection.equals(TRAVELERS)) {
                view.promptToUpdateTravelers(currentVacation);
            } else if (selection.equals(LODGING_MEALS_TRANSPORTATION)) {
                displayLodgingMealsTransportationMenu();
            } else if (selection.equals(ACTIVITIES)) {
                displayActivitiesMenu();
            } else if (selection.equals(REPORT)) {
                displayExpenseReport();
            } else if (selection.equals(SAVE)) {
                saveVacation();
            } else if (selection.equals(DONE)) {
                finished = true;
            }
        }
    }

    private void displayExpenseReport() {
        view.displayExpenseSummary(currentVacation);
        boolean wantsDetails = view.promptForYesNo("Would you like the calculation details? ");
        if (wantsDetails) {
            view.displayExpenseDetails(currentVacation);
        }
    }

    private void saveVacation() {
        String filename = view.promptForFilename();
        if (!filename.isBlank()) {
            //Example: try with two catch blocks
            try {
                storage.writeVacationToFile(currentVacation, filename);
            } catch (BasicXmlException e) {
                view.printErrorMessage("Please make sure your vacation information doesn't contain < or > characters.");
            } catch (FileStorageException e) {
                view.printErrorMessage(e.getMessage());
            }
        }
    }

    private void displayLodgingMealsTransportationMenu() {
        final String LODGING = "Specify lodging";
        final String MEALS = "Specify meal information";
        final String TRANSPORTATION = "Specify transportation";
        final String BACK = "Return to previous menu";
        final String[] MENU_OPTIONS = {LODGING, MEALS, TRANSPORTATION, BACK};

        boolean finished = false;
        while (!finished) {
            String selection = view.getMenuSelection("Lodging, Meals, Transportation", MENU_OPTIONS);
            if (selection.equals(LODGING)) {
                Lodging lodging = view.promptForLodging(currentVacation.getDates());
                if (lodging != null) {
                    currentVacation.setLodging(lodging);
                }
            } else if (selection.equals(MEALS)) {
                MealPlan mealPlan = view.promptForMealPlan(currentVacation.getDates(),
                                                              currentVacation.getTravelers());
                if (mealPlan != null) {
                    currentVacation.setMealPlan(mealPlan);
                }
            } else if (selection.equals(TRANSPORTATION)) {
                Transportation transportation = view.promptForTransportation(currentVacation.getDates(),
                                                                                currentVacation.getTravelers());
                if (transportation != null) {
                    currentVacation.setTransportation(transportation);
                }
            } else if (selection.equals(BACK)) {
                finished = true;
            }
        }
    }

    private void displayActivitiesMenu() {
        final String LIST = "List all Activities";
        final String ADD = "Add an Activity";
        final String REMOVE = "Remove an Activity";
        final String BACK = "Return to previous menu";
        final String[] MENU_OPTIONS = {LIST, ADD, REMOVE, BACK};

        boolean finished = false;
        while (!finished) {
            String selection = view.getMenuSelection("Activities", MENU_OPTIONS);
            if (selection.equals(LIST)) {
                view.displayActivityDescriptions(currentVacation.getActivityDescriptions());
            } else if (selection.equals(ADD)) {
                Activity activity = view.promptForActivity(currentVacation);
                if (activity != null) {
                    currentVacation.addActivity(activity);
                }
            } else if (selection.equals(REMOVE)) {
                String description = view.promptToRemoveActivity(currentVacation.getActivityDescriptions());
                if (description != null) {
                    currentVacation.removeActivity(description);
                }
            } else if (selection.equals(BACK)) {
                finished = true;
            }
        }
    }

}

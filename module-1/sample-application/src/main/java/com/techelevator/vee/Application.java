package com.techelevator.vee;

import com.techelevator.util.SystemInOutConsole;
import com.techelevator.util.TextFileStorage;

/**
 * Application is the class that launches the Vacation Expense Estimator by creating
 * the objects needed to interact with the user and file system and passing them to
 * the application's controller object.
 */

public class Application {

    public static void main(String[] args) {
        SystemInOutConsole systemInOutConsole = new SystemInOutConsole();
        TextFileStorage textFileStorage = new TextFileStorage();
        VacationExpenseEstimatorController controller =
                new VacationExpenseEstimatorController(systemInOutConsole, textFileStorage);

        controller.run();
    }

}

package com.petelevator;

import com.petelevator.util.SystemInOutConsole;

/**
 * Application is the class that launches the Pet Elevator HR module by creating
 * the objects needed to interact with the user and passing them to
 * the application's controller object.
 */

public class Application {

    public static void main(String[] args) {
        SystemInOutConsole systemInOutConsole = new SystemInOutConsole();
        HRController controller =
                new HRController(systemInOutConsole);

        controller.run();
    }

}

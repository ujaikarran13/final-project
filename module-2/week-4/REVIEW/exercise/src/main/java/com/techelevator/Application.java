package com.techelevator;

import com.techelevator.util.SystemInOutConsole;

public class Application {

    public static void main(String[] args) {
        try {
            SystemInOutConsole systemInOutConsole = new SystemInOutConsole();
            ApplicationController controller = new ApplicationController(systemInOutConsole);
            controller.run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

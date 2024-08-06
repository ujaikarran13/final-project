package com.petelevator;

import com.petelevator.model.hr.Department;
import com.petelevator.model.hr.Employee;
import com.petelevator.model.hr.Manager;
import com.petelevator.model.hr.UnqualifiedDepartmentHeadException;
import com.petelevator.util.BasicConsole;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * HRController is a class that the application creates a single
 * instance of to orchestrate all of its HR operations through a series of menus. It relies
 * on other classes for the details of interacting with the user and file system.
 *
 * (The term Controller comes from the Model-View-Controller pattern, which you'll learn
 * more about in module 2.)
 */

public class HRController {

    private final HRView view;

    //********** Data Model - built here because we have no database yet ***************
    static private List<Department> departments = null;

    // User chooses what department to work in
    private Department selectedDepartment = null;

    public HRController(BasicConsole console) {
        view = new HRView(console);
        if (departments == null) {
            initializeData();
        }
    }

    /**
     * This method is called to begin the main menu loop. It's usually called from the application's
     * entry-point method (e.g., main)
     */
    public void run() {
        displayHRMenu();
    }

    /**
     * The main menu loop
     */
    public void displayHRMenu() {
        final String SELECT = "Select a department";
        final String EXIT = "Exit the program";
        final String[] MENU_OPTIONS = {SELECT, EXIT};

        boolean finished = false;
        while (!finished) {
            if (selectedDepartment != null) {
                displayDepartmentMenu();
            } else {
                String selection =
                        view.getMenuSelection("Welcome to the HR Module", MENU_OPTIONS);
                if (selection.equals(SELECT)) {
                    // Present a menu that lists all the departments and allow the user to choose one
                    selectedDepartment = view.getDepartmentSelection((departments.toArray(new Department[0])));
                } else if (selection.equals(EXIT)) {
                    finished = true;
                }
            }
        }
    }

    /**
     * After the user chooses a department to work in, this is the main menu within the department.
     */
    public void displayDepartmentMenu() {
        final String INFO = "Display department information";
        final String LIST_EMPLOYEES = "List employees";
        final String HIRE = "Hire an employee";
        final String TRANSFER = "Transfer an employee";
        final String RAISE = "Give a raise";
        final String DEPT_HEAD = "Set new dept head";
        final String DONE = "Back to Main menu";
        final String[] MENU_OPTIONS = {INFO, LIST_EMPLOYEES, HIRE, TRANSFER, RAISE, DEPT_HEAD, DONE};

        boolean finished = false;
        while (!finished) {
            String selection = view.getMenuSelection(selectedDepartment.toString() + " Menu", MENU_OPTIONS);
            // React to the user's menu selection
            switch (selection) {
                case INFO:
                    view.displayDepartmentInfo(selectedDepartment);
                    break;
                case LIST_EMPLOYEES:
                    view.listEmployees(selectedDepartment.getDepartmentEmployees());
                    break;
                case HIRE:
                    hireEmployee();
                    break;
                case TRANSFER:
                    transferEmployeeOut();
                    break;
                case RAISE:
                    awardRaise();
                    break;
                case DEPT_HEAD:
                    setDepartmentHead();
                    break;
                case DONE:
                    selectedDepartment = null;
                    finished = true;
                    break;
            }
        }
    }

    /**
     * User selected "Hire an employee". Prompt for information and call the method on Manager
     */
    private void hireEmployee() {
        // Prompt the user for employee information. Even though not yet hired, the Employee class is
        // used to collect all the information for convenience.
        Employee person = view.promptForNewEmployee();
        if (person == null) {
            view.printErrorMessage("Hiring process was canceled by the user.");
            return;
        }

        // The department head is who actually does the hiring
        Employee employee = selectedDepartment.getDepartmentHead().hireEmployee(
                person.getFirstName(), person.getLastName(), person.getTitle(), person.getSalary()
        );

        view.printMessage(employee.getFullName() + " has been hired.");
    }

    /**
     * User selected "Transfer an employee". Prompt for information and call the method on Department
     */
    private void transferEmployeeOut() {
        // First allow the user to select an employee
        Employee selectedEmployee = view.getEmployeeSelection(selectedDepartment.getDepartmentEmployees().toArray(new Employee[0]));
        if (selectedEmployee == null) {
            // User cancelled
            view.printErrorMessage("Transfer was canceled by the user.");
            return;
        }

        // Then prompt the user for the department to ship the employee off to.
        // Create a list of departments that *doesn't* include this department, since
        // we can't transfer an employee out of and into the same department.
        if (departments.size() < 2) {
            view.printErrorMessage("There is no other department the employee can be transferred to!");
            return;
        }
        Department[] otherDepartments = new Department[departments.size() - 1];
        int ix = 0;
        for (Department d : departments) {
            // Note: We can use == here because we are checking whether they are the exact same object reference
            if (d != selectedDepartment) {
                otherDepartments[ix] = d;
                ix++;
            }
        }

        Department newDepartment = view.getDepartmentSelection(otherDepartments);

        // Make the move
        if (newDepartment == null) {
            // User cancelled
            view.printErrorMessage("Transfer was canceled by the user.");
            return;
        }

        newDepartment.transferEmployeeIn(selectedEmployee);

        view.printMessage(selectedEmployee.getFullName() + " was transferred to department " + selectedEmployee.getDepartment().getDepartmentName() + ".");
    }

    /**
     * User selected "Give a raise". Prompt for information and call the method on Employee
     */
    private void awardRaise() {
        // First allow the user to select an employee
        Employee selectedEmployee = view.getEmployeeSelection(selectedDepartment.getDepartmentEmployees().toArray(new Employee[0]));
        if (selectedEmployee == null) {
            // User cancelled
            view.printErrorMessage("Raise process was canceled by the user.");
            return;
        }
        Double amount = view.promptForRaiseAmount();
        if (amount == null) {
            // User cancelled
            view.printErrorMessage("Raise process was canceled by the user.");
            return;
        }

        // TODO Step 3: Since raiseSalary may throw an exception, enclose the code in a try block and report any exception to the user.
        try {
            BigDecimal oldSalary = selectedEmployee.getSalary();
            selectedEmployee.raiseSalary(amount);
            view.printMessage(selectedEmployee.getFullName() + " got a raise from " + oldSalary + " to " + selectedEmployee.getSalary() + ".");
        } catch (Exception e) {
            view.printErrorMessage("There was an error raising the employee's salary: " + e.getMessage());
        }
    }

    /**
     * User selected "Set a new department head". Prompt for information, and then call the method on Department
     */
    private void setDepartmentHead() {
        // TODO Step 5c: try and catch the new custom exception
        Employee person = view.promptForNewEmployee();
        if (person == null) {
            view.printErrorMessage("Set dept head process was canceled by the user.");
            return;
        }
        Manager newManager = new Manager(person.getFirstName(), person.getLastName(), person.getTitle(), person.getSalary());
        try {
            selectedDepartment.setDepartmentHead(newManager);
            view.printMessage(newManager.getFullName() + " has been added as head of " + selectedDepartment.getDepartmentName());
        } catch (UnqualifiedDepartmentHeadException e) {
            view.printErrorMessage(newManager.getFullName() + " could not be added: " + e.getMessage());
        }
    }

    /************************** Sample Data ***************************
     * Since we don't have any database or data storage, the data is hard-coded
     * here. In a more full-featured application, the departments and people will be
     * read from a database.
     */
    private void initializeData() {
        try {
            departments = new ArrayList<>();

            //region Starting Model Data...
            // ******************************************************
            // Engineering
            Department dept = new Department(1, "Engineering");
            dept.setDepartmentHead(
                    new Manager(1, "Josephine", "Darakjy", "Director of Engineering", new BigDecimal("150000"), dept)
            );

            dept.setDepartmentEmployees(
                    new ArrayList<>(
                            Arrays.asList(
                                    new Employee(2, "Art", "Venere", "Principal Engineer", new BigDecimal("160000"), dept),
                                    new Employee(3, "Lenna", "Paprocki", "Senior Engineer", new BigDecimal("120000"), dept),
                                    new Employee(4, "Donette", "Foller", "Railroad Engineer", new BigDecimal("80000"), dept),
                                    new Employee(5, "Simona", "Morasca", "Intern", new BigDecimal("40000"), dept)
                            )
                    )
            );
            departments.add(dept);

            // ******************************************************
            // Marketing
            dept = new Department(2, "Marketing");
            dept.setDepartmentHead(
                    new Manager(6, "Mitsue", "Tollner", "Manager of Marketing", new BigDecimal("110000"), dept)
            );

            dept.setDepartmentEmployees(
                    new ArrayList<>(
                            Arrays.asList(
                                    new Employee(7, "Leota", "Dilliard", "Chief Designer", new BigDecimal("100000"), dept),
                                    new Employee(8, "Sage", "Wieser", "Marketing Specialist", new BigDecimal("75000"), dept),
                                    new Employee(9, "Kris", "Marrier", "Advertising Lead", new BigDecimal("70000"), dept),
                                    new Employee(10, "Minna", "Amigon", "Intern", new BigDecimal("0"), dept)
                            )
                    )
            );
            departments.add(dept);

            // ******************************************************
            // Sales
            dept = new Department(3, "Sales");
            dept.setDepartmentHead(
                    new Manager(11, "", "", "Director of Sales", new BigDecimal("150000"), dept)
            );

            dept.setDepartmentEmployees(
                    new ArrayList<>(
                            Arrays.asList(
                                    new Employee(12, "Abel", "Maclead", "Lead Account Executive", new BigDecimal("99000"), dept),
                                    new Employee(13, "Kiley", "Caldarera", "Sales Operations Lead", new BigDecimal("112000"), dept),
                                    new Employee(14, "Graciela", "Ruta", "Account Executive", new BigDecimal("89000"), dept),
                                    new Employee(15, "Cammy", "Albares", "Telesales Executive", new BigDecimal("45000"), dept)
                            )
                    )
            );
            departments.add(dept);
            //endregion
        } catch (Exception e) {
            view.printErrorMessage("There was an error initializing data: " + e.getMessage());
        }
    }

}

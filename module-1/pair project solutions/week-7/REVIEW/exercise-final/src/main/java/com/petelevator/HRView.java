package com.petelevator;

import com.petelevator.model.hr.Department;
import com.petelevator.model.hr.Employee;
import com.petelevator.util.BasicConsole;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;

/**
 * HRView is a class that the HRController uses
 * for gathering information from the user and presenting information to
 * the user. It requires an object that implements the BasicConsole interface to handle the
 * mechanics of reading from and writing to the console.
 *
 * (The term View comes from the Model-View-Controller pattern, which you'll learn
 * more about in module 2.)
 */

public class HRView {

    private final BasicConsole console;

    public HRView(BasicConsole console) {
        this.console = console;
    }

    public void printErrorMessage(String message) {
        console.printErrorMessage(message);
    }
    public void printMessage(String message) {
        console.printMessage(message);
    }

    public String getMenuSelection(String menuTitle, String[] options) {
        console.printBanner(menuTitle);
        return console.getMenuSelection(options);
    }

    /**
     * Given an array of departments, present a menu to the user to select one.
     * @param departments An array of departments to select from
     * @return null if the user cancels selection, otherwise, the selected department.
     */
    public Department getDepartmentSelection(Department[] departments) {
        console.printBanner("Departments:");
        String[] options = new String[departments.length];
        for (int i = 0; i < departments.length; i++) {
            options[i] = departments[i].getDepartmentName();
        }

        Integer selectedIndex = console.getMenuSelectionIndex(options, true);
        return selectedIndex == null ? null : departments[selectedIndex];
    }

    /**
     * Present on the console information about the given department.
     * @param department The department to show information for
     */
    public void displayDepartmentInfo(Department department) {
        console.printBanner(department.getDepartmentName() + " Department Information");
        console.printMessage("\tId: " + department.getDepartmentId());
        console.printMessage("\tDept. head: " + department.getDepartmentHead().getFullName());
        console.printMessage("\tNumber of employees: " + department.getDepartmentEmployees().size());
        console.printMessage("");
    }

    /**
     * Present on the console a formatted list of employees
     * @param employees A List of employees to show.
     */
    public void listEmployees(List<Employee> employees) {
        String heading1 = "  Id Name                     Title                          Salary";
        String heading2 = "==== ======================== ======================== ============";
        String formatString = "%4d %-24s %-24s %12s";
        NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();
        console.printMessage(heading1);
        console.printMessage(heading2);
        for (Employee employee : employees) {
            String s = String.format(formatString, employee.getEmployeeId(), employee.getFullName(), employee.getTitle(), moneyFormat.format(employee.getSalary()));
            console.printMessage(s);
        }
    }

    /***
     * Prompt the user for information for a new person: first & last name, title and salary, to be used
     * in the hiring process and the set department head process.
     * @return An Employee object with names, title and salary information only. If the user cancels by pressing Enter without
     * entering any information, null is returned.
     */
    public Employee promptForNewEmployee() {
        // Prompt for employee information: first, last, title, salary
        String firstName = console.promptForString("First name: ");
        if (firstName.isBlank()) {
            return null;
        }
        String lastName = console.promptForString("Last name: ");
        if (lastName.isBlank()) {
            return null;
        }
        String title = console.promptForString("Title: ");
        BigDecimal salary = console.promptForBigDecimal("Salary: ");
        Employee person = new Employee(firstName, lastName, title, salary);
        return person;
    }

    /**
     *     Prompt the user for a percentage raise to give. The raiseSalary method Employee class
     *     expects that value to be positive only - you can't use it to decrement an employee's salary.
     * @return A number representing percentage. A 10.5% raise would be returned as 10.5
     */
    public Double promptForRaiseAmount() {
        // TODO Step 4: Practice "defensive coding" by making sure the user only enters a positive raise value
        Double percentage = 0.0;
        while (true) {
            percentage = console.promptForDouble("Enter the percentage increase (e.g. '5' for 5%): ");
            if (percentage == null) {
                return null;
            } else if (percentage > 0.0) {
                return percentage;
            }
            printErrorMessage("Percentage must be positive");
        }
    }


    /**
     * Given an array of employees, present a menu to the user to select one.
     * @param employees An array of employees to select from
     * @return null if the user cancels selection, otherwise, the selected employee.
     */
    public Employee getEmployeeSelection(Employee[] employees) {
        console.printBanner("Employees:");
        String[] options = new String[employees.length];
        for (int i = 0; i < employees.length; i++) {
            options[i] = employees[i].getFullName();
        }

        Integer selectedIndex = console.getMenuSelectionIndex(options, true);
        return selectedIndex == null ? null : employees[selectedIndex];
    }
}

package com.techelevator;


public class Employee {

    private int employeeID;
    private String firstName;
    private String lastName;
    private String department;
    private double annualSalary;

    public Employee(int employeeID, String firstName, String lastName, double annualSalary) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.annualSalary = annualSalary;
    }

    public Employee() {
        this.employeeID = 0;
        this.firstName = "";
        this.lastName = "";
        this.annualSalary = 0;
    }

    public int getEmployeeID() {
        return employeeID;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getAnnualSalary() {
        return annualSalary;
    }


    public String getFullName() {
        return lastName + "," + firstName;
    }

    public void raiseSalary(double percent) {
        if (percent > 0) {
            double raiseIncrease = annualSalary * (percent / 100);
            annualSalary += raiseIncrease;
        }
    }
}




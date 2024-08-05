package com.petelevator.hr;

import java.math.BigDecimal;

public class Employee {

    private String firstName;
    private String lastName;
    private String email;
    private int employeeId;
    private String title;
    private Department department;
    private BigDecimal salary;

    public Employee(String firstName, String lastName) {
        this(firstName, lastName, "", BigDecimal.ZERO);
    }

    public Employee(String firstName, String lastName, String title, BigDecimal salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.salary = salary;
    }

    public String getFullName() {
        return this.getLastName() + ", " + this.getFirstName();
    }

    public void raiseSalary(double percentage) {
        if (percentage > 0) {
            double newSalary = salary.doubleValue() + (salary.doubleValue() * percentage / 100);
            salary = new BigDecimal(newSalary);
        }
    }


    // getters and setters


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

}

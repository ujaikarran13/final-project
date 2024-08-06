package com.petelevator.model.hr;

import com.petelevator.model.Billable;
import com.petelevator.model.Person;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class Employee extends Person implements Billable {

    private int employeeId;
    private String title;
    private Department department;
    private BigDecimal salary;

    public Employee(String firstName, String lastName) {
        this(firstName, lastName, "", BigDecimal.ZERO);
    }

    public Employee(String firstName, String lastName, String title, BigDecimal salary) {
        super(firstName, lastName);
        this.title = title;
        this.salary = salary;
    }

    public Employee(int employeeId, String firstName, String lastName, String title, BigDecimal salary, Department department) {
        this(firstName, lastName, title, salary);
        this.employeeId = employeeId;
        this.department = department;
    }

    @Override
    public String getFullName() {
        return this.getLastName() + ", " + this.getFirstName();
    }

    public void raiseSalary(double percentage) {
        if (percentage <= 0) {
            throw new IllegalArgumentException("Percentage must be a positive value.");
        }
        // Added the .setScale(2, RoundingMode.HALF_UP) because the scale should be kept at 2.
        // Testing will fail if the scale is wrong.
        double newSalary = salary.doubleValue() + (salary.doubleValue() * percentage / 100);
        salary = new BigDecimal(newSalary).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getBalanceDue(Map<String, BigDecimal> servicesRendered) {
        BigDecimal owes = BigDecimal.ZERO;
        for (Map.Entry<String, BigDecimal> sr : servicesRendered.entrySet()) {
            if (sr.getKey().equals("Walking")) {
                BigDecimal discountedRate = sr.getValue().divide(new BigDecimal("2"));
                owes = owes.add(discountedRate);
            } else {
                owes = owes.add(sr.getValue());
            }
        }
        return owes;
    }

    // getters and setters

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

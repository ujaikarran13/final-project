package com.techelevator;

import java.math.BigDecimal;

public class Employee {

    public static final BigDecimal DEFAULT_STARTING_SALARY = new BigDecimal("60000.00");

    private long employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private BigDecimal salary;
    private Department department;
    private String hireDate;

    public Employee() {
    }

    public Employee(long employeeId, String firstName, String lastName, String email, Department department, String hireDate) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.department = department;
        this.hireDate = hireDate;
        this.salary = DEFAULT_STARTING_SALARY;
    }


    public String getFullName() {
        return this.lastName + ", " + this.getFirstName();
    }

    public void raiseSalary(double percent) {
        BigDecimal raise = salary.multiply(new BigDecimal(Double.toString(percent))).divide(new BigDecimal("100"));
        this.salary = salary.add(raise);
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

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

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }


}

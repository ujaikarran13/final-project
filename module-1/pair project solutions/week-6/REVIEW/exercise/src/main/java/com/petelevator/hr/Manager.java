package com.petelevator.hr;

import java.math.BigDecimal;

public class Manager {

    private String firstName;
    private String lastName;
    private String email;
    private int employeeId;
    private String title;
    private Department department;
    private BigDecimal salary;

    public Manager(String firstName, String lastName) {
        this(firstName, lastName, "", BigDecimal.ZERO);
    }

    public Manager(String firstName, String lastName, String title, BigDecimal salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.salary = salary;
    }

    public Employee hireEmployee(String first, String last, String title, BigDecimal salary) {
        Employee newEmployee = new Employee(first, last, title, salary);
        newEmployee.setEmail( first.substring(0, 1).toLowerCase() + last.toLowerCase() + "@petelevator.com");
        newEmployee.setDepartment(this.getDepartment());
        newEmployee.getDepartment().getDepartmentEmployees().add(newEmployee);

        return newEmployee;
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

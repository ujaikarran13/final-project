package com.petelevator.hr;

import java.math.BigDecimal;

public class Manager extends Employee {

    public Manager(String firstName, String lastName) {
        this(firstName, lastName, "", BigDecimal.ZERO);
    }

    public Manager(String firstName, String lastName, String title, BigDecimal salary) {
        super(firstName, lastName, title, salary);
    }

    public Employee hireEmployee(String first, String last, String title, BigDecimal salary) {
        Employee newEmployee = new Employee(first, last, title, salary);
        newEmployee.setEmail( first.substring(0, 1).toLowerCase() + last.toLowerCase() + "@petelevator.com");
        newEmployee.setDepartment(this.getDepartment());
        newEmployee.getDepartment().getDepartmentEmployees().add(newEmployee);

        return newEmployee;
    }
}

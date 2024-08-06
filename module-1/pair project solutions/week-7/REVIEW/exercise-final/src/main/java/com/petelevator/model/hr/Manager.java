package com.petelevator.model.hr;

import java.math.BigDecimal;

public class Manager extends Employee {

    public Manager(String firstName, String lastName) {
        this(firstName, lastName, "", BigDecimal.ZERO);
    }

    public Manager(String firstName, String lastName, String title, BigDecimal salary) {
        super(firstName, lastName, title, salary);
    }

    public Manager(int employeeId, String firstName, String lastName, String title, BigDecimal salary, Department department) {
        super(employeeId, firstName, lastName, title, salary, department);
    }

    public Employee hireEmployee(String first, String last, String title, BigDecimal salary) {
        Employee newEmployee = new Employee(first, last, title, salary);
        newEmployee.setEmail( first.substring(0, 1).toLowerCase() + last.toLowerCase() + "@petelevator.com");
        newEmployee.setDepartment(this.getDepartment());
        newEmployee.setEmployeeId(generateEmployeeId());
        newEmployee.getDepartment().getDepartmentEmployees().add(newEmployee);

        return newEmployee;
    }

    private int generateEmployeeId() {
        // Normally a database will assign a unique id. We'll just generate a random number, between 100 and 9999
        return (int)(100 + (9900 * Math.random()));
    }
}

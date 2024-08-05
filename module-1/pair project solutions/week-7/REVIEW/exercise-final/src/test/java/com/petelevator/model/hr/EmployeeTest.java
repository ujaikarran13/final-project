package com.petelevator.model.hr;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EmployeeTest {

    @Test
    public void employeeConstructor_shouldSetProperties() {
        // Arrange
        Department department = new Department(100, "Test");
        // Act
        Employee employee = new Employee(101, "Bat",
                "Masterson", "Sheriff", new BigDecimal("10000"),
                department);
        // Assert
        assertEquals(101, employee.getEmployeeId());
        assertEquals(100, employee.getDepartment().getDepartmentId());
        assertEquals("Bat", employee.getFirstName());
        assertNotNull("Masterson", employee.getLastName());
        assertEquals("Sheriff", employee.getTitle());
    }

    @Test
    public void getFullName_shouldReturnCorrectFormat() {
        // Arrange
        Employee employee = new Employee("Test", "Testerson");
        // Act
        String fullName = employee.getFullName();
        // Assert
        assertEquals("The employee full name is not in the correct format.", "Testerson, Test", fullName);
    }

    @Test
    public void raiseSalaryTest_Positive_shouldRaiseSalary() {
        // Arrange
        BigDecimal startingSalary = new BigDecimal("100.00");
        Employee employee = new Employee("Test", "Testerson");
        employee.setSalary(startingSalary);
        // Act
        employee.raiseSalary(5);
        // Assert
        assertEquals("The employee raise of 5% was not computed correctly.", new BigDecimal("105.00"), employee.getSalary());
    }

    @Test(expected = IllegalArgumentException.class)
    public void raiseSalaryTest_Negative_shouldThrowIllegalArgumentException() {
        // Arrange
        BigDecimal startingSalary = new BigDecimal("100.00");
        Employee employee = new Employee("Test", "Testerson");
        employee.setSalary(startingSalary);
        // Act
        employee.raiseSalary(-10); //"raise" by negative 10%
        // Assert - nothing to assert, the method should have thrown.
    }

    @Test
    public void getBalanceDue_shouldCalculateServices() {
        // Arrange
        Employee employee = new Employee("Test", "Testerson");
        Map<String,BigDecimal> services = new HashMap<>();
        services.put("Grooming", new BigDecimal("45.00"));
        services.put("Sitting", new BigDecimal("120.00"));
        services.put("Walking", new BigDecimal("37.50"));
        // Act
        BigDecimal due = employee.getBalanceDue(services);
        // Assert - nothing to assert, the method should have thrown.
        assertEquals("The balance for services rendered is not correct.",new BigDecimal("183.75"), due);
    }

}

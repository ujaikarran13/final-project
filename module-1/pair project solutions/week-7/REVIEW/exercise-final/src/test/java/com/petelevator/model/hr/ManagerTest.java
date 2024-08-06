package com.petelevator.model.hr;
import org.junit.Test;
import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ManagerTest {

    @Test
    public void managerConstructor_shouldSetProperties() {
        // Arrange
        Department department = new Department(100, "Test");
        // Act
        Manager manager = new Manager(101, "Bat",
                "Masterson", "Manager of people", new BigDecimal("10000"),
                department);
        // Assert
        assertEquals(101, manager.getEmployeeId());
        assertEquals(100, manager.getDepartment().getDepartmentId());
        assertEquals("Bat", manager.getFirstName());
        assertNotNull("Masterson", manager.getLastName());
        assertEquals("Manager of people", manager.getTitle());
    }

    @Test
    public void hireEmployee_shouldSetHireEmployee() {
        // Arrange
        Manager manager = new Manager("Manager", "Managerson");
        Department department = new Department(1, "Test Department");
        manager.setDepartment(department);
        // Act
        Employee employee = manager.hireEmployee("Test", "Testerson", "Tester of testing", new BigDecimal("50000.00"));
        // Assert
        assertEquals("Employee first name does not equal expected result.","Test", employee.getFirstName());
        assertEquals("Employee last name does not equal expected result.","Testerson", employee.getLastName());
        assertEquals("Employee title does not equal expected result.","Tester of testing", employee.getTitle());
        assertEquals("Employee salary does not equal expected result.",new BigDecimal("50000.00"), employee.getSalary());
        assertEquals("Employee email does not equal expected result.","ttesterson@petelevator.com", employee.getEmail());
        assertEquals("Employee department does not equal expected result.",department, employee.getDepartment());
        assertTrue("Test Department does not include the new employee",department.getDepartmentEmployees().contains(employee));
    }
}

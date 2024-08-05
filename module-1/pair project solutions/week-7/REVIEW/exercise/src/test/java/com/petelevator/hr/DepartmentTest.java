package com.petelevator.hr;

import com.petelevator.model.hr.Department;
import com.petelevator.model.hr.Employee;
import com.petelevator.model.hr.Manager;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotEquals;

public class DepartmentTest {

    @Test
    public void departmentConstructor_shouldSetProperties() {
        // Arrange
        String departmentName = "Test Department";
        // Act
        Department department = new Department(100, departmentName);
        // Assert
        assertEquals(100, department.getDepartmentId());
        assertEquals(departmentName, department.getDepartmentName());
        assertNotNull(department.getDepartmentEmployees());
        assertEquals(0, department.getDepartmentEmployees().size());
    }
}

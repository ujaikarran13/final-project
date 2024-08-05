package com.petelevator.model.hr;
import org.junit.Test;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.Assert.*;

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

    @Test
    public void transferEmployeeIn_shouldTransferEmployee() {

        // Arrange
        Department oldDept = new Department(1, "Old Department");
        Department newDept = new Department(2, "New Department");
        Employee employee = new Employee("Test", "Test");
        employee.setDepartment(oldDept);

        // Act
        newDept.transferEmployeeIn(employee);

        // Assert
        List<Employee> oldDeptEmployees = oldDept.getDepartmentEmployees();
        List<Employee> newDeptEmployees = newDept.getDepartmentEmployees();
        assertEquals("Old Department Employees size is not equal to 0", 0, oldDeptEmployees.size());
        assertEquals("New Department Employees size is not equal to 1", 1, newDeptEmployees.size());
        assertTrue("New Department Employees does not contain employee", newDeptEmployees.contains(employee));
    }

    @Test
    public void setDepartmentHead_TestManager_shouldSetHead() {
        try {
            // Arrange
            Department department = new Department(1, "Test Department");
            Manager manager = new Manager("Manager", "Managerson", "Manager of Testing", new BigDecimal("100000"));
            // Act
            department.setDepartmentHead(manager);
            // Assert
            assertEquals("Manager is not equal to the department head.", manager, department.getDepartmentHead());
        } catch (Exception ex) {
            fail("Exception caught while testing setDepartmentHead: " + ex.getMessage());
        }
    }

    @Test
    public void setDepartmentHead_TestDirector_shouldSetHead() {
        try {
            // Arrange
            Department department = new Department(1, "Test Department");
            Manager manager = new Manager("Manager", "Managerson", "Director of Testing", new BigDecimal("100000"));
            // Act
            department.setDepartmentHead(manager);
            // Assert
            assertEquals("Manager is not equal to the department head.", manager, department.getDepartmentHead());
        } catch (Exception ex) {
            fail("Exception caught while testing setDepartmentHead: " + ex.getMessage());
        }
    }

    @Test(expected = UnqualifiedDepartmentHeadException.class)
    public void setDepartmentHead_BadTitle_shouldThrowUnqualifiedDepartmentHeadException() throws UnqualifiedDepartmentHeadException{
        // Arrange
        Department department = new Department(1, "Test Department");
        Manager manager = new Manager("Manager", "Managerson", "Head of Testing", new BigDecimal("100000"));
        // Act
        department.setDepartmentHead(manager);
        // Assert
        // Nothing to assert. If the method throws, test passes
    }

    @Test
    public void setDepartmentHead_BadTitle2_shouldThrowUnqualifiedDepartmentHeadException() {
        // Arrange
        Department department = new Department(1, "Test Department");
        Manager manager = new Manager("Manager", "Managerson", "Head of Testing", new BigDecimal("100000"));
        try {
            // Act
            department.setDepartmentHead(manager);
            // Assert - If program flow gets to here, the exception was not thrown
            fail("Manager title does not start with Manager or Director so UnqualifiedDepartmentHeadException is expected.");
        } catch (UnqualifiedDepartmentHeadException e) {
            // Assert - Test passes because the exception was thrown
        }
    }
}

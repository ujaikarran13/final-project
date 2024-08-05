package com.petelevator.hr;

import com.petelevator.util.SafeReflection;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.lang.reflect.*;
import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ManagerTest {

    private static Class managerClass;

    private static boolean isWellFormed = false;

    @Test
    public void test01_ClassWellFormed() throws NullPointerException {
        try {

            // Assert Manager class exists
            managerClass = Class.forName("com.petelevator.hr.Manager");

            // Assert Manager extends Employee
            Class superclass = managerClass.getSuperclass();
            assertEquals("Manager must extend Employee.", superclass.getName(),"com.petelevator.hr.Employee");

            // Assert fields DON'T exist, since they are members of Person and shouldn't be in Manager anymore
            Field firstNameField = SafeReflection.getDeclaredField(managerClass, "firstName");
            assertNull("Manager must not have the field firstName, it must inherit it from Person.", firstNameField);

            Field lastNameField = SafeReflection.getDeclaredField(managerClass, "lastName");
            assertNull("Manager must not have the field lastName, it must inherit it from Person.", lastNameField);

            Field emailField = SafeReflection.getDeclaredField(managerClass, "email");
            assertNull("Manager must not have the field email, it must inherit it from Person.", emailField);

            // Assert fields DON'T exist, since they are members of Employee and shouldn't be in Manager anymore
            Field employeeIdField = SafeReflection.getDeclaredField(managerClass, "employeeId");
            assertNull("Manager must not have the field employeeId, it must inherit it from Employee.", employeeIdField);

            Field titleField = SafeReflection.getDeclaredField(managerClass, "title");
            assertNull("Manager must not have the field title, it must inherit it from Employee.", titleField);

            Field departmentField = SafeReflection.getDeclaredField(managerClass, "department");
            assertNull("Manager must not have the field department, it must inherit it from Employee.", departmentField);

            Field salaryField = SafeReflection.getDeclaredField(managerClass, "salary");
            assertNull("Manager must not have the field salary, it must inherit it from Employee.", salaryField);

            // Assert getters and setters for fields DON'T exist in Manager, since they are members of Person and shouldn't be in Manager anymore
            Method firstNameGetterManager = SafeReflection.getMethod(managerClass, "getFirstName");
            Method firstNameGetterPerson = SafeReflection.getMethod(superclass.getSuperclass(), "getFirstName");
            assertEquals("Manager must not have a getter getFirstName().", firstNameGetterPerson, firstNameGetterManager);
            Method firstNameSetterManager = SafeReflection.getMethod(managerClass, "setFirstName", String.class);
            Method firstNameSetterPerson = SafeReflection.getMethod(superclass.getSuperclass(), "setFirstName", String.class);
            assertEquals("Manager must not have a setter setFirstName(String).", firstNameSetterPerson, firstNameSetterManager);

            Method lastNameGetterManager = SafeReflection.getMethod(managerClass, "getLastName");
            Method lastNameGetterPerson = SafeReflection.getMethod(superclass.getSuperclass(), "getLastName");
            assertEquals("Manager must not have a getter getLastName().", lastNameGetterPerson, lastNameGetterManager);
            Method lastNameSetterManager = SafeReflection.getMethod(managerClass, "setLastName", String.class);
            Method lastNameSetterPerson = SafeReflection.getMethod(superclass.getSuperclass(), "setLastName", String.class);
            assertEquals("Manager must not have a setter setLastName(String).", lastNameSetterPerson, lastNameSetterManager);

            Method emailGetterManager = SafeReflection.getMethod(managerClass, "getEmail");
            Method emailGetterPerson = SafeReflection.getMethod(superclass.getSuperclass(), "getEmail");
            assertEquals("Manager must not have a getter getEmail().", emailGetterPerson, emailGetterManager);
            Method emailSetterManager = SafeReflection.getMethod(managerClass, "setEmail", String.class);
            Method emailSetterPerson = SafeReflection.getMethod(superclass.getSuperclass(), "setEmail", String.class);
            assertEquals("Manager must not have a setter setEmail(String).", emailSetterPerson, emailSetterManager);

            // Assert getters and setters for fields DON'T exist in Manager, since they are members of Employee and shouldn't be in Manager anymore
            Method employeeIdGetterManager = SafeReflection.getMethod(managerClass, "getEmployeeId");
            Method employeeIdGetterEmployee = SafeReflection.getMethod(superclass, "getEmployeeId");
            assertEquals("Manager must not have a getter getEmployeeId().", employeeIdGetterEmployee, employeeIdGetterManager);
            Method employeeIdSetterManager = SafeReflection.getMethod(managerClass, "setEmployeeId", int.class);
            Method employeeIdSetterEmployee = SafeReflection.getMethod(superclass, "setEmployeeId", int.class);
            assertEquals("Manager must not have a setter setEmployeeId(String).", employeeIdSetterEmployee, employeeIdSetterManager);

            Method titleGetterManager = SafeReflection.getMethod(managerClass, "getTitle");
            Method titleGetterEmployee = SafeReflection.getMethod(superclass, "getTitle");
            assertEquals("Manager must not have a getter getTitle().", titleGetterEmployee, titleGetterManager);
            Method titleSetterManager = SafeReflection.getMethod(managerClass, "setTitle", String.class);
            Method titleSetterEmployee = SafeReflection.getMethod(superclass, "setTitle", String.class);
            assertEquals("Manager must not have a setter setTitle(String).", titleSetterEmployee, titleSetterManager);

            Method departmentGetterManager = SafeReflection.getMethod(managerClass, "getDepartment");
            Method departmentGetterEmployee = SafeReflection.getMethod(superclass, "getDepartment");
            assertEquals("Manager must not have a getter getDepartment().", departmentGetterEmployee, departmentGetterManager);
            Method departmentSetterManager = SafeReflection.getMethod(managerClass, "setDepartment", Department.class);
            Method departmentSetterEmployee = SafeReflection.getMethod(superclass, "setDepartment", Department.class);
            assertEquals("Manager must not have a setter setDepartment(String).", departmentSetterEmployee, departmentSetterManager);

            Method salaryGetterManager = SafeReflection.getMethod(managerClass, "getSalary");
            Method salaryGetterEmployee = SafeReflection.getMethod(superclass, "getSalary");
            assertEquals("Manager must not have a getter getSalary().", salaryGetterEmployee, salaryGetterManager);
            Method salarySetterManager = SafeReflection.getMethod(managerClass, "setSalary", BigDecimal.class);
            Method salarySetterEmployee = SafeReflection.getMethod(superclass, "setSalary", BigDecimal.class);
            assertEquals("Manager must not have a setter setSalary(String).", salarySetterEmployee, salarySetterManager);

            isWellFormed = true;
        }
        catch (Exception e) {
            fail("com.petelevator.hr.Manager class does not exist.");
        }
    }

    @Test
    public void test02_HappyPath_Tests() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        assumeTrue("Manager is not well formed.", isWellFormed);

        // assert constructor is using superclass to set first and last name
        Constructor fourArgConstructor = SafeReflection.getConstructor(managerClass, String.class, String.class, String.class, BigDecimal.class);
        Object manager = null;
        if (fourArgConstructor != null) {
            manager = fourArgConstructor.newInstance("Anna", "Johnson", "Manager of Testing", new BigDecimal(100000));

            Method getFirstName = manager.getClass().getSuperclass().getSuperclass().getMethod("getFirstName");
            assertEquals("Manager four argument constructor Manager(String, String, String, BigDecimal) does not correctly set the field firstName.","Anna", getFirstName.invoke(manager));

            Method getLastName = manager.getClass().getSuperclass().getSuperclass().getMethod("getLastName");
            assertEquals("Manager four argument constructor Manager(String, String, String, BigDecimal) does not correctly set the field lasName.","Johnson", getLastName.invoke(manager));

        } else {
            fail("Manager four argument constructor Manager(String, String, String, BigDecimal) was not found. Please verify this constructor wasn't removed or parameters changed.");
        }

    }

    // no edge case tests, class hasn't implemented or changed any other methods
}

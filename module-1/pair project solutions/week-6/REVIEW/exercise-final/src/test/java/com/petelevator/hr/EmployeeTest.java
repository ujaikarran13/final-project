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
public class EmployeeTest {

    private static Class employeeClass;

    private static boolean isWellFormed = false;

    @Test
    public void test01_ClassWellFormed() throws NullPointerException {
        try {

            // Assert Employee class exists
            employeeClass = Class.forName("com.petelevator.hr.Employee");

            // Assert Employee extends Person
            Class superclass = employeeClass.getSuperclass();
            assertEquals("Employee must extend Person.", superclass.getName(),"com.petelevator.Person");

            // Assert fields DON'T exist, since they are members of Person and shouldn't be in Employee anymore
            Field firstNameField = SafeReflection.getDeclaredField(employeeClass, "firstName");
            assertNull("Employee must not have the field firstName, it must inherit it from Person.", firstNameField);

            Field lastNameField = SafeReflection.getDeclaredField(employeeClass, "lastName");
            assertNull("Employee must not have the field lastName, it must inherit it from Person.", lastNameField);

            Field emailField = SafeReflection.getDeclaredField(employeeClass, "email");
            assertNull("Employee must not have the field email, it must inherit it from Person.", emailField);

            // Assert getters and setters for fields DON'T exist in Employee, since they are members of Person and shouldn't be in Employee anymore
            Method firstNameGetterEmployee = SafeReflection.getMethod(employeeClass, "getFirstName");
            Method firstNameGetterPerson = SafeReflection.getMethod(superclass, "getFirstName");
            assertEquals("Employee must not have a getter getFirstName().", firstNameGetterPerson, firstNameGetterEmployee);
            Method firstNameSetterEmployee = SafeReflection.getMethod(employeeClass, "setFirstName", String.class);
            Method firstNameSetterPerson = SafeReflection.getMethod(superclass, "setFirstName", String.class);
            assertEquals("Employee must not have a setter setFirstName(String).", firstNameSetterPerson, firstNameSetterEmployee);

            Method lastNameGetterEmployee = SafeReflection.getMethod(employeeClass, "getLastName");
            Method lastNameGetterPerson = SafeReflection.getMethod(superclass, "getLastName");
            assertEquals("Employee must not have a getter getLastName().", lastNameGetterPerson, lastNameGetterEmployee);
            Method lastNameSetterEmployee = SafeReflection.getMethod(employeeClass, "setLastName", String.class);
            Method lastNameSetterPerson = SafeReflection.getMethod(superclass, "setLastName", String.class);
            assertEquals("Employee must not have a setter setLastName(String).", lastNameSetterPerson, lastNameSetterEmployee);

            Method emailGetterEmployee = SafeReflection.getMethod(employeeClass, "getEmail");
            Method emailGetterPerson = SafeReflection.getMethod(superclass, "getEmail");
            assertEquals("Employee must not have a getter getEmail().", emailGetterPerson, emailGetterEmployee);
            Method emailSetterEmployee = SafeReflection.getMethod(employeeClass, "setEmail", String.class);
            Method emailSetterPerson = SafeReflection.getMethod(superclass, "setEmail", String.class);
            assertEquals("Employee must not have a setter setEmail(String).", emailSetterPerson, emailSetterEmployee);

            isWellFormed = true;
        }
        catch (Exception e) {
            fail("com.petelevator.hr.Employee class does not exist.");
        }
    }

    @Test
    public void test02_HappyPath_Tests() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        assumeTrue("Customer is not well formed.", isWellFormed);

        // assert constructor is using superclass to set first and last name
        Constructor fourArgConstructor = SafeReflection.getConstructor(employeeClass, String.class, String.class, String.class, BigDecimal.class);
        Object employee = null;
        if (fourArgConstructor != null) {
            employee = fourArgConstructor.newInstance("John", "Smith", "Tester", new BigDecimal(50000));

            Method getFirstName = employee.getClass().getSuperclass().getMethod("getFirstName");
            assertEquals("Employee four argument constructor Employee(String, String, String, BigDecimal) does not correctly set the field firstName.","John", getFirstName.invoke(employee));

            Method getLastName = employee.getClass().getSuperclass().getMethod("getLastName");
            assertEquals("Employee four argument constructor Employee(String, String, String, BigDecimal) does not correctly set the field lasName.","Smith", getLastName.invoke(employee));

        } else {
            fail("Employee four argument constructor Employee(String, String, String, BigDecimal) was not found. Please verify this constructor wasn't removed or parameters changed.");
        }

        // assert getFullName() returns name in the correct format
        // can't check if override annotation is on method, method.getDeclaredAnnotations() and method.getAnnotations() return nothing even with the annotation present
        Method getFullName = SafeReflection.getMethod(employeeClass, "getFullName");
        if (getFullName != null) {
            assertEquals("Employee method getFullName() does not return the correct format.", "Smith, John", getFullName.invoke(employee));

        } else {
            fail("Employee method getFullName() was not found. Please verify this method wasn't removed or parameters changed.");
        }
    }

    // no edge case tests, class hasn't implemented or changed any other methods yet
    // getBalanceDue isn't written yet and will be tested in BillableTest only because that's when students will add the method
}

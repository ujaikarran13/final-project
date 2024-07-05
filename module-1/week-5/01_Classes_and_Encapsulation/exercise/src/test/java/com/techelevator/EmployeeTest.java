package com.techelevator;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeTest {

    @BeforeClass
    public static void EmployeeVerifyClassDefinition() {
        Class klass = Employee.class;

        // Verify not abstract
        assertFalse("Employee class must not be abstract. Remove the 'abstract' modifier on Employee.", Modifier.isAbstract(klass.getModifiers()));

        // Verify constructor(s)
        Constructor<Employee> constructor = SafeReflection.getConstructor(klass, Integer.TYPE, String.class,
                String.class, Double.TYPE);
        assertTrue("Employee class does not have the required constructor(int, String, String, double)", constructor != null);

        // Verify attributes
        EncapsulationTestHelpers.verifyClassField(Employee.class, "employeeId", Integer.TYPE, true, false);
        EncapsulationTestHelpers.verifyClassField(Employee.class, "firstName", String.class, true, false);
        EncapsulationTestHelpers.verifyClassField(Employee.class, "lastName", String.class, true, true);
        EncapsulationTestHelpers.verifyDerivedField(Employee.class, "fullName", String.class);
        EncapsulationTestHelpers.verifyClassField(Employee.class, "department", String.class, true, true);
        EncapsulationTestHelpers.verifyClassField(Employee.class, "annualSalary", Double.TYPE, true, false);
    }

    @Test
    public void employeeConstructor() {
        Class klass = Employee.class;

        Constructor<Employee> constructor = SafeReflection.getConstructor(klass, Integer.TYPE, String.class,
                String.class, Double.TYPE);
        assertTrue("You do not have the required constructor(int, String, String, double)", constructor != null);
        try {
            Employee employee = constructor.newInstance(1, "Jane", "Smith", 100000.00);
            Method getEmployeeId = SafeReflection.getMethod(klass, "getEmployeeId");
            Method getFirstName = SafeReflection.getMethod(klass, "getFirstName");
            Method getLastName = SafeReflection.getMethod(klass, "getLastName");
            Method getAnnualSalary = SafeReflection.getMethod(klass, "getAnnualSalary");

            assertTrue("Passed 1 into constructor and expected EmployeeId property to hold 1.",
                    1 == (int) getEmployeeId.invoke(employee));
            assertTrue("Passed Jane into constructor and expected FirstName property to hold Jane.",
                    "Jane".equals((String) getFirstName.invoke(employee)));
            assertTrue("Passed Smith into constructor and expected LastName property to hold Smith.",
                    "Smith".equals((String) getLastName.invoke(employee)));
            assertTrue("Passed 100000.00 into constructor and expected AnnualSalary property to hold 100000.00.",
                    100000.00 == (double) getAnnualSalary.invoke(employee));
        } catch (Exception e) {
            fail("An unknown error occurred with Employee class: " + e.getMessage());
        }
    }

    @Test
    public void employeeFullNameTest() {
        Class klass = Employee.class;

        Constructor<Employee> constructor = SafeReflection.getConstructor(klass, Integer.TYPE, String.class,
                String.class, Double.TYPE);
        assertTrue("You do not have the required constructor(int, String, String, double)", constructor != null);
        try {
            Employee employee = constructor.newInstance(1, "Jane", "Smith", 100000.00);
            Method getFullName = SafeReflection.getMethod(klass, "getFullName");

            assertTrue("Expected FullName to return derived value of 'LastName, FirstName'",
                    "Smith, Jane".equals((String) getFullName.invoke(employee)));
        } catch (Exception e) {
            fail("An unknown error occurred with Employee class: " + e.getMessage());
        }
    }

    @Test
    public void employeeRaiseSalaryTest() {
        Class klass = Employee.class;

        Constructor<Employee> constructor = SafeReflection.getConstructor(klass, Integer.TYPE, String.class,
                String.class, Double.TYPE);
        assertTrue("You do not have the required constructor(int, String, String, double)", constructor != null);
        try {
            Employee employee = constructor.newInstance(1, "Jane", "Smith", 100000.00);
            Method getAnnualSalary = SafeReflection.getMethod(klass, "getAnnualSalary");
            Method raiseSalary = SafeReflection.getMethod(klass, "raiseSalary", Double.TYPE);

            assertNotNull("raiseSalary method is missing", raiseSalary);

            raiseSalary.invoke(employee, new Object[] { 5.5 });
            assertTrue("Raised salary by 5.5%. Expected to go from 100000.00 to 105500.00",
                    105500.00 == (double) getAnnualSalary.invoke(employee));
        } catch (Exception e) {
            fail("An unknown error occurred with Employee class: " + e.getMessage());
        }
    }
}

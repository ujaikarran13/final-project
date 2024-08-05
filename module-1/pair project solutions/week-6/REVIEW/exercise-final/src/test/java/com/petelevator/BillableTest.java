package com.petelevator;

import com.petelevator.hr.Employee;
import com.petelevator.util.SafeReflection;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.lang.reflect.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BillableTest {

    // NOTE: Normally you would test these methods in their respective test classes.
    // These tests are here to match the exercise README flow.

    private static Class customerClass;
    private static Class employeeClass;

    private static Constructor customerConstructor;

    @Test
    public void test01_getBalanceDue_Customer_HappyPath() throws InvocationTargetException, InstantiationException, IllegalAccessException {

        setUpCustomer();
        Object customer = customerConstructor.newInstance("Test", "Testerson");
        Map<String, BigDecimal> services = new HashMap<>();
        services.put("Grooming", new BigDecimal("45.00"));
        services.put("Sitting", new BigDecimal("120.00"));
        services.put("Walking", new BigDecimal("37.50"));

        Method getBalanceDue = SafeReflection.getMethod(customerClass, "getBalanceDue", java.util.Map.class);
        BigDecimal balanceDue = null;
        if (getBalanceDue != null) {
             balanceDue = (BigDecimal)getBalanceDue.invoke(customer, services);
        } else {
            fail("Customer method getBalanceDue(Map<String, BigDecimal>) could not be found.");
        }

        assertEquals("Customer.getBalanceDue({\"Grooming\": 45.00, \"Sitting\": 120.00, \"Walking\": 37.50}) doesn't return the correct value.", new BigDecimal("202.50"), balanceDue);
    }

    @Test
    public void test02_getBalanceDue_Customer_EdgeCase() throws InvocationTargetException, InstantiationException, IllegalAccessException {

        setUpCustomer();
        Object customer = customerConstructor.newInstance("Test", "Testerson");
        Map<String, BigDecimal> services = new HashMap<>();
        // no values added

        Method getBalanceDue = SafeReflection.getMethod(customerClass, "getBalanceDue", java.util.Map.class);
        BigDecimal balanceDue = null;
        if (getBalanceDue != null) {
            balanceDue = (BigDecimal)getBalanceDue.invoke(customer, services);
        } else {
            fail("Customer method getBalanceDue(Map<String, BigDecimal>) could not be found.");
        }

        assertEquals("Customer.getBalanceDue({}) doesn't return the correct value.", BigDecimal.ZERO, balanceDue);
    }

    @Test
    public void test03_getBalanceDue_Employee_HappyPath() throws InvocationTargetException, IllegalAccessException {

        setUpEmployee();
        Employee employee = new Employee("Test", "Testerson");
        Map<String, BigDecimal> services = new HashMap<>();
        services.put("Grooming", new BigDecimal("45.00"));
        services.put("Sitting", new BigDecimal("120.00"));
        services.put("Walking", new BigDecimal("37.50"));

        Method getBalanceDue = SafeReflection.getMethod(employeeClass, "getBalanceDue", java.util.Map.class);
        BigDecimal balanceDue = null;
        if (getBalanceDue != null) {
            balanceDue = (BigDecimal)getBalanceDue.invoke(employee, services);
        } else {
            fail("Employee method getBalanceDue(Map<String, BigDecimal>) could not be found.");
        }

        assertEquals("Employee.getBalanceDue({\"Grooming\": 45.00, \"Sitting\": 120.00, \"Walking\": 37.50}) doesn't return the correct value.", new BigDecimal("183.75"), balanceDue);
    }

    @Test
    public void test04_getBalanceDue_Employee_EdgeCase() throws InvocationTargetException, IllegalAccessException {

        setUpEmployee();
        Employee employee = new Employee("Test", "Testerson");
        Map<String, BigDecimal> services = new HashMap<>();
        // no values added

        Method getBalanceDue = SafeReflection.getMethod(employeeClass, "getBalanceDue", java.util.Map.class);
        BigDecimal balanceDue = null;
        if (getBalanceDue != null) {
            balanceDue = (BigDecimal)getBalanceDue.invoke(employee, services);
        } else {
            fail("Employee method getBalanceDue(Map<String, BigDecimal>) could not be found.");
        }

        assertEquals("Employee.getBalanceDue({}) doesn't return the correct value.", BigDecimal.ZERO, balanceDue);
    }


    private void setUpCustomer() {
        try {
            customerClass = Class.forName("com.petelevator.crm.Customer");
        } catch (ClassNotFoundException e) {
            fail("Customer class was not found.");
        }
        customerConstructor = SafeReflection.getConstructor(customerClass, String.class, String.class);
        if (customerConstructor == null) {
            fail("Customer constructor customer(String, String) was not found.");
        }
    }

    private void setUpEmployee() {
        try {
            employeeClass = Class.forName("com.petelevator.hr.Employee");
        } catch (ClassNotFoundException e) {
            fail("Employee class was not found.");
        }
        // assume there is a constructor since it's provided
    }
}

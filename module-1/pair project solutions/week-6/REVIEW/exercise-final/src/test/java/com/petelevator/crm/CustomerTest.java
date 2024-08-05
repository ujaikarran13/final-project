package com.petelevator.crm;

import com.petelevator.util.SafeReflection;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerTest {

    private static Class customerClass;
    private static Constructor twoArgConstructor;
    private static Constructor threeArgConstructor;

    private static boolean isWellFormed = false;

    @Test
    public void test01_ClassWellFormed() {
        try {

            // Assert Customer class exists
            customerClass = Class.forName("com.petelevator.crm.Customer");

            // Assert Customer extends Person
            Class superclass = customerClass.getSuperclass();
            assertEquals("Customer must extend Person.", superclass.getName(),"com.petelevator.Person");

            // Assert constructors exist
            twoArgConstructor = SafeReflection.getConstructor(customerClass, String.class, String.class);
            assertNotNull("Customer does not have the required two argument constructor Customer(String, String). Make sure access for the constructor is public.", twoArgConstructor);
            assertTrue("Customer constructor Customer(String, String) must be public.", Modifier.isPublic(twoArgConstructor.getModifiers()));

            threeArgConstructor = SafeReflection.getConstructor(customerClass, String.class, String.class, String.class);
            assertNotNull("Customer does not have the required three argument constructor Customer(String, String, String). Make sure access for the constructor is public.", threeArgConstructor);
            assertTrue("Customer constructor Customer(String, String, String) must be public.", Modifier.isPublic(threeArgConstructor.getModifiers()));

            // Assert fields exist, are of correct type and access
            Field phoneNumberField = SafeReflection.getDeclaredField(customerClass, "phoneNumber");
            assertNotNull("Customer does not have the required field phoneNumber.", phoneNumberField);
            assertEquals("Customer field phoneNumber must be type String.", "java.lang.String", phoneNumberField.getType().getName());
            assertTrue("Customer field phoneNumber must be private.", Modifier.isPrivate(phoneNumberField.getModifiers()));

            Field petsField = SafeReflection.getDeclaredField(customerClass, "pets");
            assertNotNull("Customer does not have the required field pets.", petsField);
            assertEquals("Customer field pets must be type List.", "java.util.List", petsField.getType().getName());
            assertEquals("Customer field pets must be type List<Pet>.", "java.util.List<com.petelevator.crm.Pet>", petsField.getGenericType().getTypeName());
            assertTrue("Customer field pets must be private.", Modifier.isPrivate(petsField.getModifiers()));

            // Assert getters and setters for fields
            Method phoneNumberGetter = SafeReflection.getMethod(customerClass, "getPhoneNumber");
            assertTrue("Customer must have getter getPhoneNumber(). Make sure access for the getter is public.", phoneNumberGetter != null);
            assertTrue("Customer getter getPhoneNumber() must return type String.", phoneNumberGetter.getReturnType() == String.class);
            assertTrue("Customer getter getPhoneNumber() must be public.", Modifier.isPublic(phoneNumberGetter.getModifiers()));
            Method phoneNumberSetter = SafeReflection.getMethod(customerClass, "setPhoneNumber", String.class);
            assertTrue("Customer must have a setter setPhoneNumber(String).", phoneNumberSetter != null);

            Method petsGetter = SafeReflection.getMethod(customerClass, "getPets");
            assertTrue("Customer must have getter getPets(). Make sure access for the getter is public.", petsGetter != null);
            assertEquals("Customer getter getPets() must return type List<Pet>.", "java.util.List<com.petelevator.crm.Pet>", petsGetter.getGenericReturnType().getTypeName());
            assertTrue("Customer getter getPets() must be public.", Modifier.isPublic(petsGetter.getModifiers()));
            Method petsSetter = SafeReflection.getMethod(customerClass, "setPets", List.class);
            assertTrue("Customer must have a setter for pets.", petsSetter != null);

            isWellFormed = true;
        }
        catch (Exception e) {
            fail("com.petelevator.crm.Customer class does not exist.");
        }
    }
    
    @Test
    public void test02_HappyPath_Tests() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        assumeTrue("Customer is not well formed.", isWellFormed);

        // Assert constructors set fields
        // Two arg constructor
        Object customer = twoArgConstructor.newInstance("John", "Smith");
        Method getFirstName = customer.getClass().getSuperclass().getMethod("getFirstName");
        assertEquals("Customer two argument constructor Customer(String, String) does not correctly set the field firstName.","John", getFirstName.invoke(customer));
        Method getLastName = customer.getClass().getSuperclass().getMethod("getLastName");
        assertEquals("Customer two argument constructor Customer(String, String) does not correctly set the field lastName.","Smith", getLastName.invoke(customer));
        Method getPets = customer.getClass().getMethod("getPets");
        assertEquals("Customer two argument constructor Customer(String, String) does not correctly set the field pets as new ArrayList.", new ArrayList<>(), getPets.invoke(customer));

        // Three arg constructor
        customer = threeArgConstructor.newInstance("John", "Smith", "555-555-4321");
        getFirstName = customer.getClass().getMethod("getFirstName");
        assertEquals("Customer three argument constructor Customer(String, String, String) does not correctly set the field firstName.","John", getFirstName.invoke(customer));
        getLastName = customer.getClass().getMethod("getLastName");
        assertEquals("Customer three argument constructor Customer(String, String, String) does not correctly set the field lastName.","Smith", getLastName.invoke(customer));
        Method getPhoneNumber = customer.getClass().getMethod("getPhoneNumber");
        assertEquals("Customer three argument constructor Customer(String, String, String) does not correctly set the field phoneNumber.","555-555-4321", getPhoneNumber.invoke(customer));
        getPets = customer.getClass().getMethod("getPets");
        assertEquals("Customer three argument constructor Customer(String, String, String) does not correctly set the field pets as new ArrayList.", new ArrayList<>(), getPets.invoke(customer));

    }

    // no edge case tests, class hasn't implemented any other methods yet
    // getBalanceDue() isn't written yet and will be tested in BillableTest only because that's when students will add the method
}

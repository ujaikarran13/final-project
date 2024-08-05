package com.petelevator;

import com.petelevator.util.SafeReflection;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.lang.reflect.*;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonTest {

    private static Class personClass;
    private static Constructor twoArgConstructor;

    @Test
    public void test01_ClassWellFormed() {
        try {

            // Assert Person class exists
            personClass = Class.forName("com.petelevator.Person");

            // Assert constructors exist
            twoArgConstructor = SafeReflection.getConstructor(personClass, String.class, String.class);
            assertNotNull("Person does not have the required two argument constructor Person(String, String). Make sure access for the constructor is public.", twoArgConstructor);
            assertTrue("Person constructor Person(String, String) must be public.", Modifier.isPublic(twoArgConstructor.getModifiers()));

            // Assert fields exist, are of correct type and access
            Field firstNameField = SafeReflection.getDeclaredField(personClass, "firstName");
            assertNotNull("Person does not have the required field firstName.", firstNameField);
            assertEquals("Person field firstName must be type String.", "java.lang.String", firstNameField.getType().getName());
            assertTrue("Person field firstName must be private.", Modifier.isPrivate(firstNameField.getModifiers()));

            Field lastNameField = SafeReflection.getDeclaredField(personClass, "lastName");
            assertNotNull("Person does not have the required field lastName.", lastNameField);
            assertEquals("Person field lastName must be type String.", "java.lang.String", lastNameField.getType().getName());
            assertTrue("Person field lastName must be private.", Modifier.isPrivate(lastNameField.getModifiers()));

            Field emailField = SafeReflection.getDeclaredField(personClass, "email");
            assertNotNull("Person does not have the required field email.", emailField);
            assertEquals("Person field email must be type String.", "java.lang.String", emailField.getType().getName());
            assertTrue("Person field email must be private.", Modifier.isPrivate(emailField.getModifiers()));

            // Assert getters and setters for fields
            Method firstNameGetter = SafeReflection.getMethod(personClass, "getFirstName");
            assertTrue("Person must have getter getFirstName(). Make sure access for the getter is public.", firstNameGetter != null);
            assertTrue("Person getter getFirstName() must return type String.", firstNameGetter.getReturnType() == String.class);
            assertTrue("Person getter getFirstName() must be public.", Modifier.isPublic(firstNameGetter.getModifiers()));
            Method firstNameSetter = SafeReflection.getMethod(personClass, "setFirstName", String.class);
            assertTrue("Person must have a setter setFirstName(String).", firstNameSetter != null);

            Method lastNameGetter = SafeReflection.getMethod(personClass, "getLastName");
            assertTrue("Person must have getter getLastName(). Make sure access for the getter is public.", lastNameGetter != null);
            assertTrue("Person getter getLastName() must return type String.", lastNameGetter.getReturnType() == String.class);
            assertTrue("Person getter getLastName() must be public.", Modifier.isPublic(lastNameGetter.getModifiers()));
            Method lastNameSetter = SafeReflection.getMethod(personClass, "setLastName", String.class);
            assertTrue("Person must have a setter setLastName(String).", lastNameSetter != null);

            Method emailGetter = SafeReflection.getMethod(personClass, "getEmail");
            assertTrue("Person must have getter getEmail(). Make sure access for the getter is public.", emailGetter != null);
            assertTrue("Person getter getEmail() must return type String.", emailGetter.getReturnType() == String.class);
            assertTrue("Person getter getEmail() must be public.", Modifier.isPublic(emailGetter.getModifiers()));
            Method emailSetter = SafeReflection.getMethod(personClass, "setEmail", String.class);
            assertTrue("Person must have a setter setEmail(String).", emailSetter != null);

            // Assert methods are present -- whether they work is done in other test methods
            Method getFullNameMethod = SafeReflection.getMethod(personClass, "getFullName");
            assertTrue("Person must have method getFullName(). Make sure access for the method is public.", getFullNameMethod != null);
            assertTrue("Person method getFullName() must return type String.", getFullNameMethod.getReturnType() == String.class);
            assertTrue("Person method getFullName() must be public.", Modifier.isPublic(getFullNameMethod.getModifiers()));

            // Assert class is abstract
            assertTrue("Person must be an abstract class.", Modifier.isAbstract(personClass.getModifiers()));

        }
        catch (Exception e) {
            fail("com.petelevator.Person class does not exist.");
        }
    }

    // no happy path and edge case tests since Person is abstract and cannot be instantiated
    // they are technically tested in derived class tests
}


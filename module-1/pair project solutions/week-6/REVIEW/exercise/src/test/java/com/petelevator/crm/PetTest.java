package com.petelevator.crm;

import com.petelevator.util.SafeReflection;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PetTest {

    private static Class petClass;
    private static Constructor twoArgConstructor;

    private static boolean isWellFormed = false;
    private static boolean hasPassedHappyPathTests = false;

    @Test
    public void test01_ClassWellFormed() {
        try {

            // Assert Pet class exists
            petClass = Class.forName("com.petelevator.crm.Pet");

            // Assert constructors exist
            twoArgConstructor = SafeReflection.getConstructor(petClass, String.class, String.class);
            assertNotNull("Pet does not have the required two argument constructor Pet(String, String). Make sure access for the constructor is public.", twoArgConstructor);
            assertTrue("Pet constructor Pet(String, String) must be public.", Modifier.isPublic(twoArgConstructor.getModifiers()));

            // Assert fields exist, are of correct type and access
            Field nameField = SafeReflection.getDeclaredField(petClass, "name");
            assertNotNull("Pet does not have the required field name.", nameField);
            assertEquals("Pet field name must be type String.", "java.lang.String", nameField.getType().getName());
            assertTrue("Pet field name must be private.", Modifier.isPrivate(nameField.getModifiers()));

            Field speciesField = SafeReflection.getDeclaredField(petClass, "species");
            assertNotNull("Pet does not have the required field species.", speciesField);
            assertEquals("Pet field species must be type String.", "java.lang.String", speciesField.getType().getName());
            assertTrue("Pet field species must be private.", Modifier.isPrivate(speciesField.getModifiers()));

            Field vaccinationsField = SafeReflection.getDeclaredField(petClass, "vaccinations");
            assertNotNull("Pet does not have the required field vaccinations.", vaccinationsField);
            assertEquals("Pet field vaccinations must be type List.", "java.util.List", vaccinationsField.getType().getName());
            assertEquals("Pet field vaccinations must be type List<String>.", "java.util.List<java.lang.String>", vaccinationsField.getGenericType().getTypeName());
            assertTrue("Pet field vaccinations must be private.", Modifier.isPrivate(vaccinationsField.getModifiers()));

            // Assert getters and setters for fields
            Method nameGetter = SafeReflection.getMethod(petClass, "getName");
            assertTrue("Pet must have getter getName(). Make sure access for the getter is public.", nameGetter != null);
            assertTrue("Pet getter getName() must return type String.", nameGetter.getReturnType() == String.class);
            assertTrue("Pet getter getName() must be public.", Modifier.isPublic(nameGetter.getModifiers()));
            Method nameSetter = SafeReflection.getMethod(petClass, "setName", String.class);
            assertTrue("Pet must have a setter setName(String).", nameSetter != null);

            Method speciesGetter = SafeReflection.getMethod(petClass, "getSpecies");
            assertTrue("Pet must have getter getSpecies(). Make sure access for the getter is public.", speciesGetter != null);
            assertTrue("Pet getter getSpecies() must return type String.", speciesGetter.getReturnType() == String.class);
            assertTrue("Pet getter getSpecies() must be public.", Modifier.isPublic(speciesGetter.getModifiers()));
            Method speciesSetter = SafeReflection.getMethod(petClass, "setSpecies", String.class);
            assertTrue("Pet must have a setter setSpecies(String).", speciesSetter != null);

            Method vaccinationsGetter = SafeReflection.getMethod(petClass, "getVaccinations");
            assertTrue("Pet must have getter getVaccinations(). Make sure access for the getter is public.", vaccinationsGetter != null);
            assertEquals("Pet getter getVaccinations() must return type List<String>.", "java.util.List<java.lang.String>", vaccinationsGetter.getGenericReturnType().getTypeName());
            assertTrue("Pet getter getVaccinations() must be public.", Modifier.isPublic(vaccinationsGetter.getModifiers()));
            Method vaccinationsSetter = SafeReflection.getMethod(petClass, "setVaccinations");
            assertTrue("Pet must not have a setter for vaccinations.", vaccinationsSetter == null);

            // Assert methods are present -- whether they work is done in other test methods
            Method listVaccinationsMethod = SafeReflection.getMethod(petClass, "listVaccinations");
            assertTrue("Pet must have method listVaccinations(). Make sure access for the method is public.", listVaccinationsMethod != null);
            assertTrue("Pet method listVaccinations() must return type String.", listVaccinationsMethod.getReturnType() == String.class);
            assertTrue("Pet method listVaccinations() must be public.", Modifier.isPublic(listVaccinationsMethod.getModifiers()));
            Method addVaccinationMethod = SafeReflection.getMethod(petClass, "addVaccination", String.class);
            assertTrue("Pet must have method addVaccination(). Make sure access for the method is public.", addVaccinationMethod != null);
            assertTrue("Pet method addVaccination() must return type void.", addVaccinationMethod.getReturnType() == void.class);
            assertTrue("Pet method addVaccination() must be public.", Modifier.isPublic(addVaccinationMethod.getModifiers()));

            isWellFormed = true;
        }
        catch (Exception e) {
            fail("com.petelevator.crm.Pet class does not exist.");
        }
    }

    @Test
    public void test02_HappyPath_Tests() throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        assumeTrue("Pet is not well formed.", isWellFormed);

        // Assert constructors set fields
        // Two arg constructor
        Object pet = twoArgConstructor.newInstance("Quill", "Dog");
        Method getName = pet.getClass().getMethod("getName");
        assertEquals("Pet two argument constructor Pet(String, String) does not correctly set the field name.","Quill", getName.invoke(pet));
        Method getSpecies = pet.getClass().getMethod("getSpecies");
        assertEquals("Pet two argument constructor Pet(String, String) does not correctly set the field species.","Dog", getSpecies.invoke(pet));
        Method getVaccinations = pet.getClass().getMethod("getVaccinations");
        assertEquals("Pet two argument constructor Pet(String, String) does not correctly set the field vaccinations as new ArrayList.", (new ArrayList<String>()), getVaccinations.invoke(pet));

        Method listVaccinations = pet.getClass().getMethod("listVaccinations");
        assertEquals("Pet method listVaccinations does not return an empty string when there are no vaccinations.", "", listVaccinations.invoke(pet));

        // assert addVaccination() works by calling getVaccinations() and listVaccinations() to confirm it was added
        Method addVaccination = pet.getClass().getMethod("addVaccination", String.class);
        addVaccination.invoke(pet, "Distemper");
        assertEquals("Pet method addVaccination does not add the passed in string to vaccinations.", (new ArrayList<>(List.of("Distemper"))), getVaccinations.invoke(pet));
        assertEquals("Pet method listVaccinations does not return the string of vaccinations.", "Distemper", listVaccinations.invoke(pet));

        // assert more than one vaccination works
        addVaccination.invoke(pet, "Rabies");
        assertEquals("Pet method addVaccination does not add the passed in string to vaccinations.", (new ArrayList<>(Arrays.asList("Distemper", "Rabies"))), getVaccinations.invoke(pet));
        assertEquals("Pet method listVaccinations does not return the string of vaccinations.", "Distemper, Rabies", listVaccinations.invoke(pet));

        hasPassedHappyPathTests = true;
    }

    // no edge case tests, adding and accessing a list doesn't provide any edge case scenarios
}

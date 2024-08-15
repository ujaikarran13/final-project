package com.techelevator;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HotelReservationTest {

    private Class klass = HotelReservation.class;

    //region Private Helper methods
    private Method findMethod(String methodName, Class<?> returnType, Class<?>... parameterTypes) {
        // Look for a method with a given signature. Assert it's there.
        Method method = SafeReflection.getMethod(klass, methodName, parameterTypes);
        assertNotNull(klass.getSimpleName() + " class must have method " + methodName + "()" + ".", method);
        assertTrue(methodName + "() method must return type " + returnType.getSimpleName(), method.getReturnType() == returnType);
        return method;
    }

    private Method findNoMethod(String methodName, Class<?> returnType, Class<?>... parameterTypes) {
        // Look for a method with a given signature. Assert it's NOT there.
        Method method = SafeReflection.getMethod(klass, methodName, parameterTypes);
        assertNull(klass.getSimpleName() + " class must NOT have method " + methodName + "()" + ".", method);
        return method;
    }

    private String getMethodString(String methodName, Object... args) {
        String msg = methodName + "(";
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                if (i > 0) msg += ", ";
                msg += args[i].toString();
            }
        }
        msg += ")";
        return msg;
    }

    private void verifyMethod(Method method, Object instance, Object expectedResult, Object... args) {
        try {
//            System.out.print("Verifying " + getMethodString(method.getName(), args) + "... ");
            Object actualResult = method.invoke(instance, args);

            if (expectedResult == null) {
                // Expecting null or void result
                if (actualResult == null) {
                    // Got null or void result
//                    System.out.println(getMethodString(method.getName(), args) + " verified.");
                    return;
                } else {
                    // Got a non-null result
                    fail("Expected no result from " + getMethodString(method.getName(), args) + ", but got " + actualResult.toString());
                }
            } else if (actualResult == null) {
                // Fail because actual is null but expected is NOT null
                // Got a non-null result
                fail(getMethodString(method.getName(), args) + " must return " + expectedResult.toString() + ". Actual result was NULL.");
            }

            // non-null / non-void expected
            assertTrue(getMethodString(method.getName(), args) + " must return " + expectedResult.toString() + ". Actual result was " + actualResult.toString(),
                    expectedResult.equals(actualResult));
//            System.out.println(getMethodString(method.getName(), args) + " verified.");
        } catch (Exception e) {
            fail("Failed to run method " + getMethodString(method.getName(), args) + ". Error was: " + e.getMessage());
        }
    }

    private HotelReservation createHotelReservationInstance(String name, int numberOfNights, int nightlyRate) {
        // By default, look only for the 3-parameter constructor
        return createHotelReservationInstance(name, numberOfNights, nightlyRate, false);
    }

    private HotelReservation createHotelReservationInstance(String name, int numberOfNights, int nightlyRate, boolean fallbackToDefaultConstructor) {
        // Look for a constructor with (String, int, int)
        HotelReservation hotelReservation = null;
        Constructor<HotelReservation> constructor = SafeReflection.getConstructor(klass, String.class, Integer.TYPE, Integer.TYPE);
        try {
            if (constructor == null) {
                if (fallbackToDefaultConstructor) {
                    // Look for a default constructor
                    constructor = SafeReflection.getConstructor(klass);

                    if (constructor == null) {
                        fail(klass.getSimpleName() + " must have the required constructor(String, int, int), and no default constructor found.");
                    }

                    // We have a default constructor
                    hotelReservation = constructor.newInstance();

                } else {    // Don't fallback to a default constructor
                    fail(klass.getSimpleName() + " must have the required constructor(String, int, int)");
                }
            } else {
                // We have a constructor with the proper parameters
                // Create the instance
                hotelReservation = constructor.newInstance(name, numberOfNights, nightlyRate);

            }
        } catch (Exception e) {
            fail("Failed to instantiate HotelReservation");
        }
        return hotelReservation;
    }
    //endregion Private Helper methods

    //region Tests for properties
    @Test
    public void A01_nameTest() {
        String name = "Emerson Lake";
        HotelReservation hotelReservation = createHotelReservationInstance("Default", 1, 1, true);

        // findMethod looks for the method and asserts it exists
        Method method = findMethod("setName", void.class, String.class);
        // Verify tries to invoke the method and asserts its result
        verifyMethod(method, hotelReservation, null, name);

        // Now get the property
        method = findMethod("getName", String.class);
        verifyMethod(method, hotelReservation, name);
    }

    @Test
    public void A02_numberOfNightsTest() {
        int numberOfNights = 4;
        HotelReservation hotelReservation = createHotelReservationInstance("Default", 1, 1, true);

        Method method = findMethod("setNumberOfNights", void.class, Integer.TYPE);
        verifyMethod(method, hotelReservation, null, numberOfNights);

        // Now get the property
        method = findMethod("getNumberOfNights", Integer.TYPE);
        verifyMethod(method, hotelReservation, numberOfNights);
    }

    @Test
    public void A03_nightlyRateTest() {
        int nightlyRate = 129;
        HotelReservation hotelReservation = createHotelReservationInstance("Default", 1, 1, true);

        Method method = findMethod("setNightlyRate", void.class, Integer.TYPE);
        verifyMethod(method, hotelReservation, null, nightlyRate);

        // Now get the property
        method = findMethod("getNightlyRate", Integer.TYPE);
        verifyMethod(method, hotelReservation, nightlyRate);
    }

    @Test
    public void A04_EstimatedTotalExistsTest() {
        HotelReservation hotelReservation = createHotelReservationInstance("Default", 1, 1, true);

        // Make sure there's NOT a setter
        Method method = findNoMethod("setEstimatedTotal", void.class, Integer.TYPE);

        // Make sure there IS a getter
        method = findMethod("getEstimatedTotal", Integer.TYPE);

        // Set properties and test the calculated property
        runEstimatedTotalTest(hotelReservation, 1, 99, 99);
        runEstimatedTotalTest(hotelReservation, 4, 25, 100);
        runEstimatedTotalTest(hotelReservation, 12, 100, 1200);
    }
    private void runEstimatedTotalTest(HotelReservation hotelReservation, int numberOfNights, int nightlyRate, int expectedEstimatedTotal) {
        // Setup
        Method method = findMethod("setNumberOfNights", void.class, Integer.TYPE);
        verifyMethod(method, hotelReservation, null, numberOfNights );
        method = findMethod("setNightlyRate", void.class, Integer.TYPE);
        verifyMethod(method, hotelReservation, null, nightlyRate );

        method = findMethod("getEstimatedTotal", Integer.TYPE);
        verifyMethod(method, hotelReservation, expectedEstimatedTotal);
    }

    //endregion

    //region HotelReservation constructor tests
    @Test
    public void A05_HotelReservationConstructorTest() {
        // Make sure there's a constructor with parameters (will assert if constructor doesn't exist)
        String name = "Customer name";
        int numberOfNights = 1;
        int nightlyRate = 150;
//        System.out.println("Looking for constructor...");
        HotelReservation hotelReservation = createHotelReservationInstance(name, numberOfNights, nightlyRate);

        // Verify that the name, number of nights, and nightly rate were set in the constructor
        Method method = findMethod("getName", String.class);
        verifyMethod(method, hotelReservation, name);
        method = findMethod("getNumberOfNights", Integer.TYPE);
        verifyMethod(method, hotelReservation, numberOfNights);
        method = findMethod("getNightlyRate", Integer.TYPE);
        verifyMethod(method, hotelReservation, nightlyRate);
    }

    //endregion

    //region HotelReservation method tests

    @Test
    public void A06_GetActualTotalTest() {
        // Test getActualTotal method exists and calculations

        // 1 night
        System.out.println("Verifying actual total for 1 night, $100/night");
        runGetActualTotalTest(1, 100, false, false, 100);
        runGetActualTotalTest(1, 100, true, false, 125);
        runGetActualTotalTest(1, 100, false, true, 115);
        runGetActualTotalTest(1, 100, true, true, 165);

        // 5 nights
        System.out.println("Verifying actual total for 5 nights, $299/night");
        runGetActualTotalTest(5, 299, false, false, 1495);
        runGetActualTotalTest(5, 299, true, false, 1520);
        runGetActualTotalTest(5, 299, false, true, 1510);
        runGetActualTotalTest(5, 299, true, true, 1560);
    }
    private void runGetActualTotalTest(int numberOfNights, int nightlyRate, boolean requiresCleaning, boolean usedMinibar, int expectedTotal) {
        // Create a reservation with a set of parameters
        HotelReservation hotelReservation = createHotelReservationInstance("Default", numberOfNights, nightlyRate);

        // Call getActualTotal with a set of parameters and check the result
        Method method = findMethod("getActualTotal", Integer.TYPE, boolean.class, boolean.class);
        verifyMethod(method, hotelReservation, expectedTotal, requiresCleaning, usedMinibar);
    }

    @Test
    public void A07_ToStringTest() {
        // Test toString method return value

        runToStringTest("Julius Erving", 1, 100, "Julius Erving:100");
        runToStringTest("Drew Barrymore", 14, 79, "Drew Barrymore:1106");
        runToStringTest("Steve Irwin", 5, 199, "Steve Irwin:995");
    }

    private void runToStringTest(String name, int numberOfNights, int nightlyRate, String expectedValue) {
        // Create a reservation with a set of parameters
        HotelReservation hotelReservation = createHotelReservationInstance(name, numberOfNights, nightlyRate);

        // Call getActualTotal with a set of parameters and check the result
        Method method = findMethod("toString", String.class);
        verifyMethod(method, hotelReservation, expectedValue);
    }
    //endregion
}

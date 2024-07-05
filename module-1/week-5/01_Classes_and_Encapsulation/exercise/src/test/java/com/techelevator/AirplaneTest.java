package com.techelevator;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AirplaneTest {

	@BeforeClass
	public static void AirplaneVerifyClassDefinition() {
		Class klass = Airplane.class;

		// Verify not abstract
		assertFalse("Airplane class must not be abstract. Remove the 'abstract' modifier on Airplane.", Modifier.isAbstract(klass.getModifiers()));

		// Verify constructor(s)
		Constructor<Airplane> constructor = SafeReflection.getConstructor(klass, String.class, Integer.TYPE,
				Integer.TYPE);
		assertTrue("Airplane class does not have the required constructor(String, int, int)", constructor != null);

		// Verify attributes
		EncapsulationTestHelpers.verifyClassField(Airplane.class, "planeNumber", String.class, true, false);
		EncapsulationTestHelpers.verifyClassField(Airplane.class, "totalFirstClassSeats", Integer.TYPE, true, false);
		EncapsulationTestHelpers.verifyClassField(Airplane.class, "bookedFirstClassSeats", Integer.TYPE, true, false);
		EncapsulationTestHelpers.verifyDerivedField(Airplane.class, "availableFirstClassSeats", Integer.TYPE);
		EncapsulationTestHelpers.verifyClassField(Airplane.class, "totalCoachSeats", Integer.TYPE, true, false);
		EncapsulationTestHelpers.verifyClassField(Airplane.class, "bookedCoachSeats", Integer.TYPE, true, false);
		EncapsulationTestHelpers.verifyDerivedField(Airplane.class, "availableCoachSeats", Integer.TYPE);
	}

	@Test
	public void airplaneConstructor() {
		Class klass = Airplane.class;

		Constructor<Airplane> constructor = SafeReflection.getConstructor(klass, String.class, Integer.TYPE,
				Integer.TYPE);
		assertTrue("You do not have the required constructor(String, int, int)", constructor != null);
		try {
			Airplane airplane = constructor.newInstance("ABC123", 2, 3);
			Method getPlaneNumber = SafeReflection.getMethod(klass, "getPlaneNumber");
			Method getTotalFirstClassSeats = SafeReflection.getMethod(klass, "getTotalFirstClassSeats");
			Method getTotalCoachSeats = SafeReflection.getMethod(klass, "getTotalCoachSeats");
			Method getBookedFirstClassSeats = SafeReflection.getMethod(klass, "getBookedFirstClassSeats");
			Method getBookedCoachSeats = SafeReflection.getMethod(klass, "getBookedCoachSeats");
			assertTrue("Testing constructor with Airplane(\"ABC123\", 2, 3) and expected PlaneNumber property to hold \"ABC123\".",
					"ABC123".equals((String) getPlaneNumber.invoke(airplane)));
			assertTrue("Testing constructor with Airplane(\"ABC123\", 2, 3) and expected totalFirstClassSeats property to return 2",
					2 == (int) getTotalFirstClassSeats.invoke(airplane));
			assertTrue("Testing constructor with Airplane(\"ABC123\", 2, 3) and expected TotalCoachSeats property to return 3.",
					3 == (int) getTotalCoachSeats.invoke(airplane));
			assertTrue("New planes should initially have 0 booked first class seats.",
					0 == (int) getBookedFirstClassSeats.invoke(airplane));
			assertTrue("New planes should initially have 0 booked coach seats.",
					0 == (int) getBookedCoachSeats.invoke(airplane));
		} catch (Exception e) {
			fail("An unknown error occurred with Airplane class: " + e.getMessage());
		}
	}

	@Test
	public void airplaneGetAvailableSeatsTest() {
		Class klass = Airplane.class;

		Constructor<Airplane> constructor = SafeReflection.getConstructor(klass, String.class, Integer.TYPE,
				Integer.TYPE);
		assertTrue("You do not have the required constructor(String, int, int)", constructor != null);
		try {
			Airplane airplane = constructor.newInstance("ABC123", 2, 3);
			Method getAvailableFirstClassSeats = SafeReflection.getMethod(klass, "getAvailableFirstClassSeats");
			Method getAvailableCoachSeats = SafeReflection.getMethod(klass, "getAvailableCoachSeats");
			assertTrue(
					"No seats have been booked for first class. There are 2 first class seats, 2 should be available.",
					2 == (int) getAvailableFirstClassSeats.invoke(airplane));
			assertTrue("No seats have been booked for coach. There are 3 coach seats, 3 should be available.",
					3 == (int) getAvailableCoachSeats.invoke(airplane));
		} catch (Exception e) {
			fail("An unknown error occurred with Airplane class: " + e.getMessage());
		}
	}

	@Test
	public void airplaneReserveSeatsTest() {
		Class klass = Airplane.class;

		Constructor<Airplane> constructor = SafeReflection.getConstructor(klass, String.class, Integer.TYPE,
				Integer.TYPE);
		assertTrue("You do not have the required constructor(String, int, int)", constructor != null);
		try {
			Airplane airplane = constructor.newInstance("ABC123", 2, 3);
			Method reserveSeats = SafeReflection.getMethod(klass, "reserveSeats", Boolean.TYPE, Integer.TYPE);
			Method getAvailableFirstClassSeats = SafeReflection.getMethod(klass, "getAvailableFirstClassSeats");
			Method getBookedFirstClassSeats = SafeReflection.getMethod(klass, "getBookedFirstClassSeats");
			Method getAvailableCoachSeats = SafeReflection.getMethod(klass, "getAvailableCoachSeats");
			Method getBookedCoachSeats = SafeReflection.getMethod(klass, "getBookedCoachSeats");

			assertNotNull("reserveSeats method is missing", reserveSeats);

			// Reserve one less than available
			boolean firstClassResult = (boolean) reserveSeats.invoke(airplane, new Object[] { true, 1 });
			boolean coachResult = (boolean) reserveSeats.invoke(airplane, new Object[] { false, 2 });
			assertTrue("reserveSeats should return true if a seat can be booked.", firstClassResult);
			assertTrue("reserveSeats should return true if a seat can be booked.", coachResult);
			assertEquals("getAvailableFirstClassSeats did not return the correct value. Total 2, Booked 1, Available 1",
					1, (int) getAvailableFirstClassSeats.invoke(airplane));
			assertEquals("getBookedFirstClassSeats did not return the correct value. Total 2, Booked 1, Available 1",
					1, (int) getBookedFirstClassSeats.invoke(airplane));
			assertEquals("getAvailableCoachSeats did not return the correct value. Total 3, Booked 2, Available 1",
					1, (int) getAvailableCoachSeats.invoke(airplane));
			assertEquals("getBookedCoachSeats did not return the correct value. Total 3, Booked 2, Available 1",
					2, (int) getBookedCoachSeats.invoke(airplane));

			// Reserve the exact number available
			airplane = constructor.newInstance("ABC123", 2, 3);
			firstClassResult = (boolean) reserveSeats.invoke(airplane, new Object[] { true, 2 });
			coachResult = (boolean) reserveSeats.invoke(airplane, new Object[] { false, 3 });
			assertTrue("reserveSeats should return true if seats can be booked.", firstClassResult);
			assertTrue("reserveSeats should return true if seats can be booked.", coachResult);
			assertEquals("getAvailableFirstClassSeats did not return the correct value. Total 2, Booked 2, Available 0",
					0, (int) getAvailableFirstClassSeats.invoke(airplane));
			assertEquals("getBookedFirstClassSeats did not return the correct value. Total 2, Booked 2, Available 0",
					2, (int) getBookedFirstClassSeats.invoke(airplane));
			assertEquals("getAvailableCoachSeats did not return the correct value. Total 3, Booked 3, Available 0",
					0, (int) getAvailableCoachSeats.invoke(airplane));
			assertEquals("getBookedCoachSeats did not return the correct value. Total 3, Booked 3, Available 0",
					3, (int) getBookedCoachSeats.invoke(airplane));

			// Reserve one more than available
			airplane = constructor.newInstance("ABC123", 2, 3);
			firstClassResult = (boolean) reserveSeats.invoke(airplane, new Object[] { true, 3 });
			coachResult = (boolean) reserveSeats.invoke(airplane, new Object[] { false, 4 });
			assertFalse("reserveSeats should return false if seats cannot be booked.", firstClassResult);
			assertFalse("reserveSeats should return false if seats cannot be booked.", coachResult);
			assertEquals("AvailableFirstClassSeats did not return the correct value. Total 2, Booked 0, Available 2",
					2, (int) getAvailableFirstClassSeats.invoke(airplane));
			assertEquals("getBookedFirstClassSeats did not return the correct value. Total 2, Booked 0, Available 2",
					0, (int) getBookedFirstClassSeats.invoke(airplane));
			assertEquals("getAvailableCoachSeats did not return the correct value. Total 3, Booked 0, Available 3",
					3, (int) getAvailableCoachSeats.invoke(airplane));
			assertEquals("getBookedCoachSeats did not return the correct value. Total 3, Booked 0, Available 3",
					0, (int) getBookedCoachSeats.invoke(airplane));
		} catch (Exception e) {
			fail("An unknown error occurred with Airplane class: " + e.getMessage());
		}
	}

}

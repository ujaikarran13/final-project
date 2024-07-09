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
public class ElevatorTest {

	@BeforeClass
	public static void elevatorVerifyClassDefinition() {
		Class klass = Elevator.class;

		// Verify not abstract
		assertFalse("Elevator class must not be abstract. Remove the 'abstract' modifier on Elevator.", Modifier.isAbstract(klass.getModifiers()));

		// Verify constructor(s)
		Constructor<Elevator> constructor = SafeReflection.getConstructor(klass, Integer.TYPE);
		assertTrue("Elevator class does not have the required constructor(int)", constructor != null);

		// Verify attributes
		EncapsulationTestHelpers.verifyClassField(Elevator.class, "currentFloor", Integer.TYPE, true, false);
		EncapsulationTestHelpers.verifyClassField(Elevator.class, "numberOfFloors", Integer.TYPE, true, false);
		EncapsulationTestHelpers.verifyClassField(Elevator.class, "isDoorOpen", Boolean.TYPE, true, false);
	}

	@Test
	public void elevatorConstructor() {
		Class klass = Elevator.class;

		Constructor<Elevator> constructor = SafeReflection.getConstructor(klass, Integer.TYPE);
		assertTrue("You do not have the required constructor(int)", constructor != null);
		try {
			Elevator elevator = constructor.newInstance(3);
			Method getCurrentFloor = SafeReflection.getMethod(klass, "getCurrentFloor");
			Method getNumberOfFloors = SafeReflection.getMethod(klass, "getNumberOfFloors");
			Method isDoorOpen = SafeReflection.getMethod(klass, "isDoorOpen");

			assertTrue("getCurrentFloor for a new Elevator should return 1.", 1 == (int) getCurrentFloor.invoke(elevator));
			assertTrue("getNumberOfFloors should be equal to the argument passed into the constructor.",
					3 == (int) getNumberOfFloors.invoke(elevator));
			assertFalse("The door should be closed for a new Elevator.", (boolean) isDoorOpen.invoke(elevator));
		} catch (Exception e) {
			fail("An unknown error occurred with Elevator class: " + e.getMessage());
		}
	}

	@Test
	public void elevatorOpenDoorTests() {
		Class klass = Elevator.class;

		Constructor<Elevator> constructor = SafeReflection.getConstructor(klass, Integer.TYPE);
		assertTrue("You do not have the required constructor(int)", constructor != null);
		try {
			Elevator elevator = constructor.newInstance(3);
			Method openDoor = SafeReflection.getMethod(klass, "openDoor");
			Method closeDoor = SafeReflection.getMethod(klass, "closeDoor");
			Method isDoorOpen = SafeReflection.getMethod(klass, "isDoorOpen");

			assertNotNull("openDoor method is missing", openDoor);

			openDoor.invoke(elevator);
			assertTrue("The door should be open after calling openDoor.", (boolean) isDoorOpen.invoke(elevator));

			assertNotNull("closeDoor method is missing", closeDoor);

			closeDoor.invoke(elevator);
			assertFalse("The door should be closed after calling closeDoor.", (boolean) isDoorOpen.invoke(elevator));
		} catch (Exception e) {
			fail("An unknown error occurred with Elevator class: " + e.getMessage());
		}
	}

	@Test
	public void elevatorGoUpAndDownTests() {
		Class klass = Elevator.class;

		Constructor<Elevator> constructor = SafeReflection.getConstructor(klass, Integer.TYPE);
		assertTrue("You do not have the required constructor(int)", constructor != null);
		try {
			Elevator elevator = constructor.newInstance(5);
			Method openDoor = SafeReflection.getMethod(klass, "openDoor");
			Method closeDoor = SafeReflection.getMethod(klass, "closeDoor");
			Method goUp = SafeReflection.getMethod(klass, "goUp", Integer.TYPE);
			Method goDown = SafeReflection.getMethod(klass, "goDown", Integer.TYPE);
			Method getCurrentFloor = SafeReflection.getMethod(klass, "getCurrentFloor");
			Method isDoorOpen = SafeReflection.getMethod(klass, "isDoorOpen");

			assertNotNull("openDoor method is missing", openDoor);
			assertNotNull("closeDoor method is missing", closeDoor);
			assertNotNull("goUp method is missing", goUp);

			// Newly instantiated elevator is on floor 1 and door is closed
			assertTrue("Newly instantiated elevator is not on floor 1.",
					1 == (int) getCurrentFloor.invoke(elevator));
			assertFalse("Newly instantiated elevator's door is not closed.", (boolean) isDoorOpen.invoke(elevator));

			// Move up to floor 2
			goUp.invoke(elevator, new Object[] { 2 });
			assertTrue("The elevator did not go up to floor 2.",
					2 == (int) getCurrentFloor.invoke(elevator));

			// Open the door
			openDoor.invoke(elevator);
			assertTrue("The elevator door did not open.", (boolean) isDoorOpen.invoke(elevator));

			// Attempt to move up to floor 3 with door open
			goUp.invoke(elevator, new Object[] { 3 });
			assertTrue("The elevator moved from floor 2 to floor 3 with the door open.",
					2 == (int) getCurrentFloor.invoke(elevator));

			// Close the door
			closeDoor.invoke(elevator);
			assertFalse("The elevator door did not close.", (boolean) isDoorOpen.invoke(elevator));

			// Move up to floor 4
			goUp.invoke(elevator, new Object[] { 4 });
			assertTrue("The elevator did not move up to floor 4.",
					4 == (int) getCurrentFloor.invoke(elevator));

			// Attempt to move "up" to floor 3 from floor 4
			goUp.invoke(elevator, new Object[] { 3 });
			assertTrue("The elevator moved \"up\" to floor 3 from floor 4.",
					4 == (int) getCurrentFloor.invoke(elevator));

			// Move up to the top floor
			goUp.invoke(elevator, new Object[] { 5 });
			assertTrue("The elevator did not move up to the top floor.",
					5 == (int) getCurrentFloor.invoke(elevator));

			// Attempt to move past the top floor
			goUp.invoke(elevator, new Object[] { 6 });
			assertTrue("The elevator went past the top floor.", 5 == (int) getCurrentFloor.invoke(elevator));
			
			assertNotNull("goDown method is missing", goDown);

			// Move down to floor 3 from floor 5
			goDown.invoke(elevator, new Object[] { 3 });
			assertTrue("The elevator did not move down to the floor 3.",
					3 == (int) getCurrentFloor.invoke(elevator));

			// Open the door
			openDoor.invoke(elevator);
			assertTrue("The elevator door did not open.", (boolean) isDoorOpen.invoke(elevator));

			// Attempt to move down to floor 2 with door open
			goDown.invoke(elevator, new Object[] { 2 });
			assertTrue("The elevator moved from floor 3 to floor 2 with the door open.",
					3 == (int) getCurrentFloor.invoke(elevator));

			// Close the door
			closeDoor.invoke(elevator);
			assertFalse("The elevator door did not close.", (boolean) isDoorOpen.invoke(elevator));

			// Move down to the floor 2
			goDown.invoke(elevator, new Object[] { 2 });
			assertTrue("The elevator did not move down to floor 2.",
					2 == (int) getCurrentFloor.invoke(elevator));

			// Attempt to move "down" from floor 2 to floor 3
			goDown.invoke(elevator, new Object[] { 3 });
			assertTrue("The elevator moved \"down\" to floor 3 from floor 2.",
					2 == (int) getCurrentFloor.invoke(elevator));

			// Move down to the bottom floor
			goDown.invoke(elevator, new Object[] { 1 });
			assertTrue("The elevator did not move down to the bottom floor.",
					1 == (int) getCurrentFloor.invoke(elevator));

			// Attempt to move below the bottom floor
			goDown.invoke(elevator, new Object[] { 0 });
			assertTrue("The elevator went below the bottom floor.", 1 == (int) getCurrentFloor.invoke(elevator));

		} catch (Exception e) {
			fail("An unknown error occurred with Elevator class: " + e.getMessage());
		}
	}
}

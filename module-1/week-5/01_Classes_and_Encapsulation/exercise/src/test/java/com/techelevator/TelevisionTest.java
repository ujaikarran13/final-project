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
public class TelevisionTest {

	@BeforeClass
	public static void televisionVerifyClassDefinition() {
		Class klass = Television.class;

		// Verify not abstract
		assertFalse("Television class must not be abstract. Remove the 'abstract' modifier on Television.", Modifier.isAbstract(klass.getModifiers()));

		// Verify constructor(s)
		Constructor<Television> constructor = SafeReflection.getConstructor(klass);
		assertTrue("Television class does not have the required constructor()", constructor != null);

		// Verify attributes
		EncapsulationTestHelpers.verifyClassField(Television.class, "isOn", Boolean.TYPE, true, false);
		EncapsulationTestHelpers.verifyClassField(Television.class, "currentChannel", Integer.TYPE, true, false);
		EncapsulationTestHelpers.verifyClassField(Television.class, "currentVolume", Integer.TYPE, true, false);
	}

	@Test
	public void televisionConstructor() {
		Class klass = Television.class;

		Constructor<Television> constructor = SafeReflection.getConstructor(klass);
		assertTrue("You do not have the required constructor()", constructor != null);
		try {
			Television television = constructor.newInstance();
			Method isOn = SafeReflection.getMethod(klass, "isOn");
			Method getCurrentChannel = SafeReflection.getMethod(klass, "getCurrentChannel");
			Method getCurrentVolume = SafeReflection.getMethod(klass, "getCurrentVolume");
			assertFalse("A new TV should be turned off by default.", (boolean) isOn.invoke(television));
			assertTrue("A new TV should have currentChannel set to 3 by default.",
					3 == (int) getCurrentChannel.invoke(television));
			assertTrue("A new TV should have currentVolume set to 2 by default.",
					2 == (int) getCurrentVolume.invoke(television));
		} catch (Exception e) {
			fail("An unknown error occurred with Television class: " + e.getMessage());
		}
	}

	@Test
	public void televisionTurnOnOffTests() {
		Class klass = Television.class;

		Constructor<Television> constructor = SafeReflection.getConstructor(klass);
		assertTrue("You do not have the required constructor()", constructor != null);
		try {
			Television television = constructor.newInstance();
			Method isOn = SafeReflection.getMethod(klass, "isOn");
			Method turnOn = SafeReflection.getMethod(klass, "turnOn");
			Method turnOff = SafeReflection.getMethod(klass, "turnOff");
			Method getCurrentChannel = SafeReflection.getMethod(klass, "getCurrentChannel");
			Method getCurrentVolume = SafeReflection.getMethod(klass, "getCurrentVolume");

			assertNotNull("turnOn method is missing", turnOn);
			assertNotNull("turnOff method is missing", turnOff);
			assertNotNull("getCurrentChannel method is missing", getCurrentChannel);
			assertNotNull("getCurrentVolume method is missing", getCurrentVolume);

			turnOn.invoke(television);
			assertTrue("The TV was turned on. isOn should now be true.", (boolean) isOn.invoke(television));
			assertTrue("The TV was turned on. currentChannel should now be 3.",
					3 == (int) getCurrentChannel.invoke(television));
			assertTrue("The TV was turned on. currentVolume should now be 2.",
					2 == (int) getCurrentVolume.invoke(television));

			turnOff.invoke(television);
			assertFalse("The TV was turned off. isOn should now be false.", (boolean) isOn.invoke(television));
		} catch (Exception e) {
			fail("An unknown error occurred with Television class: " + e.getMessage());
		}
	}

	@Test
	public void televisionChangeChannelTests() {
		Class klass = Television.class;

		Constructor<Television> constructor = SafeReflection.getConstructor(klass);
		assertTrue("You do not have the required constructor()", constructor != null);
		try {
			Television television = constructor.newInstance();
			Method turnOn = SafeReflection.getMethod(klass, "turnOn");
			Method changeChannel = SafeReflection.getMethod(klass, "changeChannel", Integer.TYPE);
			Method getCurrentChannel = SafeReflection.getMethod(klass, "getCurrentChannel");

			assertNotNull("turnOn method is missing", turnOn);
			assertNotNull("changeChannel method is missing", changeChannel);

			changeChannel.invoke(television, new Object[] { 5 });
			assertTrue("The TV was not turned on. currentChannel should not have changed.",
					3 == (int) getCurrentChannel.invoke(television));

			turnOn.invoke(television);
			changeChannel.invoke(television, new Object[] { 5 });
			assertTrue("The TV is turned on. The channel was changed to 5. currentChannel should now be 5.",
					5 == (int) getCurrentChannel.invoke(television));
		} catch (Exception e) {
			fail("An unknown error occurred with Television class: " + e.getMessage());
		}
	}

	@Test
	public void televisionChannelUpTests() {
		Class klass = Television.class;

		Constructor<Television> constructor = SafeReflection.getConstructor(klass);
		assertTrue("You do not have the required constructor()", constructor != null);
		try {
			Television television = constructor.newInstance();
			Method turnOn = SafeReflection.getMethod(klass, "turnOn");
			Method getCurrentChannel = SafeReflection.getMethod(klass, "getCurrentChannel");
			Method channelUp = SafeReflection.getMethod(klass, "channelUp");

			assertNotNull("turnOn method is missing", turnOn);
			assertNotNull("getCurrentChannel method is missing", getCurrentChannel);
			assertNotNull("channelUp method is missing", channelUp);

			channelUp.invoke(television);
			assertTrue("channelUp should only change if the TV has been turned on.",
					3 == (int) getCurrentChannel.invoke(television));

			turnOn.invoke(television);
			channelUp.invoke(television);
			assertTrue("channelUp should have incremented currentChannel by 1.",
					4 == (int) getCurrentChannel.invoke(television));
		} catch (Exception e) {
			fail("An unknown error occurred with Television class: " + e.getMessage());
		}
	}

	@Test
	public void televisionChannelUpPast18Tests() {
		Class klass = Television.class;

		Constructor<Television> constructor = SafeReflection.getConstructor(klass);
		assertTrue("You do not have the required constructor()", constructor != null);
		try {
			Television television = constructor.newInstance();
			Method turnOn = SafeReflection.getMethod(klass, "turnOn");
			Method changeChannel = SafeReflection.getMethod(klass, "changeChannel", Integer.TYPE);
			Method getCurrentChannel = SafeReflection.getMethod(klass, "getCurrentChannel");
			Method channelUp = SafeReflection.getMethod(klass, "channelUp");

			assertNotNull("turnOn method is missing", turnOn);
			assertNotNull("changeChannel method is missing", changeChannel);
			assertNotNull("channelUp method is missing", channelUp);

			turnOn.invoke(television);
			changeChannel.invoke(television, new Object[] { 17 });

			channelUp.invoke(television);
			assertTrue("channelUp should have incremented currentChannel by 1.",
					18 == (int) getCurrentChannel.invoke(television));

			channelUp.invoke(television);
			assertTrue("channelUp should not let currentChannel go past 18. Reset to 3.",
					3 == (int) getCurrentChannel.invoke(television));

		} catch (Exception e) {
			fail("An unknown error occurred with Television class: " + e.getMessage());
		}
	}

	@Test
	public void televisionChannelDownTests() {
		Class klass = Television.class;

		Constructor<Television> constructor = SafeReflection.getConstructor(klass);
		assertTrue("You do not have the required constructor()", constructor != null);
		try {
			Television television = constructor.newInstance();
			Method turnOn = SafeReflection.getMethod(klass, "turnOn");
			Method changeChannel = SafeReflection.getMethod(klass, "changeChannel", Integer.TYPE);
			Method getCurrentChannel = SafeReflection.getMethod(klass, "getCurrentChannel");
			Method channelDown = SafeReflection.getMethod(klass, "channelDown");

			assertNotNull("turnOn method is missing", turnOn);
			assertNotNull("changeChannel method is missing", changeChannel);
			assertNotNull("channelDown method is missing", channelDown);

			channelDown.invoke(television);
			assertTrue("channelDown should only change if the TV has been turned on.",
					3 == (int) getCurrentChannel.invoke(television));

			turnOn.invoke(television);
			changeChannel.invoke(television, new Object[] { 5 });
			channelDown.invoke(television);
			assertTrue("channelDown should have decremented currentChannel by 1.",
					4 == (int) getCurrentChannel.invoke(television));
		} catch (Exception e) {
			fail("An unknown error occurred with Television class: " + e.getMessage());
		}
	}

	@Test
	public void televisionChannelDownPast3Tests() {
		Class klass = Television.class;

		Constructor<Television> constructor = SafeReflection.getConstructor(klass);
		assertTrue("You do not have the required constructor()", constructor != null);
		try {
			Television television = constructor.newInstance();
			Method turnOn = SafeReflection.getMethod(klass, "turnOn");
			Method changeChannel = SafeReflection.getMethod(klass, "changeChannel", Integer.TYPE);
			Method getCurrentChannel = SafeReflection.getMethod(klass, "getCurrentChannel");
			Method channelDown = SafeReflection.getMethod(klass, "channelDown");

			assertNotNull("turnOn method is missing", turnOn);
			assertNotNull("changeChannel method is missing", changeChannel);
			assertNotNull("channelDown method is missing", channelDown);

			turnOn.invoke(television);
			changeChannel.invoke(television, new Object[] { 4 });

			channelDown.invoke(television);
			assertTrue("channelUp should have decremented currentChannel by 1.",
					3 == (int) getCurrentChannel.invoke(television));

			channelDown.invoke(television);
			assertTrue("channelDown should not let currentChannel go past 3. Reset to 18.",
					18 == (int) getCurrentChannel.invoke(television));

		} catch (Exception e) {
			fail("An unknown error occurred with Television class: " + e.getMessage());
		}
	}

	@Test
	public void televisionVolumeRaiseTests() {
		Class klass = Television.class;

		Constructor<Television> constructor = SafeReflection.getConstructor(klass);
		assertTrue("You do not have the required constructor()", constructor != null);
		try {
			Television television = constructor.newInstance();
			Method turnOn = SafeReflection.getMethod(klass, "turnOn");
			Method getCurrentVolume = SafeReflection.getMethod(klass, "getCurrentVolume");
			Method raiseVolume = SafeReflection.getMethod(klass, "raiseVolume");

			assertNotNull("turnOn method is missing", turnOn);
			assertNotNull("raiseVolume method is missing", raiseVolume);

			raiseVolume.invoke(television);
			assertTrue("currentVolume should only be increased if the TV is on.",
					2 == (int) getCurrentVolume.invoke(television));

			turnOn.invoke(television);
			raiseVolume.invoke(television);
			assertTrue("raiseVolume should have incremented current volume to 3.",
					3 == (int) getCurrentVolume.invoke(television));

			raiseVolume.invoke(television);
			assertTrue("raiseVolume should have incremented current volume to 4.",
					4 == (int) getCurrentVolume.invoke(television));

			raiseVolume.invoke(television); // 5
			raiseVolume.invoke(television); // 6
			raiseVolume.invoke(television); // 7
			raiseVolume.invoke(television); // 8
			raiseVolume.invoke(television); // 9
			raiseVolume.invoke(television); // 10

			assertTrue("raiseVolume should have incremented current volume to 10.",
					10 == (int) getCurrentVolume.invoke(television));

			raiseVolume.invoke(television); // 10 (max)
			assertTrue("raiseVolume should not let current volume go above 10.",
					10 == (int) getCurrentVolume.invoke(television));

		} catch (Exception e) {
			fail("An unknown error occurred with Television class: " + e.getMessage());
		}
	}

	@Test
	public void televisionVolumeLowerTests() {
		Class klass = Television.class;

		Constructor<Television> constructor = SafeReflection.getConstructor(klass);
		assertTrue("You do not have the required constructor()", constructor != null);
		try {

			Television television = constructor.newInstance();
			Method turnOn = SafeReflection.getMethod(klass, "turnOn");
			Method getCurrentVolume = SafeReflection.getMethod(klass, "getCurrentVolume");
			Method lowerVolume = SafeReflection.getMethod(klass, "lowerVolume");

			assertNotNull("turnOn method is missing", turnOn);
			assertNotNull("lowerVolume method is missing", lowerVolume);

			lowerVolume.invoke(television);
			assertTrue("currentVolume should only be decremented if the TV is on.",
					2 == (int) getCurrentVolume.invoke(television));

			turnOn.invoke(television);
			lowerVolume.invoke(television);
			assertTrue("lowerVolume should have decremented current volume to 1.",
					1 == (int) getCurrentVolume.invoke(television));

			lowerVolume.invoke(television);
			assertTrue("lowerVolume should have decremented current volume to 0.",
					0 == (int) getCurrentVolume.invoke(television));

			lowerVolume.invoke(television);
			assertTrue("lowerVolume should not let current volume go below 0.",
					0 == (int) getCurrentVolume.invoke(television));

		} catch (Exception e) {
			fail("An unknown error occurred with Television class: " + e.getMessage());
		}
	}
}

package com.techelevator;

import org.junit.*;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FruitTreeTest {

	@BeforeClass
	public static void fruitTreeVerifyClassDefinition() {
		Class klass = FruitTree.class;

		// Verify not abstract
		assertFalse("FruitTree class must not be abstract. Remove the 'abstract' modifier on FruitTree.", Modifier.isAbstract(klass.getModifiers()));

		// Verify constructor(s)
		Constructor<FruitTree> constructor = SafeReflection.getConstructor(klass, String.class, Integer.TYPE);
		assertTrue("FruitTree class does not have the required constructor(String, int)", constructor != null);

		// Verify attributes
		EncapsulationTestHelpers.verifyClassField(FruitTree.class, "typeOfFruit", String.class, true, false);
		EncapsulationTestHelpers.verifyClassField(FruitTree.class, "piecesOfFruitLeft", Integer.TYPE, true, false);
	}

	@Test
	public void fruitTreeConstructorTest() {
		Class klass = FruitTree.class;

		Constructor<FruitTree> constructor = SafeReflection.getConstructor(klass, String.class, Integer.TYPE);
		assertTrue("You do not have the required constructor(String, int)", constructor != null);
		try {
			FruitTree fruitTree = constructor.newInstance("Apple", 97);
			Method getPiecesOfFruitLeft = SafeReflection.getMethod(klass, "getPiecesOfFruitLeft");
			Method getTypeOfFruit = SafeReflection.getMethod(klass, "getTypeOfFruit");

			assertTrue("Passed Apple into constructor and expected typeOfFruit equal Apple",
					"Apple".equals((String) getTypeOfFruit.invoke(fruitTree)));
			assertTrue("Passed 97 into constructor and expected piecesOfFruitLeft equal 97",
					97 == (int) getPiecesOfFruitLeft.invoke(fruitTree));
		} catch (Exception e) {
			fail("An unknown error occurred with FruitTree class: " + e.getMessage());
		}
	}

	@Test
	public void fruitTreePickFruitTests() {
		Class klass = FruitTree.class;

		Constructor<FruitTree> constructor = SafeReflection.getConstructor(klass, String.class, Integer.TYPE);
		assertTrue("You do not have the required constructor(String, int)", constructor != null);
		try {
			FruitTree fruitTree = constructor.newInstance("Apple", 3);
			Method pickFruit = SafeReflection.getMethod(klass, "pickFruit", Integer.TYPE);
			Method getPiecesOfFruitLeft = SafeReflection.getMethod(klass, "getPiecesOfFruitLeft");

			assertNotNull("pickFruit method is missing", pickFruit);

			assertTrue("pickFruit(int) should return TRUE when pieces remain",
					(boolean) pickFruit.invoke(fruitTree, new Object[] { 2 }));
			assertTrue("Tree started with 3 pieces of fruit. 2 were picked, 1 should be remaining",
					1 == (int) getPiecesOfFruitLeft.invoke(fruitTree));

			assertTrue("pickFruit(int) should return TRUE when pieces remain",
					(boolean) pickFruit.invoke(fruitTree, new Object[] { 1 }));
			assertTrue("Tree started with 1 piece of fruit. 1 were picked, 0 should be remaining",
					0 == (int) getPiecesOfFruitLeft.invoke(fruitTree));

			assertFalse("pickFruit(int) should return FALSE when not enough remaining pieces of fruit.",
					(boolean) pickFruit.invoke(fruitTree, new Object[] { 1 }));
			assertTrue("Tree had 0 pieces of fruit. 1 was not picked, 0 should be remaining",
					0 == (int) getPiecesOfFruitLeft.invoke(fruitTree));
		} catch (Exception e) {
			fail("An unknown error occurred with FruitTree class: " + e.getMessage());
		}
	}

}

package com.techelevator;

import org.junit.*;
import org.junit.runners.MethodSorters;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AssessmentPart1Test {

	AssessmentPart1 assessmentPart1 = new AssessmentPart1();


	/*
	 */
	@Test
	public void Q01_calculateFraction_test() {
		assertEquals("Q01_calculateFraction(6, 3) should == 2.0", 2.0, assessmentPart1.Q01_calculateFraction(6, 3), 0.001);
		assertEquals("Q01_calculateFraction(5, 2) should == 2.5", 2.5, assessmentPart1.Q01_calculateFraction(5, 2), 0.001);
		assertEquals("Q01_calculateFraction(7, 3) should == 2.333", 2.333, assessmentPart1.Q01_calculateFraction(7, 3), 0.001);
		assertEquals("Q01_calculateFraction(94, 14) should == 6.714", 6.714, assessmentPart1.Q01_calculateFraction(94, 14), 0.001);
	}

	/*
	 */
	@Test
	public void Q02_isSumEven_test() {
		assertEquals("Q02_isSumEven(14,8) should == true", true, assessmentPart1.Q02_isSumEven(14, 8));
		assertEquals("Q02_isSumEven(13,15) should == true", true, assessmentPart1.Q02_isSumEven(13, 15));
		assertEquals("Q02_isSumEven(13,16) should == false", false, assessmentPart1.Q02_isSumEven(13, 16));
		assertEquals("Q02_isSumEven(-10, 22) should == true", true, assessmentPart1.Q02_isSumEven(-10, 22));
		assertEquals("Q02_isSumEven(-3,10) should == false", false, assessmentPart1.Q02_isSumEven(-3, 10));
	}

	/*
	 */
	@Test
	public void Q03_moviePrice_test() {
		assertEquals("Q03_moviePrice(8) should == 7", 7, assessmentPart1.Q03_moviePrice(8));
		assertEquals("Q03_moviePrice(21) should == 12", 12, assessmentPart1.Q03_moviePrice(21));
		assertEquals("Q03_moviePrice(12) should == 7", 7, assessmentPart1.Q03_moviePrice(12));
		assertEquals("Q03_moviePrice(13) should == 12", 12, assessmentPart1.Q03_moviePrice(13));
		assertEquals("Q03_moviePrice(99) should == 12", 12, assessmentPart1.Q03_moviePrice(99));
	}

	/*
	 */
	@Test
	public void Q04_moviePriceMatinee_test() {
		assertEquals("Q04_moviePriceMatinee(12,false,false) should == 7", 7, assessmentPart1.Q04_moviePriceMatinee(12, false, false));
		assertEquals("Q04_moviePriceMatinee(12,true,false) should == 5",  5, assessmentPart1.Q04_moviePriceMatinee(12, true, false));
		assertEquals("Q04_moviePriceMatinee(12,false,true) should == 7",  7, assessmentPart1.Q04_moviePriceMatinee(12, false, true));
		assertEquals("Q04_moviePriceMatinee(12,true,true) should == 7",   7, assessmentPart1.Q04_moviePriceMatinee(12, true, true));
		assertEquals("Q04_moviePriceMatinee(13,false,false) should == 12", 12, assessmentPart1.Q04_moviePriceMatinee(13, false, false));
		assertEquals("Q04_moviePriceMatinee(13,true,false) should == 8",  8, assessmentPart1.Q04_moviePriceMatinee(13, true, false));
		assertEquals("Q04_moviePriceMatinee(13,false,true) should == 12",  12, assessmentPart1.Q04_moviePriceMatinee(13, false, true));
		assertEquals("Q04_moviePriceMatinee(13,true,true) should == 12",   12, assessmentPart1.Q04_moviePriceMatinee(13, true, true));
	}

	/*
	 */
	@Test
	public void Q05_sumOfOddNumbersBetween_test() {
		assertEquals("Q05_sumOfOddNumbersBetween(101,103) should == 204", 204, assessmentPart1.Q05_sumOfOddNumbersBetween(101, 103));
		assertEquals("Q05_sumOfOddNumbersBetween(100,104) should == 204", 204, assessmentPart1.Q05_sumOfOddNumbersBetween(100, 104));
		assertEquals("Q05_sumOfOddNumbersBetween(0,200) should == 10000", 10000, assessmentPart1.Q05_sumOfOddNumbersBetween(0, 200));
		assertEquals("Q05_sumOfOddNumbersBetween(0,0) should == 0", 0, assessmentPart1.Q05_sumOfOddNumbersBetween(0, 0));
		assertEquals("Q05_sumOfOddNumbersBetween(-12,-1) should == -36", -36, assessmentPart1.Q05_sumOfOddNumbersBetween(-12, -1));
		assertEquals("Q05_sumOfOddNumbersBetween(25,5) should == 0", 0, assessmentPart1.Q05_sumOfOddNumbersBetween(25, 5));
	}

	/*
	 */
	@Test
	public void Q06_firstNCharacters_test() {
		assertEquals("Q06_firstNCharacters(Elephant, 3) should == Ele", "Ele", assessmentPart1.Q06_firstNCharacters("Elephant", 3));
		assertEquals("Q06_firstNCharacters(No, 3) should == No", "No", assessmentPart1.Q06_firstNCharacters("No", 3));
		assertEquals("Q06_firstNCharacters(Yes, 3) should == Yes", "Yes", assessmentPart1.Q06_firstNCharacters("Yes", 3));
		assertEquals("Q06_firstNCharacters(<empty string>, 5) should == <empty string>", "", assessmentPart1.Q06_firstNCharacters("", 5));
		assertEquals("Q06_firstNCharacters(Submarine, 0) should == <empty string>", "", assessmentPart1.Q06_firstNCharacters("Submarine", 0));

	}

	/*
	 */
	@Test
	public void Q07_spaceReplacer_test() {
		assertEquals("Q07_spaceReplacer(George Washington, _) should == George_Washington", "George_Washington", assessmentPart1.Q07_spaceReplacer("George Washington", "_"));
		assertEquals("Q07_spaceReplacer(Stop Wait Listen , ! ) should == Stop! Wait! Listen! ", "Stop! Wait! Listen! ", assessmentPart1.Q07_spaceReplacer("Stop Wait Listen ", "! "));
		assertEquals("Q07_spaceReplacer(The quick brown fox jumped over the lazy dog, -) should == The-quick-brown-fox-jumped-over-the-lazy-dog", "The-quick-brown-fox-jumped-over-the-lazy-dog", assessmentPart1.Q07_spaceReplacer("The quick brown fox jumped over the lazy dog", "-"));
		assertEquals("Q07_spaceReplacer(Supercalifragilisticexpialidocious, -) should == Supercalifragilisticexpialidocious", "Supercalifragilisticexpialidocious", assessmentPart1.Q07_spaceReplacer("Supercalifragilisticexpialidocious", "-"));
	}

	/*
	 */
	@Test
	public void Q08_convertToFahrenheit_test() {
		assertEquals("Q08_convertToFahrenheit(0.0) should == 32.0", 32.0, assessmentPart1.Q08_convertToFahrenheit(0.0), 0.001);
		assertEquals("Q08_convertToFahrenheit(10.0) should == 50.0", 50.0, assessmentPart1.Q08_convertToFahrenheit(10.0), 0.001);
		assertEquals("Q08_convertToFahrenheit(20.0) should == 68.0", 68.0, assessmentPart1.Q08_convertToFahrenheit(20.0), 0.001);
		assertEquals("Q08_convertToFahrenheit(100.0) should == 212.0", 212.0, assessmentPart1.Q08_convertToFahrenheit(100.0), 0.001);
		assertEquals("Q08_convertToFahrenheit(-40.0) should == -40.0", -40.0, assessmentPart1.Q08_convertToFahrenheit(-40.0), 0.001);
	}

	/*
	 */
	@Test
	public void Q09_convertToCelsius_test() {
		assertEquals("Q09_convertToCelsius(32.0) should == 0.0", 0.0, assessmentPart1.Q09_convertToCelsius(32.0), 0.001);
		assertEquals("Q09_convertToCelsius(50.0) should == 10.0", 10.0, assessmentPart1.Q09_convertToCelsius(50.0), 0.001);
		assertEquals("Q09_convertToCelsius(68.0) should == 20.0", 20.0, assessmentPart1.Q09_convertToCelsius(68.0), 0.001);
		assertEquals("Q09_convertToCelsius(212.0) should == 100.0", 100.0, assessmentPart1.Q09_convertToCelsius(212.0), 0.001);
		assertEquals("Q09_convertToCelsius(-40.0) should == -40.0", -40.0, assessmentPart1.Q09_convertToCelsius(-40.0), 0.001);
	}

	/*
	 */
	@Test
	public void Q10_swapFirstAndLastElements_test() {
		assertArrayEquals("Q10_swapFirstAndLastElements([1,2,3]) should == [3,2,1]", new int[] { 3, 2, 1 },
				assessmentPart1.Q10_swapFirstAndLastElements(new int[] { 1, 2, 3 }));
		assertArrayEquals("Q10_swapFirstAndLastElements([1,2]) should == [2,1]", new int[] { 2, 1 },
				assessmentPart1.Q10_swapFirstAndLastElements(new int[] { 1, 2 }));
		assertArrayEquals("Q10_swapFirstAndLastElements([100,300,400,500]) should == [500,300,400,100]", new int[] { 500,300,400,100 },
				assessmentPart1.Q10_swapFirstAndLastElements(new int[] { 100,300,400,500 }));
		assertArrayEquals("Q10_swapFirstAndLastElements([100,300,200,400,500]) should == [500,300,200,400,100]", new int[] { 500,300,200,400,100 },
				assessmentPart1.Q10_swapFirstAndLastElements(new int[] { 100,300,200,400,500 }));
		assertArrayEquals("Q10_swapFirstAndLastElements([10,2,10]) should == [10,2,10]", new int[] { 10, 2, 10 },
				assessmentPart1.Q10_swapFirstAndLastElements(new int[] { 10, 2, 10 }));
		assertArrayEquals("Q10_swapFirstAndLastElements([1,2,3,4,5,6,7,8,9]) should == [9,2,3,4,5,6,7,8,1]", new int[] { 9,2,3,4,5,6,7,8,1 },
				assessmentPart1.Q10_swapFirstAndLastElements(new int[] { 1,2,3,4,5,6,7,8,9 }));
	}

	/*
	 */
	@Test
	public void Q11_mealCount_test() {

		runMealTest(
				new String[]{"Beef", "Chicken", "Fish", "Tofu", "Tofu", "Fish"},
				Map.ofEntries(
						Map.entry("Beef", 1),
						Map.entry("Chicken", 1),
						Map.entry("Fish", 2),
						Map.entry("Tofu", 2)
				)
		);

		runMealTest(
				new String[]{"No restrictions", "Gluten-free", "No restrictions", "No restrictions", "No restrictions", "No restrictions", "No restrictions", "Vegan",
						"Vegan", "Vegan", "Gluten-free", "No restrictions", "No restrictions", "No restrictions", "No restrictions", "No restrictions",
						"Gluten-free", "No restrictions", "Kosher", "No restrictions", "No restrictions", "No restrictions", "Vegan", "No restrictions",
				},
				Map.ofEntries(
						Map.entry("No restrictions", 16),
						Map.entry("Gluten-free", 3),
						Map.entry("Kosher", 1),
						Map.entry("Vegan", 4)
				)
		);
	}

	private void runMealTest(String[] meals, Map<String, Integer> expectedResult) {
		// Run the method under test to get the result
		Map<String, Integer> result = assessmentPart1.Q11_mealCount(meals);

		String inputValue = "Q11_mealCount([" + String.join(",", meals) + "])";
		// Compare to expected result

		// First make sure that all the entries in expectedResult are in result
		for(String key : expectedResult.keySet()) {
			assertThat(inputValue + " should return map with entry (" + key + "," + expectedResult.get(key) + ")", result, hasEntry(key, expectedResult.get(key)));
		}

		// Make sure the result map doesn't have extra entries. We could just check length, but the
		// error message wouldn't be useful. So loop through the keys and report which key is extra.
		for(String key : result.keySet()) {
			if (!expectedResult.containsKey(key)) {
				fail(inputValue + " return map should NOT contain key (" + key + ")");
			}
		}
	}


}

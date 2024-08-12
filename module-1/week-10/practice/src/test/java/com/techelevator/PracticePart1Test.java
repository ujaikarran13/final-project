package com.techelevator;

import org.junit.*;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PracticePart1Test {

	PracticePart1 practiceAssessmentPart1 = new PracticePart1();

	/*
	 */
	@Test
	public void Q01_removeFraction_test() {
		assertEquals("Q01_calculateFraction(7.0, 3.0) should == 2.0", 2.0, practiceAssessmentPart1.Q01_removeFraction(7.0, 3.0), 0.0);
		assertEquals("Q01_calculateFraction(8.0, 2.5) should == 3.0", 3.0, practiceAssessmentPart1.Q01_removeFraction(8.0, 2.5), 0.0);
		assertEquals("Q01_calculateFraction(29.44, 7.0) should == 4.0", 4.0, practiceAssessmentPart1.Q01_removeFraction(29.44, 7.0), 0.0);
		assertEquals("Q01_calculateFraction(1.23, 3.21) should == 0.0", 0.0, practiceAssessmentPart1.Q01_removeFraction(1.23, 3.21), 0.0);
	}

	/*
	 */
	@Test
	public void Q02_isWaterTemperatureExtreme_test() {
		assertEquals("Q02_isWaterTemperatureExtreme(33) should be false", false, practiceAssessmentPart1.Q02_isWaterTemperatureExtreme(33));
		assertEquals("Q02_isWaterTemperatureExtreme(32) should be true", true, practiceAssessmentPart1.Q02_isWaterTemperatureExtreme(32));
		assertEquals("Q02_isWaterTemperatureExtreme(31) should be true", true, practiceAssessmentPart1.Q02_isWaterTemperatureExtreme(31));
		assertEquals("Q02_isWaterTemperatureExtreme(211) should be false", false, practiceAssessmentPart1.Q02_isWaterTemperatureExtreme(211));
		assertEquals("Q02_isWaterTemperatureExtreme(212) should be true", true, practiceAssessmentPart1.Q02_isWaterTemperatureExtreme(212));
		assertEquals("Q02_isWaterTemperatureExtreme(213) should be true", true, practiceAssessmentPart1.Q02_isWaterTemperatureExtreme(213));
	}

	/*
	 */
	@Test
	public void Q03_isLeapYear_test() {
		assertEquals("Q03_isLeapYear(2000) should be true", true, practiceAssessmentPart1.Q03_isLeapYear(2000));
		assertEquals("Q03_isLeapYear(1900) should be true", false, practiceAssessmentPart1.Q03_isLeapYear(1900));
		assertEquals("Q03_isLeapYear(2008) should be true", true, practiceAssessmentPart1.Q03_isLeapYear(2008));
		assertEquals("Q03_isLeapYear(2019) should be false", false, practiceAssessmentPart1.Q03_isLeapYear(2019));
		assertEquals("Q03_isLeapYear(2100) should be false", false, practiceAssessmentPart1.Q03_isLeapYear(2100));
		assertEquals("Q03_isLeapYear(333) should be false", false, practiceAssessmentPart1.Q03_isLeapYear(333));
		assertEquals("Q03_isLeapYear(334) should be true", false, practiceAssessmentPart1.Q03_isLeapYear(334));
	}

	/*
	 */
	@Test
	public void Q04_carWashPrice_test() {
		assertEquals("Q04_carWashPrice('B', false, false) should == 8", 8, practiceAssessmentPart1.Q04_carWashPrice('B', false, false));
		assertEquals("Q04_carWashPrice('S', false, false) should == 12", 12, practiceAssessmentPart1.Q04_carWashPrice('S', false, false));
		assertEquals("Q04_carWashPrice('P', false, false) should == 10", 10, practiceAssessmentPart1.Q04_carWashPrice('P', false, false));
		assertEquals("Q04_carWashPrice('P', true, false) should == 9", 9, practiceAssessmentPart1.Q04_carWashPrice('P', true, false));
		assertEquals("Q04_carWashPrice('P', false, true) should == 12", 12, practiceAssessmentPart1.Q04_carWashPrice('P', false, true));
		assertEquals("Q04_carWashPrice('P', true, true) should == 11", 11, practiceAssessmentPart1.Q04_carWashPrice('P', true, true));
	}

	/*
	 */
	@Test
	public void Q05_sumOfNumbersDivisibleBy7Between_test() {
		assertEquals("Q05_sumOfNumbersDivisibleBy7Between(49, 49) should == 49", 49, practiceAssessmentPart1.Q05_sumOfNumbersDivisibleBy7Between(49, 49));
		assertEquals("Q05_sumOfNumbersDivisibleBy7Between(49, 56) should == 105", 105, practiceAssessmentPart1.Q05_sumOfNumbersDivisibleBy7Between(49, 56));
		assertEquals("Q05_sumOfNumbersDivisibleBy7Between(48, 57) should == 105", 105, practiceAssessmentPart1.Q05_sumOfNumbersDivisibleBy7Between(48, 57));
		assertEquals("Q05_sumOfNumbersDivisibleBy7Between(56, 49) should == 0", 0, practiceAssessmentPart1.Q05_sumOfNumbersDivisibleBy7Between(56, 49));
		assertEquals("Q05_sumOfNumbersDivisibleBy7Between(20, 200) should == 2821", 2821, practiceAssessmentPart1.Q05_sumOfNumbersDivisibleBy7Between(20, 200));
		assertEquals("Q05_sumOfNumbersDivisibleBy7Between(-200, -20) should == -2821", -2821, practiceAssessmentPart1.Q05_sumOfNumbersDivisibleBy7Between(-200, -20));
		assertEquals("Q05_sumOfNumbersDivisibleBy7Between(-20, -200) should == 0", 0, practiceAssessmentPart1.Q05_sumOfNumbersDivisibleBy7Between(-20, -200));
	}

	/*
	 */
	@Test
	public void Q06_wordBetweenTags_test() {
		assertEquals("Q06_wordBetweenTags(\"<P>This is between.<P>\", \"<P>\") should == \"This is between.\"",
				"This is between.",
				practiceAssessmentPart1.Q06_stringBetweenTags("<P>This is between.<P>", "<P>"));

		assertEquals("Q06_wordBetweenTags(\"<P>This <Q>is<Q> between.<P>\", \"<Q>\") should == \"is\"",
				"is",
				practiceAssessmentPart1.Q06_stringBetweenTags("<P>This <Q>is<Q> between.<P>", "<Q>"));

		assertEquals("Q06_wordBetweenTags(\"<P>This <Q>is<Q> between.<P>\", \"#P#\") should == \"This <Q>is<Q> between.\"",
				"This <Q>is<Q> between.",
				practiceAssessmentPart1.Q06_stringBetweenTags("\"<P>This <Q>is<Q> between.<P>", "<P>"));

		assertEquals("Q06_wordBetweenTags(\"<P>This is between.<P>\", \"<Q>\") should == \"\" // Tag <Q> not found.",
				"",
				practiceAssessmentPart1.Q06_stringBetweenTags("<P>This is between.<P>", "<Q>"));

		assertEquals("Q06_wordBetweenTags(\"\"<P>This is between.\", \"<P>\") should == \"\" // End tag <P> not found",
				"",
				practiceAssessmentPart1.Q06_stringBetweenTags("<P>This is between.", "<P>"));

		assertEquals("Q06_wordBetweenTags(\"<P><P>\", \"<P>\") should == \"\" // Nothing between the tags.",
				"",
				practiceAssessmentPart1.Q06_stringBetweenTags("", "<P>"));

		assertEquals("Q06_wordBetweenTags(\"\", \"<P>\") should == \"\" // String to search is empty.",
				"",
				practiceAssessmentPart1.Q06_stringBetweenTags("", "<P>"));

		assertEquals("Q06_wordBetweenTags(\"<P>This is between.<P>\", \"\") should == \"\" // Tag to find is empty.",
				"",
				practiceAssessmentPart1.Q06_stringBetweenTags("", "<P>"));

		assertEquals("Q06_wordBetweenTags(\"\", \"\") should == \"\" // Both string to search and tag to find are empty.",
				"",
				practiceAssessmentPart1.Q06_stringBetweenTags("", ""));
	}

	/*
	 */
	@Test
	public void Q07_isPalindrome_test() {
		assertEquals("Q07_isPalindrome(\"level\") should be true", true, practiceAssessmentPart1.Q07_isPalindrome(("level")));
		assertEquals("Q07_isPalindrome(\"seven\") should be false", false, practiceAssessmentPart1.Q07_isPalindrome(("seven")));
		assertEquals("Q07_isPalindrome(\"toot\") should be true", true, practiceAssessmentPart1.Q07_isPalindrome(("toot")));
		assertEquals("Q07_isPalindrome(\"effete\") should be false", false, practiceAssessmentPart1.Q07_isPalindrome(("effete")));
	}

	/*
	 */
	@Test
	public void Q08_allowanceCalculator_test() {
		assertEquals("Q08_allowanceCalculator((7, 100, 40) should == 12", 12, practiceAssessmentPart1.Q08_allowanceCalculator(7,100, 40));
		assertEquals("Q08_allowanceCalculator((7, 100, 0) should == 8", 8, practiceAssessmentPart1.Q08_allowanceCalculator(7,100, 0));
		assertEquals("Q08_allowanceCalculator((7, 0, 40) should == 10", 10, practiceAssessmentPart1.Q08_allowanceCalculator(7,0, 40));
		assertEquals("Q08_allowanceCalculator((0, 100, 40) should == 5", 5, practiceAssessmentPart1.Q08_allowanceCalculator(0,100, 40));
	}

	/*
	 */
	@Test
	public void Q09_isIncreasing_test() {
		assertEquals("Q09_isIncreasing(new int[]{1, 2, 3, 4, 5}) should be true", true, practiceAssessmentPart1.Q09_isIncreasing(new int[]{1, 2, 3, 4, 5}));
		assertEquals("Q09_isIncreasing(new int[]{2, 4, 8, 32, 256}) should be true", true, practiceAssessmentPart1.Q09_isIncreasing(new int[]{2, 4, 8, 32, 256}));
		assertEquals("Q09_isIncreasing(new int[]{2, 4, 8, 32, 31}) should false", false, practiceAssessmentPart1.Q09_isIncreasing(new int[]{2, 4, 8, 32, 31}));
		assertEquals("Q09_isIncreasing(new int[]{2, 4, 8, 7, 256}) should false", false, practiceAssessmentPart1.Q09_isIncreasing(new int[]{2, 4, 8, 7, 256}));
		assertEquals("Q09_isIncreasing(new int[]{2, 1, 8, 32, 256}) should false", false, practiceAssessmentPart1.Q09_isIncreasing(new int[]{2, 1, 8, 32, 256}));
	}

	/*
	 */
	@Test
	public void Q10_reverseList_test() {
		List<Integer> expected = new ArrayList<Integer>(List.of(4, 3, 2, 1));
		List<Integer> actual = practiceAssessmentPart1.Q10_reverseList(new ArrayList<>(List.of(1, 2, 3, 4)));
		assertArrayEquals("Q10_reverseList_test(new ArrayList<>(List.of(1, 2, 3, 4))) should == [4, 3, 2, 1]",
				expected.toArray(), actual.toArray() );

		expected = new ArrayList<Integer>(List.of(933, -11, 2344, 6, 33));
		actual = practiceAssessmentPart1.Q10_reverseList(new ArrayList<>(List.of(33, 6, 2344, -11, 933)));
		assertArrayEquals("Q10_reverseList_test(new ArrayList<>(List.of(33, 6, 2344, -11, 933))) should == [933, -11, 2344, 6, 33]",
				expected.toArray(), actual.toArray() );

		expected = new ArrayList<Integer>(List.of(282, 919));
		actual = practiceAssessmentPart1.Q10_reverseList(new ArrayList<>(List.of(919, 282)));
		assertArrayEquals("Q10_reverseList_test(new ArrayList<>(List.of(55))) should == [282, 919]",
				expected.toArray(), actual.toArray() );

		expected = new ArrayList<Integer>(List.of(55));
		actual = practiceAssessmentPart1.Q10_reverseList(new ArrayList<>(List.of(55)));
		assertArrayEquals("Q10_reverseList_test(new ArrayList<>(List.of(55))) should == [55]",
				expected.toArray(), actual.toArray() );

		expected = new ArrayList<Integer>(List.of());
		actual = practiceAssessmentPart1.Q10_reverseList(new ArrayList<>(List.of()));
		assertArrayEquals("Q10_reverseList_test(new ArrayList<>(List.of())) should == []",
				expected.toArray(), actual.toArray() );
	}

	/*
	 */
	@Test
	public void Q11_idealPets_test() {

		runIdealPetsTest(
				new String[]{},
				Map.ofEntries(
				)
		);

		runIdealPetsTest(
				new String[]{"Dog"},
				Map.ofEntries(
						Map.entry("Dog", 1)
				)
		);

		runIdealPetsTest(
				new String[]{"Dog", "Cat"},
				Map.ofEntries(
						Map.entry("Dog", 1),
						Map.entry("Cat", 1)
				)
		);

		runIdealPetsTest(
				new String[]{"Dog", "Cat", "Dog"},
				Map.ofEntries(
						Map.entry("Dog", 2),
						Map.entry("Cat", 1)
				)
		);

		runIdealPetsTest(
				new String[]{"Cat", "Dog", "Cat"},
				Map.ofEntries(
						Map.entry("Dog", 1),
						Map.entry("Cat", 2)
				)
		);

		runIdealPetsTest(
				new String[]{"Dog", "Cat", "Chicken", "Fish", "Dog", "Dinosaur", "Cat", "Dog", "Llama"},
				Map.ofEntries(
						Map.entry("Dog", 3),
						Map.entry("Chicken", 1),
						Map.entry("Fish", 1),
						Map.entry("Cat", 2),
						Map.entry("Dinosaur", 1),
						Map.entry("Llama", 1)
				)
		);
	}

	private void runIdealPetsTest(String[] suggestedPets, Map<String, Integer> expectedResult) {
		// Run the method under test to get the result
		Map<String, Integer> result = practiceAssessmentPart1.Q11_idealPets(suggestedPets);

		String inputValue = "Q11_mealCount([" + String.join(",", suggestedPets) + "])";
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

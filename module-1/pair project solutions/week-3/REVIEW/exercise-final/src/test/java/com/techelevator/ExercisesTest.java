package com.techelevator;

import java.math.BigDecimal;
import java.util.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.equalTo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExercisesTest {

	final double DOUBLE_FUDGE_FACTOR = 0.001d;

	@Test
	public void exercise01_noStartingVowels() {
		Exercise01 exercise = new Exercise01();

		List<String> input = Arrays.asList("Tooth", "Easy", "Mirror");
		List<String> expected = Arrays.asList("Tooth", "Mirror");
		List<String> actual = exercise.noStartingVowels(input);
		Assert.assertEquals("noStartingVowels({\"Tooth\", \"Easy\", \"Mirror\"}) should return {\"Tooth\", \"Mirror\"}", expected, actual);

		input = Arrays.asList("red", "green", "orange");
		expected = Arrays.asList("red", "green");
		actual = exercise.noStartingVowels(input);
		Assert.assertEquals("noStartingVowels({\"red\", \"green\", \"orange\"}) should return {\"red\", \"green\"}", expected, actual);

		input = Arrays.asList("Call", "Bill", "about", "the", "Elephant");
		expected = Arrays.asList("Call", "Bill", "the");
		actual = exercise.noStartingVowels(input);
		Assert.assertEquals("noStartingVowels({\"Call\", \"Bill\", \"about\", \"the\", \"Elephant\"}) should return {\"Call\", \"Bill\", \"the\"}", expected, actual);

		input = Arrays.asList("Alice", "Bob", "Carol", "David", "Erin", "Frank", "Grace", "Heidi", "Ivan", "Judy");
		expected = Arrays.asList("Bob", "Carol", "David", "Frank", "Grace", "Heidi", "Judy");
		actual = exercise.noStartingVowels(input);
		Assert.assertEquals("noStartingVowels({\"Alice\", \"Bob\", \"Carol\", \"David\", \"Erin\", \"Frank\", \"Grace\", \"Heidi\", \"Ivan\", \"Judy\"}) should return {\"Bob\", \"Carol\", \"David\", \"Frank\", \"Grace\", \"Heidi\", \"Judy\"}", expected, actual);

		input = Arrays.asList("Apple", "banana", "Coconut", "donut", "egg", "Flour");
		expected = Arrays.asList("banana", "Coconut", "donut", "Flour");
		actual = exercise.noStartingVowels(input);
		Assert.assertEquals("noStartingVowels({\"Apple\", \"banana\", \"Coconut\", \"donut\", \"egg\", \"Flour\"}) should return {\"banana\", \"Coconut\", \"donut\", \"Flour\"}", expected, actual);

		input = Arrays.asList("always", "excellent");
		expected = new ArrayList<>();
		actual = exercise.noStartingVowels(input);
		Assert.assertEquals("noStartingVowels({\"always\", \"excellent\"}) should return {}", expected, actual);

		input = Arrays.asList("something", "noteworthy");
		expected = Arrays.asList("something", "noteworthy");
		actual = exercise.noStartingVowels(input);
		Assert.assertEquals("noStartingVowels({\"something\", \"noteworthy\"}) should return {\"something\", \"noteworthy\"}", expected, actual);

		input = new ArrayList<>();
		expected = new ArrayList<>();
		actual = exercise.noStartingVowels(input);
		Assert.assertEquals("noStartingVowels({}) should return {}", expected, actual);
	}

	@Test
	public void exercise02_shuffleList() {
		Exercise02 exercise = new Exercise02();

		List<String> input = Arrays.asList("Tooth", "Easy", "Mirror");
		List<String> expected = Arrays.asList("Easy", "Tooth", "Mirror");
		List<String> actual = exercise.shuffleList(input);
		Assert.assertEquals("shuffleList({\"Tooth\", \"Easy\", \"Mirror\"}) should return {\"Easy\", \"Tooth\", \"Mirror\"}", expected, actual);

		input = Arrays.asList("Apple", "Banana", "Coconut", "Donut", "Egg", "Flour");
		expected = Arrays.asList("Banana", "Donut", "Flour", "Apple", "Coconut", "Egg");
		actual = exercise.shuffleList(input);
		Assert.assertEquals("shuffleList({\"Apple\", \"Banana\", \"Coconut\", \"Donut\", \"Egg\", \"Flour\"}) should return {\"Banana\", \"Donut\", \"Flour\", \"Apple\", \"Coconut\", \"Egg\"}", expected, actual);

		input = Arrays.asList("Call", "Bill", "about", "the", "Elephant");
		expected = Arrays.asList("Bill", "the", "Call", "about", "Elephant");
		actual = exercise.shuffleList(input);
		Assert.assertEquals("shuffleList({\"Call\", \"Bill\", \"about\", \"the\", \"Elephant\"}) should return {\"Bill\", \"the\", \"Call\", \"about\", \"Elephant\"}", expected, actual);

		input = Arrays.asList("Alice", "Bob", "Carol", "David", "Erin", "Frank", "Grace", "Heidi", "Ivan", "Judy");
		expected = Arrays.asList("Bob", "David", "Frank", "Heidi", "Judy", "Alice", "Carol", "Erin", "Grace", "Ivan");
		actual = exercise.shuffleList(input);
		Assert.assertEquals("shuffleList({\"Alice\", \"Bob\", \"Carol\", \"David\", \"Erin\", \"Frank\", \"Grace\", \"Heidi\", \"Ivan\", \"Judy\"}) should return {\"Bob\", \"David\", \"Frank\", \"Heidi\", \"Judy\", \"Alice\", \"Carol\", \"Erin\", \"Grace\", \"Ivan\"}", expected, actual);

		input = Arrays.asList("one element");
		expected = Arrays.asList("one element");
		actual = exercise.shuffleList(input);
		Assert.assertEquals(expected, actual);
		Assert.assertEquals("shuffleList({\"one element\"}) should return {\"one element\"}", expected, actual);

		input = new ArrayList<>();
		expected = new ArrayList<>();
		actual = exercise.shuffleList(input);
		Assert.assertEquals(expected, actual);
		Assert.assertEquals("shuffleList({}) should return {}", expected, actual);
	}

	@Test
	public void exercise03_isAccountOverdrawn() {
		Exercise03 exercise = new Exercise03();

		BigDecimal startingBalance = new BigDecimal("23.37");
		List<BigDecimal> deposits = Arrays.asList(new BigDecimal("15.0"), new BigDecimal("9.21"));
		List<BigDecimal> withdrawals = Arrays.asList(new BigDecimal("3.7"), new BigDecimal("4.62"));
		boolean actual = exercise.isAccountOverdrawn(startingBalance, deposits, withdrawals);
		Assert.assertFalse("isAccountOverdrawn(23.37, {15.0, 9.21}, {3.7, 4.62}) should return false", actual);

		startingBalance = new BigDecimal("109.29");
		deposits = Arrays.asList(new BigDecimal("38.59"), new BigDecimal("242.7"));
		withdrawals = Arrays.asList(new BigDecimal("393.83"), new BigDecimal("5.52"));
		actual = exercise.isAccountOverdrawn(startingBalance, deposits, withdrawals);
		Assert.assertTrue("isAccountOverdrawn(109.29, {38.59, 242.7}, {393.83, 5.52}) should return true", actual);

		startingBalance = new BigDecimal("74.83");
		deposits = Arrays.asList(new BigDecimal("107.1"), new BigDecimal("93.3"));
		withdrawals = Arrays.asList(new BigDecimal("275.23"));
		actual = exercise.isAccountOverdrawn(startingBalance, deposits, withdrawals);
		Assert.assertFalse("isAccountOverdrawn(74.83, {107.1, 93.3}, {275.23}) should return false", actual);

		startingBalance = new BigDecimal("174.98");
		deposits = Arrays.asList(new BigDecimal("27.29"), new BigDecimal("2"));
		withdrawals = Arrays.asList(new BigDecimal("182.95"), new BigDecimal("22.32"));
		actual = exercise.isAccountOverdrawn(startingBalance, deposits, withdrawals);
		Assert.assertTrue("isAccountOverdrawn(174.98, {27.29, 2.0}, {182.95, 22.32}) should return true", actual);

		startingBalance = new BigDecimal("100");
		deposits = new ArrayList<>();
		withdrawals = Arrays.asList(new BigDecimal("50"), new BigDecimal("49"), new BigDecimal("0.99"));
		actual = exercise.isAccountOverdrawn(startingBalance, deposits, withdrawals);
		Assert.assertFalse("isAccountOverdrawn(100.0, {}, {50.0, 49.0, 0.99}) should return false", actual);

		startingBalance = new BigDecimal("100");
		deposits = Arrays.asList(new BigDecimal("50"), new BigDecimal("49"), new BigDecimal("0.99"));
		withdrawals = new ArrayList<>();
		actual = exercise.isAccountOverdrawn(startingBalance, deposits, withdrawals);
		Assert.assertFalse("isAccountOverdrawn(100.0, {50.0, 49.0, 0.99}, {}) should return false", actual);
	}

	@Test
	public void exercise04_getLetterCount() {
		Exercise04 exercise = new Exercise04();

		Map<Character, Integer> expected = Map.of('a', 2, 'b', 1, 'c', 1, 'u', 1, 's', 1);
		Map<Character, Integer> actual = exercise.getLetterCount("abacus");
		Assert.assertThat("getLetterCount(\"abacus\") should return a Map with a size of " + expected.size(), actual.size(), equalTo(expected.size()));
		for (Map.Entry<Character, Integer> e : expected.entrySet()) {
			Assert.assertThat("getLetterCount(\"abacus\") should return {'a': 2, 'b': 1, 'c': 1, 'u': 1, 's': 1}", actual, hasEntry(e.getKey(), e.getValue()));
		}

		expected = Map.of('N', 1, 'i', 2, 'c', 2, 'e', 3, ' ', 1, 'n', 1, 'k', 1, 't', 1, '!', 1);
		actual = exercise.getLetterCount("Nice necktie!");
		Assert.assertThat("getLetterCount(\"Nice necktie!\") should return a Map with a size of " + expected.size(), actual.size(), equalTo(expected.size()));
		for (Map.Entry<Character, Integer> e : expected.entrySet()) {
			Assert.assertThat("getLetterCount(\"Nice necktie!\") should return {'N': 1, 'i': 2, 'c': 2, 'e': 3, ' ': 1, 'n': 1, 'k': 1, 't': 1, '!': 1}", actual, hasEntry(e.getKey(), e.getValue()));
		}

		expected = Map.of('r', 2, 'a', 2, 'c', 2, 'e', 1);
		actual = exercise.getLetterCount("racecar");
		Assert.assertThat("getLetterCount(\"racecar\") should return a Map with a size of " + expected.size(), actual.size(), equalTo(expected.size()));
		for (Map.Entry<Character, Integer> e : expected.entrySet()) {
			Assert.assertThat("getLetterCount(\"racecar\") should return {'r': 2, 'a': 2, 'c': 2, 'e': 1}", actual, hasEntry(e.getKey(), e.getValue()));
		}

		expected = Map.of('a', 8, 'A', 4);
		actual = exercise.getLetterCount("aaaaAAAAaaaa");
		Assert.assertThat("getLetterCount(\"aaaaAAAAaaaa\") should return a Map with a size of " + expected.size(), actual.size(), equalTo(expected.size()));
		for (Map.Entry<Character, Integer> e : expected.entrySet()) {
			Assert.assertThat("getLetterCount(\"aaaaAAAAaaaa\") should return {'a': 8, 'A': 4}", actual, hasEntry(e.getKey(), e.getValue()));
		}

		expected = Map.of('T', 2, 'a', 3, 's', 2, 't', 3, 'y', 1, ' ', 2, 'o', 2, 'm', 1, 'p', 1, 'e', 1);
		actual = exercise.getLetterCount("Tasty Tomato paste");
		Assert.assertThat("getLetterCount(\"Tasty Tomato paste\") should return a Map with a size of " + expected.size(), actual.size(), equalTo(expected.size()));
		for (Map.Entry<Character, Integer> e : expected.entrySet()) {
			Assert.assertThat("getLetterCount(\"Tasty Tomato paste\") should return {'T': 2, 'a': 3, 's': 2, 't': 3, 'y': 1, ' ': 2, 'o': 2, 'm': 1, 'p': 1, 'e': 1}", actual, hasEntry(e.getKey(), e.getValue()));
		}

		expected = new HashMap<>();
		actual = exercise.getLetterCount("");
		Assert.assertThat("getLetterCount(\"\") should return a Map with a size of " + expected.size(), actual.size(), equalTo(expected.size()));
	}

	@Test
	public void exercise05_getTotal() {
		Exercise05 exercise = new Exercise05();

		Map<String, Integer> input = Map.of("total", 29, "item1", 12, "item2", 17);
		int expected = 29;
		int actual = exercise.getTotal(input);
		Assert.assertEquals("getTotal({\"total\": 29, \"item1\": 12, \"item2\": 17}) should return " + expected, expected, actual);

		input = Map.of("Alice", 37, "Bob", 51, "Charlie", 22);
		expected = 110;
		actual = exercise.getTotal(input);
		Assert.assertEquals("getTotal({\"Alice\": 37, \"Bob\": 51, \"Charlie\": 22}) should return " + expected, expected, actual);

		input = Map.of("pizza", 15, "wings", 10, "bread", 7, "total", 32);
		expected = 32;
		actual = exercise.getTotal(input);
		Assert.assertEquals("getTotal({\"pizza\": 15, \"wings\": 10, \"bread\": 7, \"total\": 32}) should return " + expected, expected, actual);

		// ensure values aren't being simply added
		input = Map.of("thing1", 1, "thing2", 1, "total", 99);
		expected = 99;
		actual = exercise.getTotal(input);
		Assert.assertEquals("getTotal({\"thing1\": 1, \"thing2\": 1, \"total\": 99}) should return " + expected, expected, actual);
	}

	@Test
	public void exercise06_calculateTotalWithTax() {
		Exercise06 exercise = new Exercise06();
		final BigDecimal ONE_HUNDRED = new BigDecimal("100");

		BigDecimal expected = new BigDecimal("105.6");
		BigDecimal actual = exercise.calculateTotalWithTax(ONE_HUNDRED, "Arizona");
		Assert.assertEquals("calculateTotalWithTax(100, \"Arizona\") should return " + expected, expected.stripTrailingZeros(), actual.stripTrailingZeros());

		expected = new BigDecimal("107.25");
		actual = exercise.calculateTotalWithTax(ONE_HUNDRED, "California");
		Assert.assertEquals("calculateTotalWithTax(100, \"California\") should return " + expected, expected.stripTrailingZeros(), actual.stripTrailingZeros());

		expected = new BigDecimal("102.9");
		actual = exercise.calculateTotalWithTax(ONE_HUNDRED, "Colorado");
		Assert.assertEquals("calculateTotalWithTax(100, \"Colorado\") should return " + expected, expected.stripTrailingZeros(), actual.stripTrailingZeros());

		expected = new BigDecimal("104");
		actual = exercise.calculateTotalWithTax(ONE_HUNDRED, "Hawaii");
		Assert.assertEquals("calculateTotalWithTax(100, \"Hawaii\") should return " + expected, expected.stripTrailingZeros(), actual.stripTrailingZeros());

		expected = new BigDecimal("106");
		actual = exercise.calculateTotalWithTax(ONE_HUNDRED, "Idaho");
		Assert.assertEquals("calculateTotalWithTax(100, \"Idaho\") should return " + expected, expected.stripTrailingZeros(), actual.stripTrailingZeros());

		expected = new BigDecimal("106.85");
		actual = exercise.calculateTotalWithTax(ONE_HUNDRED, "Nevada");
		Assert.assertEquals("calculateTotalWithTax(100, \"Nevada\") should return " + expected, expected.stripTrailingZeros(), actual.stripTrailingZeros());

		expected = new BigDecimal("104.85");
		actual = exercise.calculateTotalWithTax(ONE_HUNDRED, "Utah");
		Assert.assertEquals("calculateTotalWithTax(100, \"Utah\") should return " + expected, expected.stripTrailingZeros(), actual.stripTrailingZeros());

		expected = new BigDecimal("106.5");
		actual = exercise.calculateTotalWithTax(ONE_HUNDRED, "Washington");
		Assert.assertEquals("calculateTotalWithTax(100, \"Washington\") should return " + expected, expected.stripTrailingZeros(), actual.stripTrailingZeros());

		expected = new BigDecimal("104");
		actual = exercise.calculateTotalWithTax(ONE_HUNDRED, "Wyoming");
		Assert.assertEquals("calculateTotalWithTax(100, \"Wyoming\") should return " + expected, expected.stripTrailingZeros(), actual.stripTrailingZeros());

		// no sales tax or not in table
		expected = ONE_HUNDRED;

		actual = exercise.calculateTotalWithTax(ONE_HUNDRED, "New Jersey");
		Assert.assertEquals("calculateTotalWithTax(100, \"New Jersey\") should return " + expected, expected.stripTrailingZeros(), actual.stripTrailingZeros());

		actual = exercise.calculateTotalWithTax(ONE_HUNDRED, "New Hampshire");
		Assert.assertEquals("calculateTotalWithTax(100, \"New Hampshire\") should return " + expected, expected.stripTrailingZeros(), actual.stripTrailingZeros());
	}
}

package com.techelevator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PracticePart1 {

	/*
	Topics: Data types

	The purpose of this method is to divide two floating-point values and remove any fractional result
	from the returned floating-point value.

		Q01_removeFraction(7.0, 3.0) -> 2.0
		Q01_removeFraction(8.0, 2.5) -> 3.0
	*/
	public double Q01_removeFraction(double numerator, double denominator) {
		return 0;
	}

	/*
	Topics: Expressions

	The purpose of this method is to determine if a given water temperature is extreme. Return true
	if the temperature is extreme, otherwise return false. Water temperature is considered extreme
	when it is at or below freezing (32° F), or at or above boiling (212° F).

	Note:
		Assume temperature is always given in Fahrenheit (°F).

		Q02_isWaterTemperatureExtreme(33) -> false
		Q02_isWaterTemperatureExtreme(212) -> true
	*/
	public boolean Q02_isWaterTemperatureExtreme(int temperature) {
		return false;
	}

	/*
	Topics: Branching

	The purpose of this method is to determine if a given year is a leap year. Return true if
	the year is a leap year, otherwise return false.

	How to determine if a year is a leap year:
	- All years divisible by 400 ARE leap years.
	- All years divisible by 100 but not by 400 are NOT leap years.
	- All years divisible by 4 but not by 100 ARE leap years.
	- All years not divisible by 4 are NOT leap years.

	Note:
		Assume all values are positive Gregorian years.

		Q03_isLeapYear(2000) -> true
		Q03_isLeapYear(1900) -> false
		Q03_isLeapYear(2008) -> true
		Q03_isLeapYear(2019) -> false
	*/
	public boolean Q03_isLeapYear(int year) {
		return false;
	}

	/*
	Topics: Branching

	The purpose of this method is to determine the price of a car wash.

	There are three types of car wash:
	'B' - basic: $8
	'P' - premium: $10
	'S' - super: $12

	Prices are reduced by $1 dollar in the morning, and increased by $2 on the weekend.

		Q04_carWashPrice('P', false, false) -> 10
		Q04_carWashPrice('P', true, false) -> 9
		Q04_carWashPrice('P', false, true) -> 12
		Q04_carWashPrice('P', true, true) -> 11
	*/
	public int Q04_carWashPrice(char typeOfWash, boolean isMorning, boolean isWeekend) {
		return 0;
	}

	/*
	Topics: Loops

	The purpose of this method is to determine the sum of the numbers that are divisible by 7
	between the two int parameters (inclusive).

	If `highestNumber` is less than `lowestNumber`, the result should be zero.

		 Q05_sumOfNumbersDivisibleBy7Between(49, 49) -> 49
		 Q05_sumOfNumbersDivisibleBy7Between(49, 56) -> 105
		 Q05_sumOfNumbersDivisibleBy7Between(48, 57) -> 105
		 Q05_sumOfNumbersDivisibleBy7Between(56, 49) -> 0
	*/
	public int Q05_sumOfNumbersDivisibleBy7Between(int lowestNumber, int highestNumber) {
		return 0;
	}

	/*
	Topics: Strings, Branching

	The purpose of this method is to return the substring between tags in a given string. If the tag
	cannot be found in the given string, or has a beginning tag but no end tag, return an empty
	string.

	Notes:
		`stringToSearch` can be any length, zero (empty string) or more.
		`tag` can be any arbitrary string of zero (empty string) or more characters.

		Q06_wordBetweenTags("<P>This is between.<P>", "<P>") -> "This is between."
		Q06_wordBetweenTags("<P>This <Q>is<Q> between.<P>", "<Q>") -> "is"
		Q06_wordBetweenTags("<P><P>", "<P>") -> "" // Nothing between the tags.
		Q06_wordBetweenTags("<P>This is between.<P>", "<Q>") -> "" // Tag <Q> not found.
		Q06_wordBetweenTags("<P>This is between.", "<P>") -> "" // End tag <P> not found.
	*/
	public String Q06_stringBetweenTags(String stringToSearch, String tag) {
		return null;
	}

	/*
	Topics: Strings, Loops

	Fix the bug!
	The purpose of this method is to determine if a string is a palindrome.

	A palindrome is a word that reads the same forward and backward:
		level
		toot
		madam

	Tests are failing, and you have been asked to fix the bug in this method. The method
	is incorrectly identifying every word as a palindrome.

		Q07_isPalindrome("level") -> true
		Q07_isPalindrome("seven") -> false
		Q07_isPalindrome("toot") -> true
		Q07_isPalindrome("effete") -> false
	*/
	public boolean Q07_isPalindrome(String word) {
		String reverseWord = "";
		for (int i = 0; i < word.length(); i++) {
			reverseWord += word.charAt(i);
		}
		return word.equalsIgnoreCase(reverseWord);
	}

	/*
	Topics: Expressions

	Fix the bug!
	The purpose of this method is to calculate a weekly allowance. It includes the base weekly amount in
	addition to any significant gift and incentive bonus amounts which are distributed across a year
	rather than "paid out" in lump sums. The incentive bonus is awarded on a quarterly basis.

	Tests are failing, and you have been asked to fix the bug in this method. For example, when the method
	is called with Q08_allowanceCalculator(7, 100, 40), the expected result is 12, but the actual value returned
	is 110.

	Note: For convenience, all monetary amounts are treated as whole dollars.

	Q08_allowanceCalculator(7, 100, 40) -> 12
	Q08_allowanceCalculator(7, 100, 0) -> 8
	Q08_allowanceCalculator(7, 0, 40) -> 10
	*/
	public int Q08_allowanceCalculator(int weekly, int gift, int bonus) {
		int allowance = weekly + gift + bonus * 4 / 52;
		return allowance;
	}

	/*
	Topics: Arrays, Loops

	Fix the bug!
	The purpose of this method is to determine if an array of integers is continuously increasing
	such that each value is at least 1 more than the previous value in the array.

	Tests are failing, and you have been asked to fix the bug in this method. For example, when the method
	is called with Q09_isIncreasing(new int[]{10, 9, 8, 7, 6}), the expected result is false, but the actual value
	returned is true. In fact, no matter what the sequence of integers is, the method consistently returns
	true.

		Q09_isIncreasing(new int[]{1, 2, 3, 4, 5}) -> true
		Q09_isIncreasing(new int[]{2, 4, 8, 32, 256}) -> true
		Q09_isIncreasing(new int[]{2, 4, 8, 32, 31}) -> false
		Q09_isIncreasing(new int[]{2, 4, 8, 7, 256}) -> false
		Q09_isIncreasing(new int[]{2, 1, 8, 32, 256}) -> false
	*/
	public boolean Q09_isIncreasing(int[] numbers) {
		boolean result = true;
		for (int i = 0; i < numbers.length - 1; i++) {
			if (numbers[i] > numbers[i++]) {
				result = false;
				break;
			}
		}
		return result;
	}

	/*
	Topics: Lists, Loops

	The purpose of this method is to reverse a List of integers. The integers can be positive
	or negative, do have to be sequential or ordered, and can repeat. The List may be of any
	size including empty.

		Q10_reverseList(new ArrayList<>(List.of(1, 2, 3, 4))) -> [4, 3, 2, 1]
		Q10_reverseList(new ArrayList<>(List.of(33, 6, 2344, -11, 933))) -> [933, -11, 2344, 6, 33]
		Q10_reverseList(new ArrayList<>(List.of(919, 282))) -> [282, 919]
		Q10_reverseList(new ArrayList<>(List.of(55))) -> [55]
		Q10_reverseList(new ArrayList<>(List.of())) -> []
	*/
	public List<Integer> Q10_reverseList(List<Integer> numbers) {
		return new ArrayList<>();
	}

	/*
	Topics: Loops, Arrays, Collections

	The purpose of this method is to count the number of individual ideal pets suggested by a group of
	pre-school children.

	Return a Map<String, Integer> where the String `key` is the type of pet, and the Integer `value` is the number of times
	that type of pet is found in the collection of ideal pets.

	NOTE: Since the Map is an unordered collection, the order in which the entries are added or displayed is not important
	to the result.

		 Q11_idealPets([]) -> {}
		 Q11_idealPets(["Dog"]) -> {"Dog": 1}
		 Q11_idealPets(["Dog", "Cat"]) -> {"Dog": 1, "Cat": 1}
		 Q11_idealPets(["Dog", "Cat", "Dog"]) -> {"Dog": 2, "Cat": 1}
		 Q11_idealPets(["Cat", "Dog", "Cat"]) -> {"Dog": 1, "Cat": 2}
		 Q11_idealPets(["Dog", "Cat", "Chicken", "Fish", "Dog", "Dinosaur", "Cat", "Dog", "Llama"])
		 	-> {"Dog": 3, "Chicken": 1, "Fish": 1, "Cat": 2, "Dinosaur": 1, "Llama": 1}
	*/
	public Map<String, Integer> Q11_idealPets(String[] suggestedPets) {
		return new HashMap<>();
	}
}

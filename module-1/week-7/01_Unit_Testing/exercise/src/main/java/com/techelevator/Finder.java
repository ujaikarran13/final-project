package com.techelevator;

import java.util.List;

public class Finder {

	/*
	 * Given a List of Integers, return the largest value. If the list is empty, return null.
	 */
	public Integer findLargest(List<Integer> integerList) {
		if (integerList.isEmpty()) {
			return null;
		}

		int largestInt = integerList.get(0);
		for (Integer currentInteger : integerList) {
			if (currentInteger > largestInt) {
				largestInt = currentInteger;
			}
		}
		return largestInt;
	}

}

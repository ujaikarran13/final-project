package com.techelevator;

public class Lucky13 {

	/*
	 * Given an array of ints, return true if the array contains no 1's and no 3's.
	 */
	public boolean getLucky(int[] nums) {
		if (nums != null) {
			for (int i = 0; i < nums.length; i++) {
				if (nums[i] == 1 || nums[i] == 3) {
					return false;
				}
			}
		}
		return true;
	}

}

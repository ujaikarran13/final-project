package com.techelevator;

import java.util.Scanner;

public class LinearConvert {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int[] footToMeter = new int [1];
		System.out.println("Please enter the length:");
		String footConversion = input.nextLine();
		int meterConversion = Integer.parseInt(footConversion);
		double meterToFoot = (int)(3.2808399 * meterConversion);
		System.out.print("Meter To Foot is:" + meterToFoot);
	}

}

package com.techelevator;

import java.util.Scanner;

public class TempConvert {

	public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	int freezingPoint = 32;
	double CONVERSION_FACTOR = 9.0 / 5.0;

		double fahrenheitTemp;
		int temperatureCelsius = 50;

		fahrenheitTemp = temperatureCelsius * 1.8 + freezingPoint;

		System.out.println ("Celsius Temperature: " + temperatureCelsius);
		System.out.println ("Fahrenheit Equivalent: " + fahrenheitTemp);



	}

}

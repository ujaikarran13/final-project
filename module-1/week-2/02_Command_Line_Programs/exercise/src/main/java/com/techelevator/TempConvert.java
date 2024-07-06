package com.techelevator;

import java.util.Scanner;

public class TempConvert {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter the temperature: ");
		double temperature = scanner.nextDouble();

		System.out.print("Enter the scale Celsius(C) or Fahrenheit(F) ");
		String scale = scanner.next();

		double convertedTemperature;
		String originalScale, newScale;

		if (scale.equals("C")) {
			convertedTemperature = celsiusToFahrenheit(temperature);
			originalScale = "Celsius";
			newScale = "Fahrenheit";
		} else if (scale.equals("F")) {
			convertedTemperature = fahrenheitToCelsius(temperature);
			originalScale = "Fahrenheit";
			newScale = "Celsius";
		} else {
			System.out.println("Invalid input. Please enter either C or F.");
			return;
		}

		System.out.printf("%.0f %s is %.0f %s.\n", temperature, originalScale, Math.floor(convertedTemperature), newScale);
	}


	public static double celsiusToFahrenheit(double celsius) {
		return celsius * 1.8 + 32;
	}


	public static double fahrenheitToCelsius(double fahrenheit) {
		return (fahrenheit - 32) / 1.8;
	}
}







package com.techelevator;

import java.io.PrintStream;
import java.util.Scanner;

public class Fibonacci {


	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Please enter a number:");
		String userInput = input.nextLine();
        int firstNumber = 0;
		int secondNumber = 1;
		int lastNumber = Integer.parseInt(userInput);
		System.out.println(firstNumber);
		System.out.println(secondNumber);

		while (firstNumber < 10){

			System.out.println(firstNumber + secondNumber);
			int temporaryNumber = firstNumber;
			int tempSecondNumber = secondNumber;
			firstNumber = secondNumber;
			secondNumber = tempSecondNumber + temporaryNumber;
		}

	}

}

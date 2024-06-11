package com.techelevator;

public class Example1 {

	public static void main(String[] args) {
		System.out.println("Hello World!");
		/*
		1. What is 5 divided by 2?
		*/
		System.out.println(5/2);

		/*
		2. What is 5.0 divided by 2? (Or 5 divided by 2.0?)
		*/
		System.out.println(5.0/2);

		/*
		3. What is 66.6 divided by 100?
		*/
		System.out.println(66.6/100);

		/*
		4. If I divide 5 by 2, what's my remainder?
		*/
		System.out.println(5%2);

		/*
		5. What is 1,000,000,000 * 3?
		*/
		long billion = 1_000_000_000;
		long threeBillion;
		threeBillion = billion * 3;
		System.out.println(threeBillion);
		
	}
}

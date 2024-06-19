package com.techelevator;

import java.io.PrintStream;

public class Fibonacci {

	public static long fib(int n) {
		if (n <= 1) return n;
		else return fib(n-1) + fib(n-2);
	}

	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);

		System.out.println(fib(N));


		for (int i = 1; i <= N; i++)
			System.out.println(i + ": " + fib(i));


	}

}

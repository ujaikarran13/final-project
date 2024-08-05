package com.techelevator;

public class Exercise03_MiddleOfThree {

    /*
     Given three integer values, return the middle value.

     middleOfThree(1, 3, 2) → 2
     middleOfThree(45, 45, 77) → 45
     middleOfThree(-119, 35, 102) → 35
     middleOfThree(63, 63, 63) → 63
     */
    public int middleOfThree(int a, int b, int c) {
        int result = b; // Assume the middle value is the middle parameter
        if (a >= b && b < c) {
            result = a;
        }
        else if (b > a && a >= c) {
            result = a;
        }
        else if (b > a && a <= c) {
            result = c;
        }
        else if (c > a && a >= b) {
            result = a;
        }
        return result;
    }
}

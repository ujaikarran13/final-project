package com.techelevator;

public class Exercise01_HighestOfThree {

    /*
     Given three integer values, return the highest value.

     Note: a value may be considered higher if it is greater than or equal to the other value.

     highestOfThree(1, 3, 2) → 3
     highestOfThree(77, 45, 19) → 77
     highestOfThree(-119, 35, 102) → 102
     highestOfThree(63, 63, 63) → 63
     */
    public int highestOfThree(int a, int b, int c) {
        int highestValue = a;   // Assume the first parameter is the highest value
        if (b > highestValue) {
            highestValue = b;
        }
        if (c > highestValue) {
            highestValue = c;
        }
        return highestValue;
//        // Alternate
//        int highestValue = a;   // Assume the first parameter is the highest value
//        if (b >= a && b >= c) {
//            highestValue = b;
//        }
//        else if (c >= a && c >= b) {
//            highestValue = c;
//        }
//        return highestValue;
    }
}

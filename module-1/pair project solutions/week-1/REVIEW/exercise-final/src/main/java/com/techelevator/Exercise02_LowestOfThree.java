package com.techelevator;

public class Exercise02_LowestOfThree {

    /*
     Given three integer values, return the lowest value.

     Note: a value may be considered lower if it is less than or equal to the other value.

     lowestOfThree(1, 3, 2) → 1
     lowestOfThree(77, 45, 19) → 19
     lowestOfThree(-119, 35, 102) → -119
     lowestOfThree(63, 63, 63) → 63
     */
    public int lowestOfThree(int a, int b, int c) {
        int lowestValue = c;   // Assume the third parameter is the lowest value
        if (a < lowestValue) {
            lowestValue = a;
        }
        if (b < lowestValue) {
            lowestValue = b;
        }
        return lowestValue;
//        // Alternate
//        int lowestValue = c;   // Assume the third parameter is the lowest value
//        if (a <= b && a <= c) {
//            lowestValue = a;
//        }
//        else if (b <= a && b <= c) {
//            lowestValue = b;
//        }
//        return lowestValue;
    }

}

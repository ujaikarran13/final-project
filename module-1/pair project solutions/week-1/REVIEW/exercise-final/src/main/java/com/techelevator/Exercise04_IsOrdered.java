package com.techelevator;

public class Exercise04_IsOrdered {

    /*
     Given three ints, a b c, return true if they are in strict increasing order, such as 1 2 3,
     or 4 5 6, but not 6 5 4 or 5 4 76. However, if "equalOk" is true, equality is allowed such
     that 5 5 7 or 5 5 5 are in increasing order.

     isOrdered(1, 2, 3, false) → true
     isOrdered(1, 2, 2, false) → false
     isOrdered(1, 2, 2, true) → true
     */
    public boolean isOrdered(int a, int b, int c, boolean equalOk) {
        if (equalOk) {
            return a <= b && b <= c;
        } else {
            return a < b && b < c;
        }
    }

}

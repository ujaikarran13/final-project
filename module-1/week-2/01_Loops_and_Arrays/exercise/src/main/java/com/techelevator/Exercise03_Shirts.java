package com.techelevator;

import java.util.Scanner;

public class Exercise03_Shirts {

    private final static char SMALL_TSHIRT = 'S';
    private final static char MEDIUM_TSHIRT = 'M';
    private final static char LARGE_TSHIRT = 'L';

    /*
    A t-shirt company rep from Top Threads just finished taking an order
    from a client and needs to key it into the system. The client ordered,
    3 Small shirts ('S'), 2 Medium shirts ('M'), and 1 Large shirt ('L').

    Implement the logic to reflect an order of 6 t-shirts.

    Examples:
    buildOrder() → ['S', 'S', 'S', 'M', 'M', 'L']
     */
    public char[] buildOrder() {
        char[] Order = new char[6];

        char SMALL_TSHIRT1= 'S';
        char SMALL_TSHIRT2 ='S';
        char SMALL_TSHIRT3 ='S';
        char MEDIUM_TSHIRT1='M';
        char MEDIUM_TSHIRT2='M';
        char LARGE_TSHIRT1 ='L';


        return Order;

    }


    /*
    Another customer called in and is hosting a large networking event and
    needs a bulk order. Rather than indicate how many of each shirt they
    wanted, they've asked for as even distribution as possible.

    Implement the logic to generate an order representing as even a distribution
    as possible, for example: ('S', 'M', 'L', 'S', 'M', 'L', 'S', ...)

    Note: The number of shirts ordered is guaranteed to be non-negative.

    Examples:
    buildBulkOrder(6) → ['S', 'M', 'L', 'S', 'M', 'L']    
    buildBulkOrder(3) → ['S', 'M', 'L']
    buildBulkOrder(4) → ['S', 'M', 'L', 'S']
    buildBulkOrder(0) → []
     */
    public char[] buildBulkOrder(int numberOfShirts) {
        char[] out = new char[numberOfShirts];
        for (int i = 0; i < numberOfShirts; i++) {
            if (i % 3 == 0) {
                out[i] = 'S';
            } else if (i % 3 == 1) {
                out[i] = 'M';
            } else {
                out[i] = 'L';
            }
        }
        return out;
    }


    /*
    The warehouse is out of small shirts and will only request more when the
    next order comes in that includes an 'S' shirt.

    Implement the logic to look through the next incoming order `char[] order`
    and return true if we need to place an order for Small shirts.

    Examples:
    placeRequest(['M', 'L', 'S']) → true
    placeRequest(['M', 'S', 'L']) → true
    placeRequest(['M', 'M', 'L']) → false
    placeRequest([]) → false
     */
    public boolean placeRequest(char[] order) {
        for (int i = 0; i < order.length; i++) {
            if (order[i] == 'S') {
                return true;
            }
        }
        return false;
    }
}



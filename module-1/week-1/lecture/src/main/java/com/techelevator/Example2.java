package com.techelevator;

import java.util.Random;

public class Example2 {

    public static void main(String[] args) {

        Random random = new Random();
        int randomNumber = random.nextInt(10); //picks a number from 0-9
        System.out.println("Random number is: " + randomNumber);

        /*
		For each of the print statements below, finish the description of when the boolean expression will
		evaluate to true.
		*/

        System.out.print("Random number is more than 5: ");
        System.out.println(randomNumber > 5);

        System.out.print("Random number...? ");
        System.out.println(randomNumber >= 2 && randomNumber <= 7);

        System.out.print("Random number...? ");
        System.out.println(!(randomNumber == 2 || randomNumber == 4 || randomNumber == 6));

        System.out.print("Random number...? ");
        System.out.println(randomNumber % 2 == 0 ^ randomNumber % 3 == 0);

        System.out.print("Random number...? ");
        System.out.println(randomNumber > 0 && 10 % randomNumber == 0);
    }

}

package com.techelevator;

import java.util.Scanner;

public class LastFirst {
    /*
    Write a command-line program that accepts a full name (first and last name) from the user.
    The program then displays the name in the format last, first.

    Enter a name (first last): Juan Valdez
    Valdez, Juan

    Enter a name (first last): Martin Jackson
    Jackson, Martin
    */

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        System.out.print("Please enter your name (first last): ");
        String firstLast = userInput.nextLine();
        // find the space between names
        int spacePos = firstLast.indexOf(' ');
        String first = firstLast.substring(0, spacePos);
        String last = firstLast.substring(spacePos + 1);
        String lastFirst = last + ", " + first;
        System.out.println(lastFirst);

    }
}
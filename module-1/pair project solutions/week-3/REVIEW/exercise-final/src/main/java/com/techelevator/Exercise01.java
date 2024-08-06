package com.techelevator;

import java.util.ArrayList;
import java.util.List;

public class Exercise01 {

    /*
    Given a List of Strings, return a List of the Strings that don't start with a vowel (A, E, I, O, U).
    Keep in mind case sensitivity, the first letter may be lowercase.

    Examples:
    noStartingVowels( {"Tooth", "Easy", "Mirror"} )  ->  {"Tooth", "Mirror"}
    noStartingVowels( {"red", "green", "orange"} )  ->  {"red", "green"}
    noStartingVowels( {"Call", "Bill", "about", "the", "Elephant"} )  ->  {"Call", "Bill", "the"}
	 */
    public List<String> noStartingVowels(List<String> inputValues) {
        List<String> filtered = new ArrayList<>();
        for (String inputValue : inputValues) {
            String firstLetter = inputValue.toLowerCase().substring(0, 1);
            if (!(firstLetter.equals("a") || firstLetter.equals("e") || firstLetter.equals("i")
                    || firstLetter.equals("o") || firstLetter.equals("u") )) {
                filtered.add(inputValue);
            }
        }
        return filtered;
    }
}

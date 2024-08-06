package com.techelevator;

import java.util.HashMap;
import java.util.Map;

public class Exercise04 {

    /*
    Given a String, return a Map with the number of times each character appears in the string.
    The count should be case-sensitive, so 'A' isn't the same as `a`.

    Examples:
    getLetterCount("abacus") -> {'a': 2, 'b': 1, 'c': 1, 'u': 1, 's': 1}
    getLetterCount("Nice necktie!") -> {'N': 1, 'i': 2, 'c': 2, 'e': 3, ' ': 1, 'n': 1, 'k': 1, 't': 1, '!': 1}
    getLetterCount("racecar") -> {'r': 2, 'a': 2, 'c': 2, 'e': 1}
     */
    public Map<Character, Integer> getLetterCount(String input) {
        Map<Character, Integer> letterCounts = new HashMap<>();
        char[] inputChars = input.toCharArray();
        for (char inputChar : inputChars) {
            if (letterCounts.containsKey(inputChar)) {
                int charCount = letterCounts.get(inputChar);
                letterCounts.put(inputChar, charCount + 1);
            } else {
                letterCounts.put(inputChar, 1);
            }
        }
        return letterCounts;
    }
}

package com.techelevator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NonStartTest {

    private final NonStart nonStart = new NonStart();

    @Test

    public void returnConcatenatedStringMoreThan1Character(){
        assertEquals("First character of each string is omitted, both strings are concatenated.","elcomeome", nonStart.getPartialString("Welcome", "Home"));

    }
    @Test

    public void returnConcatenatedString1Character(){
        assertEquals("Single character strings are not concatenated since the first characters are omitted.","", nonStart.getPartialString("W", "H"));

    }
    @Test

    public void returnConcatenatedStringNoCharacters(){
        assertEquals("There are no characters to concatenate.","", nonStart.getPartialString("", ""));

    }




}

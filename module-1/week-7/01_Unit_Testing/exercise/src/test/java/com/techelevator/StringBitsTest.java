package com.techelevator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringBitsTest {

    private final StringBits stringBits = new StringBits();

@Test
    public void return_A_String_With_Every_Other_Character(){
    assertEquals("Hlo",stringBits.getBits("Hello"));

}
    @Test
    public void return_A_String_With_A_Single_Character(){
        assertEquals("H",stringBits.getBits("H"));
    }
    @Test
    public void return_A_String_With_No_Characters(){
        assertEquals("",stringBits.getBits(""));
    }
    @Test
    public void return_A_String_With_Many_Characters(){
    String input ="";
    String expected = "";
        assertEquals(expected,stringBits.getBits(input));

    }
}

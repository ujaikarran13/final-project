package com.techelevator;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Less20Test {
    private final Less20 less20 = new Less20();

    @Test
    public void testIsLessThanMultipleOf20_OneLessThanMultipleOf20(){
        assertTrue(less20.isLessThanMultipleOf20(19), "19 is 1 less than a multiple of 20.");
        assertTrue(less20.isLessThanMultipleOf20(39), "39 is 1 less than a multiple of 20.");
        assertTrue(less20.isLessThanMultipleOf20(59), "59 is 1 less than a multiple of 20.");
        assertTrue(less20.isLessThanMultipleOf20(79), "79 is 1 less than a multiple of 20.");

    }
    @Test
    public void testIsLessThanMultipleOf20_TwoLessThanMultipleOf20(){
        assertTrue(less20.isLessThanMultipleOf20(18), "18 is 1 less than a multiple of 20.");
        assertTrue(less20.isLessThanMultipleOf20(38), "38 is 1 less than a multiple of 20.");
        assertTrue(less20.isLessThanMultipleOf20(58), "58 is 1 less than a multiple of 20.");
        assertTrue(less20.isLessThanMultipleOf20(78), "78 is 1 less than a multiple of 20.");

    }
    @Test
    public void testIsLessThanMultipleOf20_NotLessThanMultipleOf20(){
        assertTrue(less20.isLessThanMultipleOf20(20), "20 is 1 less than a multiple of 20.");
        assertTrue(less20.isLessThanMultipleOf20(21), "21 is 1 less than a multiple of 20.");
        assertTrue(less20.isLessThanMultipleOf20(40), "40 is 1 less than a multiple of 20.");
        assertTrue(less20.isLessThanMultipleOf20(41), "41 is 1 less than a multiple of 20.");

    }




}

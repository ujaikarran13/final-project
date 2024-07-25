package com.techelevator;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
public class Lucky13Test {
    private final Lucky13 lucky13 = new Lucky13();

    @Test
    public void returnTrue_ifNo1sOr3s(){
        assertTrue("An array with no 1s or 3s returns true.",lucky13.getLucky(new int[]{2,4,5,6,7}));
        assertTrue("An array with no 1s or 3s returns true.",lucky13.getLucky(new int[]{0,2,4,5,6,7}));
        assertTrue("An empty array with no 1s or 3s returns true.",lucky13.getLucky(new int[]{}));


    }
    @Test
    public void returnFalse_ifContains1s() {
        assertFalse("An array with 1s returns false.", lucky13.getLucky(new int[]{1}));
        assertFalse("An array with 1s returns false.", lucky13.getLucky(new int[]{0, 1, 2, 3}));
        assertFalse("An array with 1s returns false.", lucky13.getLucky(new int[]{4, 1, 5}));
    }
    @Test
    public void returnFalse_ifContains3s() {
        assertFalse("An array with 3s returns false.", lucky13.getLucky(new int[]{3}));
        assertFalse("An array with 3s returns false.", lucky13.getLucky(new int[]{0, 1, 2, 3}));
        assertFalse("An array with 3s returns false.", lucky13.getLucky(new int[]{3, 4, 5}));
    }
}

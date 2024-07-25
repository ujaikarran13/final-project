package com.techelevator;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SameFirstLastTest {

    private final SameFirstLast sameFirstLast = new SameFirstLast();

    @Test
    public void firstAndLastElements_Are_Equal(){

        assertTrue("An array with the same first and last elements returns true.",sameFirstLast.isItTheSame(new int[]{2,4,5,6,2}));
        assertTrue("An array with the same first and last elements returns true.", sameFirstLast.isItTheSame(new int[]{0, 2, 4, 5, 6, 0}));
        assertTrue("A single element array returns true.", sameFirstLast.isItTheSame(new int[]{1}));
    }
    @Test
    public void firstAndLastElements_Are_NOT_Equal(){
        assertFalse("An array with different first and last elements returns false.",sameFirstLast.isItTheSame(new int[]{2,4,5,6,7}));
        assertFalse("An array with different first and last elements returns false.", sameFirstLast.isItTheSame(new int[]{0, 2, 4, 5, 6, 8}));
        assertFalse("A single element array returns false.", sameFirstLast.isItTheSame(new int[]{1,2}));
    }
    @Test
    public void firstAndLastElements_EmptyArray(){
        assertFalse("An empty array returns false.",sameFirstLast.isItTheSame(new int[]{}));

    }
}

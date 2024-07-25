package com.techelevator;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class MaxEnd3Test {
    private final MaxEnd3 maxEnd3 = new MaxEnd3();

    @Test
    public void firstIntergerIsLargestOfThe3() {
        int[] input = {5, 4, 3};
        int[] expected = {5, 5, 5};
        assertArrayEquals("All elements are changed to the first integer when it is the largest integer.",expected, maxEnd3.makeArray(input));

    }

    @Test
    public void lastIntergerIsLargestOfThe3(){
        int[] input = {3,4,5};
        int[] expected = {5,5,5};
        assertArrayEquals("All elements are changed to the last integer when it is the largest integer.",expected, maxEnd3.makeArray(input));
    }
}

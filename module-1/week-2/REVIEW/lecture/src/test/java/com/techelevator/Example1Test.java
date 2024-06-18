package com.techelevator;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class Example1Test {

    private final Example1 sut = new Example1();

    @Test
    public void testSumAllNumbers() {
        assertEquals("sumAllNumbers(0, 1)", 1, sut.sumAllNumbers(0, 1));
        assertEquals("sumAllNumbers(2, 6)", 20, sut.sumAllNumbers(2, 6));
        assertEquals("sumAllNumbers(100, 104)", 510, sut.sumAllNumbers(100, 104));
    }

    @Test
    public void testArrayFront9() {
        assertEquals("arrayFront9(1, 9, 2)", true, sut.arrayFront9(new int[] {1, 9, 2}));
        assertEquals("arrayFront9(1, 2, 3, 4, 9)", false, sut.arrayFront9(new int[] {1, 2, 3, 4, 9}));
        assertEquals("arrayFront9(1, 2, 3, 4, 5)", false, sut.arrayFront9(new int[] {1, 2, 3, 4, 5}));
    }

    @Test
    public void testHalveAll() {
        assertArrayEquals("halveAll([2, 4, 6, 8])", new double[] {1.0, 2.0, 3.0, 4.0}, sut.halveAll(new int[] {2, 4, 6, 8}), 0.01);
        assertArrayEquals("halveAll([1, 1, 1])", new double[] {0.5, 0.5, 0.5}, sut.halveAll(new int[] {1, 1, 1}), 0.01);
        assertArrayEquals("halveAll([3, 300])", new double[] {1.5, 150}, sut.halveAll(new int[]{3, 300}), 0.01);
    }

}

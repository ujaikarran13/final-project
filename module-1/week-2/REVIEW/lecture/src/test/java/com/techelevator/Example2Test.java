package com.techelevator;

import org.junit.Test;

import static org.junit.Assert.*;

public class Example2Test {

    Example2 sut = new Example2();

    @Test
    public void testRoundOnBothEnds() {
        assertTrue("roundOnBothEnds(\"Ohio\")", sut.roundOnBothEnds("Ohio"));
        assertTrue("roundOnBothEnds(\"    OREO \")", sut.roundOnBothEnds("    OREO "));
        assertFalse("roundOnBothEnds(\"ooooo!\")", sut.roundOnBothEnds("ooooo!"));
    }

    @Test
    public void testConCat() {
        assertEquals("conCat(\"abc\", \"cat\")", "abcat", sut.conCat("abc", "cat"));
        assertEquals("conCat(\"dog\", \"cat\")", "dogcat", sut.conCat("dog", "cat"));
        assertEquals("conCat(\"abc\", \"\")", "abc", sut.conCat("abc", ""));
    }

    @Test
    public void testEqualIsNot() {
        assertFalse("equalIsNot(\"This is not\")", sut.equalIsNot("This is not"));
        assertTrue("equalIsNot(\"This is notnot\")", sut.equalIsNot("This is notnot"));
        assertTrue("equalIsNot(\"noisxxnotyynotxisi\")", sut.equalIsNot("noisxxnotyynotxisi"));
    }
}

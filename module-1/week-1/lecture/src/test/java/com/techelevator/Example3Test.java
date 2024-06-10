package com.techelevator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Example3Test {

    private final Example3 sut = new Example3();

    @Test
    public void testReturnAdultOrMinorOrTeen() {
        assertEquals("returnAdultOrMinorOrTeen(18)","Adult", sut.ageGroup(18));
        assertEquals("returnAdultOrMinorOrTeen(17)","Teen", sut.ageGroup(17));
        assertEquals("returnAdultOrMinorOrTeen(13)", "Teen", sut.ageGroup(13));
        assertEquals("returnAdultOrMinorOrTeen(12)","Child", sut.ageGroup(12));

    }

    @Test
    public void testGreenTicket() {
        assertEquals("greenTicket(1, 2, 3)", 0, sut.greenTicket(1, 2, 3));
        assertEquals("greenTicket(2, 2, 2)", 20, sut.greenTicket(2, 2, 2));
        assertEquals("greenTicket(1, 1, 2)", 10, sut.greenTicket(1, 1, 2));
        assertEquals("greenTicket(2, 1, 1)", 10, sut.greenTicket(2, 1, 1));
        assertEquals("greenTicket(1, 2, 1)", 10, sut.greenTicket(1, 2, 1));
    }

    @Test
    public void testBlackjack() {
        assertEquals("blackjack(19, 21)", 21, sut.blackjack(19, 21));
        assertEquals("blackjack(21, 19)", 21, sut.blackjack(21, 19));
        assertEquals("blackjack(19, 22)", 19, sut.blackjack(19, 22));
        assertEquals("blackjack(22, 19)", 19, sut.blackjack(22, 19));
        assertEquals("blackjack(23, 22)", 0, sut.blackjack(23, 22));
        assertEquals("blackjack(10, 12)", 0, sut.blackjack(23, 22));
        assertEquals("blackjack(21, 21)", 21, sut.blackjack(21, 21));
        assertEquals("blackjack(17, 17)", 17, sut.blackjack(17, 17));
    }

}

package com.techelevator;

import org.junit.*;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExercisesTest {

    @Test
    public void Exercise01_HighestOfThree() {
        Exercise01_HighestOfThree exercise = new Exercise01_HighestOfThree();
        assertEquals("highestOfThree(1, 3, 2)", 3, exercise.highestOfThree(1, 3, 2));
        assertEquals("highestOfThree(77, 45, 19)", 77, exercise.highestOfThree(77, 45, 19));
        assertEquals("highestOfThree(-119, 35, 102)", 102, exercise.highestOfThree(-119, 35, 102));
        assertEquals("highestOfThree(63, 63, 18)", 63, exercise.highestOfThree(63, 63, 18));
        assertEquals("highestOfThree(44, 63, 63)", 63, exercise.highestOfThree(44, 63, 63));
        assertEquals("highestOfThree(63, 31, 63)", 63, exercise.highestOfThree(63, 31, 63));
        assertEquals("highestOfThree(63, 63, 63)", 63, exercise.highestOfThree(63, 63, 63));
    }

    @Test
    public void Exercise02_LowestOfThree() {
        Exercise02_LowestOfThree exercise = new Exercise02_LowestOfThree();
        assertEquals("lowestOfThree(2, 1, 3)", 1, exercise.lowestOfThree(1, 3, 2));
        assertEquals("lowestOfThree(77, 45, 19)", 19, exercise.lowestOfThree(77, 45, 19));
        assertEquals("lowestOfThree(-119, 35, 102)", -119, exercise.lowestOfThree(-119, 35, 102));
        assertEquals("lowestOfThree(63, 18, 18)", 18, exercise.lowestOfThree(63, 18, 18));
        assertEquals("lowestOfThree(18, 18, 44)", 18, exercise.lowestOfThree(18, 18, 44));
        assertEquals("lowestOfThree(18, 63, 18)", 18, exercise.lowestOfThree(18, 63, 18));
        assertEquals("lowestOfThree(18, 18, 18)", 18, exercise.lowestOfThree(18, 18, 18));
    }

    @Test
    public void Exercise03_MiddleOfThree() {
        Exercise03_MiddleOfThree exercise = new Exercise03_MiddleOfThree();
        assertEquals("middleOfThree(2, 1, 3)", 2, exercise.middleOfThree(2, 1, 3));
        assertEquals("middleOfThree(77, 45, 19)", 45, exercise.middleOfThree(77, 45, 19));
        assertEquals("middleOfThree(-119, 102, 35)", 35, exercise.middleOfThree(-119, 102, 35));
        assertEquals("middleOfThree(63, 52, 52)", 52, exercise.middleOfThree(63, 52, 52));
        assertEquals("middleOfThree(52, 52, 44)", 52, exercise.middleOfThree(52, 52, 44));
        assertEquals("middleOfThree(52, 63, 52)", 52, exercise.middleOfThree(52, 63, 52));
        assertEquals("middleOfThree(52, 52, 52)", 52, exercise.middleOfThree(52, 52, 52));
    }

    @Test
    public void Exercise04_IsOrdered() {
        Exercise04_IsOrdered exercise = new Exercise04_IsOrdered();
        assertEquals("isOrdered(1, 2, 3, false)", true, exercise.isOrdered(1, 2, 3, false));
        assertEquals("isOrdered(1, 2, 2, false)", false, exercise.isOrdered(1, 2, 2, false));
        assertEquals("isOrdered(1, 2, 2, true)", true, exercise.isOrdered(1, 2, 2, true));
        assertEquals("isOrdered(1, 1, 2, false)", false, exercise.isOrdered(1, 1, 2, false));
        assertEquals("isOrdered(1, 1, 2, true)", true, exercise.isOrdered(1, 1, 2, true));
        assertEquals("isOrdered(4, 4, 4, false)", false, exercise.isOrdered(1, 1, 1, false));
        assertEquals("isOrdered(4, 4, 4, true)", true, exercise.isOrdered(4, 4, 4, true));
        assertEquals("isOrdered(-231, -131, -31, false)", true, exercise.isOrdered(-231, -131, -31, false));
    }

    @Test
    public void Exercise05_CigarParty() {
        Exercise05_CigarParty exercise = new Exercise05_CigarParty();
        assertEquals("cigarParty(30, false)", false, exercise.cigarParty(30, false));
        assertEquals("cigarParty(50, false)", true, exercise.cigarParty(50, false));
        assertEquals("cigarParty(70, true)", true, exercise.cigarParty(70, true));
        assertEquals("cigarParty(70, false)", false, exercise.cigarParty(70, false));
        assertEquals("cigarParty(30, true)", false, exercise.cigarParty(30, true));
        assertEquals("cigarParty(40, true)", true, exercise.cigarParty(40, true));
        assertEquals("cigarParty(40, false)", true, exercise.cigarParty(40, false));
        assertEquals("cigarParty(60, false)", true, exercise.cigarParty(60, false));
    }
    @Test
    public void Exercise06_YourJustDesserts() {
        Exercise06_YourJustDesserts exercise = new Exercise06_YourJustDesserts();
        assertEquals("yourJustDesserts(5, false)", "standard", exercise.yourJustDesserts(5, false));
        assertEquals("yourJustDesserts(5, true)", "standard", exercise.yourJustDesserts(5, true));
        assertEquals("yourJustDesserts(7, false)", "standard", exercise.yourJustDesserts(7, false));
        assertEquals("yourJustDesserts(7, true)", "special", exercise.yourJustDesserts(7, true));
        assertEquals("yourJustDesserts(10, false)", "standard", exercise.yourJustDesserts(10, false));
        assertEquals("yourJustDesserts(10, true)", "special", exercise.yourJustDesserts(10, true));
        assertEquals("yourJustDesserts(15, false)", "special", exercise.yourJustDesserts(15, false));
        assertEquals("yourJustDesserts(15, true)", "ginormous", exercise.yourJustDesserts(15, true));
        assertEquals("yourJustDesserts(17, false)", "ginormous", exercise.yourJustDesserts(17, false));
        assertEquals("yourJustDesserts(17, true)", "ginormous", exercise.yourJustDesserts(17, true));
    }
}

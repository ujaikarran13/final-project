package com.techelevator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FrontTimesTest {
    private final FrontTimes frontTimes = new FrontTimes();
    @Test
    public void will_get_first3_Characters_fromAStringWith_3_ORMoreCharacters() {

        assertEquals("These are the first 3 characters:", frontTimes.generateString("abcdefgh", 3), "abcabcabc");
    }

    @Test
    public void will_get_first3_Characters_fromAStringWith_3_ORLessCharacters() {
    assertEquals("These are the first 3 characters:", frontTimes.generateString("abc", 1), "abc");

    }


    @Test
    public void will_get_first3_Characters_fromANullString() {
        assertEquals("Null input", frontTimes.generateString(null, 1), "");
    }
    @Test
    public void will_get_first3_Characters_andRepeatALargNumberOfTimes () {
        String expected = "abc".repeat(10000);

        assertEquals("These are the first 3 characters repeated a large number of times:", frontTimes.generateString("abc", 10000), expected);

        assertEquals("Large input", frontTimes.generateString("abc", 10000), expected);

    }
    }


// Arrange - since this is testing a constructor, there's nothing to set up

// Act - call the constructor by creating a new object, passing valid parameters


// Assert - verify the properties are set appropriately






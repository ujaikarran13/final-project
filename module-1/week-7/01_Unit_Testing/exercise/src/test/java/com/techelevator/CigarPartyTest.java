package com.techelevator;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CigarPartyTest {
    @Test
    public void successful_when_number_of_cigars_is_between_40and60_onAWeekday() {
        // Arrange - since this is testing a constructor, there's nothing to set up
        // Act - call the constructor by creating a new object, passing valid parameters
        CigarParty party = new CigarParty();
        // Assert - verify the properties are set appropriately
        assertTrue("Party is successful with 50 cigars on a weekday", party.haveParty(50, false));
        assertFalse("Party is unsuccessful with 30 cigars on a weekday", party.haveParty(30, false));
        assertFalse("Party is unsuccessful with 70 cigars on a weekday", party.haveParty(70, false));
        assertTrue("Party is successful with 70 cigars on a weekend", party.haveParty(70, true));
    }
}




package com.techelevator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CoffeeTest {

    @Test
    public void constructor_should_set_properties() {
        // Arrange - since this is testing a constructor, there's nothing to set up

        // Act - call the constructor by creating a new object, passing valid parameters
        Coffee coffee = new Coffee("Large", "Decaf", new String[]{"cream", "sugar"}, 2.99);

        // Assert - verify the properties are set appropriately
        assertEquals("size not set correctly", "Large", coffee.getSize());
        assertEquals("blend not set correctly", "Decaf", coffee.getBlend());
        assertEquals("price not set correctly", 2.99, coffee.getPrice(), 0.001);  // 0.001 is the delta, the allowable difference when comparing doubles
        assertEquals("additions not set correctly", 2, coffee.getAdditions().length);
    }
}

package com.techelevator.vee.model.transportation;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class TransportationTest {

    @Test
    public void whenSerialized_canDeserialize() {
        // arrange
        final Wrapper original = new Wrapper();
        original.setDescription("unit-test-description");

        // act
        final Wrapper copy = new Wrapper();
        copy.initializeFromXml(original.getInnerXml());

        // assert
        Assert.assertEquals("description is not restored correctly",
                original.getDescription(),
                copy.getDescription());
    }


    private static class Wrapper extends Transportation {

        @Override
        public BigDecimal getTotalCost() {
            return null;
        }

        @Override
        public String getCostDetails() {
            return null;
        }
    }

}

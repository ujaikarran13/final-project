package com.techelevator.vee.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TravelGroupTest {

    @Test
    public void travelGroupDefaultsToOneAdult() {

        // act
        final TravelGroup group = new TravelGroup();

        // assert
        Assert.assertEquals("adult count is wrong", 1, group.getAdultCount());
        Assert.assertEquals("child count is wrong", 0, group.getChildCount());
        Assert.assertEquals("senior count is wrong", 0, group.getSeniorCount());
    }

    @Test
    public void whenConvertingToAString_singleCountsAreCorrect() {
        // arrange
        final TravelGroup group = new TravelGroup(1,1,1);

        // act
        final String description = group.toString();

        // assert
        Assert.assertTrue("adult description is not correct", description.contains("1 adult"));
        Assert.assertFalse("adult description is not correct", description.contains("1 adults"));
        Assert.assertTrue("senior description is not correct", description.contains("1 senior"));
        Assert.assertFalse("senior description is not correct", description.contains("1 seniors"));
        Assert.assertTrue("child description is not correct", description.contains("1 child"));

    }

    @Test
    public void whenConvertingToAString_pluralCountsAreCorrect() {
        // arrange
        final TravelGroup group = new TravelGroup(2,3,4);

        // act
        final String description = group.toString();

        // assert
        Assert.assertTrue("adult description is not correct", description.contains("2 adults"));
        Assert.assertTrue("senior description is not correct", description.contains("4 seniors"));
        Assert.assertTrue("child description is not correct", description.contains("3 children"));
    }

    @Test
    public void whenInitialized_totalCountIsCorrect() {
        // arrange
        final int adults = 1;
        final int children = 2;
        final int seniors = 3;
        final TravelGroup group = new TravelGroup(adults, children, seniors);

        // act
        final int totalTravelers = group.getTravelerCount();

        // assert
        Assert.assertEquals("traveler count is wrong",
                adults + children + seniors,
                totalTravelers);
    }

    @Test
    public void whenSerialized_canDeserialize() {
        // arrange
        final TravelGroup original = new TravelGroup(1,2,3);

        // act
        final TravelGroup copy = new TravelGroup(0,0,0);
        copy.initializeFromXml(original.getInnerXml());

        // assert
        Assert.assertEquals("adult count is wrong", original.getAdultCount(), copy.getAdultCount());
        Assert.assertEquals("child count is wrong", original.getChildCount(), copy.getChildCount());
        Assert.assertEquals("senior count is wrong", original.getSeniorCount(), copy.getSeniorCount());
    }

}

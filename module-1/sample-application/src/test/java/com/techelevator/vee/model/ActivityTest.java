package com.techelevator.vee.model;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class ActivityTest {

    @Test
    public void whenThereIsOnlyAFixedCost_returnIt() {

        // arrange
        final TravelGroup group = new TravelGroup();
        final Activity activity = new Activity(group, "unit test activity");
        activity.setFixedCost(BigDecimal.TEN);
        activity.setCostPerPerson(BigDecimal.ZERO);

        // act
        final BigDecimal total = activity.getTotalCost();

        // assert
        Assert.assertEquals("fixed cost is wrong", BigDecimal.TEN, total);
    }

    @Test
    public void whenThereAreAttendeeCosts_returnTheTotal() {

        // arrange
        final TravelGroup group = new TravelGroup(1,1,1);
        final Activity activity = new Activity(group, "unit test activity");
        final BigDecimal oneHundred = BigDecimal.valueOf(100);
        activity.setCostPerAdult(oneHundred);
        activity.setCostPerChild(BigDecimal.TEN);
        activity.setCostPerSenior(BigDecimal.ONE);

        // act
        final BigDecimal total = activity.getTotalCost();

        // assert
        Assert.assertEquals("attendee totals are not calculated correctly",
                oneHundred.add(BigDecimal.TEN.add(BigDecimal.ONE)),
                total);
    }

    @Test
    public void whenThereAreCosts_theyAppearInTheDetails() {

        // arrange
        final String attendeePattern = "%d at $%S each";

        final TravelGroup group = new TravelGroup(1,2,3);
        final Activity activity = new Activity(group, "unit test activity");
        final BigDecimal oneHundred = BigDecimal.valueOf(100);
        final BigDecimal oneThousand = BigDecimal.valueOf(1000);
        activity.setFixedCost(oneThousand);
        activity.setCostPerAdult(oneHundred);
        activity.setCostPerChild(BigDecimal.TEN);
        activity.setCostPerSenior(BigDecimal.ONE);

        // act
        final String details = activity.getCostDetails();

        // assert
        Assert.assertTrue("fixed cost is wrong",
                details.contains(String.format("Fixed cost of $%s", oneThousand)));

        Assert.assertTrue("adult cost is wrong",
                details.contains(String.format(attendeePattern, group.getAdultCount(), oneHundred)));

        Assert.assertTrue("child cost is wrong",
                details.contains(String.format(attendeePattern, group.getChildCount(), BigDecimal.TEN)));

        Assert.assertTrue("senior cost is wrong",
                details.contains(String.format(attendeePattern, group.getSeniorCount(), BigDecimal.ONE)));
    }

    @Test
    public void whenSerialized_canDeserialize() {

        // arrange
        final TravelGroup group = new TravelGroup(1,2,3);
        final Activity activity = new Activity(group, "persist activity");
        final BigDecimal oneHundred = BigDecimal.valueOf(100);
        final BigDecimal oneThousand = BigDecimal.valueOf(1000);
        activity.setFixedCost(oneThousand);
        activity.setCostPerAdult(oneHundred);
        activity.setCostPerChild(BigDecimal.TEN);
        activity.setCostPerSenior(BigDecimal.ONE);

        // act
        final String serializedContent = activity.getInnerXml();
        final Activity copy = new Activity(group);
        copy.initializeFromXml(serializedContent);

        // assert
        Assert.assertEquals("description is wrong", activity.getDescription(), copy.getDescription());
        Assert.assertEquals("fixed cost is wrong", activity.getFixedCost(), copy.getFixedCost());
        Assert.assertEquals("adult cost is wrong", activity.getCostPerAdult(), copy.getCostPerAdult());
        Assert.assertEquals("child cost is wrong", activity.getCostPerChild(), copy.getCostPerChild());
        Assert.assertEquals("senior cost is wrong", activity.getCostPerSenior(), copy.getCostPerSenior());
    }
}

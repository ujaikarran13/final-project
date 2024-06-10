package com.techelevator.util.xml;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class BasicXmlUtilityTest {

    @Test
    public void makeOpeningTag() {
        // arrange
        final String tagName = "vacation";
        final String openingTag = "<vacation>";

        // act
        final String tag = BasicXmlUtility.makeOpeningTag(tagName);

        // assert
        Assert.assertEquals("the tag is not correct", openingTag, tag);
    }

    @Test
    public void makeClosingTag() {
        // arrange
        final String tagName = "vacation";
        final String closingTag = "</vacation>";

        // act
        final String tag = BasicXmlUtility.makeClosingTag(tagName);

        // assert
        Assert.assertEquals("the tag is not correct", closingTag, tag);
    }
}

package com.techelevator.util.xml;

import org.junit.Assert;
import org.junit.Test;

public class BasicXmlBuilderTest {

    @Test
    public void createSingleElement() {
        // arrange
        final String tagName = "vacation";
        final String content = "<destination>\nHawaii\n</destination>\n";

        // act
        final String element = BasicXmlBuilder.createSingleElement(tagName, content);

        // assert
        Assert.assertEquals( "the element value is wrong", "<vacation>\n<destination>\nHawaii\n</destination>\n</vacation>\n", element);
    }

    @Test
    public void addElement_passingObject() {
        // arrange
        final String[] tags = new String[]{"destination", "adultCount", "childCount", "seniorCount"};
        final Object[] values = new Object[]{"Hawaii", 2, 1, 1};
        final BasicXmlBuilder builder = new BasicXmlBuilder();

        // act
        for (int i = 0; i < tags.length; ++i) {
            builder.addElement(tags[i], values[i]);
        }

        // assert
        BasicXmlParser parser = new BasicXmlParser(builder.build());
        Assert.assertEquals("Hawaii", parser.getStringContent("destination"));
        Assert.assertEquals(2, (int) parser.getIntegerContent("adultCount"));
        Assert.assertEquals(1, (int) parser.getIntegerContent("childCount"));
        Assert.assertEquals(1, (int) parser.getIntegerContent("seniorCount"));
    }

    @Test
    public void addElement_passingBasicXml() {
        // arrange
        final String tag = "vacation";
        final BasicXml xml = getBasicXml();
        xml.initializeFromXml("<vacation><destination>\nHawaii\n</destination>\n<adultCount>\n2\n</adultCount>\n" +
            "<childCount>\n1\n</childCount>\n<seniorCount>\n1\n</seniorCount>\n</vacation>\n");
        final BasicXmlBuilder builder = new BasicXmlBuilder();

        // act
        builder.addElement(tag, xml);

        // assert
        BasicXmlParser parser = new BasicXmlParser(builder.build());
        Assert.assertEquals("Hawaii", parser.getStringContent("destination"));
        Assert.assertEquals(2, (int) parser.getIntegerContent("adultCount"));
        Assert.assertEquals(1, (int) parser.getIntegerContent("childCount"));
        Assert.assertEquals(1, (int) parser.getIntegerContent("seniorCount"));
    }

    private BasicXml getBasicXml() {
        return new BasicXml() {
            private String innerXml;

            @Override
            public void initializeFromXml(String xml) {
                innerXml = xml.substring(xml.indexOf(">") + 1, xml.lastIndexOf("<")).trim();
            }

            @Override
            public String getInnerXml() {
                return innerXml;
            }
        };
    }
}

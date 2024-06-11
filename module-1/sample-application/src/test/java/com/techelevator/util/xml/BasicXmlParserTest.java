package com.techelevator.util.xml;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class BasicXmlParserTest {

    private static final String TEST_XML =
        "<vacation><destination>\nHawaii\n</destination>\n<adultCount>\n2\n</adultCount>\n" +
        "<cost>\n2198.99\n</cost>\n<approved>\ntrue\n</approved>\n" +
            "<unlimitedMiles>\nfalse\n</unlimitedMiles>\n<departOn>\n2022-05-01\n</departOn>\n</vacation>";

    @Test
    public void hasElement() {
        // arrange
        final BasicXmlParser parser = getParser(TEST_XML);
        final String elementName = "destination";

        // act
        final boolean foundElement = parser.hasElement(elementName);

        // assert
        Assert.assertTrue("element not found", foundElement);
    }

    @Test
    public void hasElement_elementNotFound() {
        // arrange
        final BasicXmlParser parser = getParser(TEST_XML);
        final String elementName = "nonExistent";

        // act
        final boolean foundElement = parser.hasElement(elementName);

        // assert
        Assert.assertFalse("element should not be found", foundElement);
    }

    @Test
    public void getStringContent() {
        // arrange
        final String expected = "Hawaii";
        final BasicXmlParser parser = getParser(TEST_XML);
        final String elementName = "destination";

        // act
        final String actual = parser.getStringContent(elementName);

        // assert
        Assert.assertEquals("destination value is wrong", expected, actual);
    }

    @Test
    public void getStringContent_elementNotFound() {
        // arrange
        final String expected = null;
        final BasicXmlParser parser = getParser(TEST_XML);
        final String elementName = "nonExistent";

        // act
        final String actual = parser.getStringContent(elementName);

        // assert
        Assert.assertEquals("nonExistent should have an empty value", expected, actual);
    }

    @Test
    public void getIntegerContent() {
        // arrange
        final int expected = 2;
        final BasicXmlParser parser = getParser(TEST_XML);
        final String elementName = "adultCount";

        // act
        final int actual = parser.getIntegerContent(elementName);

        // assert
        Assert.assertEquals("adultCount value is wrong", expected, actual);
    }

    @Test(expected = NumberFormatException.class)
    public void getIntegerContent_elementWrongType() {
        // arrange
        final BasicXmlParser parser = getParser(TEST_XML);
        final String elementName = "destination";

        // act
        parser.getIntegerContent(elementName);

        // assert : expected NumberFormatException
    }

    @Test
    public void getIntegerContent_elementNotFound() {
        // arrange
        final BasicXmlParser parser = getParser(TEST_XML);
        final String elementName = "nonExistent";

        // act
        final Integer actual = parser.getIntegerContent(elementName);

        // assert
        Assert.assertNull("Integer value for non-existent element should be null", actual);
    }

    @Test
    public void getDoubleContent() {
        // arrange
        final Double expected = 2198.99;
        final BasicXmlParser parser = getParser(TEST_XML);
        final String elementName = "cost";

        // act
        final Double actual = parser.getDoubleContent(elementName);

        // assert
        Assert.assertEquals("cost value is wrong", expected, actual);
    }

    @Test(expected = NumberFormatException.class)
    public void getDoubleContent_elementWrongType() {
        // arrange
        final BasicXmlParser parser = getParser(TEST_XML);
        final String elementName = "destination";

        // act
        parser.getDoubleContent(elementName);

        // assert : expected NumberFormatException
    }

    @Test
    public void getDoubleContent_elementNotFound() {
        // arrange
        final BasicXmlParser parser = getParser(TEST_XML);
        final String elementName = "nonExistent";

        // act
        final Double actual = parser.getDoubleContent(elementName);

        // assert
        Assert.assertNull("Double value for non-existent element should be null", actual);
    }

    @Test
    public void getBooleanContent() {
        // arrange
        final BasicXmlParser parser = getParser(TEST_XML);
        final String elementNameUnlimitedMiles = "unlimitedMiles";
        final String elementNameApproved = "approved";

        // act
        final boolean unlimitedMilesValue = parser.getBooleanContent(elementNameUnlimitedMiles);
        final boolean approvedValue = parser.getBooleanContent(elementNameApproved);

        // assert
        Assert.assertFalse("expected unlimitedMiles to be false", unlimitedMilesValue);
        Assert.assertTrue("expected approved to be true", approvedValue);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getBooleanContent_elementWrongType() {
        // arrange
        final BasicXmlParser parser = getParser(TEST_XML);
        final String elementName = "destination";

        // act
        parser.getBooleanContent(elementName);

        // assert : expected IllegalArgumentException
    }

    @Test
    public void getBooleanContent_elementNotFound() {
        // arrange
        final BasicXmlParser parser = getParser(TEST_XML);
        final String elementName = "nonExistent";

        // act
        final Boolean actual = parser.getBooleanContent(elementName);

        // assert
        Assert.assertNull("Boolean value for non-existent element should be null", actual);
    }

    @Test
    public void getDateContent() {
        // arrange
        final LocalDate expected = LocalDate.parse("2022-05-01");
        final BasicXmlParser parser = getParser(TEST_XML);
        final String elementName = "departOn";

        // act
        final LocalDate actual = parser.getDateContent(elementName);

        // assert
        Assert.assertEquals("departOn value is wrong", expected, actual);
    }

    @Test(expected = DateTimeParseException.class)
    public void getDateContent_elementWrongType() {
        // arrange
        final BasicXmlParser parser = getParser(TEST_XML);
        final String elementName = "destination";

        // act
        parser.getDateContent(elementName);

        // assert : expected DateTimeParseException
    }

    @Test
    public void getDateContent_elementNotFound() {
        // arrange
        final BasicXmlParser parser = getParser(TEST_XML);
        final String elementName = "nonExistent";

        // act
        final LocalDate actual = parser.getDateContent(elementName);

        // assert
        Assert.assertNull("LocalDate value for non-existent element should be null", actual);
    }

    @Test
    public void getBigDecimalContent() {
        // arrange
        final BigDecimal expected = new BigDecimal("2198.99");
        final BasicXmlParser parser = getParser(TEST_XML);
        final String elementName = "cost";

        // act
        final BigDecimal actual = parser.getBigDecimalContent(elementName);

        // assert
        Assert.assertEquals("cost value is wrong", expected, actual);
    }

    @Test(expected = NumberFormatException.class)
    public void getBigDecimalContent_elementWrongType() {
        // arrange
        final BasicXmlParser parser = getParser(TEST_XML);
        final String elementName = "destination";

        // act
        parser.getBigDecimalContent(elementName);

        // assert : expected NumberFormatException
    }

    @Test
    public void getBigDecimalContent_elementNotFound() {
        // arrange
        final BasicXmlParser parser = getParser(TEST_XML);
        final String elementName = "nonExistent";

        // act
        final BigDecimal actual = parser.getBigDecimalContent(elementName);

        // assert
        Assert.assertNull("BigDecimal value for non-existent element should be null", actual);
    }

    private BasicXmlParser getParser(String xml) {
        return new BasicXmlParser(xml);
    }
}

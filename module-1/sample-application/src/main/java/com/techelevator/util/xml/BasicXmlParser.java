package com.techelevator.util.xml;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * The BasicXmlParser is a utility for retrieving typed data from an XML string.
 * It can only handle a very limited form of XML, which is subject to these restrictions:
 *    1. Tags can only contain the element name, with no attributes allowed.
 *    2. No element can contain any descendant that uses the same tags it does.
 *    3. No element can have any sibling that uses the same tags it does.
 */

public class BasicXmlParser extends BasicXmlUtility {

    private final String xml;

    public BasicXmlParser(String xml) {
        this.xml = xml;
    }

    public boolean hasElement(String elementName) {
        return xml.contains(makeOpeningTag(elementName)) && xml.contains(makeClosingTag(elementName));
    }

    public String getStringContent(String elementName) {
        String content = null;
        if (hasElement(elementName)) {
            final String openingTag = makeOpeningTag(elementName);
            final String closingTag = makeClosingTag(elementName);
            //Example: string indices and substring()
            int beginningOfContent = xml.indexOf(openingTag) + openingTag.length();
            int endOfContent = xml.indexOf(closingTag);
            content = xml.substring(beginningOfContent, endOfContent).trim();
        }
        return content;
    }

    public Integer getIntegerContent(String elementName) throws NumberFormatException {
        Integer result = null;
        String content = getStringContent(elementName);
        if (content != null && !content.isEmpty()) {
            result = Integer.parseInt(content);
        }
        return result;
    }

    public Double getDoubleContent(String elementName) throws NumberFormatException {
        Double result = null;
        String content = getStringContent(elementName);
        if (content != null && !content.isEmpty()) {
            result = Double.parseDouble(content);
        }
        return result;
    }

    public Boolean getBooleanContent(String elementName) {
        Boolean result = null;
        String content = getStringContent(elementName);
        if (content != null && !content.isEmpty()) {
            content = content.toUpperCase().trim();
            //Example: string equality
            if (content.equals("TRUE")) {
                result = true;
            } else if (content.equals("FALSE")) {
                result = false;
            } else {
                throw new IllegalArgumentException(content + " is not valid boolean content");
            }
        }
        return result;
    }

    public LocalDate getDateContent(String elementName) throws DateTimeParseException {
        LocalDate result = null;
        String content = getStringContent(elementName);
        if (content != null && !content.isEmpty()) {
            result = LocalDate.parse(content);
        }
        return result;
    }

    public BigDecimal getBigDecimalContent(String elementName) throws NumberFormatException {
        BigDecimal result = null;
        String content = getStringContent(elementName);
        if (content != null && !content.isEmpty()) {
            result = new BigDecimal(content);
        }
        return result;
    }

}

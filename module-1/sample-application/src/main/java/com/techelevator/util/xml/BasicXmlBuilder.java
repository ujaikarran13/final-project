package com.techelevator.util.xml;

import com.techelevator.util.exception.BasicXmlException;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The BasicXmlBuilder is a utility for generating an XML string based on a set of data.
 * The generated XML is very limited, with the most significant limitation being that
 * every tag it includes must be unique.
 */

public class BasicXmlBuilder extends BasicXmlUtility {

    //Example: instantiating a LinkedHashMap of String keys and String values
    private final Map<String, String> elementData = new LinkedHashMap<>();

    public static String createSingleElement(String tagName, String content) {
        // Avoid ugly excess whitespace in the XML by only adding a linebreak
        // before the closing tag when there isn't already one there.
        if (!content.endsWith("\n")) {
            content += "\n";
        }
        return String.format("%s\n%s%s\n", makeOpeningTag(tagName), content, makeClosingTag(tagName));
    }

    //Example: overloading a method

    public BasicXmlBuilder addElement(String tag, Object value) throws BasicXmlException {
        if (value != null) {
            if (elementData.containsKey(tag)) {
                throw new BasicXmlException("BasicXml requires unique tags.");
            }
            String data = value.toString();
            if (data.contains(makeClosingTag(tag))) {
                throw new BasicXmlException("BasicXml doesn't support child element with same tags as parent.");
            }
            elementData.put(tag, data);
        }
        return this;
    }

    public BasicXmlBuilder addElement(String tag, BasicXml value) throws BasicXmlException {
        if (value != null) {
            addElement(tag, value.getInnerXml());
        }
        return this;
    }

    public String build() {
        String result = "";
        //Example: iterating with Map.Entry
        for (Map.Entry<String, String> entry : elementData.entrySet()) {
            String tagName = entry.getKey();
            String content = entry.getValue();
            result += createSingleElement(tagName, content);
        }
        return result;
    }
}

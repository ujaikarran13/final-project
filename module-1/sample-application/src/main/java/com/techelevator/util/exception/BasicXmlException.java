package com.techelevator.util.exception;

/**
 * The class BasicXmlException defines a custom exception the application uses to indicate
 * problems converting between objects and their BasicXml representation.
 */

public class BasicXmlException extends RuntimeException {

    public BasicXmlException(String message) {
        super(message);
    }

    public BasicXmlException(String message, Throwable cause) {
        super(message, cause);
    }

}

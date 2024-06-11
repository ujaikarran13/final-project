package com.techelevator.util.exception;

/**
 * The class FileStorageException defines a custom exception the application uses to indicate
 * problems with writing to files or reading from files.
 */

public class FileStorageException extends Exception {

    public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}

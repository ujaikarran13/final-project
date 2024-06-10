package com.techelevator.util;

import com.techelevator.util.exception.FileStorageException;

/**
 * The BasicFileStorage interface is implemented by a class that can write a string to a file
 * and read the contents of a file as a string.
 *
 * The main reason this interface is included in the application is to facilitate testing.
 * When testing, an implementation of this interface can be used that simulates file storage.
 */

public interface BasicFileStorage {

    void writeContentsToFile(String contents, String filename) throws FileStorageException;

    String readContentsOfFile(String filename) throws FileStorageException;

}

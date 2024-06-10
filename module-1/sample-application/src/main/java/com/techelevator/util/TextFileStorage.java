package com.techelevator.util;

import com.techelevator.util.exception.FileStorageException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * TextFileStorage is a class that encapsulates writing to a text file with a PrintWriter and
 * reading from a text file with a Scanner. Exceptions that occur are wrapped in a FileStorageException
 * and re-thrown.
 */

public class TextFileStorage implements BasicFileStorage {

    @Override
    public void writeContentsToFile(String contents, String filename) throws FileStorageException {
        //Example: writing to a file with a PrintWriter
        try (PrintWriter printWriter = new PrintWriter(filename)) {
            printWriter.print(contents);
        } catch (FileNotFoundException e) {
            throw new FileStorageException("Can't write to " + filename, e);
        }
    }

    @Override
    public String readContentsOfFile(String filename) throws FileStorageException {
        String contents = "";
        //Example: reading a file with a Scanner
        File inputFile = new File(filename);
        try (Scanner scanner = new Scanner(inputFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                contents += line + "\n";
            }
        } catch (FileNotFoundException e) {
            throw new FileStorageException("Failed to read from " + filename, e);
        }
        return contents;
    }
}

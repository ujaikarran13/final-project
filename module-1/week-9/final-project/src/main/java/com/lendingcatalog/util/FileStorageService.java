package com.lendingcatalog.util;

import com.lendingcatalog.util.exception.FileStorageException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileStorageService {

    // Requirement: File I/O
    public static void writeContentsToFile(String contents, String filename, boolean appendFile) throws FileStorageException, IOException {
        try (FileWriter fileWriter = new FileWriter(filename, appendFile)) {
            fileWriter.write(contents);
        } catch (IOException e) {
            throw new FileStorageException("Failed to write content to file: " + filename, e);
        }
    }

    public static List<String> readContentsOfFile(String filename) throws FileStorageException {

        List<String> lines = new ArrayList<>();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new FileStorageException("Failed to write content to file: " + filename, e);
        }
        return lines;
    }
}


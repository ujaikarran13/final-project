package com.techelevator.util;

import com.techelevator.util.exception.FileStorageException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

public class TextFileStorageTest {

    //Example: testing reading with a temporary file
    @Test
    public void canReadTextFile() throws FileStorageException, IOException {
        //arrange
        Path outputFile = Files.createTempFile("test", "txt");
        String expectedContents = "This is a test of reading from a file.\nThis is only a test.\n";
        Files.writeString(outputFile, expectedContents);

        TextFileStorage sut = new TextFileStorage();

        //act
        String contents = sut.readContentsOfFile(outputFile.toString());

        //assert
        Assert.assertEquals("Text file contents read incorrectly.", expectedContents, contents);
    }

    //Example: testing writing with a temporary file
    @Test
    public void canWriteTextFile() throws FileStorageException, IOException {
        //arrange
        String filename = "test" + UUID.randomUUID() + "txt";
        String contents = "This is a test of writing to a file.\nThis is only a test.\n";
        TextFileStorage sut = new TextFileStorage();

        //act
        sut.writeContentsToFile(contents, filename);

        //assert
        Path newFile = Path.of(filename);
        String actualContents = Files.readString(newFile);
        Files.delete(newFile);

        Assert.assertEquals("Text file contents written incorrectly.", contents, actualContents);
    }
}

package com.techelevator.util;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class SystemInOutConsoleTest {


    @Before
    public void setup() {
        System.setOut(new PrintStream(OutputStream.nullOutputStream()));
    }

    @Test
    public void whenAnIntegerIsEntered_promptForIntegerReturnsIt() {
        //arrange
        prepareSystemIn("99\n");
        SystemInOutConsole sut = new SystemInOutConsole();
        //act
        Integer result = sut.promptForInteger("prompt");
        //assert
        Assert.assertEquals("promptForInteger() returned wrong value", Integer.valueOf(99), result);
    }

    @Test
    public void whenNonIntegersAreEntered_promptForIntegerKeepsTrying() {
        //arrange
        prepareSystemIn("test\n0.1\n999\n");
        SystemInOutConsole sut = new SystemInOutConsole();
        //act
        Integer result = sut.promptForInteger("prompt");
        //assert
        Assert.assertEquals("promptForInteger() failed when invalid input was supplied", Integer.valueOf(999), result);
    }

    @Test
    public void whenInputIsBlank_promptForIntegerReturnsNull() {
        //arrange
        prepareSystemIn("\n");
        SystemInOutConsole sut = new SystemInOutConsole();
        //act
        Integer result = sut.promptForInteger("prompt");
        //assert
        Assert.assertNull("promptForInteger() failed to return null for blank input", result);
    }

    @Test
    public void whenNumberIsEntered_correspondingMenuOptionIsReturned() {
        //arrange
        String[] options = {"first", "second", "third"};
        String[] expectedResults = {"third", "first", "second"};
        prepareSystemIn("3\n1\n2\n");
        SystemInOutConsole sut = new SystemInOutConsole();
        //act
        String[] actualResults = new String[3];
        actualResults[0] = sut.getMenuSelection(options);
        actualResults[1] = sut.getMenuSelection(options);
        actualResults[2] = sut.getMenuSelection(options);
        //assert
        Assert.assertArrayEquals("getMenuSelection() returned the wrong option", expectedResults, actualResults);
    }

    @Test
    public void whenNullIsNotAllowed_getMenuSelectionKeepsTrying() {
        //arrange
        String[] options = {"first", "second", "third"};
        prepareSystemIn("\n\n2\n");
        SystemInOutConsole sut = new SystemInOutConsole();
        //act
        String result = sut.getMenuSelection(options, false);
        //assert
        Assert.assertEquals("getMenuSelection() returned the wrong option", "second", result);
    }

    @Test
    public void whenNullIsAllowedAndInputIsBlank_getMenuSelectionReturnsNull() {
        //arrange
        String[] options = {"first", "second", "third"};
        prepareSystemIn("\n\n2\n");
        SystemInOutConsole sut = new SystemInOutConsole();
        //act
        String result = sut.getMenuSelection(options, true);
        //assert
        Assert.assertNull("getMenuSelection() failed to return null", result);
    }

    private void prepareSystemIn(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

}

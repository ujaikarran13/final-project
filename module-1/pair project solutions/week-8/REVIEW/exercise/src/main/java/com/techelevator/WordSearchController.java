package com.techelevator;

import com.techelevator.util.BasicConsole;

import java.io.File;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.*;

public class WordSearchController {

    private final WordSearchView view;

    public WordSearchController(BasicConsole console) {
        view = new WordSearchView(console);
    }

    public void run() {

        // Prompt for the search path, search word, and book category
        File folderToSearch = view.promptForSearchFolder();
        String searchWord = view.promptForSearchWord();
        String category = view.promptForCategory();
        view.printBlankLine();

        /*
         Your code begins here
         */

    }
}

package com.techelevator;

import com.techelevator.util.BasicConsole;

import java.io.File;

public class WordSearchView {

    private final BasicConsole console;

    public WordSearchView(BasicConsole console) {
        this.console = console;
    }

    public File promptForSearchFolder() {
        File searchFolder = null;
        while (true) {
            String path = console.promptForString("Enter the path of the search folder: ");
            searchFolder = new File(path);
            if (searchFolder.isDirectory()) {
                break;
            }
            else {
                console.printErrorMessage("'" + path + "' is not a folder or not found.");
            }
        }
        return searchFolder;
    }

    public String promptForSearchWord() {
        String searchWord = "";
        while (true) {
            searchWord = console.promptForString("Enter the search word: ");
            if (searchWord.isBlank() == false) { // == false is more readable
                break;
            }
            else {
                console.printErrorMessage("The search word may not be blank.");
            }
        }
        return searchWord;
    }

    public String promptForCategory() {
        String category;
        while (true) {
            category = console.promptForString("Enter a category to filter by (novel, story, collection, or leave blank for all): ").toLowerCase();
            if (category.equals("novel")) {
                break;
            }
            else if (category.equals("story")) {
                break;
            }
            else if (category.equals("collection")) {
                break;
            }
            else if (category.isBlank()) {
                break;
            }
            else {
                console.printErrorMessage("The category must be `novel`, `story`, `collection`, or blank.");
            }
        }
        return category;
    }

    public void printBlankLine() {
        console.printBlankLine();
    }

    public void printMessage(String message) {
        console.printMessage(message);
    }

    public void printMessage(String message, boolean withLineFeed) {
        console.printMessage(message, withLineFeed);
    }

    public void printErrorMessage(String message) {
        console.printErrorMessage(message);
    }
}

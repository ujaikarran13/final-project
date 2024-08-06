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

        List<SearchResult> searchResults = new ArrayList<>();
        File[] myArray = folderToSearch.listFiles();

        for (File file : myArray) {
            if ((category.isBlank()) || (file.getName().endsWith("." + category))) {
                SearchResult sr = search(file, searchWord);
                if (sr.getNumberOfHits() > 0) {
                    searchResults.add(sr);
                }
            }
        }

        if (searchResults.size() > 0) {
            view.printMessage("\nSEARCH RESULTS\n");
            for (int i = 0; i < searchResults.size(); i++) {
                SearchResult sr = searchResults.get(i);
                view.printMessage(i + 1 + ".\t" + sr.getBook().getTitle() + " " + occurrences(sr.getNumberOfHits(),
                        searchWord));
                view.printMessage("\tAuthor: " + sr.getBook().getAuthor());
                view.printMessage("\tCategory: " + sr.getCategory() + " (" +
                        NumberFormat.getInstance().format(sr.getBook().getPageCount()) + " pages)");
                view.printMessage("\tPublisher: " + sr.getBook().getPublisher());
                view.printMessage("\tYear: " + sr.getBook().getDatePublished().getYear());
            }
        }
    }

    private SearchResult search(File file, String searchWord) {
        SearchResult searchResult = null;
        try (Scanner inputScanner = new Scanner(file)) {
            String[] bookParts = inputScanner.nextLine().split("\\|");
            Book book = new Book(bookParts[0], bookParts[1], bookParts[2], LocalDate.parse(bookParts[3]), Integer.parseInt(bookParts[4]));
            int hits = 0;
            view.printMessage("Searching " + file.getName(), false);
            while (inputScanner.hasNextLine()) {
                String line = inputScanner.nextLine();
                if (line.contains(searchWord)) {
                    hits++;
                }
            }
            view.printMessage(" " + occurrences(hits, searchWord));
            searchResult = new SearchResult(book, getCategory(file), hits);
        }
        catch (java.io.FileNotFoundException e) {
            view.printErrorMessage("File " + file.getName() + " unexpectedly not found.");
        }
        return searchResult;
    }

    private String occurrences(int hits, String searchWord) {
        return "(" + (hits == 0? "no hits" : hits + " occurrences of \""+searchWord+"\"") + ")";
    }
    private String getCategory(File file) {
        String category;
        int positionOfLastDot = file.getName().lastIndexOf(".");
        String extension = file.getName().substring(positionOfLastDot + 1).toLowerCase();
        switch (extension) {
            case "collection":
                category = "Collection";
                break;
            case "novel":
                category = "Novel";
                break;
            case "story":
                category = "Story";
                break;
            default:
                category = "Unknown";
                break;
        }
        return category;
    }
}

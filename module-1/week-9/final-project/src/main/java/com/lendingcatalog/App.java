package com.lendingcatalog;

import com.lendingcatalog.model.*;
import com.lendingcatalog.util.FileStorageService;
import com.lendingcatalog.util.exception.FileStorageException;

import javax.xml.catalog.Catalog;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

public class App {

    // The regex string to split the Strings in the dataset.
    private static final String FIELD_DELIMITER = "\\|";
    private static final String FILE_BASE_PATH = "src/main/resources/";

    private final Scanner keyboard = new Scanner(System.in);

    private Map<String, List<CatalogItem>> catalog = new HashMap<>();

    public static void main(String[] args) throws IOException, FileStorageException {

        App app = new App();
        app.initialize(FILE_BASE_PATH + "members.dat");
        app.run();
    }

    private void initialize(String fileName) throws IOException, FileStorageException {
        // Requirement: Data transformation
        List<String> memberLines = FileStorageService.readContentsOfFile(fileName);
        for (String line : memberLines) {
            String[] fields = line.split(FIELD_DELIMITER);
            if (fields.length != 3) continue;
            String firstName = fields[0];
            String lastName = fields[1];
            fileName = fields[2];

            String itemFileName= fileName;
            String itemFilePath = FILE_BASE_PATH + itemFileName;

            if (!Files.exists(Paths.get(itemFilePath))){
                throw new FileStorageException("File not found: " + itemFilePath);
            }
            Member member = new Member(firstName, lastName);
            catalog.put(member.toString(), new ArrayList<>());
            readItemFile (firstName, lastName, itemFilePath);
        }
    }
    private void readItemFile (String firstName, String lastName, String itemFilePath) throws FileNotFoundException, FileStorageException {
         List<String> reader = FileStorageService.readContentsOfFile(itemFilePath);

            for (String itemLine: reader){
                CatalogItem item = processItemLine(itemLine);
                String memberKey = new Member(firstName, lastName).toString();

                if (catalog.containsKey(memberKey)) {
                    catalog.get(memberKey).add(item);
                }
            }
    }

    private CatalogItem processItemLine(String line) {
        CatalogItem catalogItem = null;
        String[] itemFields = line.split(FIELD_DELIMITER);
        if (itemFields.length >= 2) {
            if (itemFields[0].equals("book")) {
                Book book = new Book(itemFields[1], itemFields[2], LocalDate.parse(itemFields[3]));
                catalogItem = book;
            } else if (itemFields[0].equals("movie")) {
                Movie movie = new Movie(itemFields[1], itemFields[2], LocalDate.parse(itemFields[3]));
                catalogItem = movie;
            } else if (itemFields[0].equals("Tool")) {
                Tool tool = new Tool(itemFields[1], itemFields[2], itemFields[3]);
                catalogItem = tool;
            }
        
        }
        return catalogItem;
    }
    private void run() {

        if (catalog.isEmpty()) {
            System.out.println("Application cannot run if catalog is empty");
            return;
        }

        while (true) {
            // Main menu loop
            printMainMenu();
            int mainMenuSelection = promptForMenuSelection("Please choose an option: ");
            if (mainMenuSelection == 1) {
                // Display data and subsets loop
                while (true) {
                    printDataAndSubsetsMenu();
                    int dataAndSubsetsMenuSelection = promptForMenuSelection("Please choose an option: ");
                    if (dataAndSubsetsMenuSelection == 1) {
                        displayFullCatalog(catalog);
                    } else if (dataAndSubsetsMenuSelection == 2) {
                        displayUsersForItemDisplay(catalog);
                        String userName = promptForString("Enter name: ");
                        displayUserItems(catalog, userName);
                    } else if (dataAndSubsetsMenuSelection == 0) {
                        break;
                    }
                }
            }
            else if (mainMenuSelection == 2) {
                while (true) {
                    printSearchMenu();
                    int searchMenuSelection = promptForMenuSelection("Please choose an option: ");
                    if (searchMenuSelection == 1) {
                        // Search by name/title
                        String searchName = promptForString("Enter name: ");
                        displayMatchesByName(catalog, searchName);
                    } else if (searchMenuSelection == 2) {
                        // Search by creator
                        String searchCreator = promptForString("Enter creator: ");
                        displayMatchesByCreator(catalog, searchCreator);
                    } else if (searchMenuSelection == 3) {
                        // Search by publish/release year
                        int searchYear = promptForYear("Enter date (YYYY): ");
                        displayMatchesByYear(catalog, searchYear);
                    } else if (searchMenuSelection == 0) {
                        break;
                    }
                }
            } else if (mainMenuSelection == 0) {
                break;
            }
        }
    }


    // UI methods

    private void printMainMenu() {
        System.out.println("1: Display catalog");
        System.out.println("2: Search catalog");
        System.out.println("0: Exit");
        System.out.println();
    }

    private void printDataAndSubsetsMenu() {
        System.out.println("1: Display full catalog");
        System.out.println("2: Display all items from a user");
        System.out.println("0: Return to main menu");
        System.out.println();
    }

    private void printSearchMenu() {
        System.out.println("1: Search items by name");
        System.out.println("2: Search items by creator");
        System.out.println("3: Search by year");
        System.out.println("0: Return to main menu");
        System.out.println();
    }

    private void displayFullCatalog(Map<String, List<CatalogItem>> catalog) {
        System.out.println("Full Catalog");
        System.out.println("-------");
        for (Map.Entry<String, List<CatalogItem>> entry : catalog.entrySet()) {
            System.out.println(entry.getKey() + ": ");
            for (CatalogItem item : entry.getValue()) {
                if (item != null) {
                    System.out.println(item.toString());
                }
            }
            System.out.println();
        }
        System.out.println();
        promptForReturn();
    }

    private void displayUsersForItemDisplay(Map<String, List<CatalogItem>> catalog) {
        System.out.println("Users");
        System.out.println("-------");
        for (Map.Entry<String, List<CatalogItem>> entry : catalog.entrySet()) {
            System.out.println(entry.getKey());
        }
        System.out.println();
    }

    private void displayUserItems(Map<String, List<CatalogItem>> catalog, String userName) {
        System.out.println("Items from " + userName);
        System.out.println("-------");
        if (catalog.containsKey(userName)) {
            List<CatalogItem> userItems = catalog.get(userName);
            for (CatalogItem item : userItems) {
                System.out.println(item);
            }
        } else {
            System.out.println("No user found for '" + userName + "'");
        }
        System.out.println();
        promptForReturn();
    }

    private void displayMatchesByName(Map<String, List<CatalogItem>> catalog, String searchName) {
        System.out.println("Matches by name: " + searchName);
        System.out.println("-------");
        for (Map.Entry<String, List<CatalogItem>> entry : catalog.entrySet()) {
            boolean hasMatches = false;
            System.out.println(entry.getKey() + ": ");
            for (CatalogItem item : entry.getValue()) {
                if (item.matchesName(searchName)) {
                    System.out.println(item);
                    hasMatches = true;
                }
            }
            if (!hasMatches) {
                System.out.println("--No matching items--");
            }
            System.out.println();
        }
        System.out.println();
        promptForReturn();
    }

    private void displayMatchesByCreator(Map<String, List<CatalogItem>> catalog, String searchCreator) {
        System.out.println("Matches by creator: " + searchCreator);
        System.out.println("-------");
        for (Map.Entry<String, List<CatalogItem>> entry : catalog.entrySet()) {
            boolean hasMatches = false;
            System.out.println(entry.getKey() + ": ");
            for (CatalogItem item : entry.getValue()) {
                if (item.matchesCreator(searchCreator)) {
                    System.out.println(item);
                    hasMatches = true;
                }
            }
            if (!hasMatches) {
                System.out.println("--No matching items--");
            }
            System.out.println();
        }
        System.out.println();
        promptForReturn();
    }

    private void displayMatchesByYear(Map<String, List<CatalogItem>> catalog, int searchYear) {
        System.out.println("Matches by year: " + searchYear);
        System.out.println("-------");
        for (Map.Entry<String, List<CatalogItem>> entry : catalog.entrySet()) {
            boolean hasMatches = false;
            System.out.println(entry.getKey() + ": ");
            for (CatalogItem item : entry.getValue()) {
                if (item.matchesYear(searchYear)) {
                    System.out.println(item);
                    hasMatches = true;
                }
            }
            if (!hasMatches) {
                System.out.println("--No matching items--");
            }
            System.out.println();
        }
        System.out.println();
        promptForReturn();
    }

    private int promptForMenuSelection(String prompt) {
        System.out.print(prompt);
        int menuSelection;
        try {
            menuSelection = Integer.parseInt(keyboard.nextLine());
        } catch (NumberFormatException e) {
            menuSelection = -1;
        }
        return menuSelection;
    }

    private String promptForString(String prompt) {
        System.out.print(prompt);
        return keyboard.nextLine();
    }

    private int promptForYear(String prompt) {
        int year;
        while (true) {
            System.out.println(prompt);
            try {
                year = Integer.parseInt(keyboard.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("The year provided is not well-formed. It must be YYYY.");
            }
        }
        return year;
    }

    private void promptForReturn() {
        System.out.println("Press RETURN to continue.");
        keyboard.nextLine();
    }
}

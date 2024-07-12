package com.techelevator;

import java.math.BigDecimal;
import java.util.*;

public class App {

    // The regex string to split the Strings in the dataset.
    private static final String FIELD_DELIMITER = "\\|";

    private static final int TITLE_FIELD = 0;
    private static final int AUTHOR_FIELD = 1;
    private static final int PUBLISHED_YEAR_FIELD = 2;
    private static final int PRICE_FIELD = 3;


    private final Scanner keyboard = new Scanner(System.in);

    private List<String> titles = new ArrayList<>();
    private List<String> authors = new ArrayList<>();
    private List<Integer> publishedYears = new ArrayList<>();
    private List<BigDecimal> prices = new ArrayList<>();

    public static void main(String[] args) {

        App app = new App();
        app.loadData();
        app.run();

    }

    private void loadData() {

        String[] dataset = Dataset.load();

        for (String data : dataset) {
            String[] parts = data.split(FIELD_DELIMITER);

            titles.add(parts[0].trim());
            authors.add(parts[1].trim());
            publishedYears.add(Integer.parseInt(parts[2].trim()));
            prices.add(new BigDecimal(parts[3].trim()));
        }

        System.out.println("Titles" + titles);
        System.out.println("Authors" + authors);
        System.out.println("Published years:" + publishedYears);
        System.out.println("Prices:" + prices);

    }

    /*
             Requirement: 1
             Populate the instance variables `titles`, `authors`, `publishedYears`,
             and `prices` by splitting each string in the `dataset` array and adding
             the individual fields to the appropriate list.
             See README for additional details.
             */
    private void run() {

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
                        displayDataset(Dataset.load());
                    } else if (dataAndSubsetsMenuSelection == 2) {
                        displayTitlesList(titles);
                    } else if (dataAndSubsetsMenuSelection == 3) {
                        displayAuthorsList(authors);
                    } else if (dataAndSubsetsMenuSelection == 4) {
                        displayPublishedYearsList(publishedYears);
                    } else if (dataAndSubsetsMenuSelection == 5) {
                        displayPricesList(prices);
                    } else if (dataAndSubsetsMenuSelection == 0) {
                        break;
                    }
                }
            } else if (mainMenuSelection == 2) {
                while (true) {
                    printSearchBooksMenu();
                    int searchBooksMenuSelection = promptForMenuSelection("Please choose an option: ");
                    if (searchBooksMenuSelection == 1) {// Search by title
                        String filterTitle = promptForString("Enter title: ");
                        List<Integer> indexes = filterByTitle(filterTitle);
                        displaySearchResults(indexes, TITLE_FIELD);
 /*
                         Requirement: 3b
                         Replace `displayTitlesList(titles)` with calls to the
                         `filterByTitle()` and `displaySearchResults()` methods.
                         */
                    } else if (searchBooksMenuSelection == 2) {// Search by author
                        String filterAuthor = promptForString("Enter author: ");
                        List<Integer> indexes = filterByAuthor(filterAuthor);
                        displaySearchResults(indexes, AUTHOR_FIELD);

 /*
                         Requirement: 4b
                         Replace `displayAuthorsList(authors)` with calls to the
                         `filterByAuthor()` and `displaySearchResults()` methods.
                         */
                    } else if (searchBooksMenuSelection == 3) { // Search by published year
                        int filterYear = promptForPublishedYear("Enter date (YYYY): ");
                        List<Integer> indexes = filterByPublishedYear(publishedYears);
                        displaySearchResults(indexes, PUBLISHED_YEAR_FIELD);
 /*
                         Requirement: 5b
                         Replace `displayPublishedYearsList(publishedYears)` with calls
                         to the `filterByPublishedYear()` and `displaySearchResults()` methods.
                         */
                    } else if (searchBooksMenuSelection == 4) {
                        // Search by published year range
                        int filterFromYear = promptForPublishedYear("Enter \"from\" date (YYYY): ");
                        int filterToYear = promptForPublishedYear("Enter \"to\" date (YYYY): ");
                        List<Integer> indexes = filterByPublishedYearRange(filterFromYear, filterToYear);
                        displaySearchResults(indexes, PUBLISHED_YEAR_FIELD);
/*
                         Requirement: 6b
                         Replace `displayPublishedYearsList(publishedYears)` with calls
                         to the `filterByPublishedYearRange()` and `displaySearchResults()` methods.
                         */

                    } else if (searchBooksMenuSelection == 5) {
                        List<Integer> indexes = findMostRecentBooks(publishedYears);
                        displaySearchResults(indexes, PUBLISHED_YEAR_FIELD);
/*

                        // Find the most recent books
                        /*
                         Requirement: 7b
                         Replace `displayPublishedYearsList(publishedYears)` with calls
                         to the `findMostRecentBooks()` and `displaySearchResults()` methods.
                         */
                    } else if (searchBooksMenuSelection == 6) {// Search by price
                        double filterPrice = promptForPrice("Enter price: ");
                        List<Integer> indexes = filterByPrice(filterPrice);
                        displaySearchResults(indexes, PRICE_FIELD);
                         /*
                         Requirement: 8b
                         Replace `displayPricesList(prices)` with calls to the
                         `filterByPrice()` and `displaySearchResults()` methods.
                         */

                    } else if (searchBooksMenuSelection == 7) {// Search by price range
                        BigDecimal filterFromPrice = BigDecimal.valueOf(promptForPrice("Enter \"from\" price: "));
                        BigDecimal filterToPrice = BigDecimal.valueOf(promptForPrice("Enter \"to\" price: "));
                        List<Integer> indexes = filterByPriceRange(filterFromPrice, filterToPrice);
                        displaySearchResults(indexes, PRICE_FIELD);
 /*
                         Requirement: 9b
                         Replace `displayPricesList(prices)` with calls to the
                         `filterByPriceRange()` and `displaySearchResults()` methods.
                         */
                    } else if (searchBooksMenuSelection == 8) {
                        List<Integer> indexes = findLeastExpensiveBooks(prices);
                        displaySearchResults(indexes, PRICE_FIELD);
                        // Find the least expensive books
                        /*
                         Requirement: 10b
                         Replace `displayPricesList(prices)` with calls to the
                         `findLeastExpensiveBooks()` and `displaySearchResults()` methods.
                         */

                    } else if (searchBooksMenuSelection == 0) {
                        break;
                    }
                }
            } else if (mainMenuSelection == 0) {
                break;
            }
        }

    }

    private List<Integer> findLeastExpensiveBooks(List<BigDecimal> prices) {
        List<Integer> leastExpensiveBooks = new ArrayList<>();
        if (prices == null || prices.isEmpty()) {
            return leastExpensiveBooks;
        }
        BigDecimal minPrice = prices.get(0);
        leastExpensiveBooks.add(0);
        for (int i = 1; i < prices.size(); i++) {
            BigDecimal currentPrice = prices.get(i);
        }
        return leastExpensiveBooks;
    }

    private List<Integer> findMostRecentBooks(List<Integer> publishedYears) {

        List<Integer> recentBooksIndexes = new ArrayList<>();

        int mostRecentYear = Integer.MIN_VALUE;
        for (int year : publishedYears) {
            if (year > mostRecentYear) {
                mostRecentYear = year;
            }
        }
        for (int i = 0; i < publishedYears.size(); i++) {
            if (publishedYears.get(i) == mostRecentYear) {
                recentBooksIndexes.add(i);
            }
        }
        return recentBooksIndexes;
    }
    /*
        Requirement: 7a
        Add the `private List<Integer> findMostRecentBooks()` method.
        See README for additional details.
        */

    /*
         Requirement: 6a
         Complete the `filterByPublishedYearRange()` method.
         See README for additional details.
         */
    private void displaySearchResults(List<Integer> indexes, int primaryField) {

        for (int index : indexes) {
            switch (primaryField) {
                case TITLE_FIELD:
                    System.out.println(titles.get(index) + ": " + authors.get(index) + ": " + publishedYears.get(index) + ": $" + prices.get(index))
                    ;

                    break;
                case AUTHOR_FIELD:
                    System.out.println(authors.get(index) + titles.get(index) + ": " + ": " + publishedYears.get(index) + ": $" + prices.get(index))
                    ;
                    break;
                case PUBLISHED_YEAR_FIELD:
                    System.out.println(publishedYears.get(index) + ": " + titles.get(index) + ": " + authors.get(index) + ": $" + prices.get(index))
                    ;
                    break;
                case PRICE_FIELD:
                    System.out.println(prices.get(index) + ": " + titles.get(index) + ": " + authors.get(index) + ": " + publishedYears.get(index))
                    ;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid primaryField value: " + primaryField);
            }
        }
    }

    /*
     Requirement: 2
     Write the displaySearchResults(List<Integer> indexes) method.
     See README for additional details.
     */


    private List<Integer> filterByTitle(String filterTitle) {

        List<Integer> titleIndexes = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            if (titles.get(i).toLowerCase().contains(filterTitle.toLowerCase())) {
                titleIndexes.add(i);
            }
        }

        return titleIndexes;
    }
    /*
        Requirement: 3a
        Complete the `filterByTitle()` method.
        See README for additional details.
        */

    private List<Integer> filterByAuthor(String filterAuthor) {

        List<Integer> authorIndexes = new ArrayList<>();
        for (int i = 0; i < authors.size(); i++) {
            if (authors.get(i).toLowerCase().contains(filterAuthor.toLowerCase())) {
                authorIndexes.add(i);
            }
        }

        return authorIndexes;
    }
    /*
         Requirement: 4a
         Complete the `filterByAuthor()` method.
         See README for additional details.
         */

    private List<Integer> filterByPublishedYear(List<Integer> filterYear) {

        List<Integer> yearIndexes = new ArrayList<>();
        for (int i = 0; i < publishedYears.size(); i++) {
            if (publishedYears.get(i).equals(filterYear)) {
                yearIndexes.add(i);
            }
        }
        return yearIndexes;
    }
    /*
         Requirement: 5a
         Complete the `filterByPublishedYear()` method.
         See README for additional details.
         */

    private List<Integer> filterByPublishedYearRange(int filterFromYear, int filterToYear) {

        List<Integer> yearFilteredIndexes = new ArrayList<>();
        for (int i = 0; i < publishedYears.size(); i++) {
            int year = publishedYears.get(i);
            if (year >= filterFromYear && year <= filterToYear) {
                yearFilteredIndexes.add(i);
            }
        }
        return yearFilteredIndexes;
    }


    private List<Integer> filterByPrice(double filterPrice) {

        List<Integer> priceIndexes = new ArrayList<>();
        for (int i = 0; i < prices.size(); i++) {
            BigDecimal price = prices.get(i);
            if (price.compareTo(price) == 0) {
                priceIndexes.add(i);
            }
        }

        return priceIndexes;
    }
    /*
        Requirement: 8a
        Complete the `filterByPrice()` method.
        See README for additional details.
        */

    private List<Integer> filterByPriceRange(BigDecimal filterFromPrice, BigDecimal filterToPrice) {


        List<Integer> priceFilteredIndexes = new ArrayList<>();

        for (int i = 0; i < prices.size(); i++) {
            BigDecimal price = prices.get(i);
            if (price.compareTo(filterFromPrice) >= 0 && price.compareTo(filterToPrice) <= 0) {
                priceFilteredIndexes.add(i);
            }
        }
        return priceFilteredIndexes;
    }

    /*
         Requirement: 9a
         Complete the `filterByPriceRange()` method.
         See README for additional details.
         */
    /*
     Requirement: 10a
     Add the `private List<Integer> findLeastExpensiveBooks()` method.
     See README for additional details.
     */
    private List<Integer> findLeastExpensiveBooks(BigDecimal leastExpensive) {
        List<Integer> priceIndexes = new ArrayList<>();
        for (int i = 0; i < prices.size(); i++) {
            BigDecimal price = prices.get(i);
            if (price.compareTo(leastExpensive) == 0) {
                priceIndexes.add(i);
            }
        }
        return priceIndexes;

    }

    // UI methods

    private void printMainMenu() {
        System.out.println("1: Display data and subsets");
        System.out.println("2: Search books");
        System.out.println("0: Exit");
        System.out.println();
    }

    private void printDataAndSubsetsMenu() {
        System.out.println("1: Display dataset");
        System.out.println("2: Display titles");
        System.out.println("3: Display authors");
        System.out.println("4: Display published years");
        System.out.println("5: Display prices");
        System.out.println("0: Return to main menu");
        System.out.println();
    }

    private void printSearchBooksMenu() {
        System.out.println("1: Search by title");
        System.out.println("2: Search by author");
        System.out.println("3: Search by published year");
        System.out.println("4: Search by published year range");
        System.out.println("5: Find most recent books");
        System.out.println("6: Search by price");
        System.out.println("7: Search by price range");
        System.out.println("8: Find least expensive books");
        System.out.println("0: Return to main menu");
        System.out.println();
    }

    private void displayDataset(String[] dataset) {
        System.out.println("Dataset");
        System.out.println("-------");
        for (String data : dataset) {
            System.out.println(data);
        }
        System.out.println();
        promptForReturn();
    }

    private void displayTitlesList(List<String> titles) {
        System.out.println("Titles");
        System.out.println("-------");
        for (int i = 0; i < titles.size(); i++) {
            System.out.println(i + ": " + titles.get(i));
        }
        System.out.println();
        promptForReturn();
    }

    private void displayAuthorsList(List<String> authors) {
        System.out.println("Authors");
        System.out.println("-------");
        for (int i = 0; i < authors.size(); i++) {
            System.out.println(i + ": " + authors.get(i));
        }
        System.out.println();
        promptForReturn();
    }

    private void displayPublishedYearsList(List<Integer> publishedYears) {
        System.out.println("Published Years");
        System.out.println("---------------");
        for (int i = 0; i < publishedYears.size(); i++) {
            System.out.println(i + ": " + publishedYears.get(i));
        }
        System.out.println();
        promptForReturn();
    }

    private void displayPricesList(List<BigDecimal> prices) {
        System.out.println("Prices");
        System.out.println("------");
        for (int i = 0; i < prices.size(); i++) {
            System.out.println(i + ": " + prices.get(i));
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

    private int promptForPublishedYear(String prompt) {
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

    private double promptForPrice(String prompt) {
        double price;
        while (true) {
            System.out.println(prompt);
            try {
                price = Double.parseDouble(keyboard.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("The price provided is not a valid monetary value.");
            }
        }
        return price;
    }

    private void promptForReturn() {
        System.out.println("Press RETURN to continue.");
        keyboard.nextLine();
    }

    private void sortSearchResults(List<Integer> indexes, int primaryField){
        Collections.sort(indexes, new Comparator<Integer>() {
            @Override
            public int compare(Integer index1, Integer index2) {
                switch (primaryField) {
                    case TITLE_FIELD:
                        return titles.get(index1).compareToIgnoreCase(titles.get(index2));
                    case AUTHOR_FIELD:
                        return authors.get(index1).compareToIgnoreCase(authors.get(index2));
                    case PUBLISHED_YEAR_FIELD:
                        return Integer.compare(publishedYears.get(index1), publishedYears.get(index2));
                    case PRICE_FIELD:
                        return prices.get(index1).compareTo(prices.get(index2));
                    default:
                        throw new IllegalArgumentException("Invalid primaryField value: " + primaryField);
                }
            }
        });

    }
}


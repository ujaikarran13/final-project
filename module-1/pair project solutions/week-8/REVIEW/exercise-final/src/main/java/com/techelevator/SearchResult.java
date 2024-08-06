package com.techelevator;

public class SearchResult {

    private Book book;
    private String category;
    private int numberOfHits;

    public SearchResult(Book book, String category, int numberOfHits) {
        this.book = book;
        this.category = category;
        this.numberOfHits = numberOfHits;
    }

    public Book getBook() {
        return book;
    }

    public String getCategory() {
        return category;
    }

    public int getNumberOfHits() {
        return numberOfHits;
    }
}

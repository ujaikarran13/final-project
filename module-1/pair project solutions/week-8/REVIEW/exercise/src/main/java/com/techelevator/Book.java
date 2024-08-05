package com.techelevator;

import java.time.LocalDate;

public class Book {

    private String title;
    private String author;
    private String publisher;
    private LocalDate datePublished;
    private int pageCount;

    public Book(String title, String author, String publisher, LocalDate datePublished, int pageCount) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.datePublished = datePublished;
        this.pageCount = pageCount;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public LocalDate getDatePublished() {
        return datePublished;
    }

    public int getPageCount() {
        return pageCount;
    }

}

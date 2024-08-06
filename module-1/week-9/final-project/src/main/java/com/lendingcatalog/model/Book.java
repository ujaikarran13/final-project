package com.lendingcatalog.model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;
import java.io.FileWriter;
import java.io.IOException;

public class Book implements CatalogItem {

    private String id;
    private String title;
    private String author;
    private LocalDate publishDate;


    public Book(String title, String author, LocalDate publishDate) {
        this.title = title;
        this.author = author;
        this.publishDate = publishDate;
    }
    @Override
    public String toString() {
        return "Book [ID: " + id + ", Title: " + title + ", Author: " + author + ", Publish Date: " + publishDate + "]";
    }

    @Override
    public boolean matchesName(String searchStr) {
        return title != null && title.toLowerCase().contains(searchStr.toLowerCase());
    }

    @Override
    public boolean matchesCreator(String searchStr) {
        return author != null && author.toLowerCase().contains(searchStr.toLowerCase());
    }

    @Override
    public boolean matchesYear(int searchYear) {
        if (publishDate != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
            return Integer.parseInt(dateFormat.format(publishDate)) == searchYear;
        }
        return false;
    }

    @Override
    public void registerItem() {
        this.id = UUID.randomUUID().toString();
        CreatedMessageToALog();
    }

    private void CreatedMessageToALog() {
        String logMessage = String.format("Date: %s, Time: %s, Type: Book, ID: %s, Title: %s, Author: %s, Publish Date: %s\n",
                new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
                new SimpleDateFormat("HH:mm:ss").format(new Date()),
                id, title, author, new SimpleDateFormat("yyyy-MM-dd").format(publishDate)
        );
        writeLogFiles (logMessage, "Book");
    }

    private void writeLogFiles (String logMessage, String book) {
        try(FileWriter fileWriter = new FileWriter("src/main/resources/logs" + book + ".log", true)) {
        fileWriter.write(logMessage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

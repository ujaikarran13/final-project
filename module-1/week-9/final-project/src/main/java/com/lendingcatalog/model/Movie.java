package com.lendingcatalog.model;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class Movie implements CatalogItem{
    private String id;
    private String name;
    private String director;
    private LocalDate releaseDate;

    public Movie(String name, String director, LocalDate releaseDate) {
        this.name = name;
        this.director = director;
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "Movie [ID: " + id + ", Name: " + name + ", Director: " + director + ", Release Date: " + releaseDate + "]";
    }


    @Override
    public boolean matchesName(String searchStr) {
        return name != null && name.toLowerCase().contains(searchStr.toLowerCase());
    }

    @Override
    public boolean matchesCreator(String searchStr) {
        return director != null && director.toLowerCase().contains(searchStr.toLowerCase());
    }

    @Override
    public boolean matchesYear(int searchYear) {
        if (releaseDate != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
            return Integer.parseInt(dateFormat.format(releaseDate)) == searchYear;
        }
        return false;
    }

    @Override
    public void registerItem() {
        this.id = UUID.randomUUID().toString();
        CreatedMessageToALog();
    }
    private void CreatedMessageToALog() {
        String logMessage = String.format("Date: %s, Time: %s, Type: Movie, ID: %s, Name: %s, Director: %s, Release Date: %s\n",
                new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
                new SimpleDateFormat("HH:mm:ss").format(new Date()),
                id, name, director, new SimpleDateFormat("yyyy-MM-dd").format(releaseDate)
        );
        writeLogFiles (logMessage, "Movie");
    }

    private void writeLogFiles(String logMessage, String movie) {
        try(FileWriter fileWriter = new FileWriter("src/main/resources/logs" + movie + ".log", true)) {
            fileWriter.write(logMessage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

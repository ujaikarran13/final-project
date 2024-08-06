package com.lendingcatalog.model;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Tool implements CatalogItem {

    private String id;
    private String type;
    private String manufacturer;
    private int count;

    public Tool(String type, String manufacturer, String count) {
        this.type = type;
        this.manufacturer = manufacturer;
        this.count = Integer.parseInt(count);
    }

    @Override
    public String toString() {
        return "Tool [ID: " + id + ", Type: " + type + ", Manufacturer: " + manufacturer + ", Count: " + count + "]";
    }

    @Override
    public boolean matchesName(String searchStr) {
        return type != null && type.toLowerCase().contains(searchStr.toLowerCase());

    }

    @Override
    public boolean matchesCreator(String searchStr) {
        return manufacturer != null && manufacturer.toLowerCase().contains(searchStr.toLowerCase());
    }

    @Override
    public boolean matchesYear(int searchYear) {
        return false;
    }

    @Override
    public void registerItem() {
        this.id = UUID.randomUUID().toString();
        CreatedMessageToALog();
    }

    private void CreatedMessageToALog() {
        String logMessage = String.format("Date: %s, Time: %s, Type: Tool, ID: %s, Type: %s, Manufacturer: %s\n",
                new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
                new SimpleDateFormat("HH:mm:ss").format(new Date()),
                id, type, manufacturer);
        writeLogFiles(logMessage, "Tool");
    }

    private void writeLogFiles(String logMessage, String tool) {
        try (FileWriter fileWriter = new FileWriter("src/main/resources/logs" + type + ".log", true)) {
            fileWriter.write(logMessage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

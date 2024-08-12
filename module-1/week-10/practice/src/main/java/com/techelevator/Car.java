package com.techelevator;

public class Car {

    public static final String COMPACT = "compact";
    public static final String MIDSIZE = "midsize";
    public static final String FULLSIZE = "fullsize";

    private String type;
    private String license;

    public Car(String type, String license) {
        this.type = type;
        this.license = license;
    }

    public String getType() {
        return type;
    }

    public String getLicense() {
        return license;
    }

    @Override
    public String toString() {
        return "Car{" +
                "type='" + type + '\'' +
                ", license='" + license + '\'' +
                '}';
    }
}

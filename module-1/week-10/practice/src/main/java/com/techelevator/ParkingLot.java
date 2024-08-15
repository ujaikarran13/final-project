package com.techelevator;

import java.text.CompactNumberFormat;

public class ParkingLot {

    // DO NOT REMOVE the DEFAULT_NUMBER_OF_... constants!!!!!
    public static final int DEFAULT_NUMBER_OF_COMPACT_SLOTS = 3;
    public static final int DEFAULT_NUMBER_OF_MIDSIZE_SLOTS = 5;
    public static final int DEFAULT_NUMBER_OF_FULLSIZE_SLOTS = 2;

    /*
    Fill in the class details here...
     */
    private String name;
    private Boolean open;
    private int numberOfCompactSlots;
    private int numberOfMidsizeSlots;
    private int numberOfFullsizeSlots;
    private int lotSize;

    public String getName() {
        return name;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public int getDefaultNumberOfCompactSlots() {
        return numberOfCompactSlots;
    }

    public int getDefaultNumberOfMidsizeSlots() {
        return numberOfMidsizeSlots;
    }

    public int getDefaultNumberOfFullsizeSlots() {
        return numberOfFullsizeSlots;
    }

    public ParkingLot(String name) {
        this.name = name;
        open = false;
        numberOfCompactSlots = DEFAULT_NUMBER_OF_COMPACT_SLOTS;
        numberOfMidsizeSlots = DEFAULT_NUMBER_OF_MIDSIZE_SLOTS;
        numberOfFullsizeSlots = DEFAULT_NUMBER_OF_FULLSIZE_SLOTS;
    }

    public ParkingLot(String name, Boolean open) {
        this.name = name;
        this.open = open;

    }

    public ParkingLot(String name, Boolean open, int numberOfCompactSlots,
                      int numberOfMidsizeSlots, int numberOfFullsizeSlots) {
        this.name = name;
        this.open = open;
        this.numberOfCompactSlots = numberOfCompactSlots;
        this.numberOfMidsizeSlots = numberOfMidsizeSlots;
        this.numberOfFullsizeSlots = numberOfFullsizeSlots;

    }

    public int numberOfAvailableSlots(String carType) {
        Car car = new Car(carType, null);
        switch (carType) {
            case Car.COMPACT:
                return numberOfCompactSlots;
            case Car.MIDSIZE:
                return numberOfMidsizeSlots;
            case Car.FULLSIZE:
                return numberOfFullsizeSlots;
            default:
                throw new IllegalArgumentException("Invalid carType");
        }

    }

    public boolean park(Car car) throws ParkingLotException {
        if (!isOpen()) {
            throw new ParkingLotException("Parking lot is closed.");
        }
        boolean parked = false;
        if (numberOfCompactSlots > 0) {
            numberOfCompactSlots--;
            parked = true;
        } else if (numberOfMidsizeSlots > 0) {
            numberOfMidsizeSlots--;
            parked = true;
        } else if (numberOfMidsizeSlots > 0) {
            numberOfFullsizeSlots--;
            parked = true;
        }
        throw new IllegalArgumentException("Invalid cartype");
    }


    private boolean isOpen() {
        return open;
    }

}


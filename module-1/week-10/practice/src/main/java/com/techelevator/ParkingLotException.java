package com.techelevator;

public class ParkingLotException extends Exception {
    public ParkingLotException() {
        super();
    }
    public ParkingLotException(String message) {
        super(message);
    }
    public ParkingLotException(String message, Exception cause) {
        super(message, cause);
    }
}

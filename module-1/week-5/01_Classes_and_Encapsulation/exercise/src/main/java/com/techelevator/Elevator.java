package com.techelevator;

public class Elevator {
    private int currentFloor = 1;
    private int numberOfFloors;
    private boolean isDoorOpen;

    public Elevator (int numberOfFloors){
        this.numberOfFloors = numberOfFloors;
    }
    public boolean isDoorOpen(){
        return isDoorOpen;
    }
    public void openDoor () {
        this.isDoorOpen = true;
    }
    public void closeDoor(){
        this.isDoorOpen = false;
    }
    public void goUp(int desiredFloor) {
        if (!isDoorOpen && desiredFloor > currentFloor && desiredFloor <= numberOfFloors) {
            currentFloor = desiredFloor;
        }
    }
    public void goDown(int desiredFloor) {
        if (!isDoorOpen && desiredFloor < currentFloor && desiredFloor >= 1) {
            currentFloor = desiredFloor;
        }
    }
    public int getCurrentFloor() {
        return currentFloor;
    }
    public int getNumberOfFloors() {
        return numberOfFloors;
    }
    public boolean getIsDoorOpen(){
        return isDoorOpen;
    }

}

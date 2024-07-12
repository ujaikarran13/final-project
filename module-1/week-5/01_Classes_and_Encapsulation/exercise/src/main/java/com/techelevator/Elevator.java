package com.techelevator;

public class Elevator {
    private int currentFloor;
    private int numberOfFloors;
    private boolean isDoorOpen;

    public Elevator (int numberOfFloors){
        this.numberOfFloors = numberOfFloors;
        this.currentFloor = 1;
        this.isDoorOpen = false;
    }
    public boolean isDoorOpen(){
        this.isDoorOpen = false;
        return false;
    }
    public void DoorOpen(){
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

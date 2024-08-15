package com.techelevator;

public class HotelReservation {
    /*
    Fill in the class details here...
     */
    private String name;
    private int numberOfNights;
    private int nightlyRate;
    private int estimatedTotal;


    public HotelReservation(String name, int numberOfNights, int nightlyRate) {
        this.name = name;
        this.numberOfNights = numberOfNights;
        this.nightlyRate = nightlyRate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
    }

    public int getNightlyRate() {
        return nightlyRate;
    }

    public void setNightlyRate(int nightlyRate) {
        this.nightlyRate = nightlyRate;
    }

    public int getEstimatedTotal() {
        estimatedTotal = numberOfNights * nightlyRate;
        return estimatedTotal;
    }

    public boolean getActualTotal(boolean requiresCleaning, boolean usedMinibar) {
        int cleaningFee = 25;
        int usedMiniBarFee = 15;
        estimatedTotal = numberOfNights * nightlyRate;

        if (requiresCleaning == true) {
            estimatedTotal += cleaningFee;
        } else if (usedMinibar == true) {
            estimatedTotal += usedMiniBarFee + (cleaningFee * 2);
        }

    return false;
}
public String toString() {
        return "Hotel:" + "{" + name + "}:{" + estimatedTotal + "}";
}

}



///If a user calls toString() on an object of type HotelReservation,
// it must return a String like {name}:{estimated total} where {name} and {estimated total}
// are placeholders for the actual object values. Don't include any extra spaces or a $.




///Start with the value of estimatedTotal.
//If requiresCleaningis true, add a cleaning fee of $25.
//If usedMinibar is true, add a mini-bar fee of $15, and double the cleaning fee.
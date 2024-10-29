package com.techelevator.model;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

public class PhysiciansOffice {
    
    private int physicianId;
    private String address;
    private String phoneNumber;
    private int fromHour;
    private int toHour;

    public PhysiciansOffice(){}
    public PhysiciansOffice(int physicianId, String address, String phoneNumber, int fromHour, int toHour){
        this.physicianId = physicianId;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.fromHour = fromHour;
        this.toHour = toHour;
    }
    public int getPhysicianId() {
        return physicianId;
    }

    public void setPhysicianId(int physicianId) {
        this.physicianId = physicianId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public int getFromHour() {
        return fromHour;
    }

    public void setFromHour(int fromHour) {
        this.fromHour = fromHour;
    }

    public int getToHour() {
        return toHour;
    }

    public void setToHour(int toHour) {
        this.toHour = toHour;
    }

}












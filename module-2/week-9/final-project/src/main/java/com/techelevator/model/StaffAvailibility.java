package com.techelevator.model;

import java.time.LocalDate;

public class StaffAvailibility {


    private int physicianId;
    private LocalDate dateAvailable;
    private LocalDate dateUnavailable;


    public StaffAvailibility(){}
    public StaffAvailibility (int physicianId, String dateAvailable, String dateUnavailable){
        this.physicianId = physicianId;
        this.dateAvailable = LocalDate.parse(dateAvailable);
        this.dateUnavailable = LocalDate.parse(dateUnavailable);
    }
    public int getPhysicianId() {
        return physicianId;
    }

    public void setPhysicianId(int physicianId) {
        this.physicianId = physicianId;
    }

    public LocalDate getdateAvailable() {
        return dateAvailable;
    }

    public void setDateAvailable(String dateAvailable) {
        this.dateAvailable = LocalDate.parse(dateAvailable);
    }
        public LocalDate getDateUnavailable() {
            return dateUnavailable;
        }

        public void setDateUnavailable(String dateUnavailable) {
            this.dateAvailable = LocalDate.parse(dateUnavailable);
    }
    
}



























}

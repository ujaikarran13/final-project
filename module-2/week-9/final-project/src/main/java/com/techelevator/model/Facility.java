package com.techelevator.model;

public class Facility {

        private int facilityId;
        private String address;
        private String phoneNumber;
        private String officeHours;
        private int costPerHour;

        public Facility(int facilityId, String address, String phoneNumber, String officeHours, int costPerHour) {
            this.facilityId = facilityId;
            this.address = address;
            this.phoneNumber = phoneNumber;
            this.officeHours = officeHours;
            this.costPerHour = costPerHour;
        }

    public Facility() {

    }

    public int getFacilityId() {
            return facilityId;
        }

        public void setFacilityId(int facilityId) {
            this.facilityId = facilityId;
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
            this.phoneNumber = this.phoneNumber;
        }

        public String getOfficeHours() {
            return officeHours;
        }

        public void setOfficeHours(String officeHours) {
            this.officeHours = officeHours;
        }

        public int getCostPerHour() {
            return costPerHour;
        }

        public void setCostPerHour(int costPerHour) {
            this.costPerHour = costPerHour;
        }
    }











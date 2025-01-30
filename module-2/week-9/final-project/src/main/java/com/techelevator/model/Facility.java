package com.techelevator.model;

public class Facility {

        private int facilityId;
        private String facilityName;
        private String address;
        private String phoneNumber;
        private int costPerHour;

        public Facility(int facilityId, String facilityName, String address, String phoneNumber, int costPerHour) {
            this.facilityId = facilityId;
            this.facilityName = facilityName;
            this.address = address;
            this.phoneNumber = phoneNumber;
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
    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
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

        public int getCostPerHour() {
            return costPerHour;
        }

        public void setCostPerHour(int costPerHour) {
            this.costPerHour = costPerHour;
        }
    }











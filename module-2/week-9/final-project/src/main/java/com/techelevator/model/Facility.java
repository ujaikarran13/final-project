package com.techelevator.model;

public class Facility {

        private Long facilityId;
        private String address;
        private String phoneNumber;
        private String officeHours;
        private Double costPerHour;

        public Facility(Long facilityId, String address, String phoneNumber, String officeHours, Double costPerHour) {
            this.facilityId = facilityId;
            this.address = address;
            this.phoneNumber = phoneNumber;
            this.officeHours = officeHours;
            this.costPerHour = costPerHour;
        }

        public Long getFacilityId() {
            return facilityId;
        }

        public void setFacilityId(Long facilityId) {
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
            this.phoneNumber = phoneNumber;
        }

        public String getOfficeHours() {
            return officeHours;
        }

        public void setOfficeHours(String officeHours) {
            this.officeHours = officeHours;
        }

        public Double getCostPerHour() {
            return costPerHour;
        }

        public void setCostPerHour(Double costPerHour) {
            this.costPerHour = costPerHour;
        }
    }











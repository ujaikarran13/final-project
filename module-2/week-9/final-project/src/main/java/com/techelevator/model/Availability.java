package com.techelevator.model;

public class Availability {

        private int availabilityId;
        private int doctorId;
        private String dayOfWeek;
        private String startTime;
        private String endTime;

        public Availability(int availabilityId, int doctorId, String dayOfWeek, String startTime, String endTime) {
            this.availabilityId = availabilityId;
            this.doctorId = doctorId;
            this.dayOfWeek = dayOfWeek;
            this.startTime = startTime;
            this.endTime = endTime;
        }

    public Availability() {

    }

    public int getAvailabilityId() {
            return availabilityId;
        }

        public void setAvailabilityId(int availabilityId) {
            this.availabilityId = availabilityId;
        }

        public int getDoctorId() {
            return doctorId;
        }

        public void setDoctorId(int doctorId) {
            this.doctorId = doctorId;
        }

        public String getDayOfWeek() {
            return dayOfWeek;
        }

        public void setDayOfWeek(String dayOfWeek) {
            this.dayOfWeek = dayOfWeek;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }
    }





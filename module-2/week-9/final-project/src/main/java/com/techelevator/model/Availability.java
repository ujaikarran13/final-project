package com.techelevator.model;

import java.time.LocalTime;

public class Availability {

        private int availabilityId;
        private int doctorId;
        private String dayOfWeek;
        private LocalTime startTime;
        private LocalTime endTime;

        public Availability(int availabilityId, int doctorId, String dayOfWeek, String startTime, String endTime) {
            this.availabilityId = availabilityId;
            this.doctorId = doctorId;
            this.dayOfWeek = dayOfWeek;
            this.startTime = LocalTime.parse(startTime);
            this.endTime = LocalTime.parse(endTime);
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

        public LocalTime getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = LocalTime.parse(startTime);
        }

        public LocalTime getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = LocalTime.parse(endTime);
        }
    }





package com.techelevator.model;

public class DoctorFacility {

        private int doctorId;
        private int facilityId;

        public DoctorFacility(int doctorId, int facilityId) {
            this.doctorId = doctorId;
            this.facilityId = facilityId;
        }

    public DoctorFacility() {

    }

    public int getDoctorId() {
            return doctorId;
        }

        public void setDoctorId(int doctorId) {
            this.doctorId = doctorId;
        }

        public int getFacilityId() {
            return facilityId;
        }

        public void setFacilityId(int facilityId) {
            this.facilityId = facilityId;
        }
    }




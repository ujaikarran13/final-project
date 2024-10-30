package com.techelevator.model;

public class DoctorFacility {

        private Long doctorId;
        private Long facilityId;

        public DoctorFacility(Long doctorId, Long facilityId) {
            this.doctorId = doctorId;
            this.facilityId = facilityId;
        }

        public Long getDoctorId() {
            return doctorId;
        }

        public void setDoctorId(Long doctorId) {
            this.doctorId = doctorId;
        }

        public Long getFacilityId() {
            return facilityId;
        }

        public void setFacilityId(Long facilityId) {
            this.facilityId = facilityId;
        }
    }




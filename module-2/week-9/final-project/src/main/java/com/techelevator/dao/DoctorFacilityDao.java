package com.techelevator.dao;

import com.techelevator.model.DoctorFacility;

import java.util.List;

public interface DoctorFacilityDao {



    List<DoctorFacility> getDoctorsAndFacilities(int DoctorID, int FacilityID);
}

package com.techelevator.dao;

import com.techelevator.model.Facility;

import java.util.List;

public interface  FacilityDao {

    List<Facility> getFacilities();
    Facility getFacilitiesByID(int facilityId);

    Facility updateFacility(Facility facility);

    Facility addFacilities(Facility facility);


    List<Facility> getPhoneNumberForFacilityId(int facilityId);

    List<String> getAllNumbers();

    Facility searchFacilities(String name);

    int deleteFacilities(int facilityId);
}

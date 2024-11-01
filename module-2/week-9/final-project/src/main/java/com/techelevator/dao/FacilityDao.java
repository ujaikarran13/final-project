package com.techelevator.dao;

import com.techelevator.model.Facility;

import java.sql.SQLException;
import java.util.List;

public interface  FacilityDao {


    Facility getFacilitiesByID(int facilityId);

    Facility updateFacility(Facility facility);



}

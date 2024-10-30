package com.techelevator.dao;

import com.techelevator.model.Facility;

import java.sql.SQLException;
import java.util.List;

public interface  FacilityDao {

/**
 * Get a facility from the datastore with the specified facility id.
 * If the id is not found, return null.
 *
 * @param facilityId The id of the bookmark to return.
 * @return The matching Facility object, or null if the FacilityId is not found.
 */

Facility getFacilityById(int facilityId);

/**
 * Get all facility from the datastore ordered alphabetically by address.
 *
 * @return List of all facility objects, or an empty list if no Tags are found.
 */
List<Facility> getFacilities();



List<Facility> getFacilitiesByAddress();


List<Facility> getFacilitiesByOfficeHours();



Facility updateFacility(Facility updatedFacility);



}

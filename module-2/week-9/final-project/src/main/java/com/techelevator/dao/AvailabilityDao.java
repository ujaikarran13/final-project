package com.techelevator.dao;

import com.techelevator.model.Availability;
import com.techelevator.model.Facility;

import java.util.List;

public interface AvailabilityDao {

    List<Availability> getAvailability();

    Availability getAvailabilityByID(int availabilityId);

    Availability updateAvailability(Availability modifiedAvailability);
}
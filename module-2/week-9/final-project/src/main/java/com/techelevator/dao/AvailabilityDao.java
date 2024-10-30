package com.techelevator.dao;

import com.techelevator.model.Availability;

public interface AvailabilityDao {

    Availability getAvailabilityById (int doctorId);

}

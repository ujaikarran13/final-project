package com.techelevator.dao;

import com.techelevator.model.Availability;

import java.util.List;

public interface AvailabilityDao {


    List<Availability> getDoctorAvailabilityByDayOfWeek (String dayOfWeek);

    List<Availability> getAvailabilities();
}
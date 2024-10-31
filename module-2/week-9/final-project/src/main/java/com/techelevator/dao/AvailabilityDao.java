package com.techelevator.dao;

import com.techelevator.model.Availability;

public interface AvailabilityDao {



    static Availability getDoctorAvailabilityById(int doctorId);

    Availability getDoctorAvailabilityByDayOfWeek(String dayOfWeek);

    Availability updateDoctorAvailability(Availability updatedAvailability);
}

package com.techelevator.service;

import com.techelevator.dao.AvailabilityDao;
import com.techelevator.dao.FacilityDao;
import com.techelevator.dao.UserDao;
import org.springframework.stereotype.Component;

@Component
public class FacilityService {

    private FacilityDao facilityDao;
    private AvailabilityDao availabilityDao;
    private UserDao userDao;

    public FacilityService(UserDao userDao, FacilityDao facilityDao, AvailabilityDao availabilityDao){
        this.availabilityDao = availabilityDao;
        this.userDao = userDao;
        this.facilityDao = facilityDao;
    }
    public int getFacilitiesByID(int facilityId){
        return facilityId;
    }
}

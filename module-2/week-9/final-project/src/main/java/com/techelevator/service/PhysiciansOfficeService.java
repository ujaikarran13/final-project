package com.techelevator.service;

import com.techelevator.dao.AvailabilityDao;
import com.techelevator.dao.DoctorFacilityDao;
import com.techelevator.dao.FacilityDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Availability;
import com.techelevator.model.Facility;
import com.techelevator.model.User;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.security.Principal;
import java.util.List;

public class PhysiciansOfficeService {

    private static FacilityDao facilityDao;
    private UserDao userDao;
    private AvailabilityDao availabilityDao;
    private DoctorFacilityDao doctorFacilityDao;

    public PhysiciansOfficeService(UserDao userDao, FacilityDao facilityDao, AvailabilityDao availabilityDao, DoctorFacilityDao doctorFacilityDao){
        this.userDao = userDao;
        this.facilityDao = facilityDao;
        this.availabilityDao = availabilityDao;
        this.doctorFacilityDao = doctorFacilityDao;
    }

    public static Facility updateOffice(Facility modifiedFacility, Principal principal) {

        return modifiedFacility;
    }

    public static List<Availability> viewAvailability() {
        return null;
    }

    public static Availability updateAvailability(Availability modifiedAvailability, Principal principal) {
        return modifiedAvailability;
    }

}

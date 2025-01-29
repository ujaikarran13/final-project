package com.techelevator.controller;


import com.techelevator.dao.FacilityDao;
import com.techelevator.dao.JdbcFacilityDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Availability;
import com.techelevator.model.Facility;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * The FacilityController is a class for handling HTTP Requests related to creating, reading,
 * updating, and deleting Facilities.
 *
 * It depends on an instance of PhysiciansOfficeService for interacting with DAOs and handling business logic.
 * This is provided through dependency injection.
 */

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")

public class FacilityController {

    private FacilityDao facilityDao;

    public FacilityController(FacilityDao facilityDao) {
        this.facilityDao = facilityDao;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @RequestMapping(path = "/facilities", method = RequestMethod.GET)
    public List<Facility> getFacilities() {
        try {
            List<Facility> facilities = facilityDao.getFacilities();
            if (facilities.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No facilities found");
            }
            return facilities;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @RequestMapping(path = "/facilities/{facilityId}", method = RequestMethod.GET)
    public Facility getFacilitiesByID(@PathVariable int facilityId) {
        try {
            Facility facility = facilityDao.getFacilitiesByID(facilityId);
            if (facility == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Facility not found");
            }
            return facility;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @RequestMapping(path= "/facilities", method = RequestMethod.GET)
    public List<Facility> searchFacilities(@RequestParam String name) {
        try {
            List<Facility> facilities = (List<Facility>) facilityDao.searchFacilities(name);
            if (facilities.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No facilities found with the name: " + name);
            }
            return facilities;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path ="/facilities", method = RequestMethod.POST)
    public Facility addFacilities(@RequestBody Facility facility) {
        try {
            Facility addedFacility = facilityDao.addFacilities(facility);
            if (addedFacility == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to add the facility");
            }
            return addedFacility;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path= "/facilities/{facilityId}", method = RequestMethod.DELETE)
    public boolean deleteFacilities(@PathVariable int facilityId) {
        try {
            boolean isDeleted = facilityDao.deleteFacilities(facilityId);
            if (!isDeleted) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Facility not found or unable to delete");
            }
            return isDeleted;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @RequestMapping(path = "/facilities/{facilityId}/phoneNumber", method = RequestMethod.GET)
    public String getPhoneNumberForFacility(@PathVariable int facilityId) {
        try {
            String phoneNumber = facilityDao.getPhoneNumberForFacility(facilityId);
            if (phoneNumber == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Phone number not found for facility ID: " + facilityId);
            }
            return phoneNumber;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @RequestMapping(path = "/facilities/phoneNumber", method = RequestMethod.GET)
    public List<String> getAllNumbers() {
        try {
            List<String> phoneNumbers = facilityDao.getAllNumbers();
            if (phoneNumbers.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No phone numbers found");
            }
            return phoneNumbers;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/facilities/{facilityId}", method = RequestMethod.PUT)
    public Facility updateFacility(@PathVariable int facilityId, @Valid @RequestBody Facility modifiedFacility) {

        try {
            modifiedFacility.setFacilityId(facilityId);
            Facility facility = facilityDao.updateFacility(modifiedFacility);
            if (facility == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Facility not found");
            }
            return facility;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}













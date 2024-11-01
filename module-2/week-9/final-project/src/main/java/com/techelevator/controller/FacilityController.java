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
    @RequestMapping(path = "/facilities/{facilityId}", method = RequestMethod.GET)
    public Facility getFacilitiesByID(@PathVariable int facilityId) {
        Facility facility = null;

        try {
            facility = facilityDao.getFacilitiesByID(facilityId);
            if (facility == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Office not found");
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return facilityDao.getFacilitiesByID(facilityId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/facilities/{facilityId}", method = RequestMethod.PUT)
    public Facility updateFacility(@PathVariable int facilityId, @Valid @RequestBody Facility modifiedFacility) {
        Facility facility = null;

        try {
            modifiedFacility.setFacilityId(facilityId);
            facility = facilityDao.updateFacility(modifiedFacility);

            if (facility == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Facility not found");
            }
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return facility;
    }
}











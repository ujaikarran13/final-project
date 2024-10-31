package com.techelevator.controller;


import com.techelevator.dao.FacilityDao;
import com.techelevator.dao.JdbcFacilityDao;
import com.techelevator.exception.DaoException;
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

    public FacilityController(FacilityDao facilityDao){
        this.facilityDao = new JdbcFacilityDao();
    }


@RequestMapping ( path= "/getOfficeInfo", method = RequestMethod.GET)
    public Facility getFacilitiesByID(@PathVariable int Id){
        Facility facility = null;

        try{
            facility = facilityDao.getFacilitiesByID(Id);
            if(facility == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Office not found");
            }
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return facilityDao.getFacilitiesByID(Id);
}
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/createFacility", method = RequestMethod.POST)
    public Facility create(@Valid @RequestBody Facility facility) {
        Facility createFacility;

        try {
            createFacility = facilityDao.createFacility(facility);
        } catch (DaoException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return createFacility;
    }
    @RequestMapping(path = "/updateofficeInfo", method = RequestMethod.PUT)
    public Facility update(@PathVariable int id, @RequestBody Facility facility) {
        // id on the path takes precedence over the id in the request body, if any
        facility.setFacilityId(id);
        try {
            Facility updatedFacility = facilityDao.updateFacility(facility);
            return updatedFacility;
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID isn't valid");
        }
    }
}









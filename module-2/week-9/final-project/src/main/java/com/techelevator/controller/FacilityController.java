package com.techelevator.controller;


import com.techelevator.exception.DaoException;
import com.techelevator.model.Facility;
import com.techelevator.service.PhysiciansOfficeService;
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
@RequestMapping( path = "/facilities" )
public class FacilityController {

    private PhysiciansOfficeService physiciansOfficeService;

    public FacilityController(PhysiciansOfficeService physiciansOfficeService){
        this.physiciansOfficeService = physiciansOfficeService;
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping( path = "/facilities", method = RequestMethod.PUT )
    public Facility updateOffice(@PathVariable int facilityId, @Valid @RequestBody Facility modifiedFacility, Principal principal) {
        Facility facility = null;

        try {
            // Make sure the facility id is set
            modifiedFacility.setFacilityId(facilityId);
            facility = PhysiciansOfficeService.updateOffice(modifiedFacility, principal);
            if (facility == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Facility not found");
            }
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return facility;

    }
}


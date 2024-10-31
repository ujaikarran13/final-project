package com.techelevator.controller;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Availability;
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

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
@RequestMapping( path = "/availability" )
public class AvailabilityController {

private PhysiciansOfficeService physiciansOfficeService;

public AvailabilityController(PhysiciansOfficeService physiciansOfficeService){
    this.physiciansOfficeService = physiciansOfficeService;
}

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping( path = "/availability", method = RequestMethod.GET )
    public List<Availability> viewAvailability() {
        List<Availability> availabilities = new ArrayList<>();

        try {
            availabilities = PhysiciansOfficeService.viewAvailability();
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return availabilities;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping( path = "/availability", method = RequestMethod.PUT )
    public Availability updateAvailability(@PathVariable int DoctorID, @Valid @RequestBody Availability modifiedAvailability, Principal principal) {
        Availability availability = null;

        try {
            // Make sure the doctor id is set
            modifiedAvailability.setDoctorId(DoctorID);
            availability = PhysiciansOfficeService.updateAvailability(modifiedAvailability, principal);
            if (availability == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Facility not found");
            }
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return availability;

    }

}

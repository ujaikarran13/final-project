package com.techelevator.controller;

import com.techelevator.dao.AvailabilityDao;
import com.techelevator.dao.JdbcAvailabilityDao;
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

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")

public class AvailabilityController {
    private AvailabilityDao availabilityDao;

    public AvailabilityController(AvailabilityDao availabilityDao) {
        this.availabilityDao = availabilityDao;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(path = "/availability", method = RequestMethod.GET)
    public List<Availability> getAvailability(Principal principal){
        List<Availability> availabilities = new ArrayList<>();
        try {
            availabilities = availabilityDao.getAvailability();
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return availabilities;
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(path = "/availability/{availabilityId}", method = RequestMethod.GET)
    public Availability getAvailabilityByID(@PathVariable int availabilityId) {
        Availability availability = null;

        try {
            availability = availabilityDao.getAvailabilityByID(availabilityId);
            if (availability == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Availability not found");
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return availabilityDao.getAvailabilityByID(availabilityId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping( path = "/availability/{availabilityId}", method = RequestMethod.PUT )
    public Availability updateAvailability(@PathVariable int availabilityId, @Valid @RequestBody Availability modifiedAvailability) {
        Availability availability = null;

        try {
            modifiedAvailability.setAvailabilityId(availabilityId);
            availability = availabilityDao.updateAvailability(modifiedAvailability);
            if (availability == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Availability not found");
            }
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return availability;
    }
}








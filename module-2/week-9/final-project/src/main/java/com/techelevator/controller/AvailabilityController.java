package com.techelevator.controller;

import com.techelevator.dao.AvailabilityDao;
import com.techelevator.dao.JdbcAvailabilityDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Availability;
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
        this.availabilityDao = new JdbcAvailabilityDao();
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(path = "/viewavailability", method = RequestMethod.GET)
    public List<Availability> getDoctorAvailabilityByDayOfWeek(@RequestParam(defaultValue = "") String weekday_like){
        List<Availability> availabilities = new ArrayList<>();
        try {
            availabilities = availabilityDao.getDoctorAvailabilityByDayOfWeek(weekday_like);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return availabilityDao.getDoctorAvailabilityByDayOfWeek(weekday_like);
    }
}





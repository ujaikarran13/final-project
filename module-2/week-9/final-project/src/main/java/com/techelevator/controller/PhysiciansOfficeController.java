package com.techelevator.controller;

import com.techelevator.exception.DaoException;
import com.techelevator.model.PhysiciansOffice;
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
@RequestMapping( path = "/physiciansoffice" )
public class PhysiciansOfficeController {








}

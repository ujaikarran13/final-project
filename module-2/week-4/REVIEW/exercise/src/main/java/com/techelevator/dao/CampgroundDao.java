package com.techelevator.dao;

import com.techelevator.model.Campground;

import java.util.List;

public interface CampgroundDao {

    Campground getCampgroundById(int campgroundId);

    List<Campground> getCampgroundsByParkId(int parkId);
}

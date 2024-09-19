package com.techelevator.dao;

import com.techelevator.model.Site;

import java.util.List;

public interface SiteDao {

    Site getSiteById(int siteId);

    List<Site> getSitesByCampgroundId(int campgroundId);

    List<Site> getSitesThatAllowRVsByParkId(int parkId);

    List<Site> getAvailableSitesByParkId(int parkId);
}

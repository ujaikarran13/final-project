package com.techelevator.locations.services;

import com.techelevator.locations.model.Location;
import com.techelevator.util.BasicLogger;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

public class LocationService {

    private static final String API_BASE_URL = "http://localhost:8080/locations/";
    private final RestTemplate restTemplate = new RestTemplate();

    private String authToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiYXV0aCI6IlJPTEVfVVNFUiIsImV4cCI6MTcyOTMwNTIwN30.0HPeivTkdyKvTteZZotJIqIY6V_j7wn40F6C4O1Fn4f3sBOU40kpBuLSfg2XRbTu0C96cJnzxsD8IsXjQRYyfQ";

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public Location[] getAll() {
        Location[] locations = null;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(authToken);

            HttpEntity<Void> entity = new HttpEntity<>(headers);
            ResponseEntity<Location[]> response =
                    restTemplate.exchange(API_BASE_URL, HttpMethod.GET, makeAuthEntity(), Location[].class);
            locations = response.getBody();

        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return locations;
    }

    public Location getOne(int id) {
        Location location = null;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(authToken);
            HttpEntity<Void> entity = new HttpEntity<>(headers);
            ResponseEntity<Location> response = restTemplate.exchange(API_BASE_URL + id,
                    HttpMethod.GET, makeAuthEntity(), Location.class);
            location = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return location;
    }

    public Location add(Location newLocation) {
        HttpEntity<Location> entity = makeLocationEntity(newLocation);

        Location returnedLocation = null;
        try {
            returnedLocation = restTemplate.postForObject(API_BASE_URL, entity, Location.class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return returnedLocation;
    }

    public boolean update(Location updatedLocation) {
        HttpEntity<Location> entity = makeLocationEntity(updatedLocation);

        boolean success = false;
        try {
            restTemplate.put(API_BASE_URL + updatedLocation.getId(), entity);
            success = true;
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return success;
    }

    public boolean delete(int id) {
        boolean success = false;
        try {
            restTemplate.exchange(API_BASE_URL + id, HttpMethod.DELETE,
                    makeAuthEntity(), Void.class);
            success = true;
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return success;
    }

    private HttpEntity<Location> makeLocationEntity(Location location) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(location, headers);
    }
    private HttpEntity<Void> makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(headers);
    }
}

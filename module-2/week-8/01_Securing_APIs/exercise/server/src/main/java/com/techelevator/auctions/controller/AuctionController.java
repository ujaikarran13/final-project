package com.techelevator.auctions.controller;

import com.techelevator.auctions.dao.AuctionDao;
import com.techelevator.auctions.dao.MemoryAuctionDao;
import com.techelevator.auctions.exception.DaoException;
import com.techelevator.auctions.model.Auction;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/auctions")
@PreAuthorize("isAuthenticated()")
public class AuctionController {
    
    private AuctionDao auctionDao;

    public AuctionController() {
        this.auctionDao = new MemoryAuctionDao();
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Auction> list(@RequestParam(defaultValue = "") String title_like, @RequestParam(defaultValue = "0") double currentBid_lte) {

        if (!title_like.equals("")) {
            return auctionDao.getAuctionsByTitle(title_like);
        }
        if (currentBid_lte > 0) {
            return auctionDao.getAuctionsByMaxBid(currentBid_lte);
        }

        return auctionDao.getAuctions();
    }
    @PostMapping
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Auction get(@RequestBody int id) {
        Auction auction = auctionDao.getAuctionById(id);
        if (auction == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Auction Not Found");
        } else {
            return auction;
        }
    }

    @PreAuthorize("hasRole('CREATOR') or hasRole('ADMIN')")
    @PostMapping
    public Auction create(@Valid @RequestBody Auction auction) {

        return auctionDao.createAuction(auction);
    }

    @PreAuthorize("hasRole('CREATOR') or hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Auction update(@Valid @RequestBody Auction auction, @PathVariable int id) {
        // The id on the path takes precedence over the id in the request body, if any

        auction.setId(id);
        try {
            Auction updatedAuction = auctionDao.updateAuction(auction);
            return updatedAuction;
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Auction Not Found");
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        auctionDao.deleteAuctionById(id);
    }
    @PreAuthorize("hasRole('CREATOR') or hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/whoami")
    public ResponseEntity<Auction> whoAmI(@Valid @RequestBody Auction auction, Principal principal) {
        Auction[] usernamers = null;
        try {
            ResponseEntity<Auction[]> response =
                    restTemplate.exchange(API_BASE_URL, HttpMethod.GET, makeAuthEntity(), Auction[].class);
            auctions = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return auctions;

    }
}

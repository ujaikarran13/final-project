package com.techelevator.auctions.controller;

import com.techelevator.auctions.dao.AuctionDao;
import com.techelevator.auctions.dao.MemoryAuctionDao;
import com.techelevator.auctions.model.Auction;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yaml.snakeyaml.nodes.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

import static org.apache.coyote.http11.Constants.a;

@RestController
@RequestMapping(path = "/auctions")
public class AuctionController {

    private AuctionDao auctionDao;

    public AuctionController() {
        this.auctionDao = new MemoryAuctionDao();
    }
    @RequestMapping( method = RequestMethod.GET)
    public List<Auction> getAllAuctions(){
        List<Auction> auctions = new ArrayList<>();

        try {
            auctions = auctionDao.getAuctions();
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return auctions;
    }
    @RequestMapping( path = "/{Id}", method = RequestMethod.GET)
    public Auction getAuctionById(@PathVariable int Id) {
        Auction auction = null;

        try {
            auction = auctionDao.getAuctionById(Id);
            if (auction == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Auction not found");
            }
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return auction;
    }
    @RequestMapping( method = RequestMethod.POST)
    public Auction addAuction(@RequestBody Auction newAuction) {
        Auction auction = null;

        try {
            auction = auctionDao.createAuction(newAuction);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return auction;
    }
    @RequestMapping( path = "/title", method = RequestMethod.GET)
    public List<Auction> getAuctionsByTitle(@RequestParam(defaultValue = "") String title_like){
        List<Auction> auctions = new ArrayList<>();

        try {
            auctions = auctionDao.getAuctionsByTitle(title_like);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return auctions;
    }
}

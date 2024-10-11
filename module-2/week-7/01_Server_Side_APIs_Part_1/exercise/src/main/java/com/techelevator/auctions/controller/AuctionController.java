package com.techelevator.auctions.controller;

import com.techelevator.auctions.dao.AuctionDao;
import com.techelevator.auctions.dao.MemoryAuctionDao;
import com.techelevator.auctions.model.Auction;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
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
    public List<Auction> getAuctions(){
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
    @RequestMapping( method = RequestMethod.GET)
    public List<Auction> getAuctionsByTitle(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "") String title_like,
            @RequestParam(defaultValue = "0") double currentBid_lte) {

        List<Auction> auctions;

        try {
            if (!title_like.isEmpty() && currentBid_lte > 0) {

                auctions = auctionDao.getAuctionsByTitleAndMaxBid(title_like, currentBid_lte);
            } else if (currentBid_lte > 0) {

                auctions = auctionDao.getAuctionsByMaxBid(currentBid_lte);
            } else if (!title_like.isEmpty()) {

                auctions = auctionDao.getAuctionsByTitle(title_like);
            } else {

                auctions = auctionDao.getAuctions();
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return auctions;
    }
}



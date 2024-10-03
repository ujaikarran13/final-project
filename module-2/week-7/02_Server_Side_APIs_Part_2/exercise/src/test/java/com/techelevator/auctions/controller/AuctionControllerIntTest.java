package com.techelevator.auctions.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.auctions.model.Auction;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrint;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Fail.fail;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@WebMvcTest(AuctionController.class)
@AutoConfigureMockMvc(print= MockMvcPrint.NONE)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuctionControllerIntTest {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    MockMvc mvc;

    @Test
    public void create_ValidAuction_ShouldAddNewAuction() throws Exception {
        final Auction auction = new Auction(
                "Standing Desk",
                "Stand up desk to help you stretch your legs during the day.",
                "Johnnie34",
                350.00);

        MvcResult mvcResult = mvc.perform(post("/auctions").contentType(MediaType.APPLICATION_JSON).content(toJson(auction))).andReturn();

        verifyRouteExists(mvcResult);
        int status = mvcResult.getResponse().getStatus();
        assertEquals("Expected CREATED status", 201, status);

        Auction returnedAuction = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<Auction>() {});
        assertNotNull("Expected an auction to be returned but received null", returnedAuction);
        assertTrue("Expected id to not equal 0", returnedAuction.getId() != 0);
        assertEquals("Expected title did not match returned title", auction.getTitle(), returnedAuction.getTitle());
        assertEquals("Expected description did not match returned description", auction.getDescription(), returnedAuction.getDescription());
        assertEquals("Expected user did not match returned user", auction.getUser(), returnedAuction.getUser());
        assertEquals("Expected currentBid did not match returned currentBid", auction.getCurrentBid(), returnedAuction.getCurrentBid(), 0.001);
    }

    @Test
    public void create_InvalidAuction_ShouldNotBeCreated() throws Exception {
        final Auction auction = new Auction(
                "",
                "",
                "",
                0);

        MvcResult mvcResult = mvc.perform(post("/auctions").contentType(MediaType.APPLICATION_JSON).content(toJson(auction))).andReturn();

        verifyRouteExists(mvcResult);
        assertEquals("Expected Bad Request status 400 when POST request contains invalid auction.", 400, mvcResult.getResponse().getStatus());
    }

    @Test
    public void update_ValidAuction_ShouldUpdateExistingAuction() throws Exception {
        Auction auction = new Auction(1,
                "Bell Computer Monitor",
                "4K LCD monitor from Bell Computers, HDMI & DisplayPort",
                "Queenie34",
                100.39);

        auction.setTitle("MY_NEW_TITLE");

        MvcResult mvcResult = mvc.perform(put("/auctions/" + auction.getId()).contentType(MediaType.APPLICATION_JSON).content(toJson(auction))).andReturn();

        verifyRouteExists(mvcResult);
        int status = mvcResult.getResponse().getStatus();
        assertEquals("Expected OK status", 200, status);

        Auction returnedAuction = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<Auction>() {});
        assertNotNull("Expected an auction to be returned but received null", returnedAuction);
        assertEquals("Expected id did not match returned id", auction.getId(), returnedAuction.getId());
        assertEquals("Expected title did not match returned title", auction.getTitle(), returnedAuction.getTitle());
        assertEquals("Expected description did not match returned description", auction.getDescription(), returnedAuction.getDescription());
        assertEquals("Expected user did not match returned user", auction.getUser(), returnedAuction.getUser());
        assertEquals("Expected currentBid did not match returned currentBid", auction.getCurrentBid(), returnedAuction.getCurrentBid(), 0.001);
    }

    @Test
    public void update_InvalidAuctionShouldNotBeUpdated() throws Exception {
        Auction auction = new Auction(1,
                "Bell Computer Monitor",
                "4K LCD monitor from Bell Computers, HDMI & DisplayPort",
                "Queenie34",
                100.39);
        auction.setTitle("");

        MvcResult mvcResult = mvc.perform(put("/auctions/" + auction.getId()).contentType(MediaType.APPLICATION_JSON).content(toJson(auction))).andReturn();

        verifyRouteExists(mvcResult);
        assertEquals("Expected Bad Request status 400 when PUT request contains invalid auction.", 400, mvcResult.getResponse().getStatus());
    }

    @Test
    public void update_InvalidAuctionId_ShouldReturnNotFound() throws Exception {
        Auction auction = new Auction(1,
                "Bell Computer Monitor",
                "4K LCD monitor from Bell Computers, HDMI & DisplayPort",
                "Queenie34",
                100.39);

        MvcResult mvcResult = mvc.perform(put("/auctions/99").contentType(MediaType.APPLICATION_JSON).content(toJson(auction))).andReturn();
        // Can't check RequestMappings using verifyRouteExists() because this expects a 404
        assertEquals("Expected NOT FOUND status 404 when PUT request has invalid auction id.", 404, mvcResult.getResponse().getStatus());
    }

    @Test
    public void delete_ShouldReturnNoContent() throws Exception {

        MvcResult mvcResult = mvc.perform(delete("/auctions/5")).andReturn();

        verifyRouteExists(mvcResult);
        assertEquals("Expected NO CONTENT status", 204, mvcResult.getResponse().getStatus());

        MvcResult afterResult = mvc.perform(get("/auctions/5")).andReturn();
        assertEquals("Expected auction to be deleted but can still be retrieved", 404, afterResult.getResponse().getStatus());
    }

    private String toJson(Auction auction) throws JsonProcessingException {
        return mapper.writeValueAsString(auction);
    }

    private int verifyRouteExists(MvcResult mvcResult) {
        int status = mvcResult.getResponse().getStatus();
        if (status == 404 || status == 405) {
            fail(mvcResult.getRequest().getMethod()+ " route " + mvcResult.getRequest().getPathInfo() + " was not found. " +
                    "Response status: " + status + ". Verify RequestMappings are correct.");
        }
        return status;
    }
}

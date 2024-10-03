package com.techelevator.auctions.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.auctions.dao.AuctionDao;
import com.techelevator.auctions.dao.MemoryAuctionDao;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(AuctionController.class)
@AutoConfigureMockMvc(print= MockMvcPrint.NONE)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuctionControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    private AuctionDao dao;

    public AuctionControllerTest() {
        dao = new MemoryAuctionDao();
    }

    @Test
    public void listShouldReturnCorrectCount() throws Exception {
        int count = dao.getAuctions().size();
        MvcResult mvcResult = mvc.perform(get("/auctions")).andReturn();

        verifyRouteExists(mvcResult);
        assertEquals("Expected successful status", 200, mvcResult.getResponse().getStatus());
        List<Auction> searchResults = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Auction>>() {});
        assertEquals("Expected result size to be " + count, count, searchResults.size());
    }

    @Test
    public void getShouldReturnSingleAuction() throws Exception {
        List<Auction> auctions = dao.getAuctions();
        Auction first = auctions.get(0);

        MvcResult mvcResult = mvc.perform(get("/auctions/" + first.getId())).andReturn();

        verifyRouteExists(mvcResult);
        assertEquals("Expected successful status", 200, mvcResult.getResponse().getStatus());

        Auction returnedAuction = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<Auction>() {});
        assertNotNull("Expected an auction to be returned but was null", returnedAuction);
        assertEquals("Expected id did not match returned id", first.getId(), returnedAuction.getId());
        assertEquals("Expected title did not match returned title", first.getTitle(), returnedAuction.getTitle());
        assertEquals("Expected description did not match returned description", first.getDescription(), returnedAuction.getDescription());
        assertEquals("Expected user did not match returned user", first.getUser(), returnedAuction.getUser());
        assertEquals("Expected currentBid did not match returned currentBid", first.currentBidToString(), returnedAuction.currentBidToString());
    }

    @Test
    public void getInvalidIdShouldReturnNothing() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/auctions/99")).andReturn();

        verifyRouteExists(mvcResult);
        assertEquals("Expected successful status", 200, mvcResult.getResponse().getStatus());
        assertEquals("Expected empty response for invalid auction id.", "", mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void createShouldThrowExceptionWhenRequestBodyDoesntExist() throws Exception {
        MvcResult mvcResult = mvc.perform(post("/auctions").contentType(MediaType.APPLICATION_JSON)).andReturn();

        verifyRouteExists(mvcResult);
        assertEquals("Expected Bad Request status 400 when POST request is missing the body.", 400, mvcResult.getResponse().getStatus());
    }

    @Test
    public void createShouldAddNewAuction() throws Exception {
        Auction auction = new Auction(
                "Standing Desk",
                "Stand up desk to help you stretch your legs during the day.",
                "Johnnie34",
                350.00);

        MvcResult mvcResult = mvc.perform(post("/auctions").contentType(MediaType.APPLICATION_JSON).content(toJson(auction))).andReturn();

        verifyRouteExists(mvcResult);
        int status = mvcResult.getResponse().getStatus();
        assertTrue("Expected successful status", status == 200 || status == 201);

        Auction returnedAuction = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<Auction>() {});
        assertNotNull("Expected an auction to be returned but received null", returnedAuction);
        assertEquals("Expected id did not match returned id", 8, returnedAuction.getId());
        assertEquals("Expected title did not match returned title", auction.getTitle(), returnedAuction.getTitle());
        assertEquals("Expected description did not match returned description", auction.getDescription(), returnedAuction.getDescription());
        assertEquals("Expected user did not match returned user", auction.getUser(), returnedAuction.getUser());
        assertEquals("Expected currentBid did not match returned currentBid", auction.getCurrentBid(), returnedAuction.getCurrentBid(), 0.001);
    }

    @Test
    public void searchByTitleShouldReturnList() throws Exception {
        List<Auction> allAuctions = dao.getAuctions();
        List<Auction> expected = new ArrayList<>();
        expected.add(allAuctions.get(1));
        expected.add(allAuctions.get(6));

        MvcResult mvcResult = mvc.perform(get("/auctions?title_like=watch")).andReturn();

        verifyRouteExists(mvcResult);
        assertEquals("Expected successful status", 200, mvcResult.getResponse().getStatus());

        List<Auction> searchResults = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Auction>>() {});
        assertEquals("Expected count of auctions did not match returned count", expected.size(), searchResults.size());
        assertEquals("Expected title of first auction did not match returned value", expected.get(0).getTitle(), searchResults.get(0).getTitle());
        assertEquals("Expected title of second auction did not match returned value", expected.get(1).getTitle(), searchResults.get(1).getTitle());
    }

    @Test
    public void searchByTitleExpectNone() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/auctions?title_like=abcsdfsdf")).andReturn();

        verifyRouteExists(mvcResult);
        assertEquals("Expected successful status", 200, mvcResult.getResponse().getStatus());

        List<Auction> searchResults = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Auction>>() {});
        assertEquals("Expected no auctions but received more than zero", 0, searchResults.size());
    }

    @Test
    public void searchByPriceShouldReturnList() throws Exception {
        List<Auction> allAuctions = dao.getAuctions();
        List<Auction> expected = new ArrayList<>();
        expected.add(allAuctions.get(3));

        MvcResult mvcResult = mvc.perform(get("/auctions?currentBid_lte=70")).andReturn();

        verifyRouteExists(mvcResult);
        assertEquals("Expected successful status", 200, mvcResult.getResponse().getStatus());

        List<Auction> searchResults = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Auction>>() {});
        assertEquals("Expected count of auctions did not match returned count", expected.size(), searchResults.size());
        assertEquals("Expected title did not match returned title", expected.get(0).getTitle(), searchResults.get(0).getTitle());
        assertEquals("Expected description did not match returned description", expected.get(0).getDescription(), searchResults.get(0).getDescription());
        assertEquals("Expected currentBid did not match returned currentBid", expected.get(0).getCurrentBid(), searchResults.get(0).getCurrentBid(), 0.001);
    }

    @Test
    public void searchByPriceExpectNone() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/auctions?currentBid_lte=1")).andReturn();

        verifyRouteExists(mvcResult);
        assertEquals("Expected successful status", 200, mvcResult.getResponse().getStatus());

        List<Auction> searchResults = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Auction>>() {});
        assertEquals("Expected no auctions but received more than zero", 0, searchResults.size());
    }

    @Test
    public void searchByTitleAndPriceExpectOne() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/auctions?title_like=Watch&currentBid_lte=200")).andReturn();

        verifyRouteExists(mvcResult);
        assertEquals("Expected successful status", 200, mvcResult.getResponse().getStatus());

        List<Auction> searchResults = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Auction>>() {});
        assertEquals("Expected one auction but received a different amount", 1, searchResults.size());
    }

    @Test
    public void searchByTitleAndPriceExpectNone() throws Exception {
        MvcResult mvcResult = mvc.perform(
                get("/auctions?title_like=Watch&currentBid_lte=50")).andReturn();

        verifyRouteExists(mvcResult);
        assertEquals("Expected successful status", 200, mvcResult.getResponse().getStatus());

        List<Auction> searchResults = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Auction>>() {});
        assertEquals("Expected no auctions but received more than zero", 0, searchResults.size());
    }

    private String toJson(Auction auction) throws JsonProcessingException {
        return mapper.writeValueAsString(auction);
    }

    private int verifyRouteExists(MvcResult mvcResult) {
        int status = mvcResult.getResponse().getStatus();
        if (status == 404 || status == 405) {
            fail(mvcResult.getRequest().getMethod() + " route " + mvcResult.getRequest().getPathInfo() + " was not found. " +
                    "Response status: " + status + ". Verify RequestMappings are correct.");
        }
        return status;
    }
}

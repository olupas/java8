package com.java_8_training.answers.ibay;

import org.junit.Test;

import java.time.Instant;

import static org.junit.Assert.*;

public class AuctionServiceTest {

    @Test
    public void actionWithNoBiddersHasNoLeader() {
        assertFalse(new AuctionService().leader().isPresent());
    }

    @Test
    public void highestBidderLeadsTheAuction() {
        // Given
        AuctionService auctionService = new AuctionService();

        // When
        Bid bid = new Bid(Instant.now(), 1L, 20);
        assertTrue(auctionService.onNewBid(bid));
        assertFalse(auctionService.onNewBid(new Bid(Instant.now(), 2L, 10)));

        // Then
        assertEquals(bid, auctionService.leader().get());
    }

}

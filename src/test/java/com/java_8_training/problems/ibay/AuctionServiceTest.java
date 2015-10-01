package com.java_8_training.problems.ibay;

import org.junit.Test;

import java.time.Instant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AuctionServiceTest {

    @Test
    public void actionWithNoBiddersHasNoLeader() {
        assertEquals(null, new AuctionService().leader());
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
        assertEquals(bid, auctionService.leader());
    }

}

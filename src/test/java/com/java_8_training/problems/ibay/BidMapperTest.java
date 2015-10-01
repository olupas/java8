package com.java_8_training.problems.ibay;

import org.junit.Test;

import java.time.Instant;

import static org.junit.Assert.assertEquals;

/**
 * .
 */
public class BidMapperTest {

    @Test
    public void canReadWhatHasBeenWritten() {
        BidMapper mapper = new BidMapper();
        Bid bid = new Bid(Instant.now(), 1L, 2L);
        mapper.write(bid);

        Bid readBid = mapper.read();
        assertEquals(bid, readBid);
    }

}

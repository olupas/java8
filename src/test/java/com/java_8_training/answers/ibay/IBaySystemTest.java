package com.java_8_training.answers.ibay;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class IBaySystemTest {

    private static final int PORT = 9999;

    private Bid receivedBid;
    private Server server;

    @Before
    public void start() throws IOException {
        Thread testThread = Thread.currentThread();
        server = new Server(PORT, bid -> {
            receivedBid = bid;
            assertNotEquals(testThread, Thread.currentThread());
        });
    }

    @After
    public void stop() throws IOException {
        server.stop();
        server.close();
    }

    @Test
    public void clientCanLeadAnAuction() throws Exception {
        try (Client client = new Client(1L, "localhost", PORT)) {
            client.bid(10);

            server.select(2);

            assertEquals(1, server.connectionCount());
            client.read();
            assertTrue(client.isLeading());
            assertReceivedBid(10L, 1L);
        }
    }

    private void assertReceivedBid(final long expectedAmount, final long expectedBidderId) {
        server.awaitBidProcessors();
        assertEquals(expectedAmount, receivedBid.getAmount());
        assertEquals(expectedBidderId, receivedBid.getBidderId());
    }

    @Test
    public void clientCanTakeoverAnAuction() throws Exception {
        try (Client client = new Client(1L, "localhost", PORT)) {
            client.bid(10);
            server.select(2);
            client.read();
            assertReceivedBid(10L, 1L);

            try (Client otherClient = new Client(2L, "localhost", PORT)) {
                otherClient.bid(20);

                server.select(2);
                client.read();
                otherClient.read();

                assertFalse(client.isLeading());
                assertTrue(otherClient.isLeading());

                assertEquals(2, server.connectionCount());
                assertReceivedBid(20L, 2L);
            }
        }
    }

}

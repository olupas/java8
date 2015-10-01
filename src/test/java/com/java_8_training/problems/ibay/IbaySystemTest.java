package com.java_8_training.problems.ibay;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IbaySystemTest {

    private static final int PORT = 9999;

    private Server server;

    @Before
    public void start() throws IOException {
        server = new Server(PORT);
    }

    @After
    public void stop() throws IOException {
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
        }
    }

    @Test
    public void clientCanTakeoverAnAuction() throws Exception {
        try (Client client = new Client(1L, "localhost", PORT)) {
            client.bid(10);
            server.select(2);
            client.read();

            try (Client otherClient = new Client(2L, "localhost", PORT)) {
                otherClient.bid(20);

                server.select(2);
                client.read();
                otherClient.read();

                assertFalse(client.isLeading());
                assertTrue(otherClient.isLeading());

                assertEquals(2, server.connectionCount());
            }
        }
    }

}

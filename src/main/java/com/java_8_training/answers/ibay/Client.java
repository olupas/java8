package com.java_8_training.answers.ibay;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.time.Instant;

/**
 * Each client represents one user who is trying to bid in the auction. Clients talk to the server over TCP.
 *
 * @see com.java_8_training.answers.ibay.Server
 */
public class Client implements AutoCloseable {

    private final SocketChannel channel;
    private final long id;
    private final BidMapper mapper;
    private Bid latestBid;

    /**
     * @param id the id of the client that you represent
     * @param host the hostname of the server to connect to
     * @param port the port of the server to connect to
     */
    public Client(long id, String host, int port) throws IOException {
        this.id = id;
        mapper = new BidMapper();
        channel = SocketChannel.open();
        channel.configureBlocking(false);
        channel.connect(new InetSocketAddress(host, port));

        while (!channel.finishConnect()) {
            sleep();
        }
    }

    /**
     * Place a new bid on the auction.
     *
     * @param amount the amount to bid.
     */
    public void bid(long amount) throws IOException {
        mapper.write(new Bid(Instant.now(), id, amount));
        int written = channel.write(mapper.byteBuffer());
        if (written != BidMapper.LENGTH) {
            System.err.println("Missing Data: " + (BidMapper.LENGTH - written));
        }
    }

    private void sleep() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read the current bid back from the auction
     *
     * @throws java.io.IOException
     */
    public void read() throws IOException {
        if (channel.read(mapper.byteBuffer()) > 0) {
            latestBid = mapper.read();
        }
    }

    /**
     *
     * @return true if you
     */
    public boolean isLeading() {
        return latestBid != null && latestBid.getBidderId() == id;
    }

    @Override
    public void close() throws Exception {
        channel.close();
    }

}

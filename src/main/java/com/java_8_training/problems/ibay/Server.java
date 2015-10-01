package com.java_8_training.problems.ibay;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static java.nio.channels.SelectionKey.OP_READ;
import static java.nio.channels.SelectionKey.OP_WRITE;

/**
 * The main server class. This accepts TCP connections from clients who
 * want to bid on the auction.
 */
public final class Server implements Runnable, AutoCloseable {

    private final ServerSocketChannel serverChannel;
    private final Selector selector;
    private final List<SocketChannel> channels;
    private final BidMapper mapper;
    private final AuctionService auctionService;

    private volatile boolean isRunning;

    public static void main(String [] args) throws IOException {
        int port = Integer.parseInt(args[0]);
        try (Server server = new Server(port)) {
            server.run();
        }
    }

    public Server(int port) throws IOException {
        mapper = new BidMapper();
        auctionService = new AuctionService();
        serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress("localhost", port));
        serverChannel.configureBlocking(false);
        selector = Selector.open();
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        channels = new ArrayList<>();
    }

    @Override
    public void run() {
        isRunning = true;
        while (isRunning) {
            select(1);
        }
    }

    public void stop() {
        isRunning = false;
        selector.wakeup();
    }

    public void select(int times) {
        for (int i = 0; i < times; i++) {
            try {
                if (selector.select() == 0)
                    return;
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectedKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();

                try {
                    if (key.channel() == serverChannel) {
                        SocketChannel channel = serverChannel.accept();
                        if (channel != null) {
                            channel.configureBlocking(false);
                            channel.register(selector, OP_READ | OP_WRITE);
                            channels.add(channel);
                        }
                    } else if (key.isReadable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer buffer = mapper.byteBuffer();
                        channel.read(buffer);
                        Bid bid = mapper.read();
                        if (auctionService.onNewBid(bid)) {
                            onNewLeader(buffer);
                        }
                        System.out.println(bid);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                iterator.remove();
            }
        }
    }

    private void onNewLeader(ByteBuffer buffer) throws IOException {
        for (SocketChannel channel : channels) {
            buffer.position(0);
            buffer.limit(BidMapper.LENGTH);
            int write = channel.write(buffer);
            if (write < BidMapper.LENGTH) {
                System.err.println("Dropped: " + (BidMapper.LENGTH - write));
            }
        }
    }

    public int connectionCount() {
        return channels.size();
    }

    @Override
    public void close() throws IOException {
        for (SocketChannel channel : channels) {
            channel.close();
        }
        serverChannel.close();
    }
}

package com.java_8_training.answers.ibay;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import static java.nio.channels.SelectionKey.OP_READ;
import static java.nio.channels.SelectionKey.OP_WRITE;

/**
 * The main server class. This accepts TCP connections from clients who
 * want to bid on the auction.
 */
public final class Server implements AutoCloseable, Runnable {

    private final ServerSocketChannel serverChannel;
    private final Selector selector;
    private final List<SocketChannel> channels;
    private final BidMapper mapper;
    private final AuctionService auctionService;
    private final Consumer<Bid> consumer;

    private volatile boolean isRunning;

    private CompletableFuture<Void> bidHandler;

    public static void main(String [] args) throws IOException {
        int port = Integer.parseInt(args[0]);
        try (Server server = new Server(port, System.out::println)) {
            server.run();
        }
    }

    public Server(int port, Consumer<Bid> consumer) throws IOException {
        this.consumer = consumer;
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

    public void awaitBidProcessors() {
        try {
            bidHandler.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void select(int times) {
        IntStream.range(0, times)
                 .forEach(i -> {

             try {
                 if (selector.select() == 0)
                     return;

                 selector.selectedKeys()
                         .removeIf(key -> {

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
                                         bidHandler = CompletableFuture.runAsync(() -> consumer.accept(bid));
                                         onNewLeader(buffer);
                                     }
                                 }
                             } catch (IOException e) {
                                 e.printStackTrace();
                             }

                             return true;
                         });
             } catch (IOException e) {
                 e.printStackTrace();
             }

         });
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

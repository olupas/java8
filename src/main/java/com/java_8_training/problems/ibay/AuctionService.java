package com.java_8_training.problems.ibay;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/**
 * The core Auction service which contains business logic for receiving bids and
 * determining who leads the auction.
 */
public final class AuctionService {

    private final AtomicReference<Bid> bid;

    public AuctionService() {
        bid = new AtomicReference<>(null);
    }

    /**
     * @return the current leader of the Auction
     */
    public Bid leader() {
        return bid.get();
    }

    public boolean onNewBid(Bid newBid) {
        Objects.requireNonNull(newBid);
        long newBidAmount = newBid.getAmount();
        return newBid == this.bid.updateAndGet(prev -> {
            if (prev == null || prev.getAmount() < newBidAmount) {
                return newBid;
            } else {
                return prev;
            }
        });
    }

}

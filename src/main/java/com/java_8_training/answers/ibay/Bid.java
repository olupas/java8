package com.java_8_training.answers.ibay;

import java.time.Instant;
import java.util.Objects;

/**
 * In immutable value type representing a bid. This is used to both represent the current bid in the Auction
 * class and also as a message to say that a new bid has been received.
 */
public final class Bid {

    private final Instant biddedAt;
    private final long bidderId;
    private final long amount;

    public Bid(Instant biddedAt, long bidderId, long amount) {
        Objects.requireNonNull(biddedAt);

        this.bidderId = bidderId;
        this.biddedAt = biddedAt;
        this.amount = amount;
    }

    public Instant getBiddedAt() {
        return biddedAt;
    }

    public long getBidderId() {
        return bidderId;
    }

    public long getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bid bid = (Bid) o;

        if (amount != bid.amount) return false;
        if (bidderId != bid.bidderId) return false;
        if (!biddedAt.equals(bid.biddedAt)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = biddedAt.hashCode();
        result = 31 * result + (int) (bidderId ^ (bidderId >>> 32));
        result = 31 * result + (int) (amount ^ (amount >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "biddedAt=" + biddedAt +
                ", bidderId=" + bidderId +
                ", amount=" + amount +
                '}';
    }
}

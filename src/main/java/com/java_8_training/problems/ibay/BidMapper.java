package com.java_8_training.problems.ibay;

import java.nio.ByteBuffer;
import java.time.Instant;
import java.util.Objects;

/**
 * A simple binary serialisation implementation for the Bid messages.
 */
public final class BidMapper {

    private static final int SIZE_OF_LONG = 8;
    private static final int SIZE_OF_INT = 4;
    public static final int LENGTH = SIZE_OF_LONG + SIZE_OF_LONG + SIZE_OF_LONG + SIZE_OF_INT;

    private final ByteBuffer byteBuffer;

    public BidMapper() {
        byteBuffer = ByteBuffer.allocateDirect(LENGTH);
    }

    public ByteBuffer byteBuffer() {
        byteBuffer.position(0);
        return byteBuffer;
    }

    public void write(Bid bid) {
        Objects.requireNonNull(bid);
        long bidderId = bid.getBidderId();
        long amount = bid.getAmount();

        Instant biddedAt = bid.getBiddedAt();
        long epochSecond = biddedAt.getEpochSecond();
        int nano = biddedAt.getNano();

        write(bidderId, amount, epochSecond, nano);
    }

    public void write(long bidderId, long amount, long epochSecond, int nano) {
        int offset = 0;

        byteBuffer.putLong(offset, bidderId);
        offset += SIZE_OF_LONG;

        byteBuffer.putLong(offset, amount);
        offset += SIZE_OF_LONG;

        byteBuffer.putLong(offset, epochSecond);
        offset += SIZE_OF_LONG;
        byteBuffer.putInt(offset, nano);
    }

    public Bid read() {
        int offset = 0;

        long bidderId = byteBuffer.getLong(offset);
        offset += SIZE_OF_LONG;

        long amount = byteBuffer.getLong(offset);
        offset += SIZE_OF_LONG;

        long epochSecond = byteBuffer.getLong(offset);
        offset += SIZE_OF_LONG;
        int nano = byteBuffer.getInt(offset);

        return new Bid(Instant.ofEpochSecond(epochSecond, nano), bidderId, amount);
    }

}

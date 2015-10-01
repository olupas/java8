package com.java_8_training.answers.ibay;

import java.util.ArrayList;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.function.Consumer;

public class BidStatisticsConsumer implements Consumer<Bid> {

    private final List<Bid> bids = new ArrayList<>();

    @Override
    public void accept(final Bid bid) {
        bids.add(bid);
    }

    public LongSummaryStatistics calculateStats() {
        return bids.stream()
                   .mapToLong(Bid::getAmount)
                   .summaryStatistics();
    }

}

package com.java_8_training.answers.concurrency;

public class CompletableFutureArtistAnalyserTest extends AbstractArtistAnalyzer {

    public CompletableFutureArtistAnalyserTest() {
        super(new CompletableFutureArtistAnalyser(lookupService::lookupArtistName));
    }

}

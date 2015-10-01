package com.java_8_training.answers.concurrency;

public class CallbackArtistAnalyserTest extends AbstractArtistAnalyzer {

    public CallbackArtistAnalyserTest() {
        super(new CallbackArtistAnalyser(lookupService::lookupArtistName));
    }

}

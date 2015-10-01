package com.java_8_training.answers.concurrency;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.assertEquals;

public abstract class AbstractArtistAnalyzer {

    protected static final FakeLookupService lookupService = new FakeLookupService();

    private final ArtistAnalyzer analyser;

    protected AbstractArtistAnalyzer(ArtistAnalyzer analyser) {
        this.analyser = analyser;
    }

    @Test
    public void largerGroupsAreLarger() {
        assertLargerGroup(true, "The Beatles", "John Coltrane");
    }

    @Test
    public void smallerGroupsArentLarger() {
        assertLargerGroup(false, "John Coltrane", "The Beatles");
    }

    private void assertLargerGroup(boolean expected, String artistName, String otherArtistName) {
        AtomicBoolean isLarger = new AtomicBoolean(!expected);
        analyser.isLargerGroup(artistName, otherArtistName, isLarger::set);
        assertEquals(expected, isLarger.get());
    }

}

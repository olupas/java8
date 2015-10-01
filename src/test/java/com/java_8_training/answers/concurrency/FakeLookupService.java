package com.java_8_training.answers.concurrency;

import com.java_8_training.music_domain.Artist;
import com.java_8_training.music_domain.SampleData;

public class FakeLookupService {

    public Artist lookupArtistName(String name) {
        sleepToSimulateLookupLatency();

        switch (name) {
            case "The Beatles":
                return SampleData.theBeatles;

            case "John Coltrane":
                return SampleData.johnColtrane;

            default:
                throw new IllegalArgumentException("Unknown artist: " + name);
        }
    }

    private void sleepToSimulateLookupLatency() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

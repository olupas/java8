package com.java_8_training.answers.concurrency;

import java.util.function.Consumer;

public interface ArtistAnalyzer {

    public void isLargerGroup(String artistName,
                              String otherArtistName,
                              Consumer<Boolean> handler);
    
}

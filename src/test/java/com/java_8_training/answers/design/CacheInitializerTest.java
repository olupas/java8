package com.java_8_training.answers.design;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CacheInitializerTest {

    @Test
    public void initializesFromFile() throws IOException {
        NumberCache cache = new NumberCache();
        cache.initialize("src/main/resources/numbers");
        assertEquals("1", cache.get(1));
        assertEquals("100111000", cache.get(312));
        assertNull(cache.get(10));
    }

}

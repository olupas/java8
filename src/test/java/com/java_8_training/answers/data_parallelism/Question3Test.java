package com.java_8_training.answers.data_parallelism;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Remove the locks, retaining the behaviour
 */
public class Question3Test {

    private static final int SIZE = 200;

    private boolean missilesFired = false;

    private int sum;

    @Test
    public void sample() {
        final Object lock = new Object();

        sum = IntStream.range(0, SIZE)
                       .parallel()
                       .sum();

        fireTheMissiles();

        assertTrue(missilesFired);
        assertEquals(19900, sum);
    }
    private void fireTheMissiles() {
        missilesFired = true;
        System.out.println("Fired Missiles!");
    }

}

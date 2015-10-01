package com.java_8_training.problems.data_parallelism;

import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Remove the locks, retaining the behaviour
 */
@Ignore
public class Question3Test {

    private static final int SIZE = 200;

    private boolean missilesFired = false;

    private int sum;

    @Test
    public void sample() {
        final Object lock = new Object();

        CountDownLatch latch = new CountDownLatch(SIZE);
        IntStream.range(0, SIZE)
                 .parallel()
                 .forEach(i -> {
                     synchronized (lock) {
                         sum += i;
                         latch.countDown();
                         //await(latch);
                         if (i == 0) {
                             fireTheMissiles();
                         }
                     }
                 });

        assertTrue(missilesFired);
        assertEquals(19900, sum);
    }

    private void await(CountDownLatch latch) {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void fireTheMissiles() {
        missilesFired = true;
        System.out.println("Fired Missiles!");
    }

}

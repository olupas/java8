package com.java_8_training.examples.data_parallelism;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * .
 */
public class DeadlockExample {

    public static void main(String[] args) {
        List<Integer> values = getValues();
        CountDownLatch latch = new CountDownLatch(values.size());
        values.parallelStream()
                .forEach(i -> {
                    try {
                        doSomething(i);
                        // Potential Deadlock
                        System.out.println("Entering Lock");
                        latch.countDown();
                        latch.await();
                        System.out.println("Exiting Lock");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }});
    }

    private static void doSomething(Object i) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 1_000_000
    public static List<Integer> getValues() {
        return IntStream.range(0, 1_000_000).boxed().collect(toList());
    }
}

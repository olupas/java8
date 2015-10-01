package com.java_8_training.examples.concurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * .
 */
public class FutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(() -> {
            return doSomething();
        });
        doOtherThings();
        String value = future.get();
    }

    private static void doOtherThings() {

    }

    private static String doSomething() {
        return null;
    }
}
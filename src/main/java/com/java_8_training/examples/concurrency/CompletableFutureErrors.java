package com.java_8_training.examples.concurrency;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureErrors {

    public static void main(String[] args) {
        // Creating
        CompletableFuture<Integer> future = new CompletableFuture<Integer>();

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            sleep(500);
            return 10;
        });

        // Transforming / Applying
        future.exceptionally(e -> 0)

              .thenApply(x -> x + 1)

              .thenAccept(x -> System.out.println("Accepting value: " + x));
        
        // Completing
        runThread(() -> {
            sleep(1000);

            // Completing
            future.completeExceptionally(new Exception("Oh dear"));
        });
    }

    private static void runThread(Runnable block) {
        new Thread(block).start();
    }

    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

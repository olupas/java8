package com.java_8_training.examples.concurrency;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureLiveCoding {

    public static void main(String[] args) throws Exception {
        // Creating
        CompletableFuture<Integer> future = new CompletableFuture<>();

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            sleep(500);

            return 5;
        });

        new Thread(() -> {
            sleep(1000);

            // Completing
            //future.completeExceptionally(new IllegalStateException("The database is down!"));
            future.complete(10);
        }).start();

        // Callbacks - thenAccept/thenRun
        future2
            // Transforming - thenApply
            .thenApply(x -> x + 1)

            // Combining
            .thenCombine(future, (future2Val, futureVal) -> future2Val + futureVal)

            // Composing
            /*.thenCompose(x -> {
                return CompletableFuture.supplyAsync(() -> 20);
            })*/

            // Recovery
            .handle((i, ex) -> {
                if (ex != null) {
                    return ex.getMessage();
                } else {
                    return String.valueOf(i);
                }
            })

            .thenAccept(i -> {
                System.out.println(i);
            });

        // Errors - completeExceptionally/exceptionally

        System.out.println("I can do the next action");
    }

    private static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

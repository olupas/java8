package com.java_8_training.examples.data_parallelism;

import java.util.stream.LongStream;

/**
 * .
 */
public class MutableStateExample {

    public static void main(String[] args) {
        long result = sideEffectParallelSum(100);
        System.out.println("Expecting 5050, Actual = " + result);
    }

    public static long sideEffectParallelSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(0, n)
                  .parallel()
                  .forEach(accumulator::add);
        return accumulator.total;
    }

    public static class Accumulator {
        private long total = 0;
        public void add(long value) {
            total += value;
        }
    }

}

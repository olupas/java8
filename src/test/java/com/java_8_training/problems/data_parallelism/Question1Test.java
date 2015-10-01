package com.java_8_training.problems.data_parallelism;

import org.junit.Assert;
import org.junit.Test;

import java.util.stream.IntStream;

/**
 * Parallelise the "sequentialSumOfSquares" method.
 */
public class Question1Test {

    @Test
    public void testSerialToParallel() {
        IntStream range = IntStream.range(0, 100);
        Assert.assertEquals(328350, sumOfSquares(range));
    }

    public static int sumOfSquares(IntStream range) {
        return range.map(x -> x * x)
                    .sum();
    }

}

package com.java_8_training.answers.data_parallelism;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Fix the bug in the "multiplyThrough" method
 */
public class Question2Test {

    @Test
    public void sample() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        int result = multiplyThrough(numbers);
        assertEquals(30, result);
    }

        /*
    Buggy Version:
    // BEGIN buggyMultiplyThrough
public static int multiplyThrough(List<Integer> linkedListOfNumbers) {
    return linkedListOfNumbers.stream()
                  .reduce(5, (acc, x) -> x * acc);
}
    // END buggyMultiplyThrough
    */

    public static int multiplyThrough(List<Integer> numbers) {
        return 5 * numbers.parallelStream()
                          .reduce(1, (acc, x) -> x * acc);
    }

}

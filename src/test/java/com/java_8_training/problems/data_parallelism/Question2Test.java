package com.java_8_training.problems.data_parallelism;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Fix the bug in the "multiplyThrough" method
 */
@Ignore
public class Question2Test {

    @Test
    public void sample() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        int result = multiplyThrough(numbers);
        assertEquals(30, result);
    }

    public static int multiplyThrough(List<Integer> linkedListOfNumbers) {
        return linkedListOfNumbers.parallelStream()
                                  .reduce(5, (acc, x) -> x * acc);
    }

}

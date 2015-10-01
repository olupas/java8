package com.java_8_training.answers.streams;

import org.junit.Test;

import java.util.stream.Stream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ReduceExerciseTest {

    @Test
    public void findMinimumValue() {
        int min = Stream.of(5, 2, 200, 33, 150, 0).reduce(Integer.MAX_VALUE, Integer::min);

        assertThat(min, is(0));
    }

    @Test
    public void findMaximumValue() {
        int min = Stream.of(5, 2, 200, 33, 150, 0).reduce(Integer.MIN_VALUE, Integer::max);

        assertThat(min, is(200));
    }

    @Test
    public void sumValues() {
        int sum = Stream.of(5, 2, 3, 150).reduce(-1, (a, b) -> a + b);

        assertThat(sum, is(160));
    }

}

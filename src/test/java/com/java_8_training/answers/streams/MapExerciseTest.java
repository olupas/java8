package com.java_8_training.answers.streams;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MapExerciseTest {

    @Test
    public void doubleNumbers() {
        List<Integer> doubleNumbers = Stream.of(0, 1, 2, 3, 4, 5)
                                            .map(x -> x * 2)
                                            .collect(toList());

        assertThat(doubleNumbers, is(Arrays.asList(0, 2, 4, 6, 8, 10)));
    }

}

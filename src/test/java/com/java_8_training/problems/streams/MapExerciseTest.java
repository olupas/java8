package com.java_8_training.problems.streams;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@Ignore
public class MapExerciseTest {

    @Test
    public void doubleNumbers() {
        Stream<Integer> input = Stream.of(0, 1, 2, 3, 4, 5);

        List<Integer> doubleNumbers = new ArrayList<>();

        assertThat(doubleNumbers, is(Arrays.asList(0, 2, 4, 6, 8, 10)));
    }

}

package com.java_8_training.answers.streams;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FilterExerciseTest {

    @Test
    public void findEvenNumbers() {
        List<Integer> evenNumbers = Stream.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                                          .filter(x -> x % 2 == 0)
                                          .collect(toList());

        assertThat(evenNumbers, is(Arrays.asList(0, 2, 4, 6, 8, 10)));
    }

}

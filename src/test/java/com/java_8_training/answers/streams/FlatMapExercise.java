package com.java_8_training.answers.streams;

import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

public class FlatMapExercise {

    @Test
    public void flatteningLists() {
        List<Integer> together = Stream.of(asList(1, 2), asList(3, 4))
                                       .flatMap(numbers -> numbers.stream())
                                       .collect(toList());

        assertEquals(asList(1, 2, 3, 4), together);
    }

}

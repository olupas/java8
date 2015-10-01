package com.java_8_training.problems.streams;

import org.junit.Ignore;
import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

@Ignore
public class FlatMapExercise {

    @Test
    public void flatteningLists() {
        Stream<List<Integer>> input = Stream.of(asList(1, 2), asList(3, 4));
        // TODO: flatten the lists into one list
        List<Integer> together = null;

        assertEquals(asList(1, 2, 3, 4), together);
    }

}

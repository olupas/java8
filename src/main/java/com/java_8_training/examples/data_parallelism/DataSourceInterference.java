package com.java_8_training.examples.data_parallelism;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class DataSourceInterference {

    // Initially throws Concurrent Modification Exception
    // Refactor to more idiomatic functional code

    public static void main(String[] args) {
        List<Integer> numbers = getNumbers();
        numbers.forEach(x -> numbers.add(x * 2));

        System.out.println(numbers);
    }

    private static List<Integer> getNumbers() {
        return new ArrayList<>(asList(1, 2, 3, 4, 5));
    }
}

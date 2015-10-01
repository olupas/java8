package com.java_8_training.problems.design;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Exercise: refactor this class in order to separate out the business logic of building the cache from file I/O.
 *
 * You only need to refactor the {@link this#initialize(String)} method in order to do split. You should use the
 * {@link com.java_8_training.problems.design.LinesProcessor} interface here with a lambda and implement the
 * processFile() method.
 */
public class NumberCache {

    private final Map<Integer, String> numbers;

    public NumberCache() throws IOException {
        numbers = new HashMap<>();
    }

    public void initialize(String file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            // Get a stream of all the lines from the buffered reader
            reader.lines()
            // Initialize the cache with them
                  .map(Integer::parseInt)
                  .forEach(this::cacheBinaryString);
        }
    }

    private void cacheBinaryString(int number) {
        numbers.computeIfAbsent(number, Integer::toBinaryString);
    }

    public String get(int number) {
        return numbers.get(number);
    }

}

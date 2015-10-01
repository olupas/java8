package com.java_8_training.answers.design;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NumberCache {

    private final Map<Integer, String> numbers;

    public NumberCache() throws IOException {
        numbers = new HashMap<>();
    }

    public void initialize(String file) throws IOException {
        LinesProcessor.processFile(file, lines -> {
            lines.map(Integer::parseInt)
                 .forEach(this::cacheBinaryString);
        });
    }
    
    private void cacheBinaryString(int number) {
        numbers.computeIfAbsent(number, Integer::toBinaryString);
    }

    public String get(int number) {
        return numbers.get(number);
    }

}

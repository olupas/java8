package com.java_8_training.examples.testing;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;


public class TestingExampleTest {

    List<String> allToUpperCase(List<String> words) {
        return words.stream()
                // How do you test this lambda:
                .map(string -> string.toUpperCase())
                .collect(toList());
    }

    @Test
    public void multipleWordsToUppercase() {
        List<String> input = asList("a", "b", "hello");
        List<String> result = allToUpperCase(input);
        assertEquals(asList("A", "B", "HELLO"), result);
    }

    List<String> elementFirstToUpperCase(List<String> words) {
        return words.stream()
                    .map(this::firstToUppercase)
                    .collect(toList());
    }

    private String firstToUppercase(String value) {
        char firstChar = Character.toUpperCase(value.charAt(0));
        return firstChar + value.substring(1);
    }

    @Test
    public void twoLetterStringConvertedToUppercase() {
        String input = "ab";
        String result = firstToUppercase(input);
        assertEquals("Ab", result);
    }

}

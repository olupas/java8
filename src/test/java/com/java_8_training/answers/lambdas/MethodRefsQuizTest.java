package com.java_8_training.answers.lambdas;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class MethodRefsQuizTest {

    @Test
    public void stringToIntegerQuiz() {
        Function<String, Integer> stringToInteger = Integer::parseInt;

        assertThat(2, is(stringToInteger.apply("2")));
    }

    @Test
    public void containsQuiz() {
        BiPredicate<List<String>, String> contains = List::contains;

        boolean doesItContainIt = contains.test(Arrays.asList("who", "how", "why"), "who");

        assertEquals(true, doesItContainIt);
    }
    
}

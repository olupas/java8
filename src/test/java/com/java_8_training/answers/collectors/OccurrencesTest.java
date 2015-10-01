package com.java_8_training.answers.collectors;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by raoul-gabrielurma on 09/08/2014.
 */
public class OccurrencesTest {


    @Test
     public void occurrencesForAWord(){
        String word = "cool";

        Map<String, Long> occ =
                Arrays.stream(word.split("")).collect(groupingBy(identity(), counting()));

        assertEquals(2, (long) occ.get("o"));
        assertEquals(1, (long) occ.get("c"));
        assertEquals(1, (long) occ.get("l"));
        assertEquals(3, occ.size());

    }

    @Test
    public void occurrencesForAListOfSentences(){

        List<String> sentences = Arrays.asList("Hello everyone!", "Java 8 is here!");

        // IntelliJ struggling with type inference but code is correct & runs
        Map<String, Long> occ =
            sentences.stream()
                    .map((String s) -> s.split(""))
                    .flatMap(Arrays::stream)
                    .collect(groupingBy(identity(), counting()));

        assertEquals(2, (long) occ.get("l"));
        assertEquals(4, (long) occ.get(" "));
        assertEquals(2, (long) occ.get("!"));
        assertEquals(2, (long) occ.get("a"));
        assertEquals(6, (long) occ.get("e"));
        assertEquals(16, occ.size());



    }

}

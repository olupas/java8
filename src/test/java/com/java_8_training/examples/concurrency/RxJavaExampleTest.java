package com.java_8_training.examples.concurrency;

import org.junit.Test;
import rx.Observable;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class RxJavaExampleTest {

    @Test
    public void example() {
        List<Integer> numbers =
            Observable.from(1, 2, 3, 4, 5, 6, 7, 8)
                      .filter(x -> x > 4)
                      .map(x -> x + 1)
                      .take(3)
                      .toList()
                      .toBlockingObservable()
                      .first();

        assertThat(numbers, is(asList(6, 7, 8)));
    }
}

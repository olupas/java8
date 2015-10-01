package com.java_8_training.answers.lambdas;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class LambdaRefactorTest {

    @Test
    public void sortInventoryByDecreasingWeight() {
        List<Apple> inventory = asList(new Apple(80, "green"), new Apple(155, "green"), new Apple(120, "red"));
        inventory.sort((a1, a2) -> a2.getWeight().compareTo(a1.getWeight()));

        assertThat(inventory, is(asList(new Apple(155, "green"), new Apple(120, "red"), new Apple(80, "green"))));
    }

    @Test
    public void filterGreenApples() {

        List<Apple> inventory = asList(new Apple(80, "green"), new Apple(155, "green"), new Apple(120, "red"));

        List<Apple> greenApples = filterApples(inventory, a -> "green".equals(a.getColor()));

        assertThat(greenApples, is(asList(new Apple(80, "green"), new Apple(155, "green"))));

    }

    @Test
    public void squareNumber() {
        UnaryOperator<Integer> square = integer -> integer * integer;

        assertThat(square.apply(2), is(4));
    }

    private static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple a : inventory) {
            if (p.test(a)) {
                result.add(a);
            }
        }
        return result;
    }

}

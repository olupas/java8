package com.java_8_training.problems.lambdas;

import com.java_8_training.answers.lambdas.Apple;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class BehaviourParameterisation {


    //TODO: implement the method prettyPrintOnlyWeightApple
    //TODO: declare a new method prettyPrintApple which takes different formatters as parameter
    //TODO: can you refactor prettyPrintOnlyWeightApple to use it?
    //TODO: can you refactor prettyPrintHeavyLightApple to use it?
    //TODO: can you make prettyPrintApple generic (i.e. can work with any type not just Apple)?

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"), new Apple(155, "green"), new Apple(120, "red"));


        prettyPrintHeavyLightApple(inventory);
        prettyPrintOnlyWeightApple(inventory);
        prettyPrintApple(inventory, new AppleFormatter<Apple>() {
            @Override
            public String format(Apple a) {
                return "An apple has:" + a.getWeight() + " grams";
            }
        });

        prettyPrintApple(inventory, a -> "An apple has:" + a.getWeight() + " grams");

        prettyPrintApple(inventory, a -> {return "An apple has:" + a.getWeight() + " grams";});


    }

    public static void prettyPrintHeavyLightApple(List<Apple> inventory) {
        Objects.requireNonNull(inventory, "Inventory must not be null");
        for (Apple apple : inventory) {
            String characteristic = apple.getWeight() > 150 ? "heavy" : "light";
            String output = "A " + characteristic + " " + apple.getColor() + " apple";
            System.out.println(output);
        }

        prettyPrintApple(inventory, a -> {
            String characteristic = a.getWeight() > 150 ? "heavy" : "light";
            String output = "A " + characteristic + " " + a.getColor() + " apple";
            return output;
        });
    }

    /**
     * Prints all the weights from the inventory one by one
     *
     * @param inventory
     */
    public static void prettyPrintOnlyWeightApple(List<Apple> inventory) {
        Objects.requireNonNull(inventory, "Inventory must not be null");
        inventory.forEach((Apple a) -> {
            System.out.println(a.getWeight());
        });

    }

    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter<Apple> formatter) {
        Objects.requireNonNull(inventory, "Inventory must not be null");
        inventory.forEach((Apple a) -> {
            System.out.println(formatter.format(a));
        });

    }


    @FunctionalInterface
    public static interface AppleFormatter<T> {
        String format(T a);
    }

}

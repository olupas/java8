package com.java_8_training.answers.lambdas;

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

        // The implementation of prettyPrintOnlyWeightApple is an exercise
        prettyPrintOnlyWeightApple(inventory);

        // The implementation and declaration of prettyPrintApple is an exercise
        // This is prettyPrintOnlyWeightApple using behavior parameterisation
        prettyPrintApple(inventory, new AppleFormatter() {
            @Override
            public String accept(Apple apple) {
                return "An apple of " + apple.getWeight() + "g";
            }
        });

        // The implementation and declaration of prettyPrintApple is an exercise
        // This is prettyPrintHeavyLightApple using behavior parameterisation
        prettyPrintApple(inventory, new AppleFormatter() {
            @Override
            public String accept(Apple apple) {
                String characteristic = apple.getWeight() > 150 ? "heavy" : "light";
                return "A " + characteristic + " " + apple.getColor() + " apple";
            }
        });


        // generic and using behavior parameterisation
        prettyPrint(inventory, new Formatter<Apple>() {
            @Override
            public String accept(Apple apple) {
                return "An apple of " + apple.getWeight() + "g";
            }
        });
    }

    public static void prettyPrintHeavyLightApple(List<Apple> inventory) {
        Objects.requireNonNull(inventory, "Inventory must not be null");
        for (Apple apple : inventory) {
            String characteristic = apple.getWeight() > 150 ? "heavy" : "light";
            String output = "A " + characteristic + " " + apple.getColor() + " apple";
            System.out.println(output);
        }
    }

    /**
     * Prints all the weights from the inventory one by one
     *
     * @param inventory
     */
    public static void prettyPrintOnlyWeightApple(List<Apple> inventory) {
        Objects.requireNonNull(inventory, "Inventory must not be null");
        for(Apple apple: inventory){
            String output = "An apple of " + apple.getWeight() + "g";
            System.out.println(output);
        }
    }


    static interface AppleFormatter {
        String accept(Apple a);
    }

    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter formatter){
        Objects.requireNonNull(inventory, "Inventory must not be null");
        Objects.requireNonNull(formatter, "Formatter must not be null");

        for(Apple apple: inventory){
            String output = formatter.accept(apple);
            System.out.println(output);
        }
    }


    static interface Formatter<T> {
        String accept(T t);
    }

    public static <T> void prettyPrint(List<T> elements, Formatter<T> formatter){
        Objects.requireNonNull(elements, "The list must not be null");
        Objects.requireNonNull(formatter, "Formatter must not be null");
        for(T e: elements){
            String output = formatter.accept(e);
            System.out.println(output);
        }
    }




}

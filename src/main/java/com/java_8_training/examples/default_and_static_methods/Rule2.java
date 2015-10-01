package com.java_8_training.examples.default_and_static_methods;

public class Rule2 implements ChildInterface {
    public static void main(String[] args) {
        new Rule2().hello();
    }
}

interface ParentInterface {
    default void hello() {
        System.out.println("Hello from Parent");
    }
}

interface ChildInterface extends ParentInterface {
    @Override
    default void hello() {
        System.out.println("Hello from Child");
    }
}
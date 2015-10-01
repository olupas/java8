package com.java_8_training.examples.default_and_static_methods;

public class Rule3 implements ChildInterface1 /*, ChildInterface2*/ {
    public static void main(String[] args) {
        new Rule3().hello();
    }

    // Solution:
    /*@Override
    public void hello() {
        ChildInterface1.super.hello();
    }*/
}

interface ChildInterface1 extends Rule3Parent {
    default void hello() {
        System.out.println("Hello 1");
    }
}

interface ChildInterface2 extends Rule3Parent {
    default void hello() {
        System.out.println("Hello 2");
    }
}

interface Rule3Parent {
    default void hello() {
        System.out.println("Hello from Parent");
    }
}

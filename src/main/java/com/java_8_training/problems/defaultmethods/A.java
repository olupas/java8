package com.java_8_training.problems.defaultmethods;


public interface A {
    default String printHello() {
        return "Hello A";
    }
}

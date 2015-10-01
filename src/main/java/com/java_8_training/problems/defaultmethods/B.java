package com.java_8_training.problems.defaultmethods;

public interface B {
    default String printHello() {
        return "Hello B";
    }
}

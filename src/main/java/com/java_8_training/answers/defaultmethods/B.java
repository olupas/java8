package com.java_8_training.answers.defaultmethods;

public interface B {
    default String printHello() {
        return "Hello B";
    }
}

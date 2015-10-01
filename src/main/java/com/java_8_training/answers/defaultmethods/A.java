package com.java_8_training.answers.defaultmethods;


public interface A {
    default String printHello() {
        return "Hello A";
    }
}

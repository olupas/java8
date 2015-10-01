package com.java_8_training.answers.defaultmethods;


public class ABBA implements A, B {
    public String printHello() {
        return A.super.printHello();
    }
}

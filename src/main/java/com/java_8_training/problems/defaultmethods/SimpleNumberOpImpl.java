package com.java_8_training.problems.defaultmethods;

public class SimpleNumberOpImpl implements SimpleNumberOp {

    private int number;

    public SimpleNumberOpImpl(int number) {

        this.number = number;
    }

    @Override
    public int getValue() {
        return number;
    }
}

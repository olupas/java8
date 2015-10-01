package com.java_8_training.answers.defaultmethods;

public interface SimpleNumberOp {

    public static int add(SimpleNumberOp lhs, SimpleNumberOp rhs) {
        return lhs.getValue() + rhs.getValue();
    }

    public int getValue();

    default double getValueAsDouble() {
        throw new UnsupportedOperationException();
    }
}

package com.java_8_training.examples.default_and_static_methods.inheritance_example;

public interface Resizable {

    int getWidth();
    void setWidth(int width);

    int getHeight();
    void setHeight(int height);

    default void expand(int widthChange, int heightChange) {
        setHeight(getHeight() + heightChange);
        setWidth(getWidth() + widthChange);
    }

    default void contract(int widthChange, int heightChange) {
        setHeight(getHeight() - heightChange);
        setWidth(getWidth() - widthChange);
    }

}

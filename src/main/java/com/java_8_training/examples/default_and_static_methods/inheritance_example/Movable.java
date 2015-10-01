package com.java_8_training.examples.default_and_static_methods.inheritance_example;

public interface Movable {

    int getX();
    void setX(int x);

    int getY();
    void setY(int y);

    default void move(int xChange, int yChange) {
        setX(getX() + xChange);
        setY(getY() + yChange);
    }
}

package com.java_8_training.examples.default_and_static_methods.inheritance_example;

/** An adult is movable, but not resizable */
public class Adult implements Movable {

    private int x;
    private int y;

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }
}

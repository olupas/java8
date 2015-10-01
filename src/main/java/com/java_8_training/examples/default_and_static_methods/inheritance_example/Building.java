package com.java_8_training.examples.default_and_static_methods.inheritance_example;

/** A building is resizable, but not movable */
public class Building implements Resizable {

    private int width;
    private int height;

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

}

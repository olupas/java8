package com.java_8_training.answers.optional;

import java.util.Optional;

public class Person {

    public Person(Car car){
        this.car = Optional.ofNullable(car);
    }

    private Optional<Car> car;

    public Optional<Car> getCar() {
        return car;
    }
}
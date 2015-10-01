package com.java_8_training.answers.optional;

import java.util.Optional;

public class Car {

    public Car(int price, Insurance insurance){
        this.price = price;
        this.insurance = Optional.ofNullable(insurance);
    }


    private int price;
    private Optional<Insurance> insurance;


    public int getPrice(){
        return this.price;
    }

    public Optional<Insurance> getInsurance() {
        return insurance;
    }
}

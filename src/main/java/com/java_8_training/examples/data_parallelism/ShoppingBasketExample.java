package com.java_8_training.examples.data_parallelism;

import java.util.Arrays;
import java.util.List;

/**
 * .
 */
public class ShoppingBasketExample {

    private static final int DELIVERY_FEE = 10;

    public static void main(String[] args) {
        List<Purchase> items = Arrays.asList(new Purchase(1), new Purchase(5), new Purchase(10), new Purchase(7));
        System.out.print(totalCost(items));
    }

    // Identity - 'the do nothing value'
    // Associative - 'you can flip order around and things still work'

    static int totalCost(List<Purchase> items) {
        return items.stream()
                    .mapToInt(Purchase::getCost)
                    .reduce(DELIVERY_FEE, Integer::sum);
    }

}

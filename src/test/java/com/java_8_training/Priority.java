package com.java_8_training;

/**
 * .
 */
public class Priority {

    public static void main(String[] args) {
        Thread.getAllStackTraces()
              .keySet()
              .stream()
              .filter(thread -> "Finalizer".equals(thread.getName()))
              .forEach(System.out::println);
    }

}

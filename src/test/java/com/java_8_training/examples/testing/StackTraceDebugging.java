package com.java_8_training.examples.testing;

import java.awt.Point;
import java.util.Arrays;
import java.util.List;

public class StackTraceDebugging {
  public static void main(String[] args) {
    List<Point> points
      = Arrays.asList(new Point(12, 2), null);

    points.stream()
          .map(Point::getX)
          .forEach(System.out::println);
  }
}

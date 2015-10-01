package com.java_8_training.answers.data_parallelism;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.concurrent.TimeUnit.MICROSECONDS;

/**
 * Run:
 *
 * <pre>
 * mvn -DskipTests clean package
 * java -cp target/examples-and-exercises-0.0.1-SNAPSHOT-jar-with-dependencies.jar com.java_8_training.answers.data_parallelism.OptimisationExampleFixed
 * </pre>
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(MICROSECONDS)
public class OptimisationExampleFixed {

    public static void main(String[] ignore) throws RunnerException, IOException {
        Options opt = new OptionsBuilder()
                .include(OptimisationExampleFixed.class.getSimpleName())
                .forks(1)
                .warmupIterations(5)
                .measurementIterations(10)
                .build();

        new Runner(opt).run();
    }

    private List<Integer> arrayListOfNumbers;
    private List<Integer> linkedListOfNumbers;

    @Setup
    public void init() {
        arrayListOfNumbers= new ArrayList<>();
        addNumbers(arrayListOfNumbers);

        linkedListOfNumbers = new LinkedList<>();
        addNumbers(linkedListOfNumbers);
    }

    private void addNumbers(List<Integer> container) {
        IntStream.range(0, 1_000_000)
                .forEach(container::add);
    }

    @Benchmark
    public int slowSumOfSquares() {
        return linkedListOfNumbers.parallelStream()
                                  .map(x -> x * x)
                                  .reduce(0, (acc, x) -> acc + x);
    }

    @Benchmark
    public int serialSlowSumOfSquares() {
        return linkedListOfNumbers.stream()
                                  .map(x -> x * x)
                                  .reduce(0, (acc, x) -> acc + x);
    }

    @Benchmark
    public int intermediateSumOfSquares() {
        return arrayListOfNumbers.parallelStream()
                                 .map(x -> x * x)
                                 .reduce(0, (acc, x) -> acc + x);
    }

    @Benchmark
    public int serialIntermediateSumOfSquares() {
        return arrayListOfNumbers.stream()
                                 .map(x -> x * x)
                                 .reduce(0, (acc, x) -> acc + x);
    }

    @Benchmark
    public int fastSumOfSquares() {
        return arrayListOfNumbers.parallelStream()
                                 .mapToInt(x -> x * x)
                                 .sum();
    }

    @Benchmark
    public int serialFastSumOfSquares() {
        return arrayListOfNumbers.parallelStream()
                           .mapToInt(x -> x * x)
                           .sum();
    }

}

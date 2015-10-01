package com.java_8_training.problems.data_parallelism;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.concurrent.TimeUnit.MICROSECONDS;

/**
 * Run:
 *
 * <pre>
 * mvn -DskipTests clean package
 * java -cp target/examples-and-exercises-0.0.1-SNAPSHOT-jar-with-dependencies.jar com.java_8_training.answers.data_parallelism.OptimisationExample
 * </pre>
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(MICROSECONDS)
public class OptimisationExample {

    public static void main(String[] ignore) throws IOException, RunnerException {
        Options opt = new OptionsBuilder()
                .include(OptimisationExample.class.getSimpleName())
                .forks(1)
                .warmupIterations(5)
                .measurementIterations(10)
                .build();

        new Runner(opt).run();
    }

    private List<Integer> linkedListOfNumbers;

    @Setup
    public void init() {
        linkedListOfNumbers = new LinkedList<>();
        addNumbers(linkedListOfNumbers);

        // TODO: put any additional setup code here
    }

    private void addNumbers(List<Integer> container) {
        IntStream.range(0, 1_000_000)
                 .forEach(container::add);
    }

    @Benchmark
    // BEGIN slowSumOfSquares
    public int slowSumOfSquares() {
        return linkedListOfNumbers.parallelStream()
                                  .map(x -> x * x)
                                  .reduce(0, (acc, x) -> acc + x);
    }
    // END slowSumOfSquares

    @Benchmark
    public int fastSumOfSquares() {
        // TODO: implement faster version of slowSumOfSquares here
        return 0;
    }

}

package com.java_8_training.problems.design;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * This interface models a function which processes lines - potentially of a file.
 */
@FunctionalInterface
public interface LinesProcessor {

    void process(Stream<String> lines);

    public static void processFile(String file, LinesProcessor processor) throws IOException {
        throw new UnsupportedOperationException("TODO: implement this");
    }

}

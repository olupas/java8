package com.java_8_training.problems.design;

import java.io.IOException;
import java.io.OutputStream;

public interface CompressionStrategy {

    public OutputStream compress(OutputStream data) throws IOException;

}

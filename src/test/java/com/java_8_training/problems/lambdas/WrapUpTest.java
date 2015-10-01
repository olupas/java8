package com.java_8_training.problems.lambdas;

import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.util.function.BiPredicate;

import static junit.framework.Assert.assertEquals;

@Ignore
public class WrapUpTest {

    private final File mainDirectory = new File("src/test/resources/wrap_up");

    @Test
    public void hiddenFiles() {
        // TODO: find hidden files, tip: mainDirectory.listFiles();
        File[] files = null;
        File[] files2 = mainDirectory.listFiles(File::isHidden);

        assertEquals(0, files2.length);

        File[] files3 = mainDirectory.listFiles((File f) -> {
            return f.isHidden();
        });

        assertEquals(0, files3.length);
    }

    @Test
    public void xmlFiles() {
        // TODO: Find XML files,

        BiPredicate<String, String> contains1 = (s, e) -> { return s.contains(e);};


        File[] files2 = mainDirectory.listFiles((File f) -> {
            return f.getName().endsWith(".xml");
        });

        assertEquals(1, files2.length);

        File[] files3 = mainDirectory.listFiles((File f) -> { return contains1.test(f.getName(), "xml");});

        assertEquals(1, files3.length);
    }
}

package com.java_8_training.answers.optional;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import java.util.Properties;

import static junit.framework.Assert.assertEquals;

public class RefactorToOptional1Test {

    private Properties properties;

    @Before
    public void setUp(){
        properties = new Properties();
        properties.setProperty("eating", "10");
        properties.setProperty("sleeping", "-1");
        properties.setProperty("coding", "30");
    }

    @Test
    public void parsingProperty(){
        assertEquals(10, readDuration(properties, "eating"));
        assertEquals(0, readDuration(properties, "sleeping"));
        assertEquals(0, readDuration(properties, "cycling"));
        assertEquals(30, readDuration(properties, "coding"));

    }

    @Test
    public void parsingPropertyWithOptional(){
        assertEquals(10, readDurationWithOptional(properties, "eating"));
        assertEquals(0, readDurationWithOptional(properties, "sleeping"));
        assertEquals(0, readDurationWithOptional(properties, "cycling"));
        assertEquals(30, readDurationWithOptional(properties, "coding"));
    }

    public int readDuration(Properties props, String name) {
        String value = props.getProperty(name);
        if (value != null) {
            int i = Integer.parseInt(value);
            if (i > 0) {
                return i;
            }
        }
        return 0;
    }

    public int readDurationWithOptional(Properties props, String name) {
        return Optional.ofNullable(props.getProperty(name))
                       .map(Integer::parseInt)
                       .filter(x -> x > 0)
                       .orElse(0);
    }

}

package com.java_8_training.problems.optional;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Properties;

import static junit.framework.Assert.assertEquals;

public class RefactorToOptional1Test
{

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

    //TODO: your task is to fix this test by implementing readDurationWithOptional
    @Ignore
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

    //TODO: use Optional to reactor the method readDuration
    public int readDurationWithOptional(Properties props, String name) {
        return 0;
    }


}

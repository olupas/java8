package com.java_8_training.mytest;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Ovidiu Lupas
 * Date: 29.09.2015
 * Time: 23:22
 * To change this template use File | Settings | File Templates.
 */

@FunctionalInterface
public interface CriticalRegion {
    public void apply();

    public default String now() {
        return new Date().toString();
    }

}

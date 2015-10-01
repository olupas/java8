package com.java_8_training.mytest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * User: Ovidiu Lupas
 * Date: 29.09.2015
 * Time: 23:24
 * To change this template use File | Settings | File Templates.
 */
public class LockUtils {

    public static void withLock(Lock lock, CriticalRegion cr) {
        lock.lock();
        try {
            System.out.println(cr.now());
            cr.apply();
            throw new RuntimeException();
          //  System.out.println(cr.now());
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        Lock rl = new ReentrantLock();
        withLock(rl, () -> {
            System.out.println("Within Lock");
        });
    }

}

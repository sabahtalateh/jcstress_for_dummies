package com.sabahtalateh.jcstress_for_dummies.samples.counter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

/**
 * CounterTest.
 */
public class CounterTest {
    @Test
    public void testNonSyncCounter() throws Exception {
        int tries = 20;
        List<Long> increments = new ArrayList<>();
        for (int i = 0; i < tries; i++) {
            Counter counter = new NonSyncCounter();
            increments.add(increment100(counter));
        }

        System.out.println(increments);
        assertThat(increments.size(), not(1));
    }

    /**
     * @param counter counter.
     * @return incremented value.
     * @throws InterruptedException exception.
     */
    private static long increment100(Counter counter) throws InterruptedException {
        final int size = 100;

        Thread[] threads = new Thread[size];

        for (int i = 0; i < size; i++) {
            threads[i] = new Thread(new Incrementer(counter));
        }

        for (int i = 0; i < size; i++) {
            threads[i].start();
        }

        for (int i = 0; i < size; i++) {
            threads[i].join();
        }

        return counter.getValue();
    }

    /**
     * Incrementer.
     */
    private static class Incrementer implements Runnable {
        Counter counter;

        /**
         * @param counter counter.
         */
        Incrementer(Counter counter) {
            this.counter = counter;
        }

        /**
         * Run.
         */
        @Override
        public void run() {
            this.counter.increment();
        }
    }
}
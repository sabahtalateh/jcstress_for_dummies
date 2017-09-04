package com.sabahtalateh.jcstress_for_dummies.samples.counter;

import org.junit.Test;

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

        int tries = 2;
        Set<Long> increments = new TreeSet<>();
        for (int i = 0; i < tries; i++) {
            Counter counter = new NonSyncCounter();
            increments.add(increment100(counter));
        }

        System.out.println(increments);

    }

    /**
     * @param counter counter.
     * @return incremented value.
     * @throws InterruptedException exception.
     */
    private static long increment100(Counter counter) throws InterruptedException {
        final int iters = 100;

        Thread[] threads = new Thread[iters];

        for (int i = 0; i < iters; i++) {
            threads[i] = new Thread(new Incrementer(counter));
        }

        for (int i = 0; i < iters; i++) {
            threads[i].start();
        }

        for (int i = 0; i < iters; i++) {
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
            try {
                Thread.sleep(1);
                this.counter.increment();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
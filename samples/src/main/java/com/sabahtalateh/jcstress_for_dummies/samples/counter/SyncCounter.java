package com.sabahtalateh.jcstress_for_dummies.samples.counter;

/**
 * NonSyncCounter.
 */
public class SyncCounter implements Counter {
    volatile private long value = 0;

    @Override
    synchronized public void increment() {
        this.value++;
    }

    @Override
    public long getValue() {
        return this.value;
    }
}

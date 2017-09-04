package com.sabahtalateh.jcstress_for_dummies.samples.counter;

/**
 * NonSyncCounter.
 */
public class NonSyncCounter implements Counter {
    private long value = 0;

    @Override
    public void increment() {
        this.value++;
    }

    @Override
    public long getValue() {
        return this.value;
    }
}

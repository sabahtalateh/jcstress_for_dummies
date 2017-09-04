package com.sabahtalateh.jcstress_for_dummies.jcstress.counter;

import com.sabahtalateh.jcstress_for_dummies.samples.counter.Counter;
import com.sabahtalateh.jcstress_for_dummies.samples.counter.NonSyncCounter;
import com.sabahtalateh.jcstress_for_dummies.samples.counter.SyncCounter;
import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.J_Result;

/**
 * CounterNonSyncTest.
 */
@JCStressTest
@State
@Outcome(id = "3", expect = Expect.ACCEPTABLE)
public class CounterSyncTest {
    private Counter syncCounter = new SyncCounter();

    @Actor
    void actor1() {
        syncCounter.increment();
    }

    @Actor
    void actor2() {
        syncCounter.increment();
    }

    @Actor
    void actor3() {
        syncCounter.increment();
    }

    @Arbiter
    void arbiter(J_Result result) {
        result.r1 = syncCounter.getValue();
    }
}

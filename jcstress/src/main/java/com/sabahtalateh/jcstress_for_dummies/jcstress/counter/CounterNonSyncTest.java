package com.sabahtalateh.jcstress_for_dummies.jcstress.counter;

import com.sabahtalateh.jcstress_for_dummies.samples.counter.Counter;
import com.sabahtalateh.jcstress_for_dummies.samples.counter.NonSyncCounter;
import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.J_Result;

/**
 * CounterNonSyncTest.
 */
@JCStressTest
@State
@Outcome(id = "1", expect = Expect.ACCEPTABLE)
@Outcome(id = "2", expect = Expect.ACCEPTABLE)
@Outcome(id = "3", expect = Expect.ACCEPTABLE)
public class CounterNonSyncTest {
    private Counter nonSyncCounter = new NonSyncCounter();

    @Actor
    void actor1() {
        nonSyncCounter.increment();
    }

    @Actor
    void actor2() {
        nonSyncCounter.increment();
    }

    @Actor
    void actor3() {
        nonSyncCounter.increment();
    }

    @Arbiter
    void arbiter(J_Result result) {
        result.r1 = nonSyncCounter.getValue();
    }
}

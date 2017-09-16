package com.sabahtalateh.jcstress_for_dummies.jcstress.counter;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.ZZ_Result;

import java.util.BitSet;

/**
 * BitSetTest.
 */
@JCStressTest
@State
@Outcome(id = "true, true", expect = Expect.ACCEPTABLE)
public class BitSetTest {
    BitSet bitSet = new BitSet();

    @Actor
    void t1() {
        synchronized (bitSet) {
            bitSet.set(0);
        }
    }

    @Actor
    void t2() {
        synchronized (bitSet) {
            bitSet.set(1);
        }
    }

    @Arbiter
    void a(ZZ_Result result) {
        result.r1 = bitSet.get(0);
        result.r2 = bitSet.get(1);
    }

}

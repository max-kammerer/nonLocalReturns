package kotlin.inline.benchmark.plong;

import kotlin.inline.benchmark.Util;
import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.logic.BlackHole;

@State(Scope.Thread)
public class BoxLongTest {

    final int iterations = Util.ITERATION;

    //@GenerateMicroBenchmark
    public void testLong(BlackHole bh) {
        for (long i = 0; i < iterations; i++) {
            BoxLong result = longOperation(i);
            if (result.doNonLocalReturn) {
                bh.consume(result.nonLocalResult);
            } else {
                bh.consume(result.localResult);
            }
        }
    }

    public BoxLong longOperation(long i) {
        boolean b;
        long i1;
        long i2;
        if (i % 2 == 0) {
            b = false;
            i1 = i;
            i2 = 0;
        } else {
            b = true;
            i1 = 0;
            i2 = i * 2;
        }
        return new BoxLong(b, i1, i2);
    }
}

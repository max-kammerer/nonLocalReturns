package kotlin.inline.benchmark.plong;

import kotlin.inline.benchmark.Util;
import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.logic.BlackHole;

@State(Scope.Thread)
public class RefLongTest {

    final int iterations = Util.ITERATION;

    @GenerateMicroBenchmark
    public void testLong(BlackHole bh) {
        for (long i = 0; i < iterations; i++) {
            RefLong ref = new RefLong();
            long localResult = longOperation(i, ref);
            if (ref.doNonLocalReturn) {
                bh.consume(ref.nonLocalResult);
            } else {
                bh.consume(localResult);
            }
        }
    }

    public long longOperation(long i, RefLong ref) {
        if (i % 2 == 0) {
            return i;
        } else {
            ref.nonLocalResult = i * 2;
            ref.doNonLocalReturn = true;
            return 0;
        }
    }
}

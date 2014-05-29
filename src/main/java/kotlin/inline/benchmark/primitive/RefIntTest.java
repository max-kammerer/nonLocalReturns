package kotlin.inline.benchmark.primitive;

import kotlin.inline.benchmark.Util;
import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.logic.BlackHole;

@State(Scope.Thread)
public class RefIntTest {

    int iterations = Util.ITERATION;

    @GenerateMicroBenchmark
    public void testInt(BlackHole bh) {
        for (int i = 0; i < iterations; i++) {
            RefInt ref = new RefInt();
            int localResult = intOperation(i, ref);
            if (ref.doNonLocalReturn) {
                bh.consume(ref.nonLocalResult);
            } else {
                bh.consume(localResult);
            }
        }
    }

    public int intOperation(int i, RefInt ref) {
        if (i % 2 == 0) {
            return i;
        } else {
            ref.nonLocalResult = i * 2;
            ref.doNonLocalReturn = true;
            return 0;
        }
    }
}

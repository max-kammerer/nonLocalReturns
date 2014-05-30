package kotlin.inline.benchmark.pint;

import kotlin.inline.benchmark.Util;
import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.logic.BlackHole;

@State(Scope.Thread)
public class BoxIntTest {

    int iterations = Util.ITERATION;

    @GenerateMicroBenchmark
    public void testInt(BlackHole bh) {
        for (int i = 0; i < iterations; i++) {
            BoxInt result = intOperation(i);
            if (result.doNonLocalReturn) {
                bh.consume(result.nonLocalResult);
            } else {
                bh.consume(result.localResult);
            }
        }
    }

    public BoxInt intOperation(int i) {
        boolean b;
        int i1;
        int i2;
        if (i % 2 == 0) {
            b = false;
            i1 = i;
            i2 = 0;
        } else {
            b = true;
            i1 = 0;
            i2 = i * 2;
        }
        return new BoxInt(b, i1, i2);
//        if (i % 2 == 0) {
//            return new BoxInt(false, i, 0);
//        } else {
//            return new BoxInt(true, 0, i*2);
//        }
    }
}

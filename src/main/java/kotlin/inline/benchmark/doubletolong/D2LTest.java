package kotlin.inline.benchmark.doubletolong;

import kotlin.inline.benchmark.Util;
import kotlin.inline.benchmark.pint.RefInt;
import kotlin.inline.benchmark.plong.RefLong;
import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.logic.BlackHole;

@State(Scope.Thread)
public class D2LTest {

    final int iterations = Util.ITERATION;

    @GenerateMicroBenchmark
    public void testDouble2Long(BlackHole bh) {
        RefLong ref = new RefLong();
        for (int i = 0; i < iterations; i++) {
            double r = doubleOperation(i, ref);
            bh.consume(Double.longBitsToDouble(ref.nonLocalResult));
        }
    }

    @GenerateMicroBenchmark
    public void testDouble(BlackHole bh) {
        RefLong ref = new RefLong();
        for (int i = 0; i < iterations; i++) {
            double r = doubleOperation(i, ref);
            bh.consume(Double.longBitsToDouble(ref.nonLocalResult));
        }
    }

    public double doubleOperation(int i, RefLong ref) {
        ref.nonLocalResult = Double.doubleToLongBits(i );
        return 0;
    }

    public double doubleOperation(int i, RefDouble ref) {
        ref.nonLocalResult = i;
        return 0;
    }
}

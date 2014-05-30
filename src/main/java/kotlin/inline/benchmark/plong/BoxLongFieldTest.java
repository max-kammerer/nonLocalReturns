package kotlin.inline.benchmark.plong;

import kotlin.inline.benchmark.Util;
import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.logic.BlackHole;

@State(Scope.Thread)
public class BoxLongFieldTest {

    final int iterations = Util.ITERATION;

    @GenerateMicroBenchmark
    public void testLong(BlackHole bh) {
        for (long i = 0; i < iterations; i++) {
            BoxLongField result = longOperation(i);
            if (result.doNonLocalReturn) {
                bh.consume(result.nonLocalResult);
            } else {
                bh.consume(result.localResult);
            }
        }
    }

    public BoxLongField longOperation(long i) {
        BoxLongField boxLongField = new BoxLongField();
        if (i % 2 == 0) {
            boxLongField.doNonLocalReturn = false;
            boxLongField.localResult = i;
            return boxLongField;

        } else {
            boxLongField.doNonLocalReturn = true;
            boxLongField.nonLocalResult = i*2 ;
            return boxLongField;
        }
    }
}

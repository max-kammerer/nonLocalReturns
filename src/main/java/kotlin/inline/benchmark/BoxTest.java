package kotlin.inline.benchmark;

import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.logic.BlackHole;

@State(Scope.Thread)
public class BoxTest {

    int iterations = Util.ITERATION;

    @GenerateMicroBenchmark
    public void testInt(BlackHole bh) {
        for (int i = 0; i < iterations; i++) {
            Box<Integer, Integer> result = intOperation(i);
            if (result.doNonLocalReturn) {
                bh.consume(result.nonLocalResult.intValue());
            } else {
                bh.consume(result.localResult.intValue());
            }
        }
    }

    public Box<Integer, Integer> intOperation(int i) {
        if (i % 2 == 0) {
            return new Box<Integer, Integer>(false, i, 0);
        } else {
            return new Box<Integer, Integer>(true, 0, i*2);
        }
    }

    @GenerateMicroBenchmark
    public void testObject(BlackHole bh) {
        for (int i = 0; i < iterations; i++) {
            Box result = objectOperation(i);
            if (result.doNonLocalReturn) {
                bh.consume(result.nonLocalResult);
            } else {
                bh.consume(result.localResult);
            }
        }
    }

    public Box<String, String> objectOperation(int i) {
        if (i % 2 == 0) {
            return new Box<String, String>(false, String.valueOf(i), null);
        } else {
            return new Box<String, String>(true, null, String.valueOf(i*2));
        }
    }

}

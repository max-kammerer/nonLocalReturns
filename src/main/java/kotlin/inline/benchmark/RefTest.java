package kotlin.inline.benchmark;

import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.logic.BlackHole;

@State(Scope.Thread)
public class RefTest {

    final int iterations = Util.ITERATION;

    @GenerateMicroBenchmark
    public void testInt(BlackHole bh) {
        long result = 0;
        for (int i = 0; i < iterations; i++) {
            Ref<Integer> ref = new Ref<Integer>();
            int localResult = intOperation(i, ref);
            if (ref.doNonLocalReturn) {
                bh.consume(ref.nonLocalResult.intValue());
            } else {
                bh.consume(localResult);
            }
        }
    }

    public int intOperation(int i, Ref<Integer> ref) {
        if (i % 2 == 0) {
            return i;
        } else {
            ref.nonLocalResult = i * 2;
            ref.doNonLocalReturn = true;
            return 0;
        }
    }


    @GenerateMicroBenchmark
    public void testObject(BlackHole bh) {
        for (int i = 0; i < iterations; i++) {
            Ref<String> ref = new Ref<String>();
            String localResult = objectOperation(i, ref);
            if (ref.doNonLocalReturn) {
                bh.consume(ref.nonLocalResult);
            } else {
                bh.consume(localResult);
            }
        }
    }

    public String objectOperation(int i, Ref<String> ref) {
        if (i % 2 == 0) {
            return String.valueOf(i);
        } else {
            ref.doNonLocalReturn = true;
            ref.nonLocalResult = String.valueOf(i*2);
            return null;
        }
    }

}

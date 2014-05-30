package kotlin.inline.benchmark.pint;

import kotlin.inline.benchmark.Util;
import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.logic.BlackHole;

@State(Scope.Thread)
public class BoxIntFieldTest {

    int iterations = Util.ITERATION;

    @GenerateMicroBenchmark
    public void testInt(BlackHole bh) {
        for (int i = 0; i < iterations; i++) {
            BoxIntField result = intOperation(i);
            if (result.doNonLocalReturn) {
                bh.consume(result.nonLocalResult);
            } else {
                bh.consume(result.localResult);
            }
        }
    }

    public BoxIntField intOperation(int i) {
        BoxIntField boxIntField = new BoxIntField();
        if (i % 2 == 0) {
            boxIntField.doNonLocalReturn = false;
            boxIntField.localResult = i;
            return boxIntField;

        } else {
            boxIntField.doNonLocalReturn = true;
            boxIntField.nonLocalResult = i*2 ;
            return boxIntField;
        }
    }
}

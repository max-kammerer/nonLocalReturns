package kotlin.inline.benchmark;

import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.logic.BlackHole;

@State(Scope.Thread)
public class BoxFieldTest {

    int iterations = Util.ITERATION;

    @GenerateMicroBenchmark
    public void testInt(BlackHole bh) {
        for (int i = 0; i < iterations; i++) {
            BoxField<Integer, Integer> result = intOperation(i);
            if (result.doNonLocalReturn) {
                bh.consume(result.nonLocalResult.intValue());
            } else {
                bh.consume(result.localResult.intValue());
            }
        }
    }

    public BoxField<Integer, Integer> intOperation(int i) {
        BoxField<Integer, Integer> boxField = new BoxField<Integer, Integer>();
        if (i % 2 == 0) {
            boxField.localResult = i;
            return boxField;
        } else {
            boxField.doNonLocalReturn = true;
            boxField.nonLocalResult = i * 2;
            return boxField;
        }
    }

    @GenerateMicroBenchmark
    public void testObject(BlackHole bh) {
        for (int i = 0; i < iterations; i++) {
            BoxField result = objectOperation(i);
            if (result.doNonLocalReturn) {
                bh.consume(result.nonLocalResult);
            } else {
                bh.consume(result.localResult);
            }
        }
    }

    public BoxField<String, String> objectOperation(int i) {
        BoxField<String, String> boxField = new BoxField<String, String>();
        if (i % 2 == 0) {
            boxField.localResult = String.valueOf(i);
            return boxField;
        } else {
            boxField.doNonLocalReturn = true;
            boxField.nonLocalResult = String.valueOf(i*2);
            return boxField;
        }
    }

}
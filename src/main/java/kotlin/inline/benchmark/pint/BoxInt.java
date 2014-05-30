package kotlin.inline.benchmark.pint;

public class BoxInt {
    public final boolean doNonLocalReturn;

    public final int localResult;

    public final int nonLocalResult;

    public BoxInt(boolean doNonLocalReturn, int localResult, int nonLocalResult) {
        this.doNonLocalReturn = doNonLocalReturn;
        this.localResult = localResult;
        this.nonLocalResult = nonLocalResult;
    }
}

package kotlin.inline.benchmark.plong;

public class BoxLong {
    public final boolean doNonLocalReturn;

    public final long localResult;

    public final long nonLocalResult;

    public BoxLong(boolean doNonLocalReturn, long localResult, long nonLocalResult) {
        this.doNonLocalReturn = doNonLocalReturn;
        this.localResult = localResult;
        this.nonLocalResult = nonLocalResult;
    }
}

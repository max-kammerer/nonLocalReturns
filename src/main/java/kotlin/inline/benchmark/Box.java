package kotlin.inline.benchmark;

public class Box<L, N> {

    public final boolean doNonLocalReturn;

    public final L localResult;

    public final N nonLocalResult;

    public Box(boolean doNonLocalReturn, L localResult, N nonLocalResult) {
        this.doNonLocalReturn = doNonLocalReturn;
        this.localResult = localResult;
        this.nonLocalResult = nonLocalResult;
    }
}

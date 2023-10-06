package h09.basic;

public class IntegerFactory/*TODO 1.1*/ implements BasicFactory<Integer> {
    // TODO: H1.1 - remove if implemented
    private int start;
    private final int step;

    public IntegerFactory(int start, int step) {
        this.start = start;
        this.step = step;
    }

    @Override
    public Integer create() {
        int returned = start;
        start += step;
        return returned;
    }
}

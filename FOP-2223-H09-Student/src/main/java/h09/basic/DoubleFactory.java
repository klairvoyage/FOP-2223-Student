package h09.basic;

public class DoubleFactory/*TODO 1.2*/ implements BasicFactory<Double> {
    // TODO: H1.2 - remove if implemented
    private double current;
    private final double step;

    public DoubleFactory(double start, double step) {
        this.current = start;
        this.step = step;
    }

    @Override
    public Double create() {
        double returned = current;
        current += step;
        return returned;
    }
}

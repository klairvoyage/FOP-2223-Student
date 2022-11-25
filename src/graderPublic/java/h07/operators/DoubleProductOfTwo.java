package h07.operators;

import java.util.function.DoubleBinaryOperator;

public class DoubleProductOfTwo implements DoubleBinaryOperator {

    @Override
    public double applyAsDouble(double left, double right) {
        return left * right;
    }

}

package h09.operator;

import java.util.function.BinaryOperator;

/**
 * Class implementing the interface DoubleBinaryOperator, such that
 * the overwritten method applyAsDouble returns the bigger value of
 * the two given parameters.
 */
public class DoubleMaxOfTwoOperator/*TODO: H2.1*/ implements BinaryOperator<Double> {
    // TODO: H2.1 - remove if implemented
    /**
     * Returns the bigger value of the two given parameters.
     *
     * @param left  The first parameter.
     * @param right The second parameter.
     * @return The bigger value of the two.
     */
    @Override
    public Double apply(Double left, Double right) {
        // Get bigger value with a Math.max()-call
        return Math.max(left, right);

        // Optionally with conditional statement:
        // return left < right ? right : left;
    }
}

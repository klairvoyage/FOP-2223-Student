package h07.doubleoperators;

import java.util.function.DoubleBinaryOperator;

import static org.tudalgo.algoutils.student.Student.crash;

/**
 * Class implementing the interface DoubleBinaryOperator, such that
 * the overwritten method applyAsDouble returns the bigger value of
 * the two given parameters.
 */
public class DoubleMaxOfTwo implements DoubleBinaryOperator {

    /**
     * Returns the bigger value of the two given parameters.
     *
     * @param left      The first parameter.
     * @param right     The second parameter.
     * @return          The bigger value of the two.
     */
    @Override
    public double applyAsDouble(double left, double right) {
        // TODO: H2.3 - remove if implemented
        if (left>right) return left;
        else return right;
    }

}

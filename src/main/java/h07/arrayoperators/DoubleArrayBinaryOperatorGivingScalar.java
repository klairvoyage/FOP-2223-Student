package h07.arrayoperators;

/**
 * Functional interface for an array operation on two arrays, returning a scalar.
 */
public interface DoubleArrayBinaryOperatorGivingScalar {

    /**
     * Applies an operation on two given double-arrays and returns a double.
     *
     * @param left      First parameter.
     * @param right     Second parameter.
     * @return          The result.
     */
    double applyAsDoubleArray(double[] left, double[] right);

}

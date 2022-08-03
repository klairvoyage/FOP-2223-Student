package h07.arrayoperators;

/**
 * Functional interface for an array operation on two arrays, returning an array.
 */
public interface DoubleArrayBinaryOperatorGivingArray {

    /**
     * Applies an operation on two given double-arrays and returns the result.
     *
     * @param left      First parameter.
     * @param right     Second Parameter.
     * @return          The result.
     */
    double[] applyAsDoubleArray(double[] left, double[] right);
}

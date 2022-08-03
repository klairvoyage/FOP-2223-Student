package h07.arrayoperators;

/**
 * Functional interface for an array operation on one array, returning an array.
 */
public interface DoubleArrayUnaryOperatorGivingArray {

    /**
     * Applies an operation on a given double-array and returns the result.
     *
     * @param array     The array.
     * @return          The result.
     */
    double[] applyAsDoubleArray(double[] array);

}

package h07.arrayoperators;

import java.util.function.DoublePredicate;

/**
 * Class implementing the interface DoubleArrayUnaryOperatorGivingArray,
 * such that the overwritten method applyAsDoubleArray returns an array
 * only containing elements of the given array for which the predicate
 * of the class returns true.
 */
public class ReduceDoubleArray implements DoubleArrayUnaryOperatorGivingArray {

    /**
     * The predicate.
     */
    private final DoublePredicate predicate;

    /**
     * Constructor initializes the predicate for reduction.
     *
     * @param predicate     The predicate.
     */
    public ReduceDoubleArray(DoublePredicate predicate) {
        // Assign given parameter to predicate
        this.predicate = predicate;
    }

    /**
     * Returns an array containing all the elements of the given array
     * for which the predicate returns true.
     *
     * @param array     The array.
     * @return          Reduced array.
     */
    @Override
    public double[] applyAsDoubleArray(double[] array) {
        //TODO: Add crash-method
        return null;
    }
}

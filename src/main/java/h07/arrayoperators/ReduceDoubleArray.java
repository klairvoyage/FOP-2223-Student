package h07.arrayoperators;

import java.util.function.DoublePredicate;

import static org.tudalgo.algoutils.student.Student.crash;

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
     * Constructor initializes the predicate for the filter.
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
     * @return          The result of the filter.
     */
    @Override
    public double[] applyAsDoubleArray(double[] array) {
        return crash(); // TODO: H1.1 - remove if implemented
    }
}

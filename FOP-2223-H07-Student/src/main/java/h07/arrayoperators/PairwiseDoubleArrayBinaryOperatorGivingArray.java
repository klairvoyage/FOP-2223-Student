package h07.arrayoperators;

import java.util.function.DoubleBinaryOperator;

import static org.tudalgo.algoutils.student.Student.crash;

/**
 * Class implementing the interface DoubleArrayBinaryOperatorGivingArray,
 * such that the overwritten method applyAsDoubleArray returns an array
 * only containing as many elements as the smaller array of the given
 * ones.
 * Applies the operator to the elements in the old arrays and fills the
 * new array with the results.
 */
public class PairwiseDoubleArrayBinaryOperatorGivingArray implements DoubleArrayBinaryOperatorGivingArray {

    /**
     * The operator.
     */
    private final DoubleBinaryOperator OPERATOR;

    /**
     * Constructor initializes the operator.
     *
     * @param operator      The operator.
     */
    public PairwiseDoubleArrayBinaryOperatorGivingArray(DoubleBinaryOperator operator) {
        // Assign parameter to operator
        this.OPERATOR = operator;
    }

    /**
     * Returns an array containing as many elements as the smaller array of the given ones.
     * Each element is the result of the application of the operator on the elements of
     * the given arrays.
     *
     * @param left      The first array.
     * @param right     The second array.
     * @return          The result of the mapping.
     */
    @Override
    public double[] applyAsDoubleArray(double[] left, double[] right) {
        // TODO: H1.2 - remove if implemented
        if (left==null || right==null) return null;
        double[] yo;
        if (left.length<right.length) yo = new double[left.length];
        else yo = new double[right.length];
        for (int i=0;i<yo.length;i++) yo[i] = OPERATOR.applyAsDouble(left[i], right[i]);
        return yo;
        //test if (a) yo.length is accurate & (b) there's no operations on left & right
    }
}

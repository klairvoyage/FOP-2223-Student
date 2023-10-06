package h09.operator;

import java.util.function.BinaryOperator;

/**
 * Class implementing the interface BinaryOperator, such that
 * the overwritten method apply returns the bigger value of
 * the two given parameters.
 */
public class MaxOfTwoOperator/*TODO: H2.3*/<T extends Comparable<? super T>> implements BinaryOperator<T> {
    // TODO: H2.3 - remove if implemented
    /**
     * Returns the bigger value of the two given parameters.
     *
     * @param left  The first parameter.
     * @param right The second parameter.
     * @return The bigger value of the two.
     */
    @Override
    public T apply(T left, T right) {
        // Get bigger value with compareTo-method and conditional statement
        return left.compareTo(right)>0 ? left : right;
    }
}

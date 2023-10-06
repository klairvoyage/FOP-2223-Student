package h09.operator;

import java.util.function.BinaryOperator;

/**
 * Class implementing the interface DoubleBinaryOperator, such that
 * the overwritten method applyAsDouble sums the products of the in
 * the class encapsulated coefficients with the given parameters.
 */
public class DoubleSumWithCoefficientsOperator/*TODO: H2.1*/ implements BinaryOperator<Double> {
    // TODO: H2.1 - remove if implemented
    /**
     * First coefficient.
     */
    private final Double coeff1;

    /**
     * Second coefficient.
     */
    private final Double coeff2;

    /**
     * Constructor initializes the two coefficients.
     *
     * @param coeff1 The first coefficient.
     * @param coeff2 The second coefficient.
     */
    public DoubleSumWithCoefficientsOperator(Double coeff1, Double coeff2) {
        // Assign first parameter to first coefficient
        this.coeff1 = coeff1;

        // Assign second parameter to second coefficient
        this.coeff2 = coeff2;
    }

    /**
     * Returns the sum of the first parameter multiplied by the first coefficient
     * with the second parameter multiplied by the second coefficient.
     *
     * @param left the first function argument
     * @param right the second function argument
     * @return The sum of the products.
     */
    @Override
    public Double apply(Double left, Double right) {
        // Multiply first coefficient with first parameter and add it to the product
        // of the second coefficient with the second parameter
        return left * coeff1 + right * coeff2;
    }
}

package h09.operator;

import h09.basic.BasicBinaryOperations;

import java.util.function.BinaryOperator;

public class SumWithCoefficientsOperator/*TODO: H2.4*/<X,Y> implements BinaryOperator<X> {
    // TODO: H2.4 - remove if implemented
    /**
     * First coefficient.
     */
    private final Y coeff1;

    /**
     * Second coefficient.
     */
    private final Y coeff2;

    /**
     * Operator.
     */
    private final BasicBinaryOperations<X,Y> op;

    /**
     * Constructor initializes the two coefficients and operator.
     *
     * @param coeff1 The first coefficient.
     * @param coeff2 The second coefficient.
     * @param op     Operator.
     */
    public SumWithCoefficientsOperator(BasicBinaryOperations<X,Y> op, Y coeff1, Y coeff2) {
        // Assign second parameter to first coefficient
        this.coeff1 = coeff1;

        // Assign third parameter to second coefficient
        this.coeff2 = coeff2;

        // Assign first parameter to op
        this.op = op;
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
    public X apply(X left, X right) {
        // Multiply first coefficient with first parameter and add it to the product
        // of the second coefficient with the second parameter
        X result1 = op.mul(left, coeff1);
        X result2 = op.mul(right, coeff2);
        return op.add(result1, result2);
    }
}

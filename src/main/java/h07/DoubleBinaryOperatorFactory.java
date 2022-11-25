package h07;

import java.util.function.DoubleBinaryOperator;

import static org.tudalgo.algoutils.student.Student.crash;

/**
 * Class to build an operator with the buildOperator()-method
 */
public class DoubleBinaryOperatorFactory {

    /**
     * Returns a lambda-expression that is logically equivalent to the implementation
     * of applyAsDouble in DoubleSumWithCoefficientsOp. If the object encapsulates
     * a PairOfDoubleCoefficients, the coefficients for the expression are taken
     * directly from said object.
     *
     * @param obj   The object specifying the operation.
     * @return      The lambda-expression.
     */
    private static DoubleBinaryOperator doubleSumWithCoefficientsOpAsLambda(Object obj) {
        return crash(); // TODO: H3.1 - remove if implemented
    }

    /**
     * Returns a lambda-expression that is logically equivalent to the implementation
     * of applyAsDouble in EuclideanNorm.
     *
     * @return  The lambda-expression
     */
    private static DoubleBinaryOperator euclideanNormAsLambda() {
        return crash(); // TODO: H3.2 - remove if implemented
    }

    /**
     * Returns a lambda-expression that is logically equivalent to the implementation
     * of applyAsDouble in DoubleMaxOfTwo. If the object encapsulates a Boolean and if
     * said Boolean encapsulates true, a lambda-expression is returned. If it encapsulates
     * false a method reference is returned.
     *
     * @param obj   The object specifying the operation.
     * @return      The lambda-expression.
     */
    private static DoubleBinaryOperator doubleMaxOfTwoAsLambda(Object obj) {
        return crash(); // TODO: H3.3 - remove if implemented
    }

    /**
     * Returns a lambda-expression that is logically equivalent to the implementation
     * of applyAsDouble in ComposedDoubleBinaryOperator. If the object encapsulates
     * a TripleOfDoubleBinaryOperators, the operators for the expression are taken
     * directly from said object.
     *
     * @param obj   The object specifying the operation.
     * @return      The lambda-expression.
     */
    private static DoubleBinaryOperator composedDoubleBinaryOperatorAsLambda(Object obj) {
        return crash(); // TODO: H3.4 - remove if implemented
    }

    /**
     * Returns an operator depending on the given input parameters.
     *
     * @param str   The type of the operator.
     * @param obj   The (optional) features of the operator.
     * @param bool  The style of operator creation.
     * @return      The operator.
     */
    public static Object buildOperator(String str, Object obj, boolean bool) {
        return crash(); // TODO: H4.1 - remove if implemented
    }

    /**
     * Returns an operator that is created solely by using new.
     *
     * @param str   The type of the operator.
     * @param obj   The (optional) features of the operator.
     * @return      The operator.
     */
    private static Object buildOperatorWithNew(String str, Object obj) {
        return crash(); // TODO: H4.2 - remove if implemented
    }

    /**
     * Returns an operator that is created solely by using a lambda-expression.
     *
     * @param str   The type of the operator.
     * @param obj   The (optional) features of the operator.
     * @return      The operator.
     */
    private static Object buildOperatorWithLambda(String str, Object obj) {
        return crash(); // TODO: H4.3 - remove if implemented
    }

}

package h07;

import h07.doubleoperators.*;

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
        // TODO: H3.1 - remove if implemented
        if (obj instanceof PairOfDoubleCoefficients) return (double left, double right) -> { return left*((PairOfDoubleCoefficients) obj).coeff1+right*((PairOfDoubleCoefficients) obj).coeff2; };
        return null;
    }

    /**
     * Returns a lambda-expression that is logically equivalent to the implementation
     * of applyAsDouble in EuclideanNorm.
     *
     * @return  The lambda-expression
     */
    private static DoubleBinaryOperator euclideanNormAsLambda() {
        // TODO: H3.2 - remove if implemented
        return (double left, double right) -> { return Math.sqrt(left*left+right*right); };
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
        // TODO: H3.3 - remove if implemented
        if (obj instanceof Boolean) {
            if ((Boolean) obj) return (left, right) -> left<right ? right : left;
            else return Math::max;
        }
        return null;
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
        // TODO: H3.4 - remove if implemented
        if (obj instanceof TripleOfDoubleBinaryOperators)  return (left, right) -> ((TripleOfDoubleBinaryOperators) obj).operator3.applyAsDouble(((TripleOfDoubleBinaryOperators) obj).operator1.applyAsDouble(left, right), ((TripleOfDoubleBinaryOperators) obj).operator2.applyAsDouble(left, right));
        return null;
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
        // TODO: H4.1 - remove if implemented
        if (!str.contains("Coeffs") && !str.contains("Euclidean") && !str.contains("Max") && !str.contains("Composed")) return null;
        else if (bool) return buildOperatorWithNew(str, obj);
        else return buildOperatorWithLambda(str, obj);
    }

    /**
     * Returns an operator that is created solely by using new.
     *
     * @param str   The type of the operator.
     * @param obj   The (optional) features of the operator.
     * @return      The operator.
     */
    private static Object buildOperatorWithNew(String str, Object obj) {
        // TODO: H4.2 - remove if implemented
        switch (str) {
            case "Coeffs":
                if (obj instanceof PairOfDoubleCoefficients) return new DoubleSumWithCoefficientsOp(((PairOfDoubleCoefficients) obj).coeff1, ((PairOfDoubleCoefficients) obj).coeff2);
            case "Euclidean":
                return new EuclideanNorm();
            case "Max":
                return new DoubleMaxOfTwo();
            case "Composed":
                if (obj instanceof TripleOfDoubleBinaryOperators) return new ComposedDoubleBinaryOperator(((TripleOfDoubleBinaryOperators) obj).operator1, ((TripleOfDoubleBinaryOperators) obj).operator2, ((TripleOfDoubleBinaryOperators) obj).operator3);
            default:
                return null;
        }
    }

    /**
     * Returns an operator that is created solely by using a lambda-expression.
     *
     * @param str   The type of the operator.
     * @param obj   The (optional) features of the operator.
     * @return      The operator.
     */
    private static Object buildOperatorWithLambda(String str, Object obj) {
        // TODO: H4.3 - remove if implemented
        return switch (str) {
            case "Coeffs" -> doubleSumWithCoefficientsOpAsLambda(obj);
            case "Euclidean" -> euclideanNormAsLambda();
            case "Max" -> doubleMaxOfTwoAsLambda(obj);
            case "Composed" -> composedDoubleBinaryOperatorAsLambda(obj);
            default -> null;
        };
    }

}

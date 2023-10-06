package h07.h3;

import h07.doubleoperators.TripleOfDoubleBinaryOperators;

import java.util.function.DoubleBinaryOperator;

public class TripleOfDoubleBinaryOperatorsDescendant extends TripleOfDoubleBinaryOperators {
    /**
     * Constructor initializing all the operators.
     *
     * @param operator1 The first operator.
     * @param operator2 The second operator.
     * @param operator3 The third operator.
     */
    public TripleOfDoubleBinaryOperatorsDescendant(DoubleBinaryOperator operator1, DoubleBinaryOperator operator2, DoubleBinaryOperator operator3) {
        super(operator1, operator2, operator3);
    }

    // Only for tests

}

package h06;

import static org.tudalgo.algoutils.student.Student.crash;
import static h06.EvaluationResult.Type.*;

public class BracketExpression {

    private final char[] expression;

    public BracketExpression(String expression) {
        this.expression = expression.toCharArray();
    }

    public final EvaluationResult evaluate() {
        return evaluate(0);
    }

    public final EvaluationResult evaluate(int i) {
        return crash(); // TODO: H3 - remove if implemented
    }
}

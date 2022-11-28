package h06.student;

import h06.BracketExpression;
import h06.BracketExpressionData;
import h06.EvaluationResult;
import org.tudalgo.algoutils.tutor.general.reflections.BasicMethodLink;
import org.tudalgo.algoutils.tutor.general.reflections.BasicTypeLink;

import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertEquals;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.callObject;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.contextBuilder;
import static org.tudalgo.algoutils.tutor.general.match.BasicReflectionMatchers.sameTypes;
import static org.tudalgo.algoutils.tutor.general.match.BasicStringMatchers.identical;
import static org.tudalgo.algoutils.tutor.general.stringify.HTML.tt;

public class BracketExpressionStudent {

    public static final BasicTypeLink LINK_TO_BRACKET_EXPRESSION = BasicTypeLink.of(BracketExpression.class);

    public static final BasicMethodLink LINK_TO_EVALUATE = (BasicMethodLink) LINK_TO_BRACKET_EXPRESSION.getMethod(
        identical("evaluate").and(sameTypes(BasicTypeLink.of(int.class)))
    );

    private final BracketExpression instance;

    private final String expression;

    public BracketExpressionStudent(String expression) {
        this.expression = expression;
        this.instance = new BracketExpression(expression);
    }

    public static void test(BracketExpressionData data) {
        var expression = new BracketExpressionStudent(data.expression());
        expression.evaluate(data.index(), data.expectedNextIndex(), data.expectedType());
    }

    public final EvaluationResult evaluate() {
        return instance.evaluate();
    }

    public final EvaluationResult evaluate(int i) {
        return callObject(
            () -> instance.evaluate(i),
            contextBuilder()
                .add("expression", expression)
                .add("i", i)
                .subject(LINK_TO_EVALUATE)
                .build(),
            r -> "unexpected error"
        );
    }

    public final void evaluate(int i, int expectedIndex, EvaluationResult.Type expectedType) {
        var result = evaluate(i);
        var context = contextBuilder()
            .add("expression", tt(expression))
            .add("index", i)
            .subject(LINK_TO_EVALUATE)
            .build();
        var expected = String.format("[nextIndex=%d, type=%s]", expectedIndex, expectedType);
        var actual = String.format("[nextIndex=%d, type=%s]", result.nextIndex(), result.type());
        assertEquals(
            expected,
            actual,
            context,
            r -> "unexpected result"
        );
    }
}

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

    public static final BasicMethodLink LINK_TO_EVALUATE_1 = (BasicMethodLink) LINK_TO_BRACKET_EXPRESSION.getMethod(
        identical("evaluate").and(sameTypes())
    );

    public static final BasicMethodLink LINK_TO_EVALUATE_2 = (BasicMethodLink) LINK_TO_BRACKET_EXPRESSION.getMethod(
        identical("evaluate").and(sameTypes(BasicTypeLink.of(int.class)))
    );

    private final BracketExpression instance;

    private final String expression;

    public BracketExpressionStudent(String expression) {
        this.expression = expression;
        this.instance = new BracketExpression(expression);
    }

    public static void testEvaluate1(BracketExpressionData data) {
        var expression = new BracketExpressionStudent(data.expression());
        expression.evaluate1(data.expectedNextIndex(), data.expectedType());
    }

    public static void testEvaluate2(BracketExpressionData data) {
        var expression = new BracketExpressionStudent(data.expression());
        expression.evaluate2(data.index(), data.expectedNextIndex(), data.expectedType());
    }

    public final EvaluationResult evaluate1() {
        return instance.evaluate();
    }

    public final EvaluationResult evaluate1(int i) {
        return callObject(
            () -> instance.evaluate(i),
            contextBuilder()
                .add("expression", tt(expression))
                .add("index", i)
                .subject(LINK_TO_EVALUATE_2)
                .build(),
            r -> "unexpected error"
        );
    }

    public final void evaluate2(int i, int expectedIndex, EvaluationResult.Type expectedType) {
        var result = evaluate1(i);
        var context = contextBuilder()
            .add("expression", tt(expression))
            .add("index", i)
            .subject(LINK_TO_EVALUATE_2)
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

    public final void evaluate1(int expectedIndex, EvaluationResult.Type expectedType) {
        var result = evaluate1();
        var context = contextBuilder()
            .add("expression", tt(expression))
            .subject(LINK_TO_EVALUATE_1)
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

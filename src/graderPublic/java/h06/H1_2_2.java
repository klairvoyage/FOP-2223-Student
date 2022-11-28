package h06;

import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.tutor.general.assertions.Context;
import spoon.reflect.code.CtConditional;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtReturn;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtMethod;

import static h06.student.StrangeFunctionsStudent.linkToUnderstandable2;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.contextBuilder;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions4.assertIsCtElementOfType;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions4.assertIsOneStatement;

@TestForSubmission
public class H1_2_2 extends H1_2 {

    private static final CtMethod<?> CT = linkToUnderstandable2().getCtElement();

    private static final Context CONTEXT = contextBuilder().subject(linkToUnderstandable2()).build();
    CtConditional<?> if1, if2, if3;
    CtStatement ifBody1, ifBody2, ifBody3, elseBody;

    @Override
    Context context() {
        return CONTEXT;
    }

    @Override
    String functionName() {
        return CT.getSimpleName();
    }

    @Override
    String m() {
        return CT.getParameters().get(0).getSimpleName();
    }

    @Override
    String n() {
        return CT.getParameters().get(1).getSimpleName();
    }

    @Override
    CtExpression<?> condition1() {
        return if1().getCondition();
    }

    @Override
    CtExpression<?> condition2() {
        return if2().getCondition();
    }

    @Override
    CtExpression<?> condition3() {
        return if3().getCondition();
    }

    @Override
    CtExpression<?> return1() {
        return if1().getThenExpression();
    }

    @Override
    CtExpression<?> return2() {
        return if2().getThenExpression();
    }

    @Override
    CtExpression<?> return3() {
        return if3().getThenExpression();
    }

    @Override
    CtExpression<?> return4() {
        return if3().getElseExpression();
    }

    CtConditional<?> if1() {
        if (if1 != null) {
            return if1;
        }
        var statement = assertIsOneStatement(
            CT.getBody(),
            context(),
            r -> "method body does not consist of exactly one conditional statement"
        );
        var expression = assertIsCtElementOfType(
            CtReturn.class,
            statement,
            context(),
            r -> "method body does not consist of exactly one conditional statement"
        );
        return if1 = assertIsCtElementOfType(
            CtConditional.class,
            expression.getReturnedExpression(),
            context(),
            r -> "method body does not consist of an conditional statement"
        );
    }

    CtConditional<?> if2() {
        if (if2 != null) {
            return if2;
        }
        return if2 = assertIsCtElementOfType(
            CtConditional.class,
            if1().getElseExpression(),
            context(),
            r -> "first else-branch does not consists of an if statement"
        );
    }

    CtConditional<?> if3() {
        if (if3 != null) {
            return if3;
        }
        return if3 = assertIsCtElementOfType(
            CtConditional.class,
            if2().getElseExpression(),
            context(),
            r -> "second else-branch does not consists of an if statement"
        );
    }

    @Test
    @Override
    public void t6_t8_1() {
        super.t6_t8_1();
    }

    @Test
    @Override
    public void t6_t8_2() {
        super.t6_t8_2();
    }

    @Test
    @Override
    public void t6_t8_3() {
        super.t6_t8_3();
    }

    @Test
    @Override
    public void t7_t9_1() {
        super.t7_t9_1();
    }

    @Test
    @Override
    public void t7_t9_2() {
        super.t7_t9_2();
    }

    @Test
    @Override
    public void t10_1() {
        super.t10_1();
    }

    @Test
    @Override
    public void t10_2() {
        super.t10_2();
    }
}

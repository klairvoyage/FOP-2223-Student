package h06;

import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.tutor.general.assertions.Context;
import spoon.reflect.code.CtConditional;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtIf;
import spoon.reflect.code.CtReturn;
import spoon.reflect.declaration.CtMethod;

import static h06.student.StrangeFunctionsStudent.linkToStrangeFunction2;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.contextBuilder;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions4.assertIsCtElementOfType;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions4.assertIsOneStatement;

@SuppressWarnings("DuplicatedCode")
@TestForSubmission
public class H1_1_2 extends H1_1 {

    private static final CtMethod<?> MODEL = linkToStrangeFunction2().getCtElement();


    private CtIf firstIf;
    private CtConditional<?> conditional1;
    private CtConditional<?> conditional2;

    @Override
    Context context() {
        return contextBuilder().subject(linkToStrangeFunction2()).build();
    }

    @Override
    String functionName() {
        return MODEL.getSimpleName();
    }

    @Override
    String m() {
        return MODEL.getParameters().get(0).getSimpleName();
    }

    @Override
    String n() {
        return MODEL.getParameters().get(1).getSimpleName();
    }

    @Override
    CtExpression<?> condition1() {
        return conditional1().getCondition();
    }

    @Override
    CtExpression<?> condition2() {
        return conditional2().getCondition();
    }

    @Override
    CtExpression<?> return1() {
        return conditional1().getThenExpression();
    }

    @Override
    CtExpression<?> return2() {
        return conditional2().getThenExpression();
    }

    @Override
    CtExpression<?> return3() {
        return conditional2().getElseExpression();
    }

    CtConditional<?> conditional1() {
        if (conditional1 != null) {
            return conditional1;
        }
        var statement = assertIsOneStatement(
            MODEL.getBody(),
            context(),
            r -> "method does not consist of exactly one return statement"
        );
        var returnStatement = assertIsCtElementOfType(
            CtReturn.class,
            statement,
            context(),
            r -> "method does not consist of a return statement"
        );
        return conditional1 = assertIsCtElementOfType(
            CtConditional.class,
            returnStatement.getReturnedExpression(),
            context(),
            r -> "return statement does not return a conditional expression"
        );
    }

    CtConditional<?> conditional2() {
        if (conditional2 != null) {
            return conditional2;
        }
        return conditional2 = assertIsCtElementOfType(
            CtConditional.class,
            conditional1().getElseExpression(),
            context(),
            r -> "first else-branch does not consist of a conditional expression"
        );
    }

    @Test
    @Override
    public void t1_t3_1() {
        super.t1_t3_1();
    }

    @Test
    @Override
    public void t1_t3_2() {
        super.t1_t3_2();
    }

    @Test
    @Override
    public void t2_t4_1() {
        super.t2_t4_1();
    }

    @Test
    @Override
    public void t5_1() {
        super.t5_1();
    }

    @Test
    @Override
    public void t5_2() {
        super.t5_2();
    }

    @Test
    @Override
    public void t5_3() {
        super.t5_3();
    }
}

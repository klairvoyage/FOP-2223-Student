package h06;

import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.tutor.general.assertions.Context;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtIf;
import spoon.reflect.code.CtReturn;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtMethod;

import static h06.student.StrangeFunctionsStudent.linkToStrangeFunction1;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.contextBuilder;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions4.assertIsCtElementOfType;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions4.assertIsOneStatement;

@SuppressWarnings("DuplicatedCode")
@TestForSubmission
public class H1_1_1 extends H1_1 {

    private static final CtMethod<?> MODEL = linkToStrangeFunction1().getCtElement();
    private static final Context CONTEXT = contextBuilder().subject(linkToStrangeFunction1()).build();
    private CtIf firstIf;
    private CtExpression<?> return1;
    private CtExpression<?> return2;
    private CtExpression<?> return3;
    private CtStatement firstElseBody;
    private CtIf if2;
    private CtStatement secondIfBody;
    private CtStatement secondElseBody;

    @Override
    Context context() {
        return CONTEXT;
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
        return if1().getCondition();
    }

    @Override
    CtExpression<?> condition2() {
        return if2().getCondition();
    }

    @Override
    CtExpression<?> return1() {
        if (return1 != null) {
            return return1;
        }
        var thenStatement = assertIsOneStatement(
            if1().getThenStatement(),
            context(),
            r -> "first if-branch does not consists of exactly one return statement"
        );
        return return1 = assertIsCtElementOfType(
            CtReturn.class,
            thenStatement,
            context(),
            r -> "first if-branch does not consists of a return statement"
        ).getReturnedExpression();
    }

    @Override
    CtExpression<?> return2() {
        if (return2 != null) {
            return return2;
        }
        return return2 = assertIsCtElementOfType(
            CtReturn.class,
            if2Body(),
            context(),
            r -> "second if-branch does not consist of a return statement"
        ).getReturnedExpression();
    }

    @Override
    CtExpression<?> return3() {
        if (return3 != null) {
            return return3;
        }
        var elseStatement = assertIsOneStatement(
            if2().getElseStatement(),
            context(),
            r -> "second else-branch does not consist of exactly one return statement"
        );
        return return3 = assertIsCtElementOfType(
            CtReturn.class,
            elseStatement,
            context(),
            r -> "second else-branch does not consist of a return statement"
        ).getReturnedExpression();
    }

    CtIf if1() {
        if (firstIf != null) {
            return firstIf;
        }
        var statement = assertIsOneStatement(
            MODEL.getBody(),
            context(),
            r -> "method body does not consist of exactly one if statement"
        );
        return firstIf = assertIsCtElementOfType(
            CtIf.class,
            statement,
            context(),
            r -> "method body does not consist of an if statement"
        );
    }

    CtStatement firstElseBody() {
        if (firstElseBody != null) {
            return firstElseBody;
        }
        return this.firstElseBody = assertIsOneStatement(
            if1().getElseStatement(),
            context(),
            r -> "first else-branch does not consist of exactly one if statement"
        );
    }

    CtIf if2() {
        if (if2 != null) {
            return if2;
        }
        return if2 = assertIsCtElementOfType(
            CtIf.class,
            firstElseBody(),
            context(),
            r -> "first else-branch does not consist of exactly one if statement"
        );
    }

    CtStatement if2Body() {
        if (secondIfBody != null) {
            return secondIfBody;
        }
        return secondIfBody = assertIsOneStatement(
            if2().getThenStatement(),
            context(),
            r -> "second if statement does not consist of a single statement"
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

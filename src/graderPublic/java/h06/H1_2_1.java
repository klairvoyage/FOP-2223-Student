package h06;

import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.tutor.general.assertions.Context;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtIf;
import spoon.reflect.code.CtReturn;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtMethod;

import static h06.student.StrangeFunctionsStudent.linkToUnderstandable1;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.contextBuilder;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions4.assertIsCtElementOfType;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions4.assertIsOneStatement;

@TestForSubmission
public class H1_2_1 extends H1_2 {

    private static final CtMethod<?> CT = linkToUnderstandable1().getCtElement();

    private static final Context CONTEXT = contextBuilder().subject(linkToUnderstandable1()).build();
    CtIf if1, if2, if3;
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
        return assertIsCtElementOfType(
            CtReturn.class,
            ifBody1(),
            context(),
            r -> "first if-branch does not consists of a return statement"
        ).getReturnedExpression();
    }

    @Override
    CtExpression<?> return2() {
        return assertIsCtElementOfType(
            CtReturn.class,
            ifBody2(),
            context(),
            r -> "second if-branch does not consists of a return statement"
        ).getReturnedExpression();
    }

    @Override
    CtExpression<?> return3() {
        return assertIsCtElementOfType(
            CtReturn.class,
            ifBody3(),
            context(),
            r -> "third if-branch does not consists of a return statement"
        ).getReturnedExpression();
    }

    @Override
    CtExpression<?> return4() {
        return assertIsCtElementOfType(
            CtReturn.class,
            elseBody(),
            context(),
            r -> "fourth if-branch does not consists of a return statement"
        ).getReturnedExpression();
    }

    CtIf if1() {
        if (if1 != null) {
            return if1;
        }
        var statement = assertIsOneStatement(
            CT.getBody(),
            context(),
            r -> "method body does not consist of exactly one if statement"
        );
        return if1 = assertIsCtElementOfType(
            CtIf.class,
            statement,
            context(),
            r -> "method body does not consist of an if statement"
        );
    }

    CtIf if2() {
        if (if2 != null) {
            return if2;
        }
        var statement = assertIsOneStatement(
            if1().getElseStatement(),
            context(),
            r -> "first else-branch does not consists of exactly one if statement"
        );
        return if2 = assertIsCtElementOfType(
            CtIf.class,
            statement,
            context(),
            r -> "first else-branch does not consists of an if statement"
        );
    }

    CtIf if3() {
        if (if3 != null) {
            return if3;
        }
        var statement = assertIsOneStatement(
            if2().getElseStatement(),
            context(),
            r -> "second else-branch does not consists of exactly one if statement"
        );
        return if3 = assertIsCtElementOfType(
            CtIf.class,
            statement,
            context(),
            r -> "second else-branch does not consists of an if statement"
        );
    }

    CtStatement ifBody1() {
        if (ifBody1 != null) {
            return ifBody1;
        }
        return ifBody1 = assertIsOneStatement(
            if1().getThenStatement(),
            context(),
            r -> "first if-branch does not consist of exactly one statement"
        );
    }

    CtStatement ifBody2() {
        if (ifBody2 != null) {
            return ifBody2;
        }
        return ifBody2 = assertIsOneStatement(
            if2().getThenStatement(),
            context(),
            r -> "second if-branch does not consist of exactly one statement"
        );
    }

    CtStatement ifBody3() {
        if (ifBody3 != null) {
            return ifBody3;
        }
        return ifBody3 = assertIsOneStatement(
            if3().getThenStatement(),
            context(),
            r -> "third if-branch does not consist of exactly one statement"
        );
    }

    CtStatement elseBody() {
        if (elseBody != null) {
            return elseBody;
        }
        return elseBody = assertIsOneStatement(
            if3().getElseStatement(),
            context(),
            r -> "else-branch does not consist of exactly one statement"
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

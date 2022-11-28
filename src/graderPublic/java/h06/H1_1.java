package h06;

import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.tutor.general.assertions.Context;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.code.CtLiteral;
import spoon.reflect.code.CtVariableRead;

import java.util.stream.Stream;

import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.fail;

@SuppressWarnings("DuplicatedCode")
@TestForSubmission
public abstract class H1_1 {

    abstract Context context();

    abstract String functionName();

    abstract String m();

    abstract String n();

    abstract CtExpression<?> condition1();

    abstract CtExpression<?> condition2();

    abstract CtExpression<?> return1();

    abstract CtExpression<?> return2();

    abstract CtExpression<?> return3();


    @Test
    public void t1_t3_1() {
        if (condition1() instanceof CtBinaryOperator<?> binaryOperator &&
            binaryOperator.getKind().equals(BinaryOperatorKind.LT) &&
            binaryOperator.getLeftHandOperand() instanceof CtVariableRead<?> left &&
            binaryOperator.getRightHandOperand() instanceof CtVariableRead<?> right &&
            left.getVariable().getSimpleName().equals(m()) &&
            right.getVariable().getSimpleName().equals(n())
        ) {
            return;
        }
        fail(
            context(),
            r -> "first condition does not match"
        );
    }

    @Test
    public void t1_t3_2() {
        if (condition2() instanceof CtBinaryOperator<?> binaryOperator &&
            binaryOperator.getKind().equals(BinaryOperatorKind.GT) &&
            binaryOperator.getLeftHandOperand() instanceof CtLiteral<?> left &&
            left.getValue().equals(0)
        ) {
            if (binaryOperator.getRightHandOperand() instanceof CtBinaryOperator<?> binaryOperator2 &&
                binaryOperator2.getKind().equals(BinaryOperatorKind.MOD) &&
                binaryOperator2.getLeftHandOperand() instanceof CtVariableRead<?> left2 &&
                binaryOperator2.getRightHandOperand() instanceof CtVariableRead<?> right2 &&
                left2.getVariable().getSimpleName().equals(m()) &&
                right2.getVariable().getSimpleName().equals(n())
            ) {
                return;
            }
            if (binaryOperator.getRightHandOperand() instanceof CtInvocation<?> invocation &&
                invocation.getExecutable().getSimpleName().equals("floorMod") &&
                invocation.getArguments().get(0) instanceof CtVariableRead<?> arg1 &&
                invocation.getArguments().get(1) instanceof CtVariableRead<?> arg2 &&
                arg1.getVariable().getSimpleName().equals(m()) &&
                arg2.getVariable().getSimpleName().equals(n())
            ) {
                return;
            }
        }
        fail(
            context(),
            r -> "second condition does not match"
        );
    }

    @Test
    public void t2_t4_1() {
        if (return2() instanceof CtInvocation<?> invocation &&
            invocation.getExecutable().getSimpleName().equals(functionName())
        ) {
            return;
        }
        fail(
            context(),
            r -> "second if statement does not contain an invocation"
        );
    }

    @Test
    public void t5_1() {
        if (return1() instanceof CtVariableRead<?> variableRead &&
            variableRead.getVariable().getSimpleName().equals(m())) {
            return;
        }
        fail(
            context(),
            r -> "first if statement does not contain a correct return statement"
        );
    }

    @Test
    public void t5_2() {
        if (return2() instanceof CtInvocation<?> invocation &&
            invocation.getExecutable().getSimpleName().equals(functionName())) {
            if ((invocation.getArguments().get(0) instanceof CtBinaryOperator<?> param1) &&
                param1.getKind().equals(BinaryOperatorKind.MINUS) &&
                // left operand
                param1.getLeftHandOperand() instanceof CtVariableRead<?> left1 &&
                left1.getVariable().getSimpleName().equals(m()) &&
                invocation.getArguments().get(1) instanceof CtVariableRead<?> param2 &&
                param2.getVariable().getSimpleName().equals(n())
            ) {
                if (param1.getRightHandOperand() instanceof CtBinaryOperator<?> right1 &&
                    right1.getKind().equals(BinaryOperatorKind.MOD) &&
                    right1.getLeftHandOperand() instanceof CtVariableRead<?> left2 &&
                    right1.getRightHandOperand() instanceof CtVariableRead<?> right2 &&
                    left2.getVariable().getSimpleName().equals(m()) &&
                    right2.getVariable().getSimpleName().equals(n())
                ) {
                    return;
                }
                if (param1.getRightHandOperand() instanceof CtInvocation<?> right1 &&
                    right1.getExecutable().getSimpleName().equals("floorMod") &&
                    right1.getArguments().size() == 2 &&
                    right1.getArguments().get(0) instanceof CtVariableRead<?> left2 &&
                    right1.getArguments().get(1) instanceof CtVariableRead<?> right2 &&
                    left2.getVariable().getSimpleName().equals(m()) &&
                    right2.getVariable().getSimpleName().equals(n())
                ) {
                    return;
                }
            }
        }
        fail(
            context(),
            r -> "second if statement does not contain a correct return statement"
        );
    }

    @Test
    public void t5_3() {
        if (!(return3() instanceof CtBinaryOperator<?> binaryOperator) ||
            binaryOperator.getKind() != BinaryOperatorKind.DIV ||
            !(binaryOperator.getLeftHandOperand() instanceof CtVariableRead<?> l) ||
            !(binaryOperator.getRightHandOperand() instanceof CtVariableRead<?> r) ||
            !l.getVariable().getSimpleName().equals(m()) ||
            !r.getVariable().getSimpleName().equals(n()) ||
            (Stream.concat(l.getTypeCasts().stream(), r.getTypeCasts().stream()).noneMatch(c -> c.getSimpleName().equals("double")))
        ) {
            fail(
                context(),
                r -> "second else statement does not contain a correct return statement"
            );
        }
    }
}

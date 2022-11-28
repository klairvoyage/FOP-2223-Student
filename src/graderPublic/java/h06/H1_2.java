package h06;

import org.tudalgo.algoutils.tutor.general.assertions.Context;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.code.CtLiteral;
import spoon.reflect.code.CtVariableRead;

import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.fail;

@SuppressWarnings("DuplicatedCode")
public abstract class H1_2 {

    abstract Context context();

    abstract String functionName();

    abstract String m();

    abstract String n();

    abstract CtExpression<?> condition1();

    abstract CtExpression<?> condition2();

    abstract CtExpression<?> condition3();

    abstract CtExpression<?> return1();

    abstract CtExpression<?> return2();

    abstract CtExpression<?> return3();

    abstract CtExpression<?> return4();

    /**
     * 1 ;; Type: real real -> boolean
     * 2 ( define ( understandable? m n )
     * 3 ( cond
     * 4 [ ( and ( <= m 0 ) ( <= n 0 ) ) #t ]
     * 5 [ ( or ( <= m 0 ) ( <= n 0 ) ) #f ]
     * 6 [ ( < m n ) ( understandable? ( - m 1 ) ( + m ( * 2 n ) ) ) ]
     * 7 [ else ( understandable? m ( - m n ) ) ] ) )
     */

    public void t6_t8_1() {
        if (condition1() instanceof CtBinaryOperator<?> o &&
            o.getKind().equals(BinaryOperatorKind.AND) &&
            o.getLeftHandOperand() instanceof CtBinaryOperator<?> l &&
            o.getRightHandOperand() instanceof CtBinaryOperator<?> r &&
            l.getKind().equals(BinaryOperatorKind.LE) &&
            r.getKind().equals(BinaryOperatorKind.LE) &&
            l.getLeftHandOperand() instanceof CtVariableRead<?> ll &&
            l.getRightHandOperand() instanceof CtLiteral<?> lr &&
            r.getLeftHandOperand() instanceof CtVariableRead<?> rl &&
            r.getRightHandOperand() instanceof CtLiteral<?> rr &&
            ll.getVariable().getSimpleName().equals(m()) &&
            lr.getValue().equals(0) &&
            rl.getVariable().getSimpleName().equals(n()) &&
            rr.getValue().equals(0)
        ) {
            return;
        }
        fail(
            context(),
            r -> "1st condition does not match"
        );
    }

    public void t6_t8_2() {
        if (condition2() instanceof CtBinaryOperator<?> o &&
            o.getKind().equals(BinaryOperatorKind.OR) &&
            o.getLeftHandOperand() instanceof CtBinaryOperator<?> l &&
            o.getRightHandOperand() instanceof CtBinaryOperator<?> r &&
            l.getKind().equals(BinaryOperatorKind.LE) &&
            r.getKind().equals(BinaryOperatorKind.LE) &&
            l.getLeftHandOperand() instanceof CtVariableRead<?> ll &&
            l.getRightHandOperand() instanceof CtLiteral<?> lr &&
            r.getLeftHandOperand() instanceof CtVariableRead<?> rl &&
            r.getRightHandOperand() instanceof CtLiteral<?> rr &&
            ll.getVariable().getSimpleName().equals(m()) &&
            lr.getValue().equals(0) &&
            rl.getVariable().getSimpleName().equals(n()) &&
            rr.getValue().equals(0)


        ) {
            return;
        }
        fail(
            context(),
            r -> "2nd condition does not match"
        );
    }

    public void t6_t8_3() {
        if (condition3() instanceof CtBinaryOperator<?> o &&
            o.getKind().equals(BinaryOperatorKind.LT) &&
            o.getLeftHandOperand() instanceof CtVariableRead<?> l &&
            o.getRightHandOperand() instanceof CtVariableRead<?> r &&
            l.getVariable().getSimpleName().equals(m()) &&
            r.getVariable().getSimpleName().equals(n())
        ) {
            return;
        }
        fail(
            context(),
            r -> "3rd condition does not match"
        );
    }

    public void t7_t9_1() {
        if (return3() instanceof CtInvocation<?> i &&
            i.getExecutable().getSimpleName().equals(functionName())
        ) {
            var argument0 = i.getArguments().get(0);
            var argument1 = i.getArguments().get(1);
            if (argument0 instanceof CtBinaryOperator<?> o0 &&
                o0.getKind().equals(BinaryOperatorKind.MINUS) &&
                o0.getLeftHandOperand() instanceof CtVariableRead<?> l0 &&
                o0.getRightHandOperand() instanceof CtLiteral<?> r0 &&
                l0.getVariable().getSimpleName().equals(m()) &&
                r0.getValue().equals(1) &&
                argument1 instanceof CtBinaryOperator<?> o1 &&
                o1.getKind().equals(BinaryOperatorKind.PLUS) &&
                o1.getLeftHandOperand() instanceof CtVariableRead<?> l1 &&
                o1.getRightHandOperand() instanceof CtBinaryOperator<?> r1 &&
                l1.getVariable().getSimpleName().equals(m()) &&
                r1.getKind().equals(BinaryOperatorKind.MUL) &&
                r1.getLeftHandOperand() instanceof CtLiteral<?> rl1 &&
                r1.getRightHandOperand() instanceof CtVariableRead<?> rr1 &&
                rl1.getValue().equals(2) &&
                rr1.getVariable().getSimpleName().equals(n())
            ) {
                return;
            }
        }
        fail(
            context(),
            r -> "3rd return does not match"
        );
    }

    public void t7_t9_2() {
        if (return4() instanceof CtInvocation<?> i &&
            i.getExecutable().getSimpleName().equals(functionName())
        ) {
            var argument0 = i.getArguments().get(0);
            var argument1 = i.getArguments().get(1);
            if (argument0 instanceof CtVariableRead<?> l &&
                argument1 instanceof CtBinaryOperator<?> o &&
                o.getKind().equals(BinaryOperatorKind.MINUS) &&
                o.getLeftHandOperand() instanceof CtVariableRead<?> l1 &&
                o.getRightHandOperand() instanceof CtVariableRead<?> r1 &&
                l.getVariable().getSimpleName().equals(m()) &&
                l1.getVariable().getSimpleName().equals(m()) &&
                r1.getVariable().getSimpleName().equals(n())
            ) {
                return;
            }
        }
        fail(
            context(),
            r -> "4th return does not match"
        );
    }

    public void t10_1() {
        if (return1() instanceof CtLiteral<?> literal &&
            literal.getValue().equals(true)
        ) {
            return;
        }
        fail(
            context(),
            r -> "1st return does not match"
        );
    }

    public void t10_2() {
        if (return2() instanceof CtLiteral<?> literal &&
            literal.getValue().equals(false)
        ) {
            return;
        }
        fail(
            context(),
            r -> "2nd return does not match"
        );
    }
}

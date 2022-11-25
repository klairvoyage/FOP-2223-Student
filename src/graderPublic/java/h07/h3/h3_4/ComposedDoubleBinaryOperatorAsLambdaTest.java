package h07.h3.h3_4;

import h07.doubleoperators.PairOfDoubleCoefficients;
import h07.doubleoperators.TripleOfDoubleBinaryOperators;
import h07.h3.TripleOfDoubleBinaryOperatorsDescendant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.reflect.ClassTester;
import org.tudalgo.algoutils.reflect.MethodTester;
import spoon.Launcher;
import spoon.reflect.code.CtLambda;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtMethod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.DoubleBinaryOperator;

import static h07.Utils.*;
import static h07.h3.H3Utils.isShortLambda;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.*;

@TestForSubmission
public class ComposedDoubleBinaryOperatorAsLambdaTest {

    private static final String PATH_TO_CSV = "/h3/h3_4/PublicTestcases.csv";

    private static final ClassTester<?> FACTORY_CT = getClassTester("h07", "DoubleBinaryOperatorFactory");

    private static Method composedDoubleBinaryOperatorAsLambdaMethod;

    private boolean nullTested = false;

    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_CSV, numLinesToSkip = 1, delimiter = ';')
    void testResults(String op1, String op2, String op3, double left, double right, double expected) throws InvocationTargetException, IllegalAccessException {
        if (!nullTested) {
            testNullCases();
        }
        var context = contextBuilder()
            .add("First operator", op1)
            .add("Second operator", op2)
            .add("Third operator", op3)
            .add("Left value", left)
            .add("Right value", right)
            .build();
        DoubleBinaryOperator operator1 = convertStringToOperator(op1);
        DoubleBinaryOperator operator2 = convertStringToOperator(op2);
        DoubleBinaryOperator operator3 = convertStringToOperator(op3);

        call(
            () -> ((DoubleBinaryOperator) composedDoubleBinaryOperatorAsLambdaMethod.invoke(
                FACTORY_CT.getNewInstance(),
                new TripleOfDoubleBinaryOperators(operator1, operator2, operator3)
            )).applyAsDouble(left, right),
            context,
            r -> "Call resulted in an error"
        );

        double actual = (
            (DoubleBinaryOperator) composedDoubleBinaryOperatorAsLambdaMethod.invoke(
                FACTORY_CT.getNewInstance(),
                new TripleOfDoubleBinaryOperators(operator1, operator2, operator3)
            )
        ).applyAsDouble(left, right);

        assertEquals(
            expected,
            actual,
            context,
            r -> String.format(
                "Expected method to return %f but it returned %f instead!",
                expected,
                actual
            )
        );
    }

    void testNullCases() throws InvocationTargetException, IllegalAccessException {
        composedDoubleBinaryOperatorAsLambdaMethod = new MethodTester(
            FACTORY_CT.resolve(),
            "composedDoubleBinaryOperatorAsLambda"
        ).resolveMethod();
        composedDoubleBinaryOperatorAsLambdaMethod.trySetAccessible();

        call(
            () -> composedDoubleBinaryOperatorAsLambdaMethod.invoke(
                FACTORY_CT.getNewInstance(),
                (Object) null
            ),
            emptyContext(),
            r -> "Call resulted in an error"
        );

        PairOfDoubleCoefficients firstNullObj = new PairOfDoubleCoefficients(0, 0);

        assertNull(
            composedDoubleBinaryOperatorAsLambdaMethod.invoke(
                FACTORY_CT.getNewInstance(),
                firstNullObj
            ),
            contextBuilder().add("Object", firstNullObj).build(),
            r -> "Expected expression to return null when invoked with an object other than PairOfDoubleCoefficients or a subtype!"
        );

        assertNull(
            composedDoubleBinaryOperatorAsLambdaMethod.invoke(
                FACTORY_CT.getNewInstance(),
                (Object) null
            ),
            contextBuilder().add("Object", null).build(),
            r -> "Expected method to return null when invoked with null!"
        );

        TripleOfDoubleBinaryOperators firstNonNullObj = new TripleOfDoubleBinaryOperators(null, null, null);

        assertNotNull(
            composedDoubleBinaryOperatorAsLambdaMethod.invoke(
                FACTORY_CT.getNewInstance(),
                firstNonNullObj
            ),
            contextBuilder().add("Object", firstNonNullObj).build(),
            r -> "Expected method to not return null when invoked with an object of type TripleOfDoubleBinaryOperators!"
        );

        TripleOfDoubleBinaryOperatorsDescendant secondNonNullObj = new TripleOfDoubleBinaryOperatorsDescendant(null, null, null);

        assertNotNull(
            composedDoubleBinaryOperatorAsLambdaMethod.invoke(
                FACTORY_CT.getNewInstance(),
                secondNonNullObj
            ),
            contextBuilder().add("Object", secondNonNullObj).build(),
            r -> "Expected method to not return null when invoked with a subtype of TripleOfDoubleBinaryOperators!"
        );

        nullTested = true;
    }

    @Test
    void testLambdaExpression() {
        Launcher launcher = getSpoonLauncherForClass("h07", "DoubleBinaryOperatorFactory");
        CtMethod<?> method = getCtMethod(launcher, "composedDoubleBinaryOperatorAsLambda");

        assertTrue(
            method.getElements(element -> element instanceof CtLambda<?>).size() != 0,
            emptyContext(),
            r -> "Expected at least one lambda expression in the method but found none!"
        );

        for (CtElement lambda : method.getElements(element -> element instanceof CtLambda<?>)) {
            assertTrue(
                isShortLambda((CtLambda<?>) lambda),
                contextBuilder().add("Lambda expression found", lambda).build(),
                r -> "Expected a short lambda expression!"
            );
        }
    }

}

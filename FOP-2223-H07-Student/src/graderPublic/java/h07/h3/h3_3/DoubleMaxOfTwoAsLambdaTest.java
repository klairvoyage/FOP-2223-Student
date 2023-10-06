package h07.h3.h3_3;

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
import spoon.support.reflect.code.CtExecutableReferenceExpressionImpl;

import java.lang.reflect.Method;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.DoubleBinaryOperator;

import static h07.Utils.*;
import static h07.h3.H3Utils.isShortLambda;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.*;

@TestForSubmission
public class DoubleMaxOfTwoAsLambdaTest {

    private static final String PATH_TO_CSV = "/h3/h3_3/PublicTestcases.csv";

    private static final ClassTester<?> FACTORY_CT = getClassTester("h07", "DoubleBinaryOperatorFactory");

    private static Method doubleMaxOfTwoAsLambdaMethod;

    private static boolean nullTested = false;

    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_CSV, numLinesToSkip = 1, delimiter = ';')
    public void testResults(double left, double right, double expected) {
        if (!nullTested) {
            testNullCases();
        }

        var context = contextBuilder()
            .add("Left value", left)
            .add("Right value", right)
            .build();

        double actual = callObject(
            () -> ((DoubleBinaryOperator) doubleMaxOfTwoAsLambdaMethod.invoke(
                FACTORY_CT.getNewInstance(),
                ThreadLocalRandom.current().nextBoolean()
            )).applyAsDouble(left, right),
            context,
            r -> "Call resulted in an error"
        );

        assertEquals(
            expected,
            actual,
            context,
            r -> String.format(
                "Expected expression to return %f but it returned %f instead!",
                expected,
                actual
            )
        );
    }

    void testNullCases() {
        doubleMaxOfTwoAsLambdaMethod = new MethodTester(
            FACTORY_CT.resolve(),
            "doubleMaxOfTwoAsLambda"
        ).resolveMethod();
        doubleMaxOfTwoAsLambdaMethod.trySetAccessible();

        Object firstNullObj = new Object();

        assertCallNull(
            () -> doubleMaxOfTwoAsLambdaMethod.invoke(
                FACTORY_CT.getNewInstance(),
                firstNullObj
            ),
            contextBuilder().add("Object", firstNullObj).build(),
            r -> "Expected expression to return null when invoked with an object other than Boolean!"
        );

        assertCallNull(
            () -> doubleMaxOfTwoAsLambdaMethod.invoke(
                FACTORY_CT.getNewInstance(),
                (Object) null
            ),
            contextBuilder().add("Object", null).build(),
            r -> "Expected method to return null when invoked with null!"
        );

        nullTested = true;
    }

    @Test
    public void testLambdaExpression() {
        Launcher launcher = getSpoonLauncherForClass("h07", "DoubleBinaryOperatorFactory");
        CtMethod<?> method = getCtMethod(launcher, "doubleMaxOfTwoAsLambda");

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

    @Test
    public void testMethodReference() {
        Launcher launcher = getSpoonLauncherForClass("h07", "DoubleBinaryOperatorFactory");
        CtMethod<?> method = getCtMethod(launcher, "doubleMaxOfTwoAsLambda");

        assertTrue(
            !method.getElements(element -> element instanceof CtExecutableReferenceExpressionImpl).isEmpty(),
            emptyContext(),
            r -> "Expected the method to contain a lambda method reference of method max() in class Math!"
        );

        CtExecutableReferenceExpressionImpl<?, ?> maxCall = (CtExecutableReferenceExpressionImpl<?, ?>) method.getElements(
            element -> element instanceof CtExecutableReferenceExpressionImpl
        ).get(0);

        assertTrue(
            maxCall.toString().contains("max") && maxCall.toString().contains("Math"),
            emptyContext(),
            r -> String.format(
                "Expected the method to contain a lambda method reference of method max() in class Math but instead found the following method reference: %s!",
                maxCall
            )
        );
    }

}

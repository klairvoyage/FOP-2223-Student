package h07.h3.h3_1;

import h07.doubleoperators.PairOfDoubleCoefficients;
import h07.doubleoperators.TripleOfDoubleBinaryOperators;
import h07.h3.PairOfDoubleCoefficientsDescendant;
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
import static h07.h3.H3Utils.isStandardLambda;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.*;

@TestForSubmission
public class DoubleSumWithCoefficientsOpAsLambdaTest {

    private static final String PATH_TO_CSV = "/h3/h3_1/PublicTestcases.csv";

    private static final ClassTester<?> FACTORY_CT = getClassTester("h07", "DoubleBinaryOperatorFactory");

    private static Method doubleSumWithCoefficientsOpAsLambdaMethod;

    private boolean nullTested = false;

    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_CSV, numLinesToSkip = 1, delimiter = ';')
    void testResults(double coeff1, double coeff2, double left, double right, double expected) throws InvocationTargetException, IllegalAccessException {
        if (!nullTested) {
            testNullCases();
        }
        var context = contextBuilder()
            .add("First coefficient", coeff1)
            .add("Second coefficient", coeff2)
            .add("Left value", left)
            .add("Right value", right)
            .build();

        call(
            () -> ((DoubleBinaryOperator) doubleSumWithCoefficientsOpAsLambdaMethod.invoke(
                FACTORY_CT.getNewInstance(),
                new PairOfDoubleCoefficients(coeff1, coeff2)
            )).applyAsDouble(left, right),
            context,
            r -> "Call resulted in an error"
        );

        double actual = ((DoubleBinaryOperator) doubleSumWithCoefficientsOpAsLambdaMethod.invoke(FACTORY_CT.getNewInstance(), new PairOfDoubleCoefficients(coeff1, coeff2))).applyAsDouble(left, right);

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
        doubleSumWithCoefficientsOpAsLambdaMethod = new MethodTester(
            FACTORY_CT.resolve(),
            "doubleSumWithCoefficientsOpAsLambda"
        ).resolveMethod();
        doubleSumWithCoefficientsOpAsLambdaMethod.trySetAccessible();

        call(
            () -> doubleSumWithCoefficientsOpAsLambdaMethod.invoke(
                FACTORY_CT.getNewInstance(),
                (Object) null
            ),
            emptyContext(),
            r -> "Call resulted in an error"
        );

        TripleOfDoubleBinaryOperators firstNullObj = new TripleOfDoubleBinaryOperators(null, null, null);

        assertNull(
            doubleSumWithCoefficientsOpAsLambdaMethod.invoke(
                FACTORY_CT.getNewInstance(),
                firstNullObj
            ),
            contextBuilder().add("Object", firstNullObj).build(),
            r -> "Expected expression to return null when invoked with an object other than PairOfDoubleCoefficients or a subtype!"
        );

        assertNull(
            doubleSumWithCoefficientsOpAsLambdaMethod.invoke(
                FACTORY_CT.getNewInstance(),
                (Object) null
            ),
            contextBuilder().add("Object", null).build(),
            r -> "Expected method to return null when invoked with null!"
        );

        PairOfDoubleCoefficients firstNonNullObj = new PairOfDoubleCoefficients(0, 0);

        assertNotNull(
            doubleSumWithCoefficientsOpAsLambdaMethod.invoke(
                FACTORY_CT.getNewInstance(),
                firstNonNullObj
            ),
            contextBuilder().add("Object", firstNonNullObj).build(),
            r -> "Expected method to not return null when invoked with an object of type PairOfDoubleCoefficients!"
        );

        PairOfDoubleCoefficientsDescendant secondNonNullObj = new PairOfDoubleCoefficientsDescendant(0, 0);

        assertNotNull(
            doubleSumWithCoefficientsOpAsLambdaMethod.invoke(
                FACTORY_CT.getNewInstance(),
                secondNonNullObj
            ),
            contextBuilder().add("Object", secondNonNullObj).build(),
            r -> "Expected method to not return null when invoked with a subtype of PairOfDoubleCoefficients!"
        );

        nullTested = true;
    }

    @Test
    void testLambdaExpression() {
        Launcher launcher = getSpoonLauncherForClass("h07", "DoubleBinaryOperatorFactory");
        CtMethod<?> method = getCtMethod(launcher, "doubleSumWithCoefficientsOpAsLambda");

        assertTrue(
            method.getElements(element -> element instanceof CtLambda<?>).size() != 0,
            emptyContext(),
            r -> "Expected at least one lambda expression in the method but found none!"
        );

        for (CtElement lambda : method.getElements(element -> element instanceof CtLambda<?>)) {
            assertTrue(
                isStandardLambda((CtLambda<?>) lambda),
                contextBuilder().add("Lambda expression found", lambda).build(),
                r -> "Expected a standard lambda expression!"
            );
        }
    }

}

package h07.h3.h3_2;

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
public class EuclideanNormAsLambdaTest {

    private static final String PATH_TO_CSV = "/h3/h3_2/PublicTestcases.csv";

    private static final ClassTester<?> FACTORY_CT = getClassTester("h07", "DoubleBinaryOperatorFactory");

    private static Method euclideanNormAsLambdaMethod;

    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_CSV, numLinesToSkip = 1, delimiter = ';')
    void testResults(double x, double y, double expected) throws InvocationTargetException, IllegalAccessException {
        var context = contextBuilder()
            .add("x", x)
            .add("y", y)
            .build();

        euclideanNormAsLambdaMethod = new MethodTester(
            FACTORY_CT.resolve(),
            "euclideanNormAsLambda"
        ).resolveMethod();
        euclideanNormAsLambdaMethod.trySetAccessible();

        call(
            () -> ((DoubleBinaryOperator) euclideanNormAsLambdaMethod.invoke(
                FACTORY_CT.getNewInstance()
            )).applyAsDouble(x, y),
            context,
            r -> "Call resulted in an error"
        );

        double actual = ((DoubleBinaryOperator) euclideanNormAsLambdaMethod.invoke(FACTORY_CT.getNewInstance())).applyAsDouble(x, y);

        assertEquals(
            expected,
            actual,
            context,
            r -> String.format(
                "Expected the expression to return %f but it returned %f instead!",
                expected,
                actual
            )
        );
    }

    @Test
    void testLambdaExpression() {
        Launcher launcher = getSpoonLauncherForClass("h07", "DoubleBinaryOperatorFactory");
        CtMethod<?> method = getCtMethod(launcher, "euclideanNormAsLambda");

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

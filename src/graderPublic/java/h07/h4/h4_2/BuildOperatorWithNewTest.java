package h07.h4.h4_2;

import h07.DoubleBinaryOperatorFactory;
import h07.doubleoperators.*;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.reflect.ClassTester;
import org.tudalgo.algoutils.reflect.MethodTester;
import spoon.reflect.code.CtIf;
import spoon.reflect.code.CtSwitch;
import spoon.reflect.declaration.CtMethod;
import spoon.support.reflect.code.CtConstructorCallImpl;
import spoon.support.reflect.code.CtSwitchExpressionImpl;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

import static h07.Utils.*;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.*;

@TestForSubmission
public class BuildOperatorWithNewTest {

    private static final CtMethod<?> BUILD_OPERATOR_WITH_NEW_METHOD = getCtMethod(
        getSpoonLauncherForClass("h07", "DoubleBinaryOperatorFactory"),
        "buildOperatorWithNew"
    );

    private static final ClassTester<?> FACTORY_CT = getClassTester("h07", "DoubleBinaryOperatorFactory");

    private static Method buildOperatorWithNewMethod = new MethodTester(
        FACTORY_CT.resolve(),
        "buildOperatorWithNew"
    ).resolveMethod();

    @Test
    public void testReturnTypes() {
        buildOperatorWithNewMethod.trySetAccessible();
        assertCallTrue(
            () -> buildOperatorWithNewMethod.invoke(
                FACTORY_CT.getNewInstance(),
                "Coeffs",
                new PairOfDoubleCoefficients(0, 0)
            ) instanceof DoubleSumWithCoefficientsOp,
            contextBuilder().add("String", "Coeffs").add("Object", new PairOfDoubleCoefficients(0,0)).build(),
            r -> "Expected method to return an object of type \"DoubleSumWithCoefficientsOp\"!"
        );
        assertCallTrue(
            () -> buildOperatorWithNewMethod.invoke(
                FACTORY_CT.getNewInstance(),
                "Euclidean",
                null
            ) instanceof EuclideanNorm,
            contextBuilder().add("String", "Euclidean").add("Object", null).build(),
            r -> "Expected method to return an object of type \"EuclideanNorm\"!"
        );
        assertCallTrue(
            () -> buildOperatorWithNewMethod.invoke(
                FACTORY_CT.getNewInstance(),
                "Max",
                null
            ) instanceof DoubleMaxOfTwo,
            contextBuilder().add("String", "Max").add("Object", null).build(),
            r -> "Expected method to return an object of type \"DoubleMaxOfTwo\"!"
        );
        assertCallTrue(
            () -> buildOperatorWithNewMethod.invoke(
                FACTORY_CT.getNewInstance(),
                "Composed",
                new TripleOfDoubleBinaryOperators(null, null, null)
            ) instanceof ComposedDoubleBinaryOperator,
            contextBuilder().add("String", "Composed").add("Object", new TripleOfDoubleBinaryOperators(null, null, null)).add("Boolean", true).build(),
            r -> "Expected method to return an object of type \"ComposedDoubleBinaryOperator\"!"
        );
    }

    @Test
    public void testSwitch() {
        assertTrue(
            !BUILD_OPERATOR_WITH_NEW_METHOD.getElements(e -> e instanceof CtSwitch<?>).isEmpty() || !BUILD_OPERATOR_WITH_NEW_METHOD.getElements(e -> e instanceof CtSwitchExpressionImpl<?,?>).isEmpty(),
            emptyContext(),
            r -> "Expected method to contain a switch block or a switch expression!"
        );

        assertTrue(
            BUILD_OPERATOR_WITH_NEW_METHOD.getBody().getDirectChildren().stream().filter(e -> e instanceof CtIf).toList().isEmpty(),
            emptyContext(),
            r -> "Expected method to not use if-statements!"
        );
    }

    @Test
    public void testUseOfNew() {
        List<CtConstructorCallImpl<?>> constructorCalls = BUILD_OPERATOR_WITH_NEW_METHOD.getElements(Objects::nonNull);

        assertEquals(
            4,
            constructorCalls.size(),
            emptyContext(),
            r -> "Expected the method to create each of the four instances by calling the constructor with new!"
        );

        assertTrue(
            !constructorCalls.stream().filter(
                e -> e.getType().toString().equals("DoubleSumWithCoefficientsOp")
            ).toList().isEmpty(),
            emptyContext(),
            r -> "Expected the method to call the constructor of \"DoubleSumWithCoefficientsOp\"!"
        );

        assertTrue(
            !constructorCalls.stream().filter(
                e -> e.getType().toString().equals("EuclideanNorm")
            ).toList().isEmpty(),
            emptyContext(),
            r -> "Expected the method to call the constructor of \"EuclideanNorm\"!"
        );

        assertTrue(
            !constructorCalls.stream().filter(
                e -> e.getType().toString().equals("DoubleMaxOfTwo")
            ).toList().isEmpty(),
            emptyContext(),
            r -> "Expected the method to call the constructor of \"DoubleMaxOfTwo\"!"
        );

        assertTrue(
            !constructorCalls.stream().filter(
                e -> e.getType().toString().equals("ComposedDoubleBinaryOperator")
            ).toList().isEmpty(),
            emptyContext(),
            r -> "Expected the method to call the constructor of \"ComposedDoubleBinaryOperator\"!"
        );

    }

}

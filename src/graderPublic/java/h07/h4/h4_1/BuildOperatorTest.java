package h07.h4.h4_1;

import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import spoon.reflect.declaration.CtMethod;
import spoon.support.reflect.code.CtInvocationImpl;

import java.util.List;
import java.util.Objects;

import static h07.Utils.getCtMethod;
import static h07.Utils.getSpoonLauncherForClass;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertTrue;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.emptyContext;

@TestForSubmission
public class BuildOperatorTest {

    @Test
    void testMethodCalls() {
        CtMethod<?> buildOperatorMethod = getCtMethod(
            getSpoonLauncherForClass("h07", "DoubleBinaryOperatorFactory"),
            "buildOperator"
        );

        List<CtInvocationImpl<?>> invocations = buildOperatorMethod.getElements(
            Objects::nonNull
        );

        assertTrue(
            !invocations.isEmpty(),
            emptyContext(),
            r -> "Expected the method to call either \"buildOperatorWithNew\" or \"buildOperatorWithLambda\"!"
        );

        assertTrue(
            !invocations.stream().filter((e) -> e.toString().contains("buildOperatorWithNew")).toList().isEmpty(),
            emptyContext(),
            r -> "Expected the method to call \"buildOperatorWithNew\"!"
        );

        assertTrue(
            !invocations.stream().filter((e) -> e.toString().contains("buildOperatorWithLambda")).toList().isEmpty(),
            emptyContext(),
            r -> "Expected the method to call \"buildOperatorWithLambda\"!"
        );
    }

}

package h07.h4.h4_1;

import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import spoon.reflect.declaration.CtMethod;
import spoon.support.reflect.code.CtInvocationImpl;

import java.util.List;
import java.util.Objects;

import static h07.Utils.getCtMethod;
import static h07.Utils.getSpoonLauncherForClass;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.*;

@TestForSubmission
public class BuildOperatorTest {

    @Test
    public void testMethodCalls() {
        CtMethod<?> buildOperatorMethod = null;
        try {
            buildOperatorMethod = getCtMethod(getSpoonLauncherForClass("h07", "DoubleBinaryOperatorFactory"), "buildOperator");
        } catch (Exception e) {
            fail(
                emptyContext(),
                r -> "Could not find method \"buildOperator\"in class \"BuildOperator\". Did you modify the method signature?"
            );
        }

        assert buildOperatorMethod != null;
        List<CtInvocationImpl<?>> invocations = buildOperatorMethod.getElements(Objects::nonNull);

        assertTrue(
            !invocations.isEmpty(),
            emptyContext(),
            r -> "Expected the method to call either \"buildOperatorWithNew\" or \"buildOperatorWithLambda\" but found no method invocations at all!"
        );

        assertTrue(
            !invocations.stream().filter((e) -> e.toString().contains("buildOperatorWithNew")).toList().isEmpty(),
            contextBuilder().add("Found invocations", invocations).build(),
            r -> "Expected the method to call \"buildOperatorWithNew\"!"
        );

        assertTrue(
            !invocations.stream().filter((e) -> e.toString().contains("buildOperatorWithLambda")).toList().isEmpty(),
            contextBuilder().add("Found invocations", invocations).build(),
            r -> "Expected the method to call \"buildOperatorWithLambda\"!"
        );
    }

}

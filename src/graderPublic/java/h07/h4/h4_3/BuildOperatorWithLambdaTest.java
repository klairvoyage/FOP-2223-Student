package h07.h4.h4_3;

import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtMethod;
import spoon.support.reflect.code.*;

import java.util.List;
import java.util.Objects;

import static h07.Utils.getCtMethod;
import static h07.Utils.getSpoonLauncherForClass;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.*;

@TestForSubmission
public class BuildOperatorWithLambdaTest {

    private static final CtMethod<?> BUILD_OPERATOR_WITH_LAMBDA_METHOD = getCtMethod(
        getSpoonLauncherForClass("h07", "DoubleBinaryOperatorFactory"),
        "buildOperatorWithLambda"
    );

    private boolean coeffCaseCorrect = false;
    private boolean euclideanCaseCorrect = false;
    private boolean maxCaseCorrect = false;
    private boolean composedCaseCorrect = false;

    @Test
    void testReturnTypes() {
        List<CtCaseImpl<?>> caseList = BUILD_OPERATOR_WITH_LAMBDA_METHOD.getElements(Objects::nonNull);

        for (CtCaseImpl<?> caze : caseList) {
            if (getLiteralOfCase(caze) == null) {
                continue;
            }

            if (
                Objects.requireNonNull(getLiteralOfCase(caze)).toString().contains("Coeff")
                    && getYieldStatementOfCase(caze).toString().contains("doubleSumWithCoefficientsOpAsLambda")
            ) {
                coeffCaseCorrect = true;
            }

            if (
                Objects.requireNonNull(getLiteralOfCase(caze)).toString().contains("Euclidean")
                    && getYieldStatementOfCase(caze).toString().contains("euclideanNormAsLambda")
            ) {
                euclideanCaseCorrect = true;
            }

            if (
                Objects.requireNonNull(getLiteralOfCase(caze)).toString().contains("Max")
                    && getYieldStatementOfCase(caze).toString().contains("doubleMaxOfTwoAsLambda")
            ) {
                maxCaseCorrect = true;
            }

            if (
                Objects.requireNonNull(getLiteralOfCase(caze)).toString().contains("Composed")
                    && getYieldStatementOfCase(caze).toString().contains("composedDoubleBinaryOperatorAsLambda")
            ) {
                composedCaseCorrect = true;
            }
        }

        assertTrue(
            coeffCaseCorrect && euclideanCaseCorrect && maxCaseCorrect && composedCaseCorrect,
            contextBuilder().add("Found switch-cases", caseList).build(),
            r -> "Expected method to contain a switch-case for every operator!"
        );
    }

    @Test
    void testSwitch() {
        assertTrue(
            !BUILD_OPERATOR_WITH_LAMBDA_METHOD.getElements(e -> e instanceof CtSwitchExpressionImpl<?,?>).isEmpty(),
            emptyContext(),
            r -> "Expected method to contain a switch block!"
        );
    }

    @Test
    void checkForUseOfNew() {
        List<CtConstructorCallImpl<?>> constructorCalls = BUILD_OPERATOR_WITH_LAMBDA_METHOD.getBody().getElements(Objects::nonNull);
        assertTrue(
            constructorCalls.isEmpty(),
            emptyContext(),
            r -> String.format(
                "Expected method to not create objects with \"new\" but it calls the following constructors: %s!",
                constructorCalls
            )
        );
    }

    private CtLiteralImpl<?> getLiteralOfCase(CtCaseImpl<?> caze) {
        return caze.getDirectChildren().stream().filter(e -> e instanceof CtLiteralImpl).toList().isEmpty() ?
            null : (CtLiteralImpl<?>) caze.getDirectChildren().stream().filter(e -> e instanceof CtLiteralImpl).toList().get(0);
    }

    private CtYieldStatementImpl getYieldStatementOfCase(CtCaseImpl<?> caze) {
        return (CtYieldStatementImpl) caze.getDirectChildren().stream().filter(e -> e instanceof CtYieldStatementImpl).toList().get(0);

    }

}

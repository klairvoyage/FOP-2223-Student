package h07.h2.h2_1;

import h07.doubleoperators.DoubleSumWithCoefficientsOp;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.*;

@TestForSubmission
public class DoubleSumWithCoefficientsOpTest {

    private static final String PATH_TO_CSV = "/h2/h2_1/PublicTestcases.csv";

    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_CSV, numLinesToSkip = 1, delimiter = ';')
    void testResults(double coeff1, double coeff2, double left, double right, double expected) {
        var context = contextBuilder()
            .add("First coefficient", coeff1)
            .add("Second coefficient", coeff2)
            .add("Left value", left)
            .add("Right value", right)
            .build();
        DoubleSumWithCoefficientsOp op = new DoubleSumWithCoefficientsOp(coeff1, coeff2);

        call(
            () -> op.applyAsDouble(left, right),
            context,
            r -> "Call resulted in an error"
        );

        double actual = op.applyAsDouble(left, right);

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

}

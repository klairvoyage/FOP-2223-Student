package h07.h2.h2_2;

import h07.doubleoperators.EuclideanNorm;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.*;

@TestForSubmission
public class EuclideanNormTest {

    private static final String PATH_TO_CSV = "/h2/h2_2/PublicTestcases.csv";

    private static final EuclideanNorm EUCLIDEAN_NORM = new EuclideanNorm();

    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_CSV, numLinesToSkip = 1, delimiter = ';')
    void testResults(double x, double y, double expected) {
        var context = contextBuilder()
            .add("x", x)
            .add("y", y)
            .build();

        call(
            () -> EUCLIDEAN_NORM.applyAsDouble(x, y),
            context,
            r -> "Call resulted in an error"
        );

        double actual = EUCLIDEAN_NORM.applyAsDouble(x, y);

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

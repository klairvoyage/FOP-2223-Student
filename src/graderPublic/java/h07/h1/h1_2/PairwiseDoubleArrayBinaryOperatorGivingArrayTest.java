package h07.h1.h1_2;

import h07.arrayoperators.PairwiseDoubleArrayBinaryOperatorGivingArray;
import h07.operators.DoubleSumOfTwo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.util.Arrays;
import java.util.function.DoubleBinaryOperator;

import static h07.Utils.convertStringToOperator;
import static h07.h1.H1Utils.convertStringToDoubleArray;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.*;

@TestForSubmission
public class PairwiseDoubleArrayBinaryOperatorGivingArrayTest {

    private static final String PATH_TO_CSV = "/h1/h1_2/PublicTestcases.csv";

    @Test
    void testNullInput() {
        PairwiseDoubleArrayBinaryOperatorGivingArray operator = new PairwiseDoubleArrayBinaryOperatorGivingArray(new DoubleSumOfTwo());

        call(
            () -> operator.applyAsDoubleArray(null, null),
            contextBuilder()
                .add("Operator", "DoubleSumOfTwo (see /src/graderPublic/java/h07/operators)")
                .add("Left Value", null)
                .add("Right Value", null)
                .build(),
            r -> "Call resulted in an error"
        );

        assertNull(
            operator.applyAsDoubleArray(null, new double[0]),
            contextBuilder()
                .add("Operator", "DoubleSumOfTwo (see /src/graderPublic/java/h07/operators)")
                .add("Left Value", null)
                .add("Right Value", "Empty Array")
                .build(),
            r -> "Expected the method to return null when one of the given arguments is null!"
        );
        assertNull(
            operator.applyAsDoubleArray(new double[0], null),
            contextBuilder()
                .add("Operator", "DoubleSumOfTwo (see /src/graderPublic/java/h07/operators)")
                .add("Left Value", "Empty Array")
                .add("Right Value", null)
                .build(),
            r -> "Expected the method to return null when one of the given arguments is null!"
        );
        assertNull(
            operator.applyAsDoubleArray(null, null),
            contextBuilder()
                .add("Operator", "DoubleSumOfTwo (see /src/graderPublic/java/h07/operators)")
                .add("Left Value", null)
                .add("Right Value", null)
                .build(),
            r ->"Expected the method to return null when both of the given arguments are null!"
        );
    }

    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_CSV, numLinesToSkip = 1, delimiter = ';')
    void testResult(String op1, String leftArray, String rightArray, String result) {
        DoubleBinaryOperator operator1 = convertStringToOperator(op1);

        double[] left = convertStringToDoubleArray(leftArray);
        double[] right = convertStringToDoubleArray(rightArray);
        double[] expected = convertStringToDoubleArray(result);

        var context = contextBuilder()
            .add("Operator", op1)
            .add("Left Array", Arrays.toString(left))
            .add("Right Array", Arrays.toString(right))
            .build();

        PairwiseDoubleArrayBinaryOperatorGivingArray operator = new PairwiseDoubleArrayBinaryOperatorGivingArray(operator1);

        call(
            () -> operator.applyAsDoubleArray(left, right),
            context,
            r -> "Call resulted in an error"
        );

        double[] actual = operator.applyAsDoubleArray(left, right);

        for (int i = 0; i < expected.length; i++) {
            int finalI = i;
            assertEquals(
                expected[i],
                actual[i],
                context,
                r -> String.format(
                    "The resulting array %s differs from the expected outcome %s at index %d!",
                    Arrays.toString(expected),
                    Arrays.toString(actual),
                    finalI
                )
            );
        }
    }
}

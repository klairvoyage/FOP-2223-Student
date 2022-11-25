package h07.h1.h1_3;

import h07.arrayoperators.PairwiseDoubleArrayBinaryOperatorGivingScalar;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import spoon.Launcher;
import spoon.reflect.code.CtLoop;

import java.util.Arrays;

import static h07.Utils.convertStringToOperator;
import static h07.Utils.getSpoonLauncherForClass;
import static h07.h1.H1Utils.convertStringToDoubleArray;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.*;

@TestForSubmission
public class PairwiseDoubleArrayBinaryOperatorGivingScalarTest {

    private static final String PATH_TO_CSV = "/h1/h1_3/PublicTestcases.csv";

    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_CSV, numLinesToSkip = 1, delimiter = ';')
    void testResult(String op1, String op2, String init, String leftArray, String rightArray, String result) {
        PairwiseDoubleArrayBinaryOperatorGivingScalar operator = new PairwiseDoubleArrayBinaryOperatorGivingScalar(
            convertStringToOperator(op1),
            convertStringToOperator(op2),
            Double.parseDouble(init)
        );

        double[] left = convertStringToDoubleArray(leftArray);
        double[] right = convertStringToDoubleArray(rightArray);
        double expected = Double.parseDouble(result);

        var context = contextBuilder()
            .add("First operator", op1)
            .add("Second operator", op2)
            .add("Init", Double.parseDouble(init))
            .add("Left Array", Arrays.toString(left))
            .add("Right Array", Arrays.toString(right))
            .build();

        PairwiseDoubleArrayBinaryOperatorGivingScalar finalOperator = operator;
        call(
            () -> finalOperator.applyAsDoubleArray(left, right),
            context,
            r -> "Call resulted in an error"
        );

        operator = new PairwiseDoubleArrayBinaryOperatorGivingScalar(
            convertStringToOperator(op1),
            convertStringToOperator(op2),
            Double.parseDouble(init)
        );
        double actual = operator.applyAsDoubleArray(left, right);

        assertEquals(
            expected,
            actual,
            context,
            r -> String.format(
                "Expected the method to return %f but it actually returned %f!",
                expected,
                actual
            )
        );
    }

    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_CSV, numLinesToSkip = 1, delimiter = ';')
    void checkRecursion(String op1, String op2, String init, String leftArray, String rightArray) {
        PairwiseDoubleArrayBinaryOperatorGivingScalar operator = new PairwiseDoubleArrayBinaryOperatorGivingScalar(
            convertStringToOperator(op1),
            convertStringToOperator(op2),
            Double.parseDouble(init)
        );

        PairwiseDoubleArrayBinaryOperatorGivingScalar operatorSpy = Mockito.spy(operator);

        var context = contextBuilder()
            .add("Operator", op1)
            .add("Operator", op2)
            .add("Init", Double.parseDouble(init))
            .add("Left Array", Arrays.toString(convertStringToDoubleArray(leftArray)))
            .add("Right Array", Arrays.toString(convertStringToDoubleArray(rightArray)))
            .build();

        call(
            () -> operatorSpy.applyAsDoubleArray(convertStringToDoubleArray(leftArray), convertStringToDoubleArray(rightArray)),
            context,
            r -> "Call resulted in an error"
        );

        call(
            () -> Mockito.verify(operatorSpy, Mockito.atMostOnce()).applyAsDoubleArray(Mockito.any(), Mockito.any()),
            context,
            r -> "No recursion allowed"
        );
    }
}

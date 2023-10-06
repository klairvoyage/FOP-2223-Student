package h08;

import h08.calculation.ArrayCalculatorWithRuntimeExceptions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junitpioneer.jupiter.json.JsonClasspathSource;
import org.junitpioneer.jupiter.json.Property;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.tutor.general.conversion.ArrayConverter;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

// DONE
@TestForSubmission
public class TutorTests_H1_1 {
    @ParameterizedTest
    @JsonClasspathSource("TutorTests_H1_1-addUpCalculatesSumCorrectly.json")
    public void addUpCalculatesSumCorrectly(@Property("testArray") @ConvertWith(ArrayConverter.Double2D.class) Double[][] testArray,
                                            @Property("expectedSum") double expectedSum) {
        var sut = new ArrayCalculatorWithRuntimeExceptions();
        double[][] doubles = new double[testArray.length][];
        for (int i = 0; i < doubles.length; i++) {
            doubles[i] = Arrays.stream(testArray[i]).mapToDouble(Double::doubleValue).toArray();
        }
        var actual = assertDoesNotThrow(() -> sut.addUp(doubles, Double.MAX_VALUE),
            "Die Funktion addUp wirft bei korrekten Eingabewerten eine unerwartete Exception.");
        assertEquals(expectedSum, actual, "Die Funktion addUp berechnet die Summe nicht korrekt.");
    }
}

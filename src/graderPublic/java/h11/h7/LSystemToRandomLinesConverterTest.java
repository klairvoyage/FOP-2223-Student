package h11.h7;

import h11.Random;
import h11.providers.LSystemToRandomLinesConverter;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junitpioneer.jupiter.json.JsonClasspathSource;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.tutor.general.assertions.Assertions2;
import org.tudalgo.algoutils.tutor.general.assertions.Context;

import java.util.List;

@TestForSubmission
public class LSystemToRandomLinesConverterTest {

    @ParameterizedTest
    @JsonClasspathSource("h11/h7/random-spacing-test.json")
    @Tag("H7")
    void testRandomSpacing(RandomSpacesTestCase testCase) throws NoSuchMethodException {
        var random = new Random(testCase.seed());
        var converter = new LSystemToRandomLinesConverter(random);
        Assertions2.assertEquals(testCase.spaces(), converter.generateSpaces(), getSpacingContext(testCase), result ->
            "The String of random spaces was not returned correctly");
    }

    private Context getSpacingContext(RandomSpacesTestCase testCase) throws NoSuchMethodException {
        return Assertions2.contextBuilder()
            .subject(LSystemToRandomLinesConverter.class.getMethod("generateSpaces"))
            .property("seed", testCase.seed())
            .build();
    }

    @ParameterizedTest
    @JsonClasspathSource("h11/h7/lsystem-as-lines-test.json")
    @Tag("H7")
    void testLSystemAsLines(LSystemAsLinesTestCase testCase) throws NoSuchMethodException {
        var random = new Random(testCase.seed());
        var converter = new LSystemToRandomLinesConverter(random);
        var actual = converter.lSystemAsLines(testCase.projections()).toList();
        Assertions2.assertEquals(testCase.lines(), actual, getLinesContext(testCase), result ->
            "The Stream of lines for the given L-System was not returned correctly");
    }

    private Context getLinesContext(LSystemAsLinesTestCase testCase) throws NoSuchMethodException {
        return Assertions2.contextBuilder()
            .subject(LSystemToRandomLinesConverter.class.getMethod("lSystemAsLines", List.class))
            .property("seed", testCase.seed())
            .build();
    }
}

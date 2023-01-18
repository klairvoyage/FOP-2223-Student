package h11.h5;

import h11.Random;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junitpioneer.jupiter.json.JsonClasspathSource;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.tutor.general.assertions.Assertions2;
import org.tudalgo.algoutils.tutor.general.assertions.Context;

@TestForSubmission
public class RandomTest {

    @ParameterizedTest
    @JsonClasspathSource("h11/h5/random-choices-test.json")
    @Tag("H5")
    public void testChoices(RandomChoicesTestCase testCase) throws NoSuchMethodException {
        var random = new Random(testCase.seed());
        var actual = random
            .choices(testCase.input())
            .limit(testCase.output().size())
            .toList();

        Assertions2.assertEquals(testCase.output(), actual, getChoicesContext(testCase), result ->
            "The random sequence was not returned correctly");
    }

    private Context getChoicesContext(RandomChoicesTestCase testCase) throws NoSuchMethodException {
        return Assertions2.contextBuilder()
            .subject(Random.class.getMethod("choices", Object[].class))
            .add("seed", testCase.seed())
            .build();
    }

    @ParameterizedTest
    @JsonClasspathSource("h11/h5/random-latin-test.json")
    @Tag("H5")
    public void testLatin(RandomLatinTestCase testCase) throws NoSuchMethodException {
        var random = new Random(testCase.seed());
        var actual = random.latin(testCase.length());
        Assertions2.assertEquals(testCase.output(), actual, getLatinContext(testCase), result ->
            "The random latin string was not returned correctly");
    }

    private Context getLatinContext(RandomLatinTestCase testCase) throws NoSuchMethodException {
        return Assertions2.contextBuilder()
            .subject(Random.class.getMethod("latin", int.class))
            .add("seed", testCase.seed())
            .add("length", testCase.length())
            .build();
    }
}

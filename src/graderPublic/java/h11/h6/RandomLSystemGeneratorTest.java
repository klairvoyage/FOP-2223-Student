package h11.h6;

import h11.Random;
import h11.parse.Projection;
import h11.providers.RandomLSystemGenerator;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junitpioneer.jupiter.json.JsonClasspathSource;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.tutor.general.assertions.Assertions2;
import org.tudalgo.algoutils.tutor.general.assertions.Context;

@TestForSubmission
public class RandomLSystemGeneratorTest {

    @ParameterizedTest
    @JsonClasspathSource("h11/h6/make-projection-test.json")
    @Tag("H6")
    void testMakeProjection(MakeProjectionTestCase testCase) throws NoSuchMethodException {
        var random = new Random(testCase.seed());
        var generator = new RandomLSystemGenerator(random);

        var projection = generator.makeProjection(testCase.source());
        var expected = new Projection(testCase.source(), testCase.destination());

        Assertions2.assertEquals(expected, projection, getMakeProjectionContext(testCase), result ->
            "The Projection from the given source was not returned correctly");
    }

    private Context getMakeProjectionContext(MakeProjectionTestCase testCase) throws NoSuchMethodException {
        return Assertions2.contextBuilder()
            .subject(RandomLSystemGenerator.class.getMethod("makeProjection", String.class))
            .property("seed", testCase.seed())
            .property("source", testCase.source())
            .build();
    }

    @ParameterizedTest
    @ValueSource(ints = { 12, 3, 5, 67, 8, 2, 1 })
    @Tag("H6")
    void testThat_sourcesAreUnique(int seed) throws NoSuchMethodException {
        var random = new Random(seed);
        var generator = new RandomLSystemGenerator(random);
        var projections = generator.generate();

        var uniqueSources = projections
            .stream()
            .map(Projection::source)
            .distinct()
            .toList();

        var noDuplicateSources = uniqueSources.size() == projections.size();
        Assertions2.assertTrue(noDuplicateSources, getGenerateContext(seed), result ->
            "There were duplicate in the sources of the returned L-System");
    }

    @ParameterizedTest
    @JsonClasspathSource("h11/h6/generate-test.json")
    @Tag("H6")
    void testGenerate(GenerateTestCase testCase) throws NoSuchMethodException {
        var random = new Random(testCase.seed());
        var generator = new RandomLSystemGenerator(random);
        var actual = generator.generate();

        Assertions2.assertEquals(testCase.projections(), actual, getGenerateContext(testCase.seed()), result ->
            "The random projections were not returned correctly");
    }

    private Context getGenerateContext(long seed) throws NoSuchMethodException {
        return Assertions2.contextBuilder()
            .subject(RandomLSystemGenerator.class.getMethod("generate"))
            .property("seed", seed)
            .build();
    }
}

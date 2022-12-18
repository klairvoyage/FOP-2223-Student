package h11.h2;

import h11.*;
import h11.parse.ParsedLSystem;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junitpioneer.jupiter.json.JsonClasspathSource;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.student.io.PropertyUtils;
import org.tudalgo.algoutils.tutor.general.assertions.Assertions2;
import org.tudalgo.algoutils.tutor.general.assertions.Context;

import java.util.stream.Stream;

@TestForSubmission
public class LSystemGrowerTest {

    private static final long STREAM_SIZE_CONSIDERED_INFINITE =
        PropertyUtils.getLongProperty("h11/h11-grader.properties", "STREAM_SIZE_CONSIDERED_INFINITE");

    @Test
    @Tag("H2")
    void testThat_streamIsInfinite() throws NoSuchMethodException {
        var grower = new LSystemGrowerImpl<>(new LSystem<Character>() {
            @Override
            public Character getAxiom() {
                return 'A';
            }

            @Override
            public Stream<Character> project(Character e) {
                return Stream.of(e);
            }
        });

        var actual = grower
            .grow()
            .limit(STREAM_SIZE_CONSIDERED_INFINITE)
            .count();

        Assertions2.assertEquals(STREAM_SIZE_CONSIDERED_INFINITE, actual, getContext(), result ->
            "The returned Stream ended to soon");
    }

    @ParameterizedTest
    @JsonClasspathSource("h11/h2/lsystem-grower-test.json")
    @Tag("H2")
    void testGrow(LSystemGrowerTestCase testCase) throws NoSuchMethodException {
        LSystemGrowerImpl<Character> grower = getGrower(testCase);

        var actual = grower
            .grow()
            .limit(testCase.growth().size())
            .map(l -> StringUtils.join("", l))
            .toList();

        var context = getContext();
        Assertions2.assertEquals(testCase.growth(), actual, context, result ->
            "The L-System was not grown correctly");
    }

    private static Context getContext() throws NoSuchMethodException {
        return Assertions2.contextBuilder()
            .subject(LSystemGrowerImpl.class.getMethod("grow"))
            .build();
    }

    private static LSystemGrowerImpl<Character> getGrower(LSystemGrowerTestCase testCase) {
        return new LSystemGrowerImpl<>(new ParsedLSystem(testCase.projections()));
    }

}

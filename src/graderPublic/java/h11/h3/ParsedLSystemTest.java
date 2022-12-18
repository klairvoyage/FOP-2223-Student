package h11.h3;

import h11.parse.ParsedLSystem;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junitpioneer.jupiter.json.JsonClasspathSource;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.tutor.general.assertions.Assertions2;
import org.tudalgo.algoutils.tutor.general.assertions.Context;

import java.util.List;
import java.util.stream.Collectors;

@TestForSubmission
public class ParsedLSystemTest {

    @ParameterizedTest
    @JsonClasspathSource("h11/h3/parsed-lsystem-test.json")
    @Tag("H3")
    void testThat_firstProjectionGivesAxiom(ParsedLSystemTestCase testCase) throws NoSuchMethodException {
        var lSystem = new ParsedLSystem(testCase.projections());
        var expected = testCase.projections().get(0).source();
        Assertions2.assertEquals(expected, lSystem.getAxiom(), getAxiomContext(), result ->
            "The axiom was not correct");
    }

    private Context getAxiomContext() throws NoSuchMethodException {
        return Assertions2.contextBuilder()
            .subject(ParsedLSystem.class.getMethod("getAxiom"))
            .build();
    }

    @ParameterizedTest
    @JsonClasspathSource("h11/h3/parsed-lsystem-test.json")
    @Tag("H3")
    void testThat_projectionsOfKnownProject(ParsedLSystemTestCase testCase) throws NoSuchMethodException {
        var lSystem = new ParsedLSystem(testCase.projections());

        for (var p : testCase.projections()) {
            var actual = lSystem
                .project(p.source())
                .map(String::valueOf)
                .collect(Collectors.joining());

            Assertions2.assertEquals(p.destination(), actual, getProjectContext(), result ->
                "The known source was not projected correctly");
        }
    }

    @ParameterizedTest
    @JsonClasspathSource("h11/h3/parsed-lsystem-test.json")
    @Tag("H3")
    void testThat_projectionsOfUnknownDoesNotProject(ParsedLSystemTestCase testCase) throws NoSuchMethodException {
        var lSystem = new ParsedLSystem(testCase.projections());

        for (char c = '0'; c <= '9'; c++) {
            Assertions2.assertEquals(List.of(c), lSystem.project(c).toList(), getProjectContext(), result ->
                "The unknown source was not ignored correctly");
        }
    }

    private Context getProjectContext() throws NoSuchMethodException {
        return Assertions2.contextBuilder()
            .subject(ParsedLSystem.class.getMethod("project", Character.class))
            .build();
    }

}

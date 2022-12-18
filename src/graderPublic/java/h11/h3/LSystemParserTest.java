package h11.h3;

import h11.parse.LSystemParser;
import h11.parse.LSystemParserImpl;
import h11.parse.Projection;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.tutor.general.assertions.Assertions2;
import org.tudalgo.algoutils.tutor.general.assertions.Context;

import java.util.List;
import java.util.stream.Stream;

@TestForSubmission
public class LSystemParserTest {

    private final LSystemParser parser = new LSystemParserImpl();

    @Test
    @Tag("H3")
    void testThat_parserParsesProjections() throws NoSuchMethodException {
        assertParser(
            List.of(
                "A -> AB",
                "B -> A"
            ),
            List.of(
                new Projection("A", "AB"),
                new Projection("B", "A")
            ));
    }

    @Test
    @Tag("H3")
    void testThat_parserIgnoresInlineCommentsAndWhitespace() throws NoSuchMethodException {
        assertParser(
            List.of(
                "   A     ->   AB # aiwdjaw awpdijawid a ",
                "   B   ->   A  # aiwdja jawoidj a ja"
            ),
            List.of(
                new Projection("A", "AB"),
                new Projection("B", "A")
            ));
    }

    @Test
    @Tag("H3")
    void testThat_parserIgnoresLinesWithCommentsAndEmptyLines() throws NoSuchMethodException {
        assertParser(
            List.of(
                "   # auiwoda aiwdjiawd aiwodj",
                "   ",
                "A -> AB",
                "B -> A",
                "     # auiwoda awdwdawdawda iwdjiawd aiwj",
                "C -> D",
                "  # auiwoda awdawdiwj",
                "   "
            ),
            List.of(
                new Projection("A", "AB"),
                new Projection("B", "A"),
                new Projection("C", "D")
            ));
    }

    private void assertParser(List<String> lines, List<Projection> projections) throws NoSuchMethodException {
        var actual = parser.parse(lines.stream());
        Assertions2.assertEquals(projections, actual, getContext(), result ->
            "The L-System was not parsed correctly");
    }

    private Context getContext() throws NoSuchMethodException {
        return Assertions2.contextBuilder()
            .subject(LSystemParserImpl.class.getMethod("parse", Stream.class))
            .build();
    }
}

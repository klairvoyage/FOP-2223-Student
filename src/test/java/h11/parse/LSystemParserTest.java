package h11.parse;

import h11.providers.ProjectionsWithLinesProvider;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LSystemParserTest {

    private final LSystemParser parser = new LSystemParserImpl();

    @ParameterizedTest
    @ArgumentsSource(ProjectionsWithLinesProvider.class)
    void testThat_projectionsMatch(List<Projection> projections, Stream<String> lines) {
        var actual = parser.parse(lines);
        assertIterableEquals(projections, actual);
    }
}

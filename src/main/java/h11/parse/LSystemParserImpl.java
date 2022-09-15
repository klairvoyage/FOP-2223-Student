package h11.parse;

import java.util.List;
import java.util.stream.Stream;

import static org.tudalgo.algoutils.student.Student.crash;

/**
 * An implementation of the {{@link LSystemParser}
 * implementing the following file format:
 * <pre>
 *     # Comments begin with a `#`
 *     # source of the first projection is the axiom
 *     A -> AB
 *     # More rules follow with each new line
 *     A -> AB
 * </pre>
 */
public class LSystemParserImpl implements LSystemParser {

    @Override
    public List<Projection> parse(Stream<String> lines) {
        return crash("Not implemented: H3.2"); // TODO: H3.2
    }
}

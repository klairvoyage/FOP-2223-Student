package h11.parse;

import java.util.List;
import java.util.stream.Stream;

public interface LSystemParser {

    List<Projection> parse(Stream<String> lines);
}

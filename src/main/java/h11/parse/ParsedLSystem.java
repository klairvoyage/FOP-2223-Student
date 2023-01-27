package h11.parse;

import h11.LSystem;

import java.util.List;
import java.util.stream.Stream;

import static org.tudalgo.algoutils.student.Student.crash;

/**
 * One can utilize this class to
 * use the {@link List} of {@link Projection} parsed
 * by the {@link LSystemParser} to implement an {@link LSystem}.
 */
public class ParsedLSystem implements LSystem<Character> {

    /**
     * The projections of the L-System this instance represents.
     */
    private final List<Projection> projections;

    /**
     * @param projections The projections of the L-System this instance represents.
     */
    public ParsedLSystem(List<Projection> projections) {
        if (projections.isEmpty()) {
            throw new IllegalArgumentException("Need at least one projection rule");
        }

        this.projections = projections;
    }

    @Override
    public Character getAxiom() {
        // TODO: H3.1 - remove if implemented
        return projections.get(0).source();
    }

    @Override
    public Stream<Character> project(Character ch) {
        // TODO: H3.1 - remove if implemented
        return projections.stream()
            .filter(x -> x.source()==ch)
            .findFirst()
            .map(x -> x.destination().chars().mapToObj(conv -> (char)conv))
            .orElse(Stream.of(ch));
    }
}

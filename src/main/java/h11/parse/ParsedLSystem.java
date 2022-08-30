package h11.parse;

import h11.LSystem;

import java.util.List;
import java.util.stream.Stream;

import static org.tudalgo.algoutils.student.Student.crash;

public class ParsedLSystem implements LSystem<Character> {

    private final List<Projection> projections;

    public ParsedLSystem(List<Projection> projections) {
        if (projections.isEmpty()) {
            throw new IllegalArgumentException("Need at least one projection rule");
        }

        this.projections = projections;
    }

    @Override
    public Character getAxiom() {
        return crash("Not implemented: H3.1"); // TODO: H3.1
    }

    @Override
    public Stream<Character> project(Character ch) {
        return crash("Not implemented: H3.1"); // TODO: H3.1
    }
}

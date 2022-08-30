package h11;

import java.util.stream.Stream;

import static org.tudalgo.algoutils.student.Student.crash;

public class Algae implements LSystem<Algae.Variable> {

    public enum Variable { A, B }

    @Override
    public Variable getAxiom() {
        return crash("Not implemented: H1.1"); // TODO: H1.1
    }

    @Override
    public Stream<Variable> project(Variable v) {
        return crash("Not implemented: H1.2"); // TODO: H1.2
    }
}

package h11;

import java.util.List;
import java.util.stream.Stream;

import static org.tudalgo.algoutils.student.Student.crash;

public class LSystemGrowerImpl<T> implements LSystemGrower<T> {

    private final LSystem<T> lSystem;

    public LSystemGrowerImpl(LSystem<T> lSystem) {
        this.lSystem = lSystem;
    }

    @Override
    public Stream<List<T>> grow() {
        return crash("Not implemented: H2"); // TODO: H2
    }
}

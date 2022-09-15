package h11;

import java.util.List;
import java.util.stream.Stream;

import static org.tudalgo.algoutils.student.Student.crash;

/**
 * Strait forward implementation of an {@link LSystemGrower}
 * using an instance of {@link LSystem}.
 *
 * @param <T> The token type as used in {@link LSystemGrower}.
 */
public class LSystemGrowerImpl<T> implements LSystemGrower<T> {

    /**
     * The underlying {@link LSystem}.
     */
    private final LSystem<T> lSystem;

    /**
     * @param lSystem The underlying {@link LSystem}.
     */
    public LSystemGrowerImpl(LSystem<T> lSystem) {
        this.lSystem = lSystem;
    }

    @Override
    public Stream<List<T>> grow() {
        return crash("Not implemented: H2"); // TODO: H2
    }
}

package h11;

import java.util.stream.Stream;

import static org.tudalgo.algoutils.student.Student.crash;

/**
 * Descendant from {@link java.util.Random}
 * adding extra methods.
 */
public class Random extends AbstractRandom {

    /**
     * Use a unique seed.
     */
    public Random() {
    }

    /**
     * Use the given seed.
     */
    public Random(long seed) {
        super(seed);
    }

    @Override
    @SafeVarargs
    public final <T> Stream<T> choices(T... values) {
        return crash("Not implemented: H5.1"); // TODO: H5.1
    }

    @Override
    public String latin(int length) {
        return crash("Not implemented: H5.2"); // TODO: H5.2
    }
}

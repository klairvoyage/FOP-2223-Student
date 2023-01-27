package h11;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
        // TODO: H5.1 - remove if implemented
        return this.ints(0, values.length)
            .mapToObj(x -> values[x]);
    }

    @Override
    public String latin(int length) {
        // TODO: H5.2 - remove if implemented
        /*return String.join("",
            IntStream.range(0, length)
                .mapToObj(x -> this.nextInt('A', 'Z' + 1))
                .map(Character::toString)
                .toArray(String[]::new));*/
        return IntStream.range(0, length)
            .mapToObj(x -> this.nextInt('A', 'Z' + 1))
            .map(Character::toString)
            .collect(Collectors.joining());
    }
}

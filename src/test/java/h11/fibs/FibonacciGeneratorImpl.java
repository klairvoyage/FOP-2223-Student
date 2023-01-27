package h11.fibs;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.tudalgo.algoutils.student.Student.crash;

/**
 * A basic implementation of the {@link FibonacciGenerator}
 * using {@link Stream} to compute the sequence.
 */
public class FibonacciGeneratorImpl implements FibonacciGenerator {

    @Override
    public List<Integer> generate(int numberOfFibs) {
        // TODO: H4.1 - remove if implemented
        FibonacciPair fiboSeed = new FibonacciPair();
        Stream<FibonacciPair> yo = Stream.iterate(fiboSeed, FibonacciPair::next);
        return yo.limit(numberOfFibs)
            .map(FibonacciPair::a)
            .collect(Collectors.toList());
    }
}

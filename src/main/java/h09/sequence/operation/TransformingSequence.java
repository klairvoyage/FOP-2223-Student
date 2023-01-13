package h09.sequence.operation;

import h09.sequence.Sequence;

import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.tudalgo.algoutils.student.Student.crash;

public class TransformingSequence/*TODO: H4.2*/<T,R> implements Sequence<R> {
    private final Sequence<T> sequence;
    private final Function<? super T,? extends R> function;

    public static <T,R> Function<Sequence<T>, Sequence<R>> of(Function<? super T,? extends R> function) {
        return sequence -> new TransformingSequence<>(sequence, function);
    }

    public TransformingSequence(Sequence<T> sequence, Function<? super T,? extends R> function) {
        this.sequence = sequence;
        this.function = function;
    }

// TODO: H4.2 - uncomment if implemented
    @Override
    public Iterator<R> iterator() {
        return new Iterator<>() {
            // TODO: H4.2 - attributes here
            private final Iterator<T> iterator = sequence.iterator();

            @Override
            public boolean hasNext() {
                // TODO: H4.2 - remove if implemented
                return iterator.hasNext();
            }

            @Override
            public R next() {
                // TODO: H4.2 - remove if implemented
                return function.apply(iterator.next());
            }
        };
    }
}

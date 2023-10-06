package h09.sequence.operation;

import h09.sequence.Sequence;

import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.tudalgo.algoutils.student.Student.crash;

public class FlatteningTransformingSequence/*TODO: H4.3*/<T,R> implements Sequence<R> {
    private final Sequence<T> sequence;
    private final Function<? super T,? extends Sequence<? extends R>> function;

    public static <T,R> Function<Sequence<T>, Sequence<R>> of(Function<? super T,? extends Sequence<? extends R>> function) {
        return sequence -> new FlatteningTransformingSequence<>(sequence, function);
    }

    public FlatteningTransformingSequence(Sequence<T> sequence, Function<? super T,? extends Sequence<? extends R>> function) {
        this.sequence = sequence;
        this.function = function;
    }

// TODO: H4.3 - uncomment if implemented
    @Override
    public Iterator<R> iterator() {
        return new Iterator<>() {
            // TODO: H4.3 - attributes here
            private final Iterator<T> iterator = sequence.iterator();
            private Iterator<? extends R> currentIterator = null;

            @Override
            public boolean hasNext() {
                // TODO: H4.3 - remove if implemented
                while ((currentIterator==null || !currentIterator.hasNext()) && iterator.hasNext()) {
                    currentIterator = function.apply(iterator.next()).iterator();
                }
                return currentIterator!=null && currentIterator.hasNext();
            }

            @Override
            public R next() {
                // TODO: H4.3 - remove if implemented
                return currentIterator.next();
            }
        };
    }
}

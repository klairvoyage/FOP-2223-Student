package h09.sequence.operation;

import h09.sequence.Sequence;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.tudalgo.algoutils.student.Student.crash;

public class FilteringSequence/*TODO: H4.1*/<T> implements Sequence<T> {
    private final Sequence<T> sequence;
    private final Predicate<? super T> predicate;

    public static <T> Function<Sequence<T>, Sequence<T>> of(Predicate<? super T> predicate) {
        return sequence -> new FilteringSequence<>(sequence, predicate);
    }

    public FilteringSequence(Sequence<T> sequence, Predicate<? super T> predicate) {
        this.sequence = sequence;
        this.predicate = predicate;
    }

// TODO: H4.1 - uncomment if implemented
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            // TODO: H4.1 - attributes here
            private final Iterator<T> iterator = sequence.iterator();
            private T next;

            @Override
            public boolean hasNext() {
                // TODO: H4.1 - remove if implemented
                while (iterator.hasNext()) {
                    T temp = iterator.next();
                    if (predicate.test(temp)) {
                        next = temp;
                        return true;
                    }
                }
                next = null;
                return false;
            }

            @Override
            public T next() {
                // TODO: H4.1 - remove if implemented
                return next;
            }
        };
    }
}

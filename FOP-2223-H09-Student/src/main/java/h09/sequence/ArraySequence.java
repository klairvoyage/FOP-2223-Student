package h09.sequence;

import java.util.Iterator;

import static org.tudalgo.algoutils.student.Student.crash;

public class ArraySequence/*TODO: H3.1*/<T> implements Sequence<T> {
    private final T[] values;

    public ArraySequence(T[] values) {
        this.values = values;
    }

// TODO: H3.1 - uncomment if implemented
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            // TODO: H3.1 - attributes here
            private int index = 0;

            @Override
            public boolean hasNext() {
                // TODO: H3.1 - remove if implemented
                return index<values.length;
            }

            @Override
            public T next() {
                // TODO: H3.1 - remove if implemented
                return values[index++];
            }
        };
    }
}

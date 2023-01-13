package h09.sequence;

import h09.basic.BasicFactory;

import java.util.Iterator;

import static org.tudalgo.algoutils.student.Student.crash;

public class BasicFactorySequence/*TODO: H3.3*/<T> implements Sequence<T> {
    private BasicFactory<T> factory;

    public BasicFactorySequence(BasicFactory<T> factory) {
        this.factory = factory;
    }

// TODO: H3.3 - uncomment if implemented
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                // TODO: H3.3 - remove if implemented
                return true;
            }

            @Override
            public T next() {
                // TODO: H3.3 - remove if implemented
                return factory.create();
            }
        };
    }
}

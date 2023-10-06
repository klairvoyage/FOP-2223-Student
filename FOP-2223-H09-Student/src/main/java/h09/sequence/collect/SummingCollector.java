package h09.sequence.collect;

import h09.basic.BasicBinaryOperations;
import h09.basic.BasicFactory;
import h09.sequence.Sequence;

import java.util.Iterator;

public class SummingCollector<T> implements SequenceCollector<T,T> {
    // TODO: H5.2 - remove if implemented
    private T init;
    private final BasicBinaryOperations<T,T> op;

    public SummingCollector(T init, BasicBinaryOperations<T,T> op) {
        this.init = init;
        this.op = op;
    }

    @Override
    public T collect(Sequence<? extends T> sequence) {
        Iterator<? extends T> iterator = sequence.iterator();
        while (iterator.hasNext()) init = op.add(iterator.next(), init);
        return init;
    }
}

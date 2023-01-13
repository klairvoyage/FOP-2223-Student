package h09.sequence.collect;

import h09.basic.BasicBinaryOperations;
import h09.sequence.Sequence;

import java.util.Iterator;
import java.util.function.BinaryOperator;

public class BinaryOpFoldCollector<T> implements SequenceCollector<T,T> {
    // TODO: H5.3 - remove if implemented
    private T init;
    private final BinaryOperator<T> op;

    public BinaryOpFoldCollector(T init, BinaryOperator<T> op) {
        this.init = init;
        this.op = op;
    }

    @Override
    public T collect(Sequence<? extends T> sequence) {
        Iterator<? extends T> iterator = sequence.iterator();
        while (iterator.hasNext()) init = op.apply(init, iterator.next());
        return init;
    }
}

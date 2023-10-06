package h09.sequence.collect;

import h09.sequence.Sequence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ToListCollector<T> implements SequenceCollector<T,List<T>> {
    // TODO: H5.1 - remove if implemented
    @Override
    public List<T> collect(Sequence<? extends T> sequence) {
        List<T> list = new ArrayList<>();
        Iterator<? extends T> iterator = sequence.iterator();
        while (iterator.hasNext()) list.add(iterator.next());
        return list;
    }
}

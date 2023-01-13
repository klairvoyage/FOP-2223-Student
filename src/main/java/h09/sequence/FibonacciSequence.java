package h09.sequence;

import java.util.Iterator;

import static org.tudalgo.algoutils.student.Student.crash;

public class FibonacciSequence/*TODO: H3.2*/ implements Sequence<Integer> {
// TODO: H3.2 - uncomment if implemented
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            // TODO: H3.2 - attributes here
            private int current = 0; //Integer?
            private int next = 1; //Integer?

            @Override
            public boolean hasNext() {
                // TODO: H3.2 - remove if implemented
                return true;
            }

            @Override
            public Integer next() {
                // TODO: H3.2 - remove if implemented
                int nextNext = current + next;
                current = next;
                next = nextNext;
                return current;
            }
        };
    }
}

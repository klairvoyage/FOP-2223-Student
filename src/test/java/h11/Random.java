package h11;

import java.util.stream.Stream;

import static org.tudalgo.algoutils.student.Student.crash;

public class Random extends java.util.Random {

    public Random() {
    }

    public Random(long seed) {
        super(seed);
    }

    @SafeVarargs
    public final <T> Stream<T> choices(T... values) {
        return crash("Not implemented: H5.1"); // TODO: H5.1
    }


    public String latin(int length) {
        return crash("Not implemented: H5.2"); // TODO: H5.2
    }
}

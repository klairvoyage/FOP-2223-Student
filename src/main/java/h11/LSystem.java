package h11;

import java.util.stream.Stream;

public interface LSystem<T> {

    T getAxiom();

    Stream<T> project(T e);
}

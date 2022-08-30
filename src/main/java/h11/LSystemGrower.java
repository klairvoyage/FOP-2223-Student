package h11;

import java.util.List;
import java.util.stream.Stream;

public interface LSystemGrower<T> {

    Stream<List<T>> grow();
}

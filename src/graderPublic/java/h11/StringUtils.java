package h11;

import java.util.Collection;
import java.util.stream.Collectors;

public class StringUtils {

    public static <T> String join(String delimiter, Collection<T> collection) {
        return collection.stream()
            .map(String::valueOf)
            .collect(Collectors.joining(delimiter));
    }
}

package h06;

import java.util.Arrays;
import java.util.List;

public class Utils {

    public static double[] cloneArray(Double[] array) {
        return Arrays.stream(array).mapToDouble(Double::doubleValue).toArray();
    }

    public static List<Double> asList(double[] array) {
        return Arrays.stream(array).boxed().toList();
    }

    public static List<Double> asList(Double[] array) {
        return Arrays.stream(array).toList();
    }
}

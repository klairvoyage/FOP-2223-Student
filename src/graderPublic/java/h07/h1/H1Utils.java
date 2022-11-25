package h07.h1;

import java.util.Arrays;
import java.util.Objects;

public class H1Utils {

    public static double[] convertStringToDoubleArray(String arrayAsString) {
        arrayAsString = arrayAsString.substring(1, arrayAsString.length() - 1);
        String[] split = arrayAsString.split(", ");
        if (Objects.equals(split[0], ""))
            return new double[0];
        return Arrays.stream(split).mapToDouble(Double::parseDouble).toArray();
    }

}

package h07;

import h07.operators.DoubleProductOfTwo;
import h07.operators.DoubleSumOfTwo;
import h07.operators.DoubleSumSqrtsOfTwo;
import org.tudalgo.algoutils.reflect.ClassTester;
import org.tudalgo.algoutils.tutor.general.assertions.Context;
import spoon.Launcher;
import spoon.reflect.declaration.CtMethod;

import java.util.function.DoubleBinaryOperator;
import java.util.function.DoublePredicate;

import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertEquals;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertTrue;

public class Utils {

    public static final double EPSILON = 0.000001;

    public static ClassTester<?> getClassTester(String packageName, String className) {
        return new ClassTester<>(packageName, className);
    }

    public static Launcher getSpoonLauncherForClass(String packageName, String className) {
        return new ClassTester<>(packageName, className)
            .resolve()
            .assureSpoonLauncherModelsBuild()
            .getSpoon();
    }

    public static CtMethod<?> getCtMethod(Launcher launcher, String methodName) {
        return (CtMethod<?>) launcher.getModel().getElements(element -> element instanceof CtMethod<?> && ((CtMethod<?>) element).getSimpleName().equals(methodName)).get(0);
    }

    public static DoubleBinaryOperator convertStringToOperator(String op) {
        return switch (op) {
            case "DoubleProductOfTwo" -> new DoubleProductOfTwo();
            case "DoubleSumOfTwo" -> new DoubleSumOfTwo();
            case "DoubleSumSqrtsOfTwo" -> new DoubleSumSqrtsOfTwo();
            default -> null;

        };
    }

    public static DoublePredicate convertStringToPredicate(String pred) {
        return switch (pred) {
            case "IsPositive" -> e -> e > 0;
            case "DivisibleByTwo" -> e -> e % 2 == 0;
            case "IsNotNaN" -> e -> !Double.isNaN(e);
            default -> null;
        };
    }

    public static void assertArrayAlmostEquals(double[] expected, double[] actual, Context context) {
        for (int i = 0; i < expected.length; i++) {
            int finalI = i;
            if (Double.isNaN(expected[i])) {
                assertEquals(
                    expected[i],
                    actual[i],
                    context,
                    r -> String.format(
                        "Expected Double.NaN at index %d!",
                        finalI
                    )
                );
            } else {
                double difference = Math.abs(expected[i] - actual[i]);
                assertTrue(
                    difference < EPSILON,
                    context,
                    r -> String.format(
                        "At index %d, the value %s in the array is not similar enough to the expected value %s!",
                        finalI,
                        actual[finalI],
                        expected[finalI]
                    )
                );
            }

        }
    }

    public static void assertAlmostEquals(double expected, double actual, Context context) {
        if (Double.isNaN(expected)) {
            assertEquals(
                expected,
                actual,
                context,
                r -> "Expected method to return Double.NaN!"
            );
        } else {
            double difference = Math.abs(expected - actual);
            assertTrue(
                difference < EPSILON,
                context,
                r -> String.format(
                    "The returned value %s is not similar enough to the expected value %s!",
                    actual,
                    expected
                )
            );
        }
    }

}

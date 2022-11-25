package h07;

import h07.arrayoperators.PairwiseDoubleArrayBinaryOperatorGivingArray;
import h07.arrayoperators.PairwiseDoubleArrayBinaryOperatorGivingScalar;
import h07.arrayoperators.ReduceDoubleArray;
import h07.doubleoperators.ComposedDoubleBinaryOperator;
import h07.operators.DoubleProductOfTwo;
import h07.operators.DoubleSumOfTwo;
import h07.operators.DoubleSumSqrtsOfTwo;
import org.junit.jupiter.api.Test;
import org.tudalgo.algoutils.reflect.ClassTester;
import spoon.Launcher;
import spoon.reflect.declaration.CtMethod;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoublePredicate;

public class Utils {

    public static ClassTester<?> getClassTester(String packageName, String className) {
        return new ClassTester<>(packageName, className);
    }

    public static Launcher getSpoonLauncherForClass(String packageName, String className) {
        ClassTester<?> CT = new ClassTester<>(packageName, className);
        CT.resolve();
        CT.assureSpoonLauncherModelsBuild();
        return CT.getSpoon();
    }

    public static CtMethod<?> getCtMethod(Launcher launcher, String methodName) {
        return (CtMethod<?>) launcher.getModel().getElements(element -> element instanceof CtMethod<?> && ((CtMethod<?>) element).getSimpleName().equals(methodName)).get(0);
    }

    public static DoubleBinaryOperator convertStringToOperator(String op) {
        switch (op) {
            case "DoubleProductOfTwo":
                return new DoubleProductOfTwo();
            case "DoubleSumOfTwo":
                return new DoubleSumOfTwo();
            case "DoubleSumSqrtsOfTwo":
                return new DoubleSumSqrtsOfTwo();
            default:
                break;
        }
        return null;
    }

    public static DoublePredicate convertStringToPredicate(String pred) {
        switch (pred) {
            case "IsPositive":
                return e -> e > 0;
            case "DivisibleByTwo":
                return e -> e % 2 == 0;
            case "IsNotNaN":
                return e -> !Double.isNaN(e);
            default:
                break;
        }
        return null;
    }

}

package h07;

import h07.operators.DoubleProductOfTwo;
import h07.operators.DoubleSumOfTwo;
import h07.operators.DoubleSumSqrtsOfTwo;
import org.tudalgo.algoutils.reflect.ClassTester;
import spoon.Launcher;
import spoon.reflect.declaration.CtMethod;

import java.util.function.DoubleBinaryOperator;
import java.util.function.DoublePredicate;

public class Utils {

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

}

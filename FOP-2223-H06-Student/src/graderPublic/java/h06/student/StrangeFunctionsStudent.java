package h06.student;

import h06.StrangeFunctions;
import org.tudalgo.algoutils.tutor.general.assertions.Assertions2;
import org.tudalgo.algoutils.tutor.general.reflections.BasicMethodLink;
import org.tudalgo.algoutils.tutor.general.reflections.BasicTypeLink;

import static h06.StrangeFunctions.transformArray1;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.contextBuilder;
import static org.tudalgo.algoutils.tutor.general.match.BasicStringMatchers.identical;

public class StrangeFunctionsStudent {

    private static final BasicTypeLink LINK_TO_STRANGE_FUNCTIONS = BasicTypeLink.of(StrangeFunctions.class);
    public static final BasicMethodLink LINK_TO_TRANSFORM_ARRAY_2 = (BasicMethodLink) LINK_TO_STRANGE_FUNCTIONS.getMethod(identical("transformArray2"));
    public static final BasicMethodLink LINK_TO_DO_THE_RECURSION = (BasicMethodLink) LINK_TO_STRANGE_FUNCTIONS.getMethod(identical("doTheRecursion"));
    private static final BasicMethodLink LINK_TO_STRANGE_FUNCTION_1 = (BasicMethodLink) LINK_TO_STRANGE_FUNCTIONS.getMethod(identical("strangeFunction1"));
    private static final BasicMethodLink LINK_TO_STRANGE_FUNCTION_2 = (BasicMethodLink) LINK_TO_STRANGE_FUNCTIONS.getMethod(identical("strangeFunction2"));
    private static final BasicMethodLink LINK_TO_UNDERSTANDABLE_1 = (BasicMethodLink) LINK_TO_STRANGE_FUNCTIONS.getMethod(identical("understandable1"));
    private static final BasicMethodLink LINK_TO_UNDERSTANDABLE_2 = (BasicMethodLink) LINK_TO_STRANGE_FUNCTIONS.getMethod(identical("understandable2"));
    private static final BasicMethodLink LINK_TO_TRANSFORM_ARRAY_1 = (BasicMethodLink) LINK_TO_STRANGE_FUNCTIONS.getMethod(identical("transformArray1"));

    public static BasicTypeLink linkToStrangeFunctions() {
        return LINK_TO_STRANGE_FUNCTIONS;
    }

    public static BasicMethodLink linkToStrangeFunction1() {
        return LINK_TO_STRANGE_FUNCTION_1;
    }

    public static BasicMethodLink linkToStrangeFunction2() {
        return LINK_TO_STRANGE_FUNCTION_2;
    }

    public static BasicMethodLink linkToUnderstandable1() {
        return LINK_TO_UNDERSTANDABLE_1;
    }

    public static BasicMethodLink linkToUnderstandable2() {
        return LINK_TO_UNDERSTANDABLE_2;
    }

    public static BasicMethodLink linkToTransformArray1() {
        return LINK_TO_TRANSFORM_ARRAY_1;
    }

    public static BasicMethodLink linkToTransformArray2() {
        return LINK_TO_TRANSFORM_ARRAY_2;
    }


    public static BasicMethodLink linkToDoTheRecursion() {
        return LINK_TO_DO_THE_RECURSION;
    }

    public static void transformArray1Student(double[] array) {
        Assertions2.call(
            () -> transformArray1(array),
            contextBuilder().subject(linkToTransformArray1()).build(),
            r -> "unexpected error"
        );
    }
}

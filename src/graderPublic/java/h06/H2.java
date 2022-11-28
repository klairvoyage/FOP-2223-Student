package h06;

import org.tudalgo.algoutils.tutor.general.assertions.Assertions2;
import org.tudalgo.algoutils.tutor.general.reflections.MethodLink;

import static h06.Utils.asList;
import static h06.Utils.cloneArray;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertEquals;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.contextBuilder;

public abstract class H2 {

    abstract MethodLink link();


    public void t11_t13(
        Double[] in,
        Double[] expected
    ) {
        var actual = cloneArray(in);
        var context = contextBuilder().subject(link()).add("in", expected).build();
        Assertions2.call(
            () -> link().invokeStatic(actual),
            context,
            r -> "method call failed"
        );
        assertEquals(
            asList(expected),
            asList(actual),
            context,
            r -> "array is not transformed correctly"
        );
    }
}

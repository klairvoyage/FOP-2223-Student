package h04.student;

import org.tudalgo.algoutils.tutor.general.assertions.Assertions2;
import org.tudalgo.algoutils.tutor.general.assertions.Context;
import org.tudalgo.algoutils.tutor.general.reflections.MethodLink;
import org.tudalgo.algoutils.tutor.general.reflections.TypeLink;

import static h04.Tests.stringMatcher;
import static h04.student.H01Student.linkToH01;
import static org.tudalgo.algoutils.tutor.general.Messages.UNEXPECTED_EXCEPTION;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions3.assertMethodExists;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions3.assertTypeExists;
import static org.tudalgo.algoutils.tutor.general.match.BasicReflectionMatchers.hasModifiers;
import static org.tudalgo.algoutils.tutor.general.reflections.Modifier.NON_STATIC;

public interface WithCoinTypesStudent {


    static TypeLink linkToWithCoinTypes() {
        return assertTypeExists(linkToH01(), stringMatcher("WithCoinTypes"));
    }

    static MethodLink linkToGetNumberOfCoinsOfType() {
        return assertMethodExists(linkToWithCoinTypes(), stringMatcher("getNumberOfCoinsOfType").and(hasModifiers(NON_STATIC)));
    }

    static MethodLink linkToSetNumberOfCoinsOfType() {
        return assertMethodExists(linkToWithCoinTypes(), stringMatcher("setNumberOfCoinsOfType").and(hasModifiers(NON_STATIC)));
    }

    default int getNumberOfCoinsOfType(Object type) {
        return Assertions2.callObject(
            () -> linkToGetNumberOfCoinsOfType().invoke(instance(), type),
            Assertions2.contextBuilder().subject(linkToGetNumberOfCoinsOfType()).add(context()).build(),
            r -> UNEXPECTED_EXCEPTION
        );
    }

    default void setNumberOfCoinsOfType(Object type, int numberOfCoinsOfType) {
        Assertions2.call(
            () -> linkToSetNumberOfCoinsOfType().invoke(instance(), type, numberOfCoinsOfType),
            Assertions2.contextBuilder().subject(linkToSetNumberOfCoinsOfType()).add(context()).build(),
            r -> UNEXPECTED_EXCEPTION
        );
    }

    Context context();

    Object instance();
}

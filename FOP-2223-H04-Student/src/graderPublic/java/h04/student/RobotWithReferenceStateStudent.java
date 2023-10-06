package h04.student;

import org.tudalgo.algoutils.tutor.general.reflections.MethodLink;
import org.tudalgo.algoutils.tutor.general.reflections.TypeLink;

import static h04.Tests.stringMatcher;
import static h04.student.H01Student.linkToH01;
import static h04.student.More.linkToDirection;
import static h04.student.More.linkToInt;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions3.assertMethodExists;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions3.assertTypeExists;
import static org.tudalgo.algoutils.tutor.general.match.BasicReflectionMatchers.*;
import static org.tudalgo.algoutils.tutor.general.reflections.Modifier.NON_STATIC;

public interface RobotWithReferenceStateStudent {

    static TypeLink linkToRobotWithReferenceState() {
        return assertTypeExists(linkToH01(), stringMatcher("RobotWithReferenceState"));
    }

    static MethodLink linkToSetCurrentStateAsReferenceState() {
        // TODO check return type
        return assertMethodExists(
            linkToRobotWithReferenceState(),
            stringMatcher("setCurrentStateAsReferenceState").and(hasModifiers(NON_STATIC)).and(sameTypes())
        );
    }

    static MethodLink linkToGetDiffX() {
        return assertMethodExists(
            linkToRobotWithReferenceState(),
            stringMatcher("getDiffX").and(hasModifiers(NON_STATIC)).and(sameTypes()).and(sameType(linkToInt()))
        );
    }

    static MethodLink linkToGetDiffY() {
        return assertMethodExists(
            linkToRobotWithReferenceState(),
            stringMatcher("getDiffY").and(hasModifiers(NON_STATIC)).and(sameTypes()).and(sameType(linkToInt()))
        );
    }

    static MethodLink linkToGetDiffDirection() {
        return assertMethodExists(
            linkToRobotWithReferenceState(),
            stringMatcher("getDiffDirection").and(hasModifiers(NON_STATIC)).and(sameTypes()).and(sameType(linkToDirection()))
        );
    }

    static MethodLink linkToGetDiffNumberOfCoins() {
        return assertMethodExists(
            linkToRobotWithReferenceState(),
            stringMatcher("getDiffNumberOfCoins").and(hasModifiers(NON_STATIC)).and(sameTypes()).and(sameType(linkToInt()))
        );
    }
}

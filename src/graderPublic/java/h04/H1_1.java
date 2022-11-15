package h04;

import h04.student.RobotWithReferenceStateStudent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import static h04.student.More.*;
import static h04.student.RobotWithReferenceStateStudent.linkToRobotWithReferenceState;
import static h04.student.RobotWithReferenceStateStudent.linkToSetCurrentStateAsReferenceState;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions3.*;
import static org.tudalgo.algoutils.tutor.general.match.BasicReflectionMatchers.sameType;
import static org.tudalgo.algoutils.tutor.general.reflections.Modifier.INTERFACE;


@TestForSubmission
@DisplayName("H1.1")
public class H1_1 {


    @Test
    @DisplayName("1 | Existenz RobotWithReferenceState")
    public void t1_1() {
        var type = linkToRobotWithReferenceState();
        assertCorrectModifiers(type, INTERFACE);
    }

    @Test
    @DisplayName("2 | setCurrentStateAsReferenceState()")
    public void t1_2() {
        var method = linkToSetCurrentStateAsReferenceState();
        assertCorrectReturnType(method, sameType(linkToVoid()));
        assertCorrectParameters(method);
    }

    @Test
    @DisplayName("3 | getDiffX()")
    public void t1_3() {
        var method = RobotWithReferenceStateStudent.linkToGetDiffX();
        assertCorrectReturnType(method, sameType(linkToInt()));
        assertCorrectParameters(method);
    }

    @Test
    @DisplayName("4 | getDiffY()")
    public void t1_4() {
        var method = RobotWithReferenceStateStudent.linkToGetDiffY();
        assertCorrectReturnType(method, sameType(linkToInt()));
        assertCorrectParameters(method);
    }

    @Test
    @DisplayName("5 | getDiffDirection()")
    public void t1_5() {
        var method = RobotWithReferenceStateStudent.linkToGetDiffDirection();
        assertCorrectReturnType(method, sameType(linkToDirection()));
        assertCorrectParameters(method);
    }

    @Test
    @DisplayName("6 | getDiffNumberOfCoins()")
    public void t1_6() {
        var method = RobotWithReferenceStateStudent.linkToGetDiffNumberOfCoins();
        assertCorrectReturnType(method, sameType(linkToInt()));
        assertCorrectParameters(method);
    }
}

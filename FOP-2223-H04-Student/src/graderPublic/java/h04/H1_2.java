package h04;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.util.List;

import static h04.student.CoinTypeStudent.linkToCoinType;
import static h04.student.More.linkToInt;
import static h04.student.More.linkToVoid;
import static h04.student.WithCoinTypesStudent.*;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions3.*;
import static org.tudalgo.algoutils.tutor.general.match.BasicReflectionMatchers.sameType;
import static org.tudalgo.algoutils.tutor.general.match.BasicStringMatchers.identical;
import static org.tudalgo.algoutils.tutor.general.reflections.Modifier.INTERFACE;
import static org.tudalgo.algoutils.tutor.general.reflections.Modifier.PUBLIC;


@TestForSubmission
@DisplayName("H1_2")
public class H1_2 {

    @Test
    @DisplayName("1 | Existenz Interface WithCoinTypes")
    public void t3_1() {
        var type = linkToWithCoinTypes();
        assertCorrectModifiers(type, INTERFACE);
    }

    @Test
    @DisplayName("2 | Existenz Enum CoinType")
    public void t2() {
        var type = linkToCoinType();
        for (var constant : List.of("BRASS", "COPPER", "SILVER")) {
            assertHasEnumConstant(type, identical(constant));
        }
    }

    @Test
    @DisplayName("3 | getNumberOfCoinsOfType()")
    public void t3_2() {
        var type = linkToGetNumberOfCoinsOfType();
        assertCorrectModifiers(type, PUBLIC);
        assertCorrectParameters(type, sameType(linkToCoinType()));
        assertCorrectReturnType(type, sameType(linkToInt()));
    }

    @Test
    @DisplayName("4 | setNumberOfCoinsOfType()")
    public void t3_3() {
        var type = linkToSetNumberOfCoinsOfType();
        assertCorrectModifiers(type, PUBLIC);
        assertCorrectParameters(type, sameType(linkToCoinType()), sameType(linkToInt()));
        assertCorrectReturnType(type, sameType(linkToVoid()));
    }
}

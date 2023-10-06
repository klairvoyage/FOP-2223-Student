package h04;

import fopbot.Direction;
import fopbot.World;
import h04.student.RobotWithCoinTypesAndRefStateOneStudent;
import h04.student.RobotWithCoinTypesData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junitpioneer.jupiter.json.JsonClasspathSource;
import org.junitpioneer.jupiter.json.Property;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import static h04.student.RobotWithCoinTypesAndRefStateOneStudent.*;
import static h04.student.RobotWithCoinTypesStudent.linkToRobotWithCoinTypes;
import static h04.student.RobotWithReferenceStateStudent.*;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.*;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions3.*;
import static org.tudalgo.algoutils.tutor.general.match.BasicReflectionMatchers.sameType;
import static org.tudalgo.algoutils.tutor.general.reflections.Modifier.PRIVATE;
import static org.tudalgo.algoutils.tutor.general.reflections.Modifier.PUBLIC;


@TestForSubmission
@DisplayName("H2_2")
public class H2_2 {

    @BeforeEach
    public void setupWorld() {
        World.reset();
        World.setDelay(0);
        World.setSize(9, 9);
    }

    @Test
    @DisplayName("12 | Declaration RobotWithCoinTypesAndRefStateOne")
    public void t12() {
        var type = linkToRobotWithCoinTypesAndRefStateOne();
        assertCorrectSuperType(type, sameType(linkToRobotWithCoinTypes()));
        assertCorrectInterfaces(type, sameType(linkToRobotWithReferenceState()));
        assertCorrectModifiers(type, PUBLIC);
    }

    @ParameterizedTest
    @JsonClasspathSource("h04/H2_2_1.json")
    @DisplayName("t14 | Konstruktor")
    public void t14(
        @Property("robot") RobotWithCoinTypesData robotData
    ) {
        linkToRobotWithCoinTypesAndRefStateOneConstructor();
        assertCorrectModifiers(linkToRobotWithCoinTypesAndRefStateOneConstructor(), PUBLIC);
        var robot = new RobotWithCoinTypesAndRefStateOneStudent(robotData, false);
        var context = contextBuilder().add("reference", robot.context()).build();
        assertEquals(
            robotData.x(),
            robot.refX(),
            contextBuilder().add(context).subject(linkToRefX()).build(),
            r -> "unexpected x-coordinate"
        );
        assertEquals(
            robotData.y(),
            robot.refY(),
            contextBuilder().add(context).subject(linkToRefY()).build(),
            r -> "unexpected y-coordinate"
        );
        assertEquals(
            robotData.direction(),
            robot.refDirection(),
            contextBuilder().add(context).subject(linkToRefDirection()).build(),
            r -> "unexpected direction"
        );
        assertEquals(
            robotData.numberOfCopperCoins() + robotData.numberOfSilverCoins() + robotData.numberOfBrassCoins(),
            robot.refNumberOfCoins(),
            contextBuilder().add(context).subject(linkToRefNumberOfCoins()).build(),
            r -> "unexpected number of coins"
        );
    }

    @ParameterizedTest
    @JsonClasspathSource("h04/H2_2-3.json")
    @DisplayName("17 | getDiffY()")
    public void t17(
        @Property("robot") RobotWithCoinTypesData robotData,
        @Property("newY") int newY,
        @Property("diffY") int diffY
    ) {
        var robot = new RobotWithCoinTypesAndRefStateOneStudent(robotData, true);
        robot.y(newY);
        var context = contextBuilder()
            .add("robot", context(robotData))
            .add("newY", diffY)
            .build();
        assertEquals(
            diffY,
            robot.getDiffY(),
            contextBuilder().add(context).subject(linkToGetDiffY()).build(),
            r -> "unexpected difference on y-coordinate"
        );
    }

    @ParameterizedTest
    @JsonClasspathSource("h04/H2_2-3.json")
    @DisplayName("16 | getDiffX()")
    public void t16(
        @Property("robot") RobotWithCoinTypesData robotData,
        @Property("newX") int newX,
        @Property("diffX") int diffX
    ) {
        var robot = new RobotWithCoinTypesAndRefStateOneStudent(robotData, true);
        robot.x(newX);
        var context = contextBuilder()
            .add("robot", context(robotData))
            .add("newX", diffX)
            .build();
        assertEquals(
            diffX,
            robot.getDiffX(),
            contextBuilder().add(context).subject(linkToGetDiffX()).build(),
            r -> "unexpected difference on x-coordinate"
        );
    }

    @Test
    @DisplayName("5 | Attribute refX, refY, refDirection, refNumberOfCoins")
    public void t13() {
        assertCorrectModifiers(linkToRefX(), PRIVATE);
        assertCorrectModifiers(linkToRefY(), PRIVATE);
        assertCorrectModifiers(linkToRefDirection(), PRIVATE);
        assertCorrectModifiers(linkToRefNumberOfCoins(), PRIVATE);
    }

    @SuppressWarnings("DuplicatedCode")
    @ParameterizedTest
    @JsonClasspathSource("h04/H2_2-3.json")
    @DisplayName("15 | setCurrentStateAsReferenceState()")
    public void t15(
        @Property("robot") RobotWithCoinTypesData robotData,
        @Property("newX") int newX,
        @Property("newY") int newY,
        @Property("newDirection") Direction newDirection,
        @Property("newNumberOfCopperCoins") int newNumberOfCopperCoins,
        @Property("newNumberOfSilverCoins") int newNumberOfSilverCoins,
        @Property("newNumberOfBrassCoins") int newNumberOfBrassCoins
    ) {
        var robot = new RobotWithCoinTypesAndRefStateOneStudent(robotData, true);
        robot.x(newX);
        robot.y(newY);
        robot.direction(newDirection);
        robot.numberOfCopperCoins(newNumberOfCopperCoins);
        robot.numberOfSilverCoins(newNumberOfSilverCoins);
        robot.numberOfBrassCoins(newNumberOfBrassCoins);
        robot.numberOfCoins(newNumberOfCopperCoins + newNumberOfSilverCoins + newNumberOfBrassCoins);
        var context = contextBuilder()
            .add("robot", context(robotData))
            .add("newX", newX)
            .add("newY", newY)
            .add("newDirection", newDirection)
            .add("newNumberOfCopperCoins", newNumberOfCopperCoins)
            .add("newNumberOfSilverCoins", newNumberOfSilverCoins)
            .add("newNumberOfBrassCoins", newNumberOfBrassCoins)
            .build();
        robot.setCurrentStateAsReferenceState();
        assertEquals(
            newX,
            robot.refX(),
            contextBuilder().add(context).subject(linkToRefX()).build(),
            r -> "unexpected x-coordinate"
        );
        assertEquals(
            newY,
            robot.refY(),
            contextBuilder().add(context).subject(linkToRefY()).build(),
            r -> "unexpected y-coordinate"
        );
        assertEquals(
            newDirection,
            robot.refDirection(),
            contextBuilder().add(context).subject(linkToRefDirection()).build(),
            r -> "unexpected direction"
        );
        assertEquals(
            newNumberOfCopperCoins + newNumberOfSilverCoins + newNumberOfBrassCoins,
            robot.refNumberOfCoins(),
            contextBuilder().add(context).subject(linkToRefNumberOfCoins()).build(),
            r -> "unexpected number of coins"
        );
    }

    @ParameterizedTest
    @JsonClasspathSource("h04/H2_2-3.json")
    @DisplayName("7 | getDiffDirection()")
    public void t19(
        @Property("robot") RobotWithCoinTypesData robotData,
        @Property("newDirection") Direction newDirection,
        @Property("diffDirection") Direction diffDirection
    ) {
        var robot = new RobotWithCoinTypesAndRefStateOneStudent(robotData, true);
        robot.direction(newDirection);
        var context = contextBuilder()
            .add("robot", context(robotData))
            .add("newDirection", newDirection)
            .build();
        assertEquals(
            diffDirection,
            robot.getDiffDirection(),
            contextBuilder().add(context).subject(linkToGetDiffDirection()).build(),
            r -> "unexpected difference on direction"
        );
    }

    @ParameterizedTest
    @JsonClasspathSource("h04/H2_2-3.json")
    @DisplayName("8 | getDiffNumberOfCoins()")
    public void t18(
        @Property("robot") RobotWithCoinTypesData robotData,
        @Property("newNumberOfSilverCoins") int newNumberOfSilverCoins,
        @Property("newNumberOfBrassCoins") int newNumberOfBrassCoins,
        @Property("newNumberOfCopperCoins") int newNumberOfCopperCoins,
        @Property("diffNumberOfCoins") int diffNumberOfCoins
    ) {
        var robot = new RobotWithCoinTypesAndRefStateOneStudent(robotData, true);
        robot.numberOfBrassCoins(newNumberOfBrassCoins);
        robot.numberOfSilverCoins(newNumberOfSilverCoins);
        robot.numberOfCopperCoins(newNumberOfCopperCoins);
        robot.numberOfCoins(newNumberOfSilverCoins + newNumberOfBrassCoins + newNumberOfCopperCoins);
        var context = contextBuilder()
            .add("robot", context(robotData))
            .add("newNumberOfSilverCoins", newNumberOfSilverCoins)
            .add("newNumberOfBrassCoins", newNumberOfBrassCoins)
            .add("newNumberOfCopperCoins", newNumberOfCopperCoins)
            .build();
        assertEquals(
            diffNumberOfCoins,
            robot.getDiffNumberOfCoins(),
            contextBuilder().add(context).subject(linkToGetDiffNumberOfCoins()).build(),
            r -> "unexpected difference on number of coins"
        );
    }
}


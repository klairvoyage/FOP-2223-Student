package h04;

import fopbot.Direction;
import fopbot.World;
import h04.student.RobotWithCoinTypesAndRefStateTwoStudent;
import h04.student.RobotWithCoinTypesData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junitpioneer.jupiter.json.JsonClasspathSource;
import org.junitpioneer.jupiter.json.Property;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import static h04.student.RobotWithCoinTypesAndRefStateTwoStudent.*;
import static h04.student.RobotWithCoinTypesStudent.linkToRobotWithCoinTypes;
import static h04.student.RobotWithReferenceStateStudent.*;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.*;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions3.*;
import static org.tudalgo.algoutils.tutor.general.match.BasicReflectionMatchers.sameType;
import static org.tudalgo.algoutils.tutor.general.reflections.Modifier.PRIVATE;
import static org.tudalgo.algoutils.tutor.general.reflections.Modifier.PUBLIC;


@TestForSubmission
@DisplayName("H2_3")
public class H2_3 {

    @BeforeEach
    public void setupWorld() {
        World.reset();
        World.setDelay(0);
        World.setSize(9, 9);
    }

    @Test
    @DisplayName("1 | Existenz Klasse")
    public void t20() {
        var type = linkToRobotWithCoinTypesAndRefStateTwo();
        assertCorrectSuperType(type, sameType(linkToRobotWithCoinTypes()));
        assertCorrectInterfaces(type, sameType(linkToRobotWithReferenceState()));
        assertCorrectModifiers(type, PUBLIC);
    }

    @ParameterizedTest
    @JsonClasspathSource("h04/H2_2-3.json")
    @DisplayName("22 | Konstruktor")
    public void t22(
        @Property("robot") RobotWithCoinTypesData robotData
    ) {
        assertCorrectModifiers(linkToRobotWithCoinTypesAndRefStateTwoConstructor(), PUBLIC);
        var robot = new RobotWithCoinTypesAndRefStateTwoStudent(robotData, false);
        var context = contextBuilder().add("reference", robot.context()).build();
        assertEquals(
            robot.x(),
            robot.refRobot().getRefX(),
            contextBuilder().add(context).subject(linkToRefRobot()).build(),
            r -> "unexpected x-coordinate"
        );
        assertEquals(
            robot.y(),
            robot.refRobot().getRefY(),
            contextBuilder().add(context).subject(linkToRefRobot()).build(),
            r -> "unexpected y-coordinate"
        );
        assertEquals(
            robot.direction(),
            robot.refRobot().getRefDirection(),
            contextBuilder().add(context).subject(linkToRefRobot()).build(),
            r -> "unexpected direction"
        );
        assertEquals(
            robot.numberOfCoins(),
            robot.refRobot().getRefNumberOfCoins(),
            contextBuilder().add(context).subject(linkToRefRobot()).build(),
            r -> "unexpected number of coins"
        );
    }

    @ParameterizedTest
    @JsonClasspathSource("h04/H2_2-3.json")
    @DisplayName("23.2 | getDiffX()")
    public void t23_2(
        @Property("robot") RobotWithCoinTypesData robotData,
        @Property("newX") int newX,
        @Property("diffX") int diffX
    ) {
        var robot = new RobotWithCoinTypesAndRefStateTwoStudent(robotData, true);
        robot.x(newX);
        var context = contextBuilder()
            .add("robot", context(robotData))
            .add("newX", newX)
            .build();
        assertEquals(
            diffX,
            robot.getDiffX(),
            contextBuilder().add(context).subject(linkToGetDiffX()).build(),
            r -> "unexpected difference on x-coordinate"
        );
    }

    @ParameterizedTest
    @JsonClasspathSource("h04/H2_2-3.json")
    @DisplayName("23.3 | getDiffY()")
    public void t23_3(
        @Property("robot") RobotWithCoinTypesData robotData,
        @Property("newY") int newY,
        @Property("diffY") int diffY
    ) {
        var robot = new RobotWithCoinTypesAndRefStateTwoStudent(robotData, true);
        robot.y(newY);
        var context = contextBuilder()
            .add("robot", context(robotData))
            .add("newY", newY)
            .build();
        assertEquals(
            diffY,
            robot.getDiffY(),
            contextBuilder().add(context).subject(linkToGetDiffY()).build(),
            r -> "unexpected difference on y-coordinate"
        );
    }

    @Test
    @DisplayName("21 | Attribute refRobot")
    public void t21() {
        linkToRefRobot();
        assertCorrectModifiers(linkToRefRobot(), PRIVATE);
    }

    //
    @SuppressWarnings("DuplicatedCode")
    @ParameterizedTest
    @JsonClasspathSource("h04/H2_2-3.json")
    @DisplayName("23.1 | setCurrentStateAsReferenceState()")
    public void t23_1(
        @Property("robot") RobotWithCoinTypesData robotData,
        @Property("newX") int newX,
        @Property("newY") int newY,
        @Property("newDirection") Direction newDirection,
        @Property("newNumberOfCopperCoins") int newNumberOfCopperCoins,
        @Property("newNumberOfSilverCoins") int newNumberOfSilverCoins,
        @Property("newNumberOfBrassCoins") int newNumberOfBrassCoins
    ) {
        var robot = new RobotWithCoinTypesAndRefStateTwoStudent(robotData, true);
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
            robot.refRobot().getRefX(),
            contextBuilder().add(context).subject(linkToRefRobot()).build(),
            r -> "unexpected x-coordinate"
        );
        assertEquals(
            newY,
            robot.refRobot().getRefY(),
            contextBuilder().add(context).subject(linkToRefRobot()).build(),
            r -> "unexpected y-coordinate"
        );
        assertEquals(
            newDirection,
            robot.refRobot().getRefDirection(),
            contextBuilder().add(context).subject(linkToRefRobot()).build(),
            r -> "unexpected direction"
        );
        assertEquals(
            newNumberOfCopperCoins + newNumberOfSilverCoins + newNumberOfBrassCoins,
            robot.refRobot().getRefNumberOfCoins(),
            contextBuilder().add(context).subject(linkToRefRobot()).build(),
            r -> "unexpected number of coins"
        );
    }

    @ParameterizedTest
    @JsonClasspathSource("h04/H2_2-3.json")
    @DisplayName("23.4 | getDiffDirection()")
    public void t23_4(
        @Property("robot") RobotWithCoinTypesData robotData,
        @Property("newDirection") Direction newDirection,
        @Property("diffDirection") Direction diffDirection
    ) {
        var robot = new RobotWithCoinTypesAndRefStateTwoStudent(robotData, true);
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
    @DisplayName("23.5 | getDiffNumberOfCoins()")
    public void t23_5(
        @Property("robot") RobotWithCoinTypesData robotData,
        @Property("newNumberOfSilverCoins") int newNumberOfSilverCoins,
        @Property("newNumberOfBrassCoins") int newNumberOfBrassCoins,
        @Property("newNumberOfCopperCoins") int newNumberOfCopperCoins,
        @Property("diffNumberOfCoins") int diffNumberOfCoins
    ) {
        var robot = new RobotWithCoinTypesAndRefStateTwoStudent(robotData, true);
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


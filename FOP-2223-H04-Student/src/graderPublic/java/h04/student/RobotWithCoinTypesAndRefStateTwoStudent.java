package h04.student;

import fopbot.Direction;
import fopbot.Robot;
import fopbot.World;
import h04.ReferenceRobot;
import org.mockito.Mockito;
import org.tudalgo.algoutils.tutor.general.assertions.Context;
import org.tudalgo.algoutils.tutor.general.reflections.ConstructorLink;
import org.tudalgo.algoutils.tutor.general.reflections.FieldLink;
import org.tudalgo.algoutils.tutor.general.reflections.MethodLink;
import org.tudalgo.algoutils.tutor.general.reflections.TypeLink;

import static h04.Tests.stringMatcher;
import static h04.student.H01Student.linkToH01;
import static h04.student.More.*;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.tudalgo.algoutils.tutor.general.Messages.UNEXPECTED_EXCEPTION;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.callObject;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.contextBuilder;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions3.*;
import static org.tudalgo.algoutils.tutor.general.match.BasicReflectionMatchers.*;
import static org.tudalgo.algoutils.tutor.general.reflections.Modifier.NON_STATIC;

public class RobotWithCoinTypesAndRefStateTwoStudent extends RobotWithCoinTypesStudent {

    public RobotWithCoinTypesAndRefStateTwoStudent(
        int x,
        int y,
        Direction direction,
        int numberOfSilverCoins,
        int numberOfBrassCoins,
        int numberOfCopperCoins,
        boolean mocked
    ) {
        context = contextBuilder()
            .subject(linkToRobotWithCoinTypesAndRefStateTwoConstructor())
            .add("x", x)
            .add("y", y)
            .add("direction", direction)
            .add("numberOfSilverCoins", numberOfSilverCoins)
            .add("numberOfBrassCoins", numberOfBrassCoins)
            .add("numberOfCopperCoins", numberOfCopperCoins)
            .build();
        if (mocked) {
            instance = Mockito.mock(linkToRobotWithCoinTypesAndRefStateTwo().reflection(), CALLS_REAL_METHODS);
            x(x);
            y(y);
            direction(direction);
            numberOfSilverCoins(numberOfSilverCoins);
            numberOfBrassCoins(numberOfBrassCoins);
            numberOfCopperCoins(numberOfCopperCoins);
            refRobot(new ReferenceRobot(x, y, direction, numberOfSilverCoins + numberOfBrassCoins + numberOfCopperCoins));
            numberOfCoins(numberOfCopperCoins + numberOfBrassCoins + numberOfSilverCoins);
            RobotStudent.linkToRobotWorld().set(instance, World.getGlobalWorld());
            RobotStudent.linkToNumberOfCoins().set(instance, numberOfBrassCoins + numberOfCopperCoins + numberOfSilverCoins);
            World.getGlobalWorld().addRobot((Robot) instance);
        } else {
            this.instance = callObject(
                () -> linkToRobotWithCoinTypesAndRefStateTwoConstructor().invoke(
                    x,
                    y,
                    direction,
                    numberOfSilverCoins,
                    numberOfBrassCoins,
                    numberOfCopperCoins
                ),
                context,
                r -> UNEXPECTED_EXCEPTION
            );
        }
    }

    public RobotWithCoinTypesAndRefStateTwoStudent(RobotWithCoinTypesData data, boolean mocked) {
        this(
            data.x(),
            data.y(),
            data.direction(),
            data.numberOfSilverCoins(),
            data.numberOfBrassCoins(),
            data.numberOfCopperCoins(),
            mocked
        );
    }

    public static TypeLink linkToRobotWithCoinTypesAndRefStateTwo() {
        return assertTypeExists(
            linkToH01(),
            stringMatcher("RobotWithCoinTypesAndRefStateTwo")
        );
    }

    public static FieldLink linkToRefRobot() {
        return assertFieldExists(
            linkToRobotWithCoinTypesAndRefStateTwo(),
            stringMatcher("refRobot").and(hasModifiers(NON_STATIC)).and(sameType(linkToReferenceRobot()))
        );
    }

    public static ConstructorLink linkToRobotWithCoinTypesAndRefStateTwoConstructor() {
        return assertConstructorExists(
            linkToRobotWithCoinTypesAndRefStateTwo(),
            sameTypes(
                linkToInt(),
                linkToInt(),
                linkToDirection(),
                linkToInt(),
                linkToInt(),
                linkToInt()
            )
        );
    }


    public MethodLink linkToGetDiffX() {
        return assertMethodExists(
            linkToRobotWithCoinTypesAndRefStateTwo(),
            stringMatcher("getDiffX").and(hasModifiers(NON_STATIC)).and(sameTypes()).and(sameType(linkToInt()))
        );
    }

    public int getDiffX() {
        // TODO
        return callObject(
            () -> linkToGetDiffX().invoke(instance),
            context,
            r -> UNEXPECTED_EXCEPTION
        );
    }

    public MethodLink linkToGetDiffY() {
        return assertMethodExists(
            linkToRobotWithCoinTypesAndRefStateTwo(),
            stringMatcher("getDiffY").and(hasModifiers(NON_STATIC)).and(sameTypes()).and(sameType(linkToInt()))
        );
    }

    public int getDiffY() {
        return callObject(
            () -> linkToGetDiffY().invoke(instance),
            context,
            r -> UNEXPECTED_EXCEPTION
        );
    }

    public MethodLink linkToGetDiffNumberOfCoins() {
        return assertMethodExists(
            linkToRobotWithCoinTypesAndRefStateTwo(),
            stringMatcher("getDiffNumberOfCoins").and(hasModifiers(NON_STATIC)).and(sameTypes()).and(sameType(linkToInt()))
        );
    }

    public int getDiffNumberOfCoins() {
        return callObject(
            () -> linkToGetDiffNumberOfCoins().invoke(instance),
            context,
            r -> UNEXPECTED_EXCEPTION
        );
    }

    public MethodLink linkToGetDiffDirection() {
        return assertMethodExists(
            linkToRobotWithCoinTypesAndRefStateTwo(),
            stringMatcher("getDiffDirection").and(hasModifiers(NON_STATIC)).and(sameTypes()).and(sameType(linkToDirection()))
        );
    }

    public Direction getDiffDirection() {
        return callObject(
            () -> linkToGetDiffDirection().invoke(instance),
            context,
            r -> UNEXPECTED_EXCEPTION
        );
    }

    public MethodLink linkToSetCurrentStateAsReferenceState() {
        return assertMethodExists(
            linkToRobotWithCoinTypesAndRefStateTwo(),
            stringMatcher("setCurrentStateAsReferenceState").and(hasModifiers(NON_STATIC)).and(sameTypes()).and(sameType(linkToVoid()))
        );
    }

    public void setCurrentStateAsReferenceState() {
        callObject(
            () -> linkToSetCurrentStateAsReferenceState().invoke(instance),
            context,
            r -> UNEXPECTED_EXCEPTION
        );
    }

    public ReferenceRobot refRobot() {
        return linkToRefRobot().get(instance());
    }

    public void refRobot(ReferenceRobot refRobot) {
        linkToRefRobot().set(instance(), refRobot);
    }

    @Override
    public Object instance() {
        return instance;
    }

    @Override
    public Context context() {
        return context;
    }
}

package h04.student;

import fopbot.Direction;
import fopbot.Robot;
import fopbot.World;
import org.mockito.Mockito;
import org.tudalgo.algoutils.tutor.general.assertions.Context;
import org.tudalgo.algoutils.tutor.general.reflections.ConstructorLink;
import org.tudalgo.algoutils.tutor.general.reflections.FieldLink;
import org.tudalgo.algoutils.tutor.general.reflections.MethodLink;
import org.tudalgo.algoutils.tutor.general.reflections.TypeLink;

import static h04.Tests.stringMatcher;
import static h04.student.H01Student.linkToH01;
import static h04.student.More.linkToDirection;
import static h04.student.More.linkToInt;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.tudalgo.algoutils.tutor.general.Messages.UNEXPECTED_EXCEPTION;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.callObject;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.contextBuilder;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions3.*;
import static org.tudalgo.algoutils.tutor.general.match.BasicReflectionMatchers.*;
import static org.tudalgo.algoutils.tutor.general.reflections.Modifier.NON_STATIC;

public class RobotWithCoinTypesAndRefStateOneStudent extends RobotWithCoinTypesStudent {

    public RobotWithCoinTypesAndRefStateOneStudent(
        int x,
        int y,
        Direction direction,
        int numberOfSilverCoins,
        int numberOfBrassCoins,
        int numberOfCopperCoins,
        boolean mocked
    ) {
        context = contextBuilder()
            .subject(linkToRobotWithCoinTypesAndRefStateOneConstructor())
            .add("x", x)
            .add("y", y)
            .add("direction", direction)
            .add("numberOfSilverCoins", numberOfSilverCoins)
            .add("numberOfBrassCoins", numberOfBrassCoins)
            .add("numberOfCopperCoins", numberOfCopperCoins)
            .build();
        if (mocked) {
            instance = Mockito.mock(linkToRobotWithCoinTypesAndRefStateOne().reflection(), CALLS_REAL_METHODS);
            x(x);
            y(y);
            direction(direction);
            numberOfSilverCoins(numberOfSilverCoins);
            numberOfBrassCoins(numberOfBrassCoins);
            numberOfCopperCoins(numberOfCopperCoins);
            refX(x);
            refY(y);
            refDirection(direction);
            refNumberOfCoins(numberOfCopperCoins + numberOfBrassCoins + numberOfSilverCoins);
            numberOfCoins(numberOfCopperCoins + numberOfBrassCoins + numberOfSilverCoins);
            RobotStudent.linkToRobotWorld().set(instance, World.getGlobalWorld());
            RobotStudent.linkToNumberOfCoins().set(instance, numberOfBrassCoins + numberOfCopperCoins + numberOfSilverCoins);
            World.getGlobalWorld().addRobot((Robot) instance);
        } else {
            this.instance = callObject(
                () -> linkToRobotWithCoinTypesAndRefStateOneConstructor().invoke(
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

    public RobotWithCoinTypesAndRefStateOneStudent(RobotWithCoinTypesData data, boolean mocked) {
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

    public static TypeLink linkToRobotWithCoinTypesAndRefStateOne() {
        return assertTypeExists(linkToH01(), stringMatcher("RobotWithCoinTypesAndRefStateOne"));
    }

    public static FieldLink linkToRefX() {
        return assertFieldExists(
            linkToRobotWithCoinTypesAndRefStateOne(),
            stringMatcher("refX").and(hasModifiers(NON_STATIC)).and(sameType(linkToInt()))
        );
    }

    public static FieldLink linkToRefY() {
        return assertFieldExists(
            linkToRobotWithCoinTypesAndRefStateOne(),
            stringMatcher("refY").and(hasModifiers(NON_STATIC)).and(sameType(linkToInt()))
        );
    }

    public static FieldLink linkToRefDirection() {
        return assertFieldExists(
            linkToRobotWithCoinTypesAndRefStateOne(),
            stringMatcher("refDirection").and(hasModifiers(NON_STATIC)).and(sameType(linkToDirection()))
        );
    }

    public static FieldLink linkToRefNumberOfCoins() {
        return assertFieldExists(
            linkToRobotWithCoinTypesAndRefStateOne(),
            stringMatcher("refNumberOfCoins").and(hasModifiers(NON_STATIC)).and(sameType(linkToInt()))
        );
    }

    public static ConstructorLink linkToRobotWithCoinTypesAndRefStateOneConstructor() {
        return assertConstructorExists(
            linkToRobotWithCoinTypesAndRefStateOne(),
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

    public int refX() {
        return linkToRefX().get(instance);
    }

    public void refX(int refX) {
        linkToRefX().set(instance, refX);
    }

    public int refY() {
        return linkToRefY().get(instance);
    }

    public void refY(int refY) {
        linkToRefY().set(instance, refY);
    }

    public Direction refDirection() {
        return linkToRefDirection().get(instance);
    }

    public void refDirection(Direction refDirection) {
        linkToRefDirection().set(instance, refDirection);
    }

    public int refNumberOfCoins() {
        return linkToRefNumberOfCoins().get(instance);
    }

    public void refNumberOfCoins(int refNumberOfCoins) {
        linkToRefNumberOfCoins().set(instance, refNumberOfCoins);
    }

    public MethodLink linkToGetDiffX() {
        return assertMethodExists(
            linkToRobotWithCoinTypesAndRefStateOne(),
            stringMatcher("getDiffX").and(hasModifiers(NON_STATIC)).and(sameTypes()).and(sameType(linkToInt()))
        );
    }

    public int getDiffX() {
        return callObject(
            () -> linkToGetDiffX().invoke(instance),
            context,
            r -> UNEXPECTED_EXCEPTION
        );
    }

    public MethodLink linkToGetDiffY() {
        return assertMethodExists(
            linkToRobotWithCoinTypesAndRefStateOne(),
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
            linkToRobotWithCoinTypesAndRefStateOne(),
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
            linkToRobotWithCoinTypesAndRefStateOne(),
            stringMatcher("getDiffDirection").and(hasModifiers(NON_STATIC).and(sameTypes()).and(sameType(linkToDirection())))
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
            linkToRobotWithCoinTypesAndRefStateOne(),
            stringMatcher("setCurrentStateAsReferenceState").and(hasModifiers(NON_STATIC)).and(sameTypes())
        );
    }

    public void setCurrentStateAsReferenceState() {
        callObject(
            () -> linkToSetCurrentStateAsReferenceState().invoke(instance),
            context,
            r -> UNEXPECTED_EXCEPTION
        );
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

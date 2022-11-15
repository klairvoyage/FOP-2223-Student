package h04.student;

import fopbot.Direction;
import fopbot.Robot;
import fopbot.World;
import org.mockito.Mockito;
import org.tudalgo.algoutils.tutor.general.assertions.Context;
import org.tudalgo.algoutils.tutor.general.reflections.ConstructorLink;
import org.tudalgo.algoutils.tutor.general.reflections.FieldLink;
import org.tudalgo.algoutils.tutor.general.reflections.TypeLink;

import static h04.Tests.stringMatcher;
import static h04.student.H01Student.linkToH01;
import static h04.student.More.linkToDirection;
import static h04.student.More.linkToInt;
import static h04.student.WithCoinTypesStudent.linkToGetNumberOfCoinsOfType;
import static h04.student.WithCoinTypesStudent.linkToSetNumberOfCoinsOfType;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.tudalgo.algoutils.tutor.general.Messages.UNEXPECTED_EXCEPTION;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.callObject;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.contextBuilder;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions3.*;
import static org.tudalgo.algoutils.tutor.general.match.BasicReflectionMatchers.*;
import static org.tudalgo.algoutils.tutor.general.reflections.Modifier.NON_STATIC;

public class RobotWithCoinTypesStudent implements RobotStudent {

    protected Object instance = null;
    protected Context context = null;

    public RobotWithCoinTypesStudent() {

    }

    public RobotWithCoinTypesStudent(RobotWithCoinTypesData data, boolean mocked) {
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

    public RobotWithCoinTypesStudent(
        int x,
        int y,
        Direction direction,
        int numberOfSilverCoins,
        int numberOfBrassCoins,
        int numberOfCopperCoins,
        boolean mocked
    ) {
        context = contextBuilder()
            .subject(linkToRobotWithCoinTypesConstructor())
            .add("x", x)
            .add("y", y)
            .add("direction", direction)
            .add("numberOfSilverCoins", numberOfSilverCoins)
            .add("numberOfBrassCoins", numberOfBrassCoins)
            .add("numberOfCopperCoins", numberOfCopperCoins)
            .build();
        if (mocked) {
            instance = Mockito.mock(linkToRobotWithCoinTypes().reflection(), CALLS_REAL_METHODS);
            x(x);
            y(y);
            direction(direction);
            numberOfSilverCoins(numberOfSilverCoins);
            numberOfBrassCoins(numberOfBrassCoins);
            numberOfCopperCoins(numberOfCopperCoins);
            numberOfCoins(numberOfCopperCoins + numberOfBrassCoins + numberOfSilverCoins);
            RobotStudent.linkToRobotWorld().set(instance(), World.getGlobalWorld());
            RobotStudent.linkToNumberOfCoins().set(instance(), numberOfBrassCoins + numberOfCopperCoins + numberOfSilverCoins);
            World.getGlobalWorld().addRobot((Robot) instance);
        } else {
            this.instance = callObject(
                () -> linkToRobotWithCoinTypesConstructor().invoke(
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

    public static FieldLink linkToNumberOfSilverCoins() {
        return assertFieldExists(
            linkToRobotWithCoinTypes(),
            stringMatcher("numberOfSilverCoins").and(hasModifiers(NON_STATIC)).and(sameType(linkToInt()))
        );
    }

    public static FieldLink linkToNumberOfBrassCoins() {
        return assertFieldExists(
            linkToRobotWithCoinTypes(),
            stringMatcher("numberOfBrassCoins").and(hasModifiers(NON_STATIC)).and(sameType(linkToInt()))
        );
    }

    public static FieldLink linkToNumberOfCopperCoins() {
        return assertFieldExists(
            linkToRobotWithCoinTypes(),
            stringMatcher("numberOfCopperCoins").and(hasModifiers(NON_STATIC)).and(sameType(linkToInt()))
        );
    }

    public static TypeLink linkToRobotWithCoinTypes() {
        return assertTypeExists(
            linkToH01(),
            stringMatcher("RobotWithCoinTypes")
        );
    }

    public static ConstructorLink linkToRobotWithCoinTypesConstructor() {
        return assertConstructorExists(
            linkToRobotWithCoinTypes(),
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

    public int numberOfSilverCoins() {
        return linkToNumberOfSilverCoins().get(instance());
    }

    public int numberOfBrassCoins() {
        return linkToNumberOfBrassCoins().get(instance());
    }

    public int numberOfCopperCoins() {
        return linkToNumberOfCopperCoins().get(instance());
    }

    public void numberOfSilverCoins(int numberOfSilverCoins) {
        linkToNumberOfSilverCoins().set(instance(), numberOfSilverCoins);
    }

    public void numberOfBrassCoins(int numberOfBrassCoins) {
        linkToNumberOfBrassCoins().set(instance(), numberOfBrassCoins);
    }

    public void numberOfCopperCoins(int numberOfCopperCoins) {
        linkToNumberOfCopperCoins().set(instance(), numberOfCopperCoins);
    }

    public int getNumberOfCoinsOfType(Object type) {
        return callObject(
            () -> linkToGetNumberOfCoinsOfType().invoke(instance(), type),
            contextBuilder().subject(linkToGetNumberOfCoinsOfType()).build(),
            r -> UNEXPECTED_EXCEPTION
        );
    }

    public void setNumberOfCoinsOfType(Object type, int numberOfCoins) {
        var context = contextBuilder()
            .subject(linkToSetNumberOfCoinsOfType())
            .add("type", type)
            .add("numberOfCoins", numberOfCoins)
            .build();
        callObject(
            () -> linkToSetNumberOfCoinsOfType().invoke(instance(), type, numberOfCoins),
            context,
            r -> UNEXPECTED_EXCEPTION
        );
    }

    public void setNumberOfCoins(int numberOfCoins) {
        var context = contextBuilder()
            .subject(RobotStudent.linkToSetNumberOfCoins())
            .add("numberOfCoins", numberOfCoins)
            .build();
        callObject(
            () -> RobotStudent.linkToSetNumberOfCoins().invoke(instance(), numberOfCoins),
            context,
            r -> UNEXPECTED_EXCEPTION
        );
    }

    @Override
    public Context context() {
        return context;
    }

    @Override
    public Object instance() {
        return instance;
    }
}

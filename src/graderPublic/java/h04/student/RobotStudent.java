package h04.student;

import fopbot.Direction;
import fopbot.Robot;
import org.tudalgo.algoutils.tutor.general.assertions.Assertions2;
import org.tudalgo.algoutils.tutor.general.assertions.Context;
import org.tudalgo.algoutils.tutor.general.reflections.BasicTypeLink;
import org.tudalgo.algoutils.tutor.general.reflections.FieldLink;
import org.tudalgo.algoutils.tutor.general.reflections.MethodLink;
import org.tudalgo.algoutils.tutor.general.reflections.TypeLink;

import static h04.student.More.linkToFieldEntity;
import static org.tudalgo.algoutils.tutor.general.match.BasicStringMatchers.identical;

public interface RobotStudent {

    TypeLink linkToRobot = BasicTypeLink.of(Robot.class);
    FieldLink linkToRobotX = linkToFieldEntity().getField(identical("x"));
    FieldLink linkToRobotY = linkToFieldEntity().getField(identical("y"));
    FieldLink linkToRobotDirection = linkToRobot().getField(identical("direction"));
    FieldLink linkToRobotWorld = linkToRobot().getField(identical("world"));
    FieldLink linkToRobotNumberOfCoins = linkToRobot().getField(identical("numberOfCoins"));
    MethodLink linkToRobotSetNumberOfCoins = linkToRobot().getMethod(identical("setNumberOfCoins"));
    MethodLink linkToPickCoin = linkToRobot().getMethod(identical("pickCoin"));
    MethodLink linkToPutCoin = linkToRobot().getMethod(identical("putCoin"));
    MethodLink linkToMove = linkToRobot().getMethod(identical("move"));

    static FieldLink linkToRobotX() {
        return linkToRobotX;
    }

    static FieldLink linkToRobotY() {
        return linkToRobotY;
    }

    static FieldLink linkToRobotDirection() {
        return linkToRobotDirection;
    }

    static FieldLink linkToRobotWorld() {
        return linkToRobotWorld;
    }

    static MethodLink linkToSetNumberOfCoins() {
        return linkToRobotSetNumberOfCoins;
    }

    static MethodLink linkToPickCoin() {
        return linkToPickCoin;
    }

    static MethodLink linkToPutCoin() {
        return linkToPutCoin;
    }

    static TypeLink linkToRobot() {
        return linkToRobot;
    }

    static FieldLink linkToNumberOfCoins() {
        return linkToRobotNumberOfCoins;
    }

    static MethodLink linkToMove() {
        return linkToMove;
    }

    Object instance();

    Context context();

    default int x() {
        return linkToRobotX().get(instance());
    }

    default int y() {
        return linkToRobotY().get(instance());
    }

    default Direction direction() {
        return linkToRobotDirection().get(instance());
    }

    default void x(int x) {
        linkToRobotX().set(instance(), x);
    }

    default void y(int y) {
        linkToRobotY().set(instance(), y);
    }

    default void direction(Direction direction) {
        linkToRobotDirection().set(instance(), direction);
    }

    default void pickCoin() {
        Assertions2.call(() -> linkToPickCoin().invoke(instance()), context(), r -> "unexpected exception");
    }

    default void putCoin() {
        Assertions2.call(() -> linkToPutCoin().invoke(instance()), context(), r -> "unexpected exception");
    }

    default void move() {
        Assertions2.call(() -> linkToMove().invoke(instance()), context(), r -> "unexpected exception");
    }

    default int numberOfCoins() {
        return linkToNumberOfCoins().get(instance());
    }

    default void numberOfCoins(int numberOfCoins) {
        linkToNumberOfCoins().set(instance(), numberOfCoins);
    }
}

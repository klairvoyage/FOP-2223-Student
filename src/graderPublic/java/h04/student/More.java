package h04.student;

import fopbot.Direction;
import fopbot.FieldEntity;
import h04.ReferenceRobot;
import org.tudalgo.algoutils.tutor.general.reflections.BasicTypeLink;
import org.tudalgo.algoutils.tutor.general.reflections.TypeLink;

public class More {

    private static final TypeLink linkToObject = BasicTypeLink.of(Object.class);
    private static final TypeLink linkToInt = BasicTypeLink.of(int.class);
    private static final TypeLink linkToVoid = BasicTypeLink.of(void.class);

    private static final TypeLink linkToFieldEntity = BasicTypeLink.of(FieldEntity.class);
    private static final TypeLink linkToDirection = BasicTypeLink.of(Direction.class);

    private static final TypeLink linkToReferenceRobot = BasicTypeLink.of(ReferenceRobot.class);

    public static TypeLink linkToObject() {
        return linkToObject;
    }

    public static TypeLink linkToInt() {
        return linkToInt;
    }

    public static TypeLink linkToVoid() {
        return linkToVoid;
    }


    public static TypeLink linkToFieldEntity() {
        return linkToFieldEntity;
    }

    public static TypeLink linkToDirection() {
        return linkToDirection;
    }

    public static TypeLink linkToReferenceRobot() {
        return linkToReferenceRobot;
    }
}

package h03;

import fopbot.Robot;
import org.tudalgo.algoutils.reflect.ClassTester;

import java.lang.reflect.Modifier;

public class H03_Class_Testers {
    public static final double SIMILARITY = 1;
    public static final ClassTester<?> robotWithOffspringCT = new ClassTester<>("h03", "RobotWithOffspring",
        SIMILARITY, Modifier.PUBLIC, Robot.class, null);

    public static final ClassTester<?> robotWithOffspring2CT = new ClassTester<>("h03", "RobotWithOffspring2",
        SIMILARITY, Modifier.PUBLIC, robotWithOffspringCT.getTheClass(), null);

    public static final ClassTester<?> twinRobotsCT = new ClassTester<>("h03", "TwinRobots",
        SIMILARITY, Modifier.PUBLIC, null, null);
}

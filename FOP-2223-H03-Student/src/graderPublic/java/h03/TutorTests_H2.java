package h03;

import fopbot.Direction;
import fopbot.Robot;
import fopbot.World;
import h03.transform.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.opentest4j.AssertionFailedError;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.sourcegrade.jagr.api.testing.TestCycle;
import org.sourcegrade.jagr.api.testing.extension.TestCycleResolver;
import org.tudalgo.algoutils.reflect.AttributeMatcher;
import org.tudalgo.algoutils.reflect.MethodTester;
import org.tudalgo.algoutils.reflect.ParameterMatcher;
import org.tudalgo.algoutils.tutor.general.assertions.Context;
import org.tudalgo.algoutils.tutor.general.assertions.PreCommentSupplier;
import org.tudalgo.algoutils.tutor.general.assertions.ResultOfFail;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import static h03.H03_Class_Testers.*;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.*;

@TestForSubmission
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("H2")
public class TutorTests_H2 {

    private static boolean robotWithOffspringClassResolved = false;
    private static PreCommentSupplier<? super ResultOfFail> errorMessageSupplier;

    private static Field directionAccuField;
    private static Field offspringField;

    private static Method getDirectionOfOffspringMethod;
    private static Method getDirectionFromAccuMethod;
    private static Method addToDirectionOfOffspringMethod;

    @BeforeAll
    @SuppressWarnings("unchecked")
    public static void setup() {
        World.reset();
        World.setSize(500, 500);
        World.setDelay(0);
        World.setVisible(false);

        try {
            errorMessageSupplier = result -> "Class RobotWithOffspring could not be resolved";
            robotWithOffspring2CT.setSuperClass((Class<Object>) robotWithOffspringCT.assureClassResolved().getTheClass());
            errorMessageSupplier = result ->
                "Class RobotWithOffspring2 could not be resolved properly, it probably does not extend RobotWithOffspring";
            robotWithOffspring2CT.verify(SIMILARITY);
            robotWithOffspringClassResolved = true;
        } catch (AssertionFailedError ignored) {

        }
    }

    @Test
    @Order(1)
    @DisplayName("Attribut \"directionAccu\" wurde korrekt deklariert.")
    public void directionAccuDeclaredCorrectly() {
        if (!robotWithOffspringClassResolved) {
            fail(emptyContext(), errorMessageSupplier);
        }

        directionAccuField = robotWithOffspring2CT.resolve().resolveAttribute(
            new AttributeMatcher("directionAccu", SIMILARITY, Modifier.PRIVATE, int.class));
        directionAccuField.trySetAccessible();
    }

    @Disabled
    @ParameterizedTest
    @Order(2)
    @CsvSource({"0,UP", "1,RIGHT", "2,DOWN", "3,LEFT", "5,RIGHT", "10,DOWN", "15,LEFT", "20,UP"})
    @DisplayName("Methode \"getDirectionOfOffspring\" wurde korrekt implementiert.")
    public void testGetDirectionOfOffspring(int fieldValue, Direction direction) throws ReflectiveOperationException {
        if (!robotWithOffspringClassResolved) {
            fail(emptyContext(), errorMessageSupplier);
        } else if (directionAccuField == null) {
            fail(emptyContext(), result -> "Field directionAccu could not be resolved");
        }

        try {
            getDirectionOfOffspringMethod = robotWithOffspring2CT.getTheClass().getDeclaredMethod("getDirectionOfOffspring");
        } catch (NoSuchMethodException e) {
            fail(e, emptyContext(), result -> "Could not resolve method getDirectionOfOffspring");
        }
        Object instance = robotWithOffspring2CT.getClassInstance();
        Context context = contextBuilder()
            .add("directionAccu", fieldValue)
            .build();

        directionAccuField.trySetAccessible();
        directionAccuField.set(instance, fieldValue);

        assertEquals(direction, getDirectionOfOffspringMethod.invoke(instance), context, result -> "");
    }

    @Test
    @Order(3)
    @DisplayName("Konstruktor von \"RobotWithOffspring2\" wurde korrekt implementiert.")
    @ExtendWith(TestCycleResolver.class)
    public void testConstructor(TestCycle testCycle) {
        if (!robotWithOffspringClassResolved) {
            fail(emptyContext(), errorMessageSupplier);
        }

        String className = robotWithOffspring2CT.getTheClass().getName();
        H2_Transformers.CONSTRUCTOR_VALUES.clear();
        testCycle.getClassLoader().visitClass(className, new ClassTransformerTemplate(className, H2_Transformers.CONSTRUCTOR_TRANSFORMER));

        assertTrue((Boolean) H2_Transformers.CONSTRUCTOR_VALUES.getOrDefault("superConstructorCalled", false),
            emptyContext(),
            result -> "super constructor was not called");
        assertFalse((Boolean) H2_Transformers.CONSTRUCTOR_VALUES.getOrDefault("fieldInstruction", false),
            emptyContext(),
            result -> "constructor initialized field directionAccu");
    }

    @Test
    @Order(4)
    @DisplayName("Überschriebene Methode \"initOffspring\" wurde korrekt deklariert und implementiert.")
    @ExtendWith(TestCycleResolver.class)
    public void testInitOffspring(TestCycle testCycle) throws ReflectiveOperationException {
        if (!robotWithOffspringClassResolved) {
            fail(emptyContext(), errorMessageSupplier);
        } else if (directionAccuField == null) {
            fail(emptyContext(), result -> "Field directionAccu could not be resolved");
        }

        Method initOffspringMethod = new MethodTester(robotWithOffspring2CT.resolve(),
            "initOffspring",
            SIMILARITY,
            Modifier.PUBLIC,
            void.class,
            new ArrayList<>(List.of(
                new ParameterMatcher("direction", 0, Direction.class),
                new ParameterMatcher("numberOfCoins", 0, int.class)
            )))
            .resolveMethod();
        String className = robotWithOffspring2CT.assureClassResolved().getTheClass().getName();
        H2_Transformers.INIT_OFFSPRING_VALUES.clear();
        testCycle.getClassLoader()
            .visitClass(className, new ClassTransformerTemplate(className, H2_Transformers.INIT_OFFSPRING_TRANSFORMER));

        assertTrue((Boolean) H2_Transformers.INIT_OFFSPRING_VALUES.getOrDefault("invokedSuperMethod", false),
            emptyContext(),
            result -> "overwritten method was not invoked");

        Object instance = robotWithOffspring2CT.getClassInstance();
        for (Direction direction : Direction.values()) {
            Context context = contextBuilder()
                .add("direction", direction)
                .add("numberOfCoins", 0)
                .build();
            initOffspringMethod.invoke(instance, direction, 0);
            assertEquals(direction.ordinal(), directionAccuField.get(instance), context,
                result -> "method did not set field directionAccu to expected value");
        }
    }

    @ParameterizedTest
    @Order(5)
    @CsvFileSource(resources = "/TutorTests_H2-getDirectionFromAccuImplementedCorrectly.csv", numLinesToSkip = 1)
    @DisplayName("Methode \"getDirectionFromAccu\" wurde für Standardfälle korrekt implementiert.")
    public void testGetDirectionFromAccu_normal(int directionAccu, Direction expectedDirection) throws ReflectiveOperationException {
        if (getDirectionFromAccuMethod == null) {
            getDirectionFromAccuMethod = new MethodTester(robotWithOffspring2CT.resolve(),
                "getDirectionFromAccu", SIMILARITY, Direction.class, new ArrayList<>()).resolveMethod();
            getDirectionFromAccuMethod.trySetAccessible();
        }

        Object instance = robotWithOffspring2CT.getNewInstance();
        Context context = contextBuilder()
            .add("directionAccu", directionAccu)
            .build();
        directionAccuField.set(instance, directionAccu);
        assertEquals(expectedDirection, getDirectionFromAccuMethod.invoke(instance), context,
            result -> "method returned wrong direction");
    }

    @ParameterizedTest
    @Order(6)
    @CsvFileSource(resources = "/TutorTests_H2-addToDirectionOfOffspringValues.csv", numLinesToSkip = 1)
    @DisplayName("Überschriebene Methode \"addToDirectionOfOffspring\" wurde korrekt deklariert und implementiert.")
    public void testAddToDirectionOfOffspring(Direction initialDirection, int directionAccu, int directionToBeAdded,
                                              Direction expectedDirection) throws ReflectiveOperationException {
        if (!robotWithOffspringClassResolved) {
            fail(emptyContext(), errorMessageSupplier);
        } else if (directionAccuField == null) {
            fail(emptyContext(), result -> "Field directionAccu could not be resolved");
        }
        if (offspringField == null) {
            try {
                offspringField = robotWithOffspringCT.findClass().getDeclaredField("offspring");
                offspringField.trySetAccessible();
            } catch (NoSuchFieldException e) {
                fail(e, emptyContext(), result -> "Could not resolve field offspring");
            }
        }
        if (addToDirectionOfOffspringMethod == null) {
            addToDirectionOfOffspringMethod = new MethodTester(robotWithOffspring2CT.resolve(),
                "addToDirectionOfOffspring",
                SIMILARITY,
                Modifier.PUBLIC,
                void.class,
                new ArrayList<>(List.of(new ParameterMatcher("directionToBeAdded", 0, int.class)))
            ).resolveMethod();
        }

        Object instance = robotWithOffspring2CT.getNewInstance();
        Robot robot = new Robot(0, 0, initialDirection, 0);
        offspringField.set(instance, robot);
        directionAccuField.set(instance, directionAccu);
        Context context = contextBuilder()
            .add("offspring", robot)
            .add("directionAccu", directionAccu)
            .add("directionToBeAdded", directionToBeAdded)
            .build();
        addToDirectionOfOffspringMethod.invoke(instance, directionToBeAdded);

        assertEquals(directionAccu + directionToBeAdded, directionAccuField.get(instance), context,
            result -> "method did not increase field directionAccu by correct amount");
        assertEquals(expectedDirection, robot.getDirection(), context,
            result -> "method did not turn offspring to look in the expected direction");
    }
}

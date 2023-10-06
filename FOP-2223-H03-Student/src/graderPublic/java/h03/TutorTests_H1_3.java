package h03;

import fopbot.Direction;
import fopbot.FieldEntity;
import fopbot.Robot;
import fopbot.World;
import h03.transform.FloorModCheckTransformer;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.opentest4j.AssertionFailedError;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.sourcegrade.jagr.api.testing.TestCycle;
import org.sourcegrade.jagr.api.testing.extension.TestCycleResolver;
import org.tudalgo.algoutils.reflect.AttributeMatcher;
import org.tudalgo.algoutils.reflect.ClassTester;
import org.tudalgo.algoutils.reflect.MethodTester;
import org.tudalgo.algoutils.reflect.ParameterMatcher;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import static h03.H03_Class_Testers.SIMILARITY;
import static h03.H03_Class_Testers.robotWithOffspringCT;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestForSubmission
@DisplayName("H1.3")
public class TutorTests_H1_3 {
    @BeforeAll
    public static void setup() {
        World.reset();
        World.setSize(500, 500);
        World.setDelay(0);
        World.setVisible(false);
    }

    // DONE
    @ParameterizedTest
    @CsvFileSource(resources = "/TutorTests_H1_3-addToXOfOffspringDeclaredAndImplementedCorrectly.csv", numLinesToSkip = 1)
    @DisplayName("Methode \"addToXOfOffspring\" wurde korrekt deklariert und implementiert.")
    public void addToXOfOffspringDeclaredAndImplementedCorrectly(int x, int y, Direction direction, int numberOfCoins,
                                                                 int xToAssign, int expectedResultX) throws IllegalAccessException {
        var offspring = new Robot(x, y, direction, numberOfCoins);
        testAddToMethod("addToXOfOffspring", FieldEntity::getX, offspring, xToAssign, expectedResultX, true);
    }

    // DONE
    @ParameterizedTest
    @CsvFileSource(resources = "/TutorTests_H1_3-addToYOfOffspringDeclaredAndImplementedCorrectly.csv", numLinesToSkip = 1)
    @DisplayName("Methode \"addToYOfOffspring\" wurde korrekt deklariert und implementiert.")
    public void addToYOfOffspringDeclaredAndImplementedCorrectly(int x, int y, Direction direction, int numberOfCoins,
                                                                 int yToAssign, int expectedResultY) throws IllegalAccessException {
        var offspring = new Robot(x, y, direction, numberOfCoins);
        testAddToMethod("addToYOfOffspring", FieldEntity::getY, offspring, yToAssign, expectedResultY, true);
    }

    // DONE
    @ParameterizedTest
    @CsvFileSource(resources = "/TutorTests_H1_3-addToNumberOfCoinsOfOffspringDeclaredAndImplementedCorrectly.csv",
        numLinesToSkip = 1)
    @DisplayName("Methode \"addToNumberOfCoinsOfOffspring\" wurde korrekt deklariert und implementiert.")
    public void addToNumberOfCoinsOfOffspringDeclaredAndImplementedCorrectly(int x, int y, Direction direction,
                                                                             int numberOfCoins, int coinsToAssign,
                                                                             int expectedResultCoins) throws IllegalAccessException {
        var offspring = new Robot(x, y, direction, numberOfCoins);
        testAddToMethod("addToNumberOfCoinsOfOffspring", Robot::getNumberOfCoins, offspring, coinsToAssign,
            expectedResultCoins, false);
    }

    // DONE
    @ParameterizedTest
    @CsvFileSource(resources = "/TutorTests_H1_3-addToDirectionOfOffspring-baseCases.csv", numLinesToSkip = 1)
    @DisplayName("Methode \"addToDirectionOfOffspring\" wurde korrekt deklariert und die Implementierung besteht einfache " +
        "Testfälle.")
    @ExtendWith(TestCycleResolver.class)
    public void addToDirectionOfOffspringDeclaredCorrectlyAndPassesBaseTests(int x, int y, Direction direction,
                                                                             int numberOfCoins, int directionToAssign,
                                                                             Direction expectedResultDirection,
                                                                             @NotNull TestCycle testCycle) throws IllegalAccessException {
        final var className = robotWithOffspringCT.assureClassResolved().getTheClass().getName();
        testCycle.getClassLoader().visitClass(className, new FloorModCheckTransformer());

        testAddToDirectionOfOffspring(x, y, direction, numberOfCoins, directionToAssign, expectedResultDirection);
    }

    // DONE
    @ParameterizedTest
    @CsvFileSource(resources = "/TutorTests_H1_3-addToDirectionOfOffspring-advancedCases.csv", numLinesToSkip = 1)
    @DisplayName("Methode \"addToDirectionOfOffspring\" wurde korrekt deklariert und die Implementierung besteht komplexe und " +
        "Rand-Testfälle.")
    @ExtendWith(TestCycleResolver.class)
    public void addToDirectionOfOffspringDeclaredCorrectlyAndPassesAdvancedTests(int x, int y, Direction direction,
                                                                                 int numberOfCoins, int directionToAssign,
                                                                                 Direction expectedResultDirection,
                                                                                 @NotNull TestCycle testCycle) throws IllegalAccessException {
        final var className = robotWithOffspringCT.assureClassResolved().getTheClass().getName();
        testCycle.getClassLoader().visitClass(className, new FloorModCheckTransformer());

        testAddToDirectionOfOffspring(x, y, direction, numberOfCoins, directionToAssign, expectedResultDirection);
    }

    private void testAddToDirectionOfOffspring(int x, int y, Direction direction, int numberOfCoins, int directionToAssign,
                                               Direction expectedResultDirection) throws IllegalAccessException {
        var offspring = new Robot(x, y, direction, numberOfCoins);
        testAddToMethod("addToDirectionOfOffspring", Robot::getDirection, offspring, directionToAssign,
            expectedResultDirection, false);
    }

    private <T> void testAddToMethod(String methodName, @NotNull ValueGetter<T> valueGetter, Robot offspring,
                                     int valueToAssign, T expectedResultValue, boolean setWorldSize) throws IllegalAccessException {
        ClassTester<?> ct = robotWithOffspringCT.resolve();
        Field offspringField = ct.resolveAttribute(
            new AttributeMatcher("offspring", SIMILARITY, Robot.class));

        assertFalse(offspringField.getType().isArray(),
            String.format("Der Datentyp von Attribut \"%s\" ist ein Array, sollte aber kein Array sein.",
                offspringField.getName()));

        Object robotInstance = robotWithOffspringCT.getClassInstance();

        if (setWorldSize) {
            Field numberOfColumnsOfWorldField = ct.resolveAttribute(
                new AttributeMatcher("numberOfColumnsOfWorld", SIMILARITY, int.class));
            assertFalse(numberOfColumnsOfWorldField.getType().isArray(),
                String.format("Der Datentyp von Attribut \"%s\" ist ein Array, sollte aber kein Array sein.",
                    numberOfColumnsOfWorldField.getName()));

            numberOfColumnsOfWorldField.setAccessible(true);
            assertDoesNotThrow(() -> numberOfColumnsOfWorldField.set(robotInstance, World.getWidth()));

            Field numberOfRowsOfWorldField = ct.resolveAttribute(
                new AttributeMatcher("numberOfRowsOfWorld", SIMILARITY, int.class));
            assertFalse(numberOfRowsOfWorldField.getType().isArray(),
                String.format("Der Datentyp von Attribut \"%s\" ist ein Array, sollte aber kein Array sein.",
                    numberOfRowsOfWorldField.getName()));

            numberOfRowsOfWorldField.setAccessible(true);
            assertDoesNotThrow(() -> numberOfRowsOfWorldField.set(robotInstance, World.getHeight()));
        }

        var methodTester = new MethodTester(ct, methodName, SIMILARITY, Modifier.PUBLIC, void.class,
            new ArrayList<>(List.of(
                new ParameterMatcher("foobar", 0, int.class)
            ))).verify();

        assertDoesNotThrow(() -> methodTester.invoke(13),
            String.format("Die Methode \"%s\" wirft eine Exception, wenn der offspring nicht initialisiert ist.",
                methodName));

        // Both the initOffspring method is called (if found) and the offspring is set manually via reflection. Here's the reasoning:
        // We want to make sure that we give points for this method even if initOffspring is implemented wrongly, so we set
        // the offspring manually.
        // We still call initOffspring if found, in case the student does not check for null, but
        // sets a boolean variable indicating whether the offspring has been initialized (since this would
        // also be a valid solution).
        try {
            var initOffspringMethodTester = new MethodTester(robotWithOffspringCT.assureClassResolved(),
                "initOffspring", 0.8, Modifier.PUBLIC,
                void.class,
                new ArrayList<>(List.of(
                    new ParameterMatcher("direction", 0.8, Direction.class),
                    new ParameterMatcher("numberOfCoins", 0.8, int.class)
                ))).resolveMethod();
            initOffspringMethodTester.invoke(robotInstance, Direction.UP, 0);
        } catch (AssertionFailedError | InvocationTargetException ex) {
            // Nothing to do. The method should be called if found, but we ignore it if not.
        }

        offspringField.setAccessible(true);
        offspringField.set(robotInstance, offspring);

        assertDoesNotThrow(() -> methodTester.invoke(valueToAssign),
            String.format("Die Methode \"%s\" wirft eine Exception beim Aufruf.",
                methodName));

        var newValue = valueGetter.getValue(offspring);
        assertEquals(expectedResultValue, newValue,
            String.format("Die Methode \"%s\" weist dem offspring den falschen Attributwert zu.", methodName));
    }

    private interface ValueGetter<T> {
        T getValue(Robot robot);
    }
}

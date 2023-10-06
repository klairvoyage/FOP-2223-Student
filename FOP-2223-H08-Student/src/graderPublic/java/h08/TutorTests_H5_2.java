package h08;

import h08.calculation.ArrayCalculator;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

// DONE
@TestForSubmission
public class TutorTests_H5_2 {

    // DONE
    @Test
    public void testExceptionFailsWhenCalculatorThrowsNoException() throws NoSuchMethodException, IllegalAccessException {
        var testExceptionMethod = CalculatorTests.class.getDeclaredMethod(
            "testException", ArrayCalculator.class, double[][].class, double.class, Class.class, String.class);
        testExceptionMethod.setAccessible(true);

        var sut = new CalculatorTests();
        var assertionThrown = false;

        try {
            testExceptionMethod.invoke(sut, new ArrayCalculatorMockSumReturner(435), new double[0][], 0,
                MockException.class, "");
        } catch (InvocationTargetException e) {
            var targetException = e.getTargetException();
            if (targetException instanceof AssertionError) {
                assertionThrown = true;
            }
        }

        assertTrue(assertionThrown, "Die Methode \"testException\" wirft keinen AssertionError, " +
            "wenn der ArrayCalculator keine Exception wirft.");
    }

    // DONE
    @Test
    public void testExceptionFailsWhenExceptionTypeIsWrong() throws NoSuchMethodException, IllegalAccessException {
        var testExceptionMethod = CalculatorTests.class.getDeclaredMethod(
            "testException", ArrayCalculator.class, double[][].class, double.class, Class.class, String.class);
        testExceptionMethod.setAccessible(true);

        var sut = new CalculatorTests();
        var assertionThrown = false;

        try {
            testExceptionMethod.invoke(sut, new ArrayCalculatorMockExceptionThrower(new DifferentMockException()),
                new double[0][], 0,
                MockException.class, "");
        } catch (InvocationTargetException e) {
            var targetException = e.getTargetException();
            if (targetException instanceof AssertionError) {
                assertionThrown = true;
            }
        }

        assertTrue(assertionThrown, "Die Methode \"testException\" wirft keinen AssertionError, " +
            "wenn der Datentyp der von ArrayCalculator geworfenen Exception nicht dem erwarteten Typ entspricht.");
    }

    // DONE
    @Test
    public void testExceptionFailsWhenMessageIsWrong() throws NoSuchMethodException, IllegalAccessException {
        var testExceptionMethod = CalculatorTests.class.getDeclaredMethod(
            "testException", ArrayCalculator.class, double[][].class, double.class, Class.class, String.class);
        testExceptionMethod.setAccessible(true);

        var sut = new CalculatorTests();
        var assertionThrown = false;

        try {
            testExceptionMethod.invoke(sut, new ArrayCalculatorMockExceptionThrower(new MockException("kleinkuenstler")),
                new double[0][], 0,
                MockException.class, "kaenguru");
        } catch (InvocationTargetException e) {
            var targetException = e.getTargetException();
            if (targetException instanceof AssertionError) {
                assertionThrown = true;
                assertTrue(targetException.getMessage().contains("kaenguru : kleinkuenstler"),
                    "Die Botschaft des von \"testException\" geworfenen AssertionError beinhaltet nicht die Zeichenkette " +
                        "\"<expectedExceptionMessage> : <actualMessage>\", wobei die beiden Parameter jeweils durch die " +
                        "Variablen ersetzt werden sollten.");
            }
        }

        assertTrue(assertionThrown, "Die Methode \"testException\" wirft keinen AssertionError, " +
            "wenn die Botschaft der von ArrayCalculator geworfenen Exception nicht der erwarteten Botschaft entspricht.");
    }

    // DONE
    @Test
    public void testExceptionPassesWhenExceptionIsThrownCorrectly() throws NoSuchMethodException, IllegalAccessException {
        var testExceptionMethod = CalculatorTests.class.getDeclaredMethod(
            "testException", ArrayCalculator.class, double[][].class, double.class, Class.class, String.class);
        testExceptionMethod.setAccessible(true);

        var sut = new CalculatorTests();
        final var expectedExceptionMessage = "vollversammlung";

        try {
            testExceptionMethod.invoke(sut, new ArrayCalculatorMockExceptionThrower(new MockException(expectedExceptionMessage)),
                new double[0][], 0,
                MockException.class, expectedExceptionMessage);
        } catch (InvocationTargetException e) {
            var targetException = e.getTargetException();
            fail(String.format(
                "Die Methode \"testException\" wirft bei korrekt geworfener Exception eine unerwartete Exception: %s",
                targetException.getClass()), targetException);

        }
    }
}

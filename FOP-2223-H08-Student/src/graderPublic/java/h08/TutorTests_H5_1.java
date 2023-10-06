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
public class TutorTests_H5_1 {

    // DONE
    @Test
    public void testSumThrowsExceptionWhenSumNotCorrect() throws NoSuchMethodException, IllegalAccessException {
        var testSumMethod = CalculatorTests.class.getDeclaredMethod(
            "testSum", ArrayCalculator.class, double[][].class, double.class, double.class);
        testSumMethod.setAccessible(true);

        var sut = new CalculatorTests();
        var assertionThrown = false;

        try {
            testSumMethod.invoke(sut, new ArrayCalculatorMockSumReturner(435), new double[0][], 0, 111);
        } catch (InvocationTargetException e) {
            if (e.getTargetException() instanceof AssertionError) {
                assertionThrown = true;
            }
        }

        assertTrue(assertionThrown, "Die Methode \"testSum\" wirft keinen AssertionError, " +
            "wenn die Summe durch den ArrayCalculator falsch berechnet wurde.");
    }

    // DONE
    @Test
    public void testSumThrowsExceptionWhenCalculatorThrowsException() throws NoSuchMethodException, IllegalAccessException {
        var testSumMethod = CalculatorTests.class.getDeclaredMethod(
            "testSum", ArrayCalculator.class, double[][].class, double.class, double.class);
        testSumMethod.setAccessible(true);

        var sut = new CalculatorTests();
        var mockCalculator = new ArrayCalculatorMockExceptionThrower(new Exception("pinguin"));

        var assertionThrown = false;

        try {
            testSumMethod.invoke(sut, mockCalculator, new double[0][], 0, 0);
        } catch (InvocationTargetException e) {
            var targetException = e.getTargetException();
            if (targetException instanceof AssertionError) {
                assertionThrown = true;
                var message = targetException.getMessage();
                assertTrue(message != null && message.toLowerCase().contains("pinguin"),
                    "Die Botschaft des von der Methode \"testSum\" geworfenen AssertionError " +
                    "beinhaltet nicht die Botschaft der unerwartet geworfenen Exception des Calculators.");
            }
        }

        assertTrue(assertionThrown, "Die Methode \"testSum\" wirft keinen AssertionError, " +
            "wenn der ArrayCalculator eine Exception wirft.");
    }

    // DONE
    @Test
    public void testSumPassesWhenSumCorrect() throws NoSuchMethodException, IllegalAccessException {
        var testSumMethod = CalculatorTests.class.getDeclaredMethod(
            "testSum", ArrayCalculator.class, double[][].class, double.class, double.class);
        testSumMethod.setAccessible(true);

        var sut = new CalculatorTests();
        final var expectedSum = 194;
        var mockCalculator = new ArrayCalculatorMockSumReturner(expectedSum);

        try {
            testSumMethod.invoke(sut, mockCalculator, new double[0][], 0, expectedSum);
        } catch (InvocationTargetException e) {
            var targetException = e.getTargetException();
            fail(String.format(
                "Die Methode \"testSum\" wirft bei korrekter Berechnung der Summe eine unerwartete Exception: %s",
                targetException.getClass()), targetException);
        }
    }
}

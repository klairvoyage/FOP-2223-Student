package h11.h4;

import h11.fibs.FibonacciGenerator;
import h11.fibs.FibonacciGeneratorImpl;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.tudalgo.algoutils.tutor.general.assertions.Assertions2;
import org.tudalgo.algoutils.tutor.general.assertions.Context;

import java.util.List;

public abstract class FibonacciGeneratorTest {

    private final Class<? extends FibonacciGenerator> classToTest;

    private final FibonacciGenerator referenceFibonaccigenerator = new FibonacciGeneratorImpl();

    private final FibonacciGenerator fibonacciGenerator;

    protected FibonacciGeneratorTest(FibonacciGenerator fibonacciGenerator) {
        this.classToTest = fibonacciGenerator.getClass();
        this.fibonacciGenerator = fibonacciGenerator;
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 8, 16})
    @Tag("H4")
    public void testThat_initialIsCorrect(int numberOfFibs) throws NoSuchMethodException {
        var fibs = fibonacciGenerator.generate(numberOfFibs);
        Assertions2.assertTrue(fibs.size() >= 2, getContext(numberOfFibs), result ->
            "Too few fibonacci numbers were generated, was: "+ fibs.size());
        Assertions2.assertEquals(List.of(1, 2), fibs.subList(0, 2), getContext(numberOfFibs), result ->
            "The first two fibonacci numbers were returned incorrectly");
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 8, 16})
    @Tag("H4")
    public void testThat_fibsAreCorrect(int numberOfFibs) throws NoSuchMethodException {
        var fibs = fibonacciGenerator.generate(numberOfFibs);
        Assertions2.assertEquals(numberOfFibs, fibs.size(), getContext(numberOfFibs), result ->
            "The wrong amount of fibonacci numbers was returned");
        Assertions2.assertEquals(referenceFibonaccigenerator.generate(numberOfFibs), fibs, getContext(numberOfFibs), result ->
            "The fibonacci numbers were returned incorrectly");
    }

    private Context getContext(int numberOfFibs) throws NoSuchMethodException {
        return Assertions2.contextBuilder()
            .subject(classToTest.getMethod("generate", int.class))
            .property("numberOfFibs", numberOfFibs)
            .build();
    }
}

package h11.h4;

import h11.fibs.FibonacciGeneratorImpl;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

@TestForSubmission
public class FibonacciGeneratorImplTest extends FibonacciGeneratorTest {

    protected FibonacciGeneratorImplTest() {
        super(new FibonacciGeneratorImpl());
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 8, 16})
    @Override
    public void testThat_initialIsCorrect(int numberOfFibs) throws NoSuchMethodException {
        super.testThat_initialIsCorrect(numberOfFibs);
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 8, 16})
    @Override
    public void testThat_fibsAreCorrect(int numberOfFibs) throws NoSuchMethodException {
        super.testThat_fibsAreCorrect(numberOfFibs);
    }
}

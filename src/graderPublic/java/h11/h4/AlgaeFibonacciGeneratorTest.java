package h11.h4;

import h11.Algae;
import h11.LSystemGrower;
import h11.LSystemGrowerImpl;
import h11.fibs.AlgaeFibonacciGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

@TestForSubmission
public class AlgaeFibonacciGeneratorTest extends FibonacciGeneratorTest {

    private static final LSystemGrower<Algae.Variable> ALGAE_GROWER = new LSystemGrowerImpl<>(new Algae());

    protected AlgaeFibonacciGeneratorTest() {
        super(new AlgaeFibonacciGenerator(ALGAE_GROWER));
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

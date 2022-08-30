package h11.fibs;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.tudalgo.algoutils.student.Student.crash;

abstract class AlgaeTest {

    private final FibonacciGenerator fibonacciGenerator;

    private final FibonacciGenerator algaeFibonacciGenerator;

    protected AlgaeTest(FibonacciGenerator fibonacciGenerator, FibonacciGenerator algaeFibonacciGenerator) {
        this.fibonacciGenerator = fibonacciGenerator;
        this.algaeFibonacciGenerator = algaeFibonacciGenerator;
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 10, 20, 30})
    void testAlgaeGeneratesFibs(int numberOfFibs) {
        crash("Not implemented: H4.3"); // TODO: H4.3
    }
}

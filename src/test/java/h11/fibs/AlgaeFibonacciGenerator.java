package h11.fibs;

import h11.Algae;
import h11.LSystemGrower;

import java.util.List;

import static org.tudalgo.algoutils.student.Student.crash;

/**
 * An implementation of the {@link FibonacciGenerator}
 * using the {@link Algae} L-System.
 */
public class AlgaeFibonacciGenerator implements FibonacciGenerator {

    /**
     * The {@link LSystemGrower} based on the Algae to use.
     */
    private final LSystemGrower<Algae.Variable> algaeGrower;

    /**
     * @param algaeGrower The {@link LSystemGrower} based on the Algae to use.
     */
    public AlgaeFibonacciGenerator(LSystemGrower<Algae.Variable> algaeGrower) {
        this.algaeGrower = algaeGrower;
    }

    @Override
    public List<Integer> generate(int numberOfFibs) {
        return crash("Not implemented: H4.2"); // TODO: H4.2 - remove if implemented
    }
}

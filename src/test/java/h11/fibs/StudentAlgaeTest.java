package h11.fibs;

import h11.Algae;
import h11.LSystemGrowerImpl;

public class StudentAlgaeTest extends AlgaeTest {

    protected StudentAlgaeTest() {
        super(new FibonacciGeneratorImpl(), new AlgaeFibonacciGenerator(new LSystemGrowerImpl<>(new Algae())));
    }
}

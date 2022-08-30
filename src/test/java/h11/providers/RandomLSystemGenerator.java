package h11.providers;

import h11.Random;
import h11.parse.Projection;

import java.util.List;

import static org.tudalgo.algoutils.student.Student.crash;

public class RandomLSystemGenerator {

    private static final int MAX_SYSTEM_SIZE = 10;
    private static final int MAX_PROJECTION_DESTINATION_SIZE = 5;

    private final Random random;

    public RandomLSystemGenerator(Random random) {
        this.random = random;
    }

    public List<Projection> generate() {
        return crash("Not implemented: H6.2"); // TODO: H6.2
    }

    private Projection makeProjection(String src) {
        return crash("Not implemented: H6.1"); // TODO: H6.1
    }
}

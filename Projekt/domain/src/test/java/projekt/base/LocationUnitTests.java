package projekt.base;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import projekt.ComparableUnitTests;
import projekt.ObjectUnitTests;

import static org.tudalgo.algoutils.student.Student.crash;
import static org.junit.jupiter.api.Assertions.*;
import java.util.function.Function;

public class LocationUnitTests {

    private static ComparableUnitTests<Location> comparableUnitTests;
    private static ObjectUnitTests<Location> objectUnitTests;

    @BeforeAll
    public static void initialize() {
        Function<Integer, Location> testObjectFactory=(i)->new Location(2*i,3*i);
        comparableUnitTests=new ComparableUnitTests<>(testObjectFactory);
        objectUnitTests=new ObjectUnitTests<>(testObjectFactory,Location::toString);
        comparableUnitTests.initialize(10);
        objectUnitTests.initialize(10);

    }

    @Test
    public void testEquals() {
        objectUnitTests.testEquals();
    }

    @Test
    public void testHashCode() {
        objectUnitTests.testHashCode();
    }

    @Test
    public void testToString() {
        objectUnitTests.testToString();
    }

    @Test
    public void testBiggerThen() {
        comparableUnitTests.testBiggerThen();
    }

    @Test
    public void testAsBigAs() {
        comparableUnitTests.testAsBigAs();
    }

    @Test
    public void testLessThen() {
        comparableUnitTests.testLessThen();
    }

}

package projekt;

import java.lang.reflect.Array;
import java.util.function.Function;

import static org.tudalgo.algoutils.student.Student.crash;
import static org.junit.jupiter.api.Assertions.*;



public class ComparableUnitTests<T extends Comparable<? super T>> {

    private final Function<Integer, T> testObjectFactory;

    private T[] testObjects;

    public ComparableUnitTests(Function<Integer, T> testObjectFactory) {
        this.testObjectFactory = testObjectFactory;
    }

    @SuppressWarnings("unchecked")
    public void initialize(int testObjectCount) {
        //testObjects=(T[]) new Object[testObjectCount];
        testObjects=(T[]) Array.newInstance(testObjectFactory.apply(0).getClass(),testObjectCount);
        for(int i=0;i<testObjectCount;i++) testObjects[i]=testObjectFactory.apply(i);
    }

    public void testBiggerThen() {
        for(int i=0;i<testObjects.length;i++) for(int j=0;i>j;j++) assertTrue(testObjectFactory.apply(i)
            .compareTo(testObjectFactory.apply(j))>0);
    }

    @SuppressWarnings("EqualsWithItself")
    public void testAsBigAs() {
        for(int i=0;i<testObjects.length;i++) assertTrue(testObjectFactory.apply(i).compareTo(testObjectFactory.apply(i))==0);
    }

    public void testLessThen() {
        for(int i=0;i<testObjects.length;i++) for(int j=i+1;j<testObjects.length;j++) assertTrue(testObjectFactory.apply(i)
            .compareTo(testObjectFactory.apply(j))<0);
    }
}

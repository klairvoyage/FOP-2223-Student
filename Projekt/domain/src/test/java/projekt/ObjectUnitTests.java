package projekt;

import java.lang.reflect.Array;
import java.util.function.Function;

import static org.tudalgo.algoutils.student.Student.crash;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;

public class ObjectUnitTests<T> {

    private final Function<Integer, T> testObjectFactory;
    private final Function<T, String> toString;

    private T[] testObjects;
    private T[] testObjectsReferenceEquality;
    private T[] testObjectsContentEquality;

    public ObjectUnitTests(Function<Integer, T> testObjectFactory, Function<T, String> toString) {
        this.testObjectFactory = testObjectFactory;
        this.toString = toString;
    }

    @SuppressWarnings("unchecked")
    public void initialize(int testObjectCount) {
        testObjects=(T[]) new Object[testObjectCount];
        testObjectsReferenceEquality=(T[]) new Object[testObjectCount];
        testObjectsContentEquality=(T[]) new Object[testObjectCount];
        for(int i=0;i<testObjectCount;i++){
            testObjects[i]=testObjectFactory.apply(i);
            testObjectsContentEquality[i]=testObjectFactory.apply(i);
            testObjectsReferenceEquality[i]=testObjects[i];
        }
    }

    public void testEquals() {
        assertArrayEquals(testObjects,testObjectsContentEquality);
        assertArrayEquals(testObjects,testObjectsReferenceEquality);
        assertArrayEquals(testObjectsReferenceEquality,testObjectsContentEquality);
        for(int i=0;i<testObjects.length;i++) for(int j=i+1;j<testObjects.length;j++)
            assertNotEquals(testObjects[i],testObjects[j]);
    }

    public void testHashCode() {
        assertArrayEquals(Stream.of(testObjects).map((myT)->myT.hashCode()).toArray(),
            Stream.of(testObjectsReferenceEquality).map((myT)->myT.hashCode()).toArray());
        assertArrayEquals(Stream.of(testObjectsReferenceEquality).map((myT)->myT.hashCode()).toArray(),
            Stream.of(testObjectsContentEquality).map((myT)->myT.hashCode()).toArray());
        assertArrayEquals(Stream.of(testObjects).map((myT)->myT.hashCode()).toArray(),
            Stream.of(testObjectsContentEquality).map((myT)->myT.hashCode()).toArray());
        for(int i=0;i<testObjects.length;i++) for(int j=i+1;j<testObjects.length;j++)
            assertNotEquals(testObjects[i],testObjects[j]);
    }

    public void testToString() {
        assertArrayEquals(Stream.of(testObjects).map((myT)->myT.toString()).toArray(),
                            Stream.of(testObjects).map(toString::apply).toArray());
    }

}

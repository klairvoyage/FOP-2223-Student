package h08.preconditions;

/**
 * Thrown when any value in a secondary array is outside the valid range.
 */
public class AtIndexPairException extends Exception {
    // TODO: H2 - remove if implemented
    public AtIndexPairException(int i, int j) {
        super("Index: ("+i+","+j+")");
    }
}

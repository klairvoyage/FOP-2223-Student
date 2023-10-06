package h08.preconditions;

/**
 * Thrown when a secondary array is null.
 */
public class AtIndexException extends RuntimeException {
    // TODO: H2 - remove if implemented
    public AtIndexException(int i) {
        super("Index: "+i);
    }
}

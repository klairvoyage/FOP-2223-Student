package h08.preconditions;

/**
 * Thrown when a max value is negative.
 */
public class WrongNumberException extends Exception {
    // TODO: H2 - remove if implemented
    public WrongNumberException(double yo) {
        super(""+yo);
    }
}

package h11.parse;

public record Projection(char source, String destination) {

    public Projection {
        checkDestinationNotEmpty(destination);
    }

    public Projection(String source, String destination) {
        this(getFirstChar(source), destination);
    }

    private static char getFirstChar(String source) {
        if (source.length() != 1) {
            throw new IllegalArgumentException("Source String must be exactly 1 character long: " + source);
        }
        return source.charAt(0);
    }

    private void checkDestinationNotEmpty(String destination) {
        if (destination.isEmpty()) {
            throw new IllegalArgumentException("Destination String cannot be empty");
        }
    }
}

package h06;

public record EvaluationResult(
    Type type,
    int nextIndex
) {
    @Override
    public String toString() {
        return "EvaluationResult{" +
            "type=" + type +
            ", nextIndex=" + nextIndex +
            '}';
    }

    public enum Type {
        CORRECT,
        INVALID_CHARACTER,
        NO_OPENING_BRACKET,
        NO_CLOSING_BRACKET,
        INVALID_CLOSING_BRACKET

    }
}

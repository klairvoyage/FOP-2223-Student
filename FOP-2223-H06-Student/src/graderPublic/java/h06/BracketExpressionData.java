package h06;

public record BracketExpressionData(
    String expression,
    int index,
    int expectedNextIndex,
    EvaluationResult.Type expectedType
) {

}

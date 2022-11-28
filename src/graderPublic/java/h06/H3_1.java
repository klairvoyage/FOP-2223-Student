package h06;

import h06.student.BracketExpressionStudent;
import org.junit.jupiter.params.ParameterizedTest;
import org.junitpioneer.jupiter.json.JsonClasspathSource;
import org.junitpioneer.jupiter.json.Property;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

@TestForSubmission
public class H3_1 {

    @ParameterizedTest
    @JsonClasspathSource("h06/H3_1_1.json")
    public void t15(
        @Property("data") BracketExpressionData data
    ) {
        BracketExpressionStudent.test(data);
    }

    @ParameterizedTest
    @JsonClasspathSource("h06/H3_1_2.json")
    public void t16(
        @Property("data") BracketExpressionData data
    ) {
        BracketExpressionStudent.test(data);
    }

    @ParameterizedTest
    @JsonClasspathSource("h06/H3_1_3.json")
    public void t17(
        @Property("data") BracketExpressionData data
    ) {
        BracketExpressionStudent.test(data);
    }

    @ParameterizedTest
    @JsonClasspathSource("h06/H3_1_4.json")
    public void t18(
        @Property("data") BracketExpressionData data
    ) {
        BracketExpressionStudent.test(data);
    }

    @ParameterizedTest
    @JsonClasspathSource("h06/H3_1_5.json")
    public void t19(
        @Property("data") BracketExpressionData data
    ) {
        BracketExpressionStudent.test(data);
    }
}

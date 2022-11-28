package h06;

import h06.student.BracketExpressionStudent;
import org.junit.jupiter.params.ParameterizedTest;
import org.junitpioneer.jupiter.json.JsonClasspathSource;
import org.junitpioneer.jupiter.json.Property;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

@TestForSubmission
public class H3_2 {

    @ParameterizedTest
    @JsonClasspathSource("h06/H3_2_1.json")
    public void t20(
        @Property("data") BracketExpressionData data
    ) {
        BracketExpressionStudent.test(data);
    }

    @ParameterizedTest
    @JsonClasspathSource("h06/H3_1_2.json")
    public void t21(
        @Property("data") BracketExpressionData data
    ) {
        BracketExpressionStudent.test(data);
    }
}

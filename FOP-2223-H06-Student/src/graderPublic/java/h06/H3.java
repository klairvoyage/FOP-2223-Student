package h06;

import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.tutor.general.assertions.Assertions4;

import static h06.student.BracketExpressionStudent.LINK_TO_EVALUATE_2;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.contextBuilder;

@TestForSubmission
public class H3 {

    @Test
    public void testRequirements() {
        Assertions4.assertIsNotIteratively(
            LINK_TO_EVALUATE_2.getCtElement(),
            contextBuilder().subject(LINK_TO_EVALUATE_2).build(),
            r -> "method is iterative"
        );
    }

}

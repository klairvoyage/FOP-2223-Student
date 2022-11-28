package h06;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junitpioneer.jupiter.json.JsonClasspathSource;
import org.junitpioneer.jupiter.json.Property;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.tutor.general.assertions.Assertions4;
import org.tudalgo.algoutils.tutor.general.conversion.ArrayConverter;
import org.tudalgo.algoutils.tutor.general.reflections.BasicMethodLink;

import static h06.student.StrangeFunctionsStudent.linkToDoTheRecursion;
import static h06.student.StrangeFunctionsStudent.linkToTransformArray2;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.contextBuilder;

@TestForSubmission
public class H2_2 extends H2 {

    @Override
    BasicMethodLink link() {
        return linkToTransformArray2();
    }

    @Test
    public void testRequirements() {
        Assertions4.assertIsNotIteratively(
            link().getCtElement(),
            contextBuilder().subject(link()).build(),
            r -> "method is iterative"
        );
        Assertions4.assertIsNotRecursively(
            link().getCtElement(),
            contextBuilder().subject(link()).build(),
            r -> "method is recursive"
        );
        Assertions4.assertIsNotIteratively(
            linkToDoTheRecursion().getCtElement(),
            contextBuilder().subject(linkToDoTheRecursion()).build(),
            r -> "method is iterative"
        );
    }

    @ParameterizedTest
    @JsonClasspathSource("h06/H2_1.json")
    @Override
    public void t11_t13(
        @Property("in") @ConvertWith(ArrayConverter.Double.class) Double[] in,
        @Property("out") @ConvertWith(ArrayConverter.Double.class) Double[] expected
    ) {
        super.t11_t13(in, expected);
    }
}

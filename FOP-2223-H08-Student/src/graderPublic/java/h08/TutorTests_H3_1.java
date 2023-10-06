package h08;

import h08.preconditions.Preconditions;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junitpioneer.jupiter.json.JsonClasspathSource;
import org.junitpioneer.jupiter.json.Property;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.sourcegrade.jagr.api.testing.ClassTransformer;
import org.sourcegrade.jagr.api.testing.TestCycle;
import org.sourcegrade.jagr.api.testing.extension.JagrExecutionCondition;
import org.sourcegrade.jagr.api.testing.extension.TestCycleResolver;
import org.tudalgo.algoutils.tutor.general.conversion.ArrayConverter;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestForSubmission
public class TutorTests_H3_1 {

    // DONE
    @ParameterizedTest
    @JsonClasspathSource("TutorTests_H1_1-addUpCalculatesSumCorrectly.json")
    public void checkPrimaryArrayNotNullHandlesRegularCaseCorrectly1(@Property("testArray") @ConvertWith(ArrayConverter.Double2D.class) Double[][] testArrayNode) {
        checkPrimaryArrayNotNullHandlesRegularCaseCorrectlyCore(testArrayNode);
    }

    private void checkPrimaryArrayNotNullHandlesRegularCaseCorrectlyCore(Double[][] testArray) {
        double[][] doubles = new double[testArray.length][];
        for (int i = 0; i < doubles.length; i++) {
            doubles[i] = Arrays.stream(testArray[i]).mapToDouble(Double::doubleValue).toArray();
        }

        assertDoesNotThrow(() -> Preconditions.checkPrimaryArrayNotNull(doubles),
            "Die Methode checkPrimaryArrayNotNull wirft eine unerwartete Exception.");
    }

    // DONE
    @Test
    @ExtendWith(TestCycleResolver.class)
    @ExtendWith(JagrExecutionCondition.class)
    public void checkNumberNotNegativeDeclaresThrowsClause(@NotNull TestCycle testCycle) {
        testCycle.getClassLoader().visitClass(Preconditions.class.getName(),
            new CT("checkNumberNotNegative", "(D)V", "h08/preconditions/WrongNumberException"));
    }

    // DONE
    @Test
    @ExtendWith(TestCycleResolver.class)
    @ExtendWith(JagrExecutionCondition.class)
    public void checkValuesInRangeDeclaresThrowsClause(@NotNull TestCycle testCycle) {
        testCycle.getClassLoader().visitClass(Preconditions.class.getName(),
            new CT("checkValuesInRange", "([[DD)V", "h08/preconditions/AtIndexPairException"));
    }

    // TODO: check that constructors of exceptions are called with the correct parameters, but without checking the message
    //  that is set (use byte code transformer to check constructor call)

    public static class CT implements ClassTransformer {
        private final String methodName;
        private final String descriptor;
        private final String expectedException;

        public CT(String methodName, String descriptor, String expectedException) {
            this.methodName = methodName;
            this.descriptor = descriptor;
            this.expectedException = expectedException;
        }

        @Override
        public String getName() {
            return "H3_1-transformer";
        }

        @Override
        public void transform(@NotNull final ClassReader reader, final ClassWriter writer) {
            reader.accept(new CV(methodName, descriptor, expectedException), 0);
        }

        private static class CV extends ClassVisitor {
            private final String methodName;
            private final String descriptor;
            private final String expectedException;

            protected CV(String methodName, String descriptor, String expectedException) {
                super(Opcodes.ASM9);
                this.methodName = methodName;
                this.descriptor = descriptor;
                this.expectedException = expectedException;
            }

            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                if (methodName.equals(name) && this.descriptor.equals(descriptor)) {
                    var message = String.format(
                        "Die Methode %s muss eine %s mittels genau einer throws-Klausel deklarieren.",
                        methodName,
                        expectedException);

                    if (exceptions == null || exceptions.length != 1) {
                        fail(message);
                    }

                    assertEquals(expectedException, exceptions[0], message);
                }

                return super.visitMethod(access, name, descriptor, signature, exceptions);
            }
        }
    }
}

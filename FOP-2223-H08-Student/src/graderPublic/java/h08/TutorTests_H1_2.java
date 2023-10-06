package h08;

import h08.calculation.ArrayCalculatorWithRuntimeExceptions;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

import static org.junit.jupiter.api.Assertions.assertTrue;

// DONE
@TestForSubmission
public class TutorTests_H1_2 {

    // DONE
    @Test
    @ExtendWith(TestCycleResolver.class)
    @ExtendWith(JagrExecutionCondition.class)
    public void addUpDoesNotExceedMaximumNumberOfThrowStatements(@NotNull TestCycle testCycle) {
        testCycle.getClassLoader().visitClass(ArrayCalculatorWithRuntimeExceptions.class.getName(), new Transformer());
    }

    public static class Transformer implements ClassTransformer {
        @Override
        public String getName() {
            return "Transformer";
        }

        @Override
        public void transform(final ClassReader reader, final ClassWriter writer) {
            reader.accept(new CV(), 0);
        }

        private static class CV extends ClassVisitor {
            protected CV() {
                super(Opcodes.ASM9);
            }

            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                if ("addUp".equals(name) && "([[DD)D".equals(descriptor)) {
                    return new MV();
                }

                return super.visitMethod(access, name, descriptor, signature, exceptions);
            }

            private static class MV extends MethodVisitor {
                private int executedThrows = 0;

                protected MV() {
                    super(Opcodes.ASM9);
                }

                @Override
                public void visitInsn(int opcode) {
                    if (opcode == Opcodes.ATHROW) {
                        executedThrows++;
                        assertTrue(executedThrows <= 4, "Es wurden mehr als die maximal erlaubten 4 throw-Anweisungen verwendet" +
                            ".");
                    }

                    super.visitInsn(opcode);
                }
            }
        }
    }
}

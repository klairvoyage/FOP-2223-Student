package h11;

import h11.h2.LSystemGrowerTest;
import h11.h2.LSystemGrowerTestCase;
import h11.h3.LSystemParserTest;
import h11.h3.ParsedLSystemTest;
import h11.h3.ParsedLSystemTestCase;
import h11.h4.AlgaeFibonacciGeneratorTest;
import h11.h4.AlgaeTestTest;
import h11.h4.FibonacciGeneratorImplTest;
import h11.h5.RandomChoicesTestCase;
import h11.h5.RandomLatinTestCase;
import h11.h5.RandomTest;
import h11.h6.GenerateTestCase;
import h11.h6.MakeProjectionTestCase;
import h11.h6.RandomLSystemGeneratorTest;
import h11.h7.LSystemAsLinesTestCase;
import h11.h7.LSystemToRandomLinesConverterTest;
import h11.h7.RandomSpacesTestCase;
import org.sourcegrade.jagr.api.rubric.*;

public class H11_RubricProvider implements RubricProvider {

    private static final Criterion H1_1 = Criterion.builder()
        .shortDescription("H11.1.1 | Das Axiom ist korrekt implementiert")
        .build();
    private static final Criterion H1_2 = Criterion.builder()
        .shortDescription("H11.1.2 | Die Projektion ist korrekt implementiert")
        .build();
    private static final Criterion H1 = Criterion.builder()
        .shortDescription("H11.1 | Algae")
        .addChildCriteria(H1_1, H1_2)
        .build();

    private static final Criterion H2_A = Criterion.builder()
        .shortDescription("H11.2.A | Der Stream ist unendlich")
        .maxPoints(2)
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> LSystemGrowerTest.class.getMethod("testThat_streamIsInfinite")))
            .pointsFailedMin()
            .pointsPassedMax()
            .build())
        .build();
    private static final Criterion H2_B = Criterion.builder()
        .shortDescription("H11.2.B | Das Wachstum wird korrekt berechnet")
        .maxPoints(2)
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> LSystemGrowerTest.class.getMethod("testGrow", LSystemGrowerTestCase.class)))
            .pointsFailedMin()
            .pointsPassedMax()
            .build())
        .build();
    private static final Criterion H2 = Criterion.builder()
        .shortDescription("H11.2 | Wachstum eines L-Systems")
        .addChildCriteria(H2_A, H2_B)
        .build();

    private static final Criterion H3_1_A = Criterion.builder()
        .shortDescription("H11.3.1.A | Die erste Projektion gibt das Axiom vor")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> ParsedLSystemTest.class.getMethod("testThat_firstProjectionGivesAxiom", ParsedLSystemTestCase.class)))
            .pointsFailedMin()
            .pointsPassedMax()
            .build())
        .build();
    private static final Criterion H3_1_B = Criterion.builder()
        .shortDescription("H11.3.1.B | Ist der aktuale Parameter bekannt, wird korrekt projektiert")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> ParsedLSystemTest.class.getMethod("testThat_projectionsOfKnownProject", ParsedLSystemTestCase.class)))
            .pointsFailedMin()
            .pointsPassedMax()
            .build())
        .build();
    private static final Criterion H3_1_C = Criterion.builder()
        .shortDescription("H11.3.1.C | Ist der aktuale Parameter nicht bekannt, wird korrekt projektiert")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> ParsedLSystemTest.class.getMethod("testThat_projectionsOfUnknownDoesNotProject", ParsedLSystemTestCase.class)))
            .pointsFailedMin()
            .pointsPassedMax()
            .build())
        .build();
    private static final Criterion H3_1 = Criterion.builder()
        .shortDescription("H11.3.1 | Eingelesene L-Systeme")
        .addChildCriteria(H3_1_A, H3_1_B, H3_1_C)
        .build();
    private static final Criterion H3_2_A = Criterion.builder()
        .shortDescription("H11.3.2.A | Der Parser parsed einfachen Input korrekt")
        .maxPoints(2)
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> LSystemParserTest.class.getMethod("testThat_parserParsesProjections")))
            .pointsFailedMin()
            .pointsPassedMax()
            .build())
        .build();
    private static final Criterion H3_2_B = Criterion.builder()
        .shortDescription("H11.3.2.A | Der Parser parsed mittleren Input korrekt")
        .maxPoints(2)
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> LSystemParserTest.class.getMethod("testThat_parserIgnoresInlineCommentsAndWhitespace")))
            .pointsFailedMin()
            .pointsPassedMax()
            .build())
        .build();
    private static final Criterion H3_2_C = Criterion.builder()
        .shortDescription("H11.3.2.A | Der Parser parsed schweren Input korrekt")
        .maxPoints(2)
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> LSystemParserTest.class.getMethod("testThat_parserIgnoresLinesWithCommentsAndEmptyLines")))
            .pointsFailedMin()
            .pointsPassedMax()
            .build())
        .build();
    private static final Criterion H3_2 = Criterion.builder()
        .shortDescription("H11.3.2 | Der Parser")
        .addChildCriteria(H3_2_A, H3_2_B, H3_2_C)
        .build();
    private static final Criterion H3 = Criterion.builder()
        .shortDescription("H11.3 | L-Systeme in Dateien speichern")
        .addChildCriteria(H3_1, H3_2)
        .build();

    private static final Criterion H4_1_A = Criterion.builder()
        .shortDescription("H11.4.1.A | Die ersten zwei Werte sind jeweils korrekt")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> FibonacciGeneratorImplTest.class.getMethod("testThat_initialIsCorrect", int.class)))
            .pointsFailedMin()
            .pointsPassedMax()
            .build())
        .build();
    private static final Criterion H4_1_B = Criterion.builder()
        .shortDescription("H11.4.1.B | Alle Werte sind jeweils korrekt")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> FibonacciGeneratorImplTest.class.getMethod("testThat_fibsMatchHardcodedValues")))
            .requirePass(JUnitTestRef.ofMethod(() -> FibonacciGeneratorImplTest.class.getMethod("testThat_fibsAreCorrect", int.class)))
            .pointsFailedMin()
            .pointsPassedMax()
            .build())
        .build();
    private static final Criterion H4_1 = Criterion.builder()
        .shortDescription("H11.4.1 | Generieren der Fibonaccizahlen")
        .addChildCriteria(H4_1_A, H4_1_B)
        .build();
    private static final Criterion H4_2_A = Criterion.builder()
        .shortDescription("H11.4.2.A | Die ersten zwei Werte sind jeweils korrekt")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> AlgaeFibonacciGeneratorTest.class.getMethod("testThat_initialIsCorrect", int.class)))
            .pointsFailedMin()
            .pointsPassedMax()
            .build())
        .build();
    private static final Criterion H4_2_B = Criterion.builder()
        .shortDescription("H11.4.2.B | Alle Werte sind jeweils korrekt")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> AlgaeFibonacciGeneratorTest.class.getMethod("testThat_fibsAreCorrect", int.class)))
            .pointsFailedMin()
            .pointsPassedMax()
            .build())
        .build();
    private static final Criterion H4_2 = Criterion.builder()
        .shortDescription("H11.4.2 | Auslesen der Fibonaccizahlen aus der Algae")
        .addChildCriteria(H4_2_A, H4_2_B)
        .build();
    private static final Criterion H4_3_A = Criterion.builder()
        .shortDescription("H11.4.3.A | Der Test ist korrekt implementiert")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> AlgaeTestTest.class.getMethod("testThat_algaeTestAcceptsPositive", int.class)))
            .requirePass(JUnitTestRef.ofMethod(() -> AlgaeTestTest.class.getMethod("testThat_algaeTestRejectsWrongSize", int.class)))
            .requirePass(JUnitTestRef.ofMethod(() -> AlgaeTestTest.class.getMethod("testThat_algaeTestRejectsWrongValues", int.class)))
            .pointsFailedMin()
            .pointsPassedMax()
            .build())
        .build();
    private static final Criterion H4_3 = Criterion.builder()
        .shortDescription("H11.4.3 | Der Testcase")
        .addChildCriteria(H4_3_A)
        .build();
    private static final Criterion H4 = Criterion.builder()
        .shortDescription("H11.4 | Testen der Algae")
        .addChildCriteria(H4_1, H4_2, H4_3)
        .build();

    private static final Criterion H5_1 = Criterion.builder()
        .shortDescription("H11.5.1 | Pythons choices")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> RandomTest.class.getMethod("testChoices", RandomChoicesTestCase.class)))
            .pointsFailedMin()
            .pointsPassedMax()
            .build())
        .build();
    private static final Criterion H5_2 = Criterion.builder()
        .shortDescription("H11.5.2 | Zufälliges Latein")
        .maxPoints(2)
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> RandomTest.class.getMethod("testLatin", RandomLatinTestCase.class)))
            .pointsFailedMin()
            .pointsPassedMax()
            .build())
        .build();
    private static final Criterion H5 = Criterion.builder()
        .shortDescription("H11.5 | Erweiterung von Random")
        .addChildCriteria(H5_1, H5_2)
        .build();

    private static final Criterion H6_1 = Criterion.builder()
        .shortDescription("H11.6.1 | Zufällige Projektionen")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.or(
                JUnitTestRef.ofMethod(() -> RandomLSystemGeneratorTest.class.getMethod("testMakeProjectionInclusive", MakeProjectionTestCase.class)),
                JUnitTestRef.ofMethod(() -> RandomLSystemGeneratorTest.class.getMethod("testMakeProjectionExclusive", MakeProjectionTestCase.class))
            ))
            .pointsFailedMin()
            .pointsPassedMax()
            .build())
        .build();
    private static final Criterion H6_2_A = Criterion.builder()
        .shortDescription("H11.6.2.A | Es gibt keine doppelten Quellen")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> RandomLSystemGeneratorTest.class.getMethod("testThat_sourcesAreUnique", int.class)))
            .pointsFailedMin()
            .pointsPassedMax()
            .build())
        .build();
    private static final Criterion H6_2_B = Criterion.builder()
        .shortDescription("H11.6.2.B | Die L-Systeme werden korrekt generiert")
        .maxPoints(2)
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.or(
                JUnitTestRef.ofMethod(() -> RandomLSystemGeneratorTest.class.getMethod("testGenerateExclusive", GenerateTestCase.class)),
                JUnitTestRef.ofMethod(() -> RandomLSystemGeneratorTest.class.getMethod("testGenerateInclusive", GenerateTestCase.class))
            ))
            .pointsFailedMin()
            .pointsPassedMax()
            .build())
        .build();
    private static final Criterion H6_2 = Criterion.builder()
        .shortDescription("H11.6.2 | Zufällige Systeme")
        .addChildCriteria(H6_2_A, H6_2_B)
        .build();
    private static final Criterion H6 = Criterion.builder()
        .shortDescription("H11.6 | Zufällige L-Systeme")
        .addChildCriteria(H6_1, H6_2)
        .build();

    private static final Criterion H7_1 = Criterion.builder()
        .shortDescription("H11.7.1 | Zufällige Leerzeichen")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.or(
                JUnitTestRef.ofMethod(() -> LSystemToRandomLinesConverterTest.class.getMethod("testRandomSpacingExclusive", RandomSpacesTestCase.class)),
                JUnitTestRef.ofMethod(() -> LSystemToRandomLinesConverterTest.class.getMethod("testRandomSpacingInclusive", RandomSpacesTestCase.class))
            ))
            .pointsFailedMin()
            .pointsPassedMax()
            .build())
        .build();
    private static final Criterion H7_2 = Criterion.builder()
        .shortDescription("H11.7.2 | Darstellung für das ganze System")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.or(
                JUnitTestRef.ofMethod(() -> LSystemToRandomLinesConverterTest.class.getMethod("testLSystemAsLinesExclusive", LSystemAsLinesTestCase.class)),
                JUnitTestRef.ofMethod(() -> LSystemToRandomLinesConverterTest.class.getMethod("testLSystemAsLinesInclusive", LSystemAsLinesTestCase.class)) ))
            .pointsFailedMin()
            .pointsPassedMax()
            .build())
        .build();
    private static final Criterion H7 = Criterion.builder()
        .shortDescription("H11.7 | Zufällige Darstellung eines L-Systems")
        .addChildCriteria(H7_1, H7_2)
        .build();

    public static final Rubric RUBRIC = Rubric.builder()
        .title("H11 | L-Systeme")
        .addChildCriteria(H1, H2, H3, H4, H5, H6, H7)
        .build();

    @Override
    public Rubric getRubric() {
        return RUBRIC;
    }
}

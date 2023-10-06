package h06;

import org.sourcegrade.jagr.api.rubric.Criterion;
import org.sourcegrade.jagr.api.rubric.Grader;
import org.sourcegrade.jagr.api.rubric.JUnitTestRef;
import org.sourcegrade.jagr.api.rubric.Rubric;
import org.sourcegrade.jagr.api.rubric.RubricProvider;

import static org.tudalgo.algoutils.tutor.general.stringify.HTML.it;
import static org.tudalgo.algoutils.tutor.general.stringify.HTML.tt;

public class H06_RubricProvider implements RubricProvider {

    static Criterion C1 = Criterion.builder()
        .shortDescription(String.format("Beide Bedingungen sind in %s korrekt übersetzt.", tt("strangeFunction1")))
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H1_1_1.class.getMethod("t1_t3_1")))
            .requirePass(JUnitTestRef.ofMethod(() -> H1_1_1.class.getMethod("t1_t3_2")))
            .pointsPassedMax()
            .build()
        )
        .build();

    static Criterion C2 = Criterion.builder()
        .shortDescription(String.format("Der rekursive Aufruf ist in %s korrekt übersetzt.", tt("strangeFunction1")))
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H1_1_1.class.getMethod("t2_t4_1")))
            .pointsPassedMax()
            .build()
        )
        .build();

    static Criterion C3 = Criterion.builder()
        .shortDescription(String.format("Beide Bedingungen sind in %s korrekt übersetzt.", tt("strangeFunction2")))
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H1_1_2.class.getMethod("t1_t3_1")))
            .requirePass(JUnitTestRef.ofMethod(() -> H1_1_2.class.getMethod("t1_t3_2")))
            .pointsPassedMax()
            .build()
        )
        .build();

    static Criterion C4 = Criterion.builder()
        .shortDescription(String.format("Der rekursive Aufruf ist in %s korrekt übersetzt.", tt("strangeFunction2")))
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H1_1_2.class.getMethod("t2_t4_1")))
            .pointsPassedMax()
            .build()
        )
        .build();

    static Criterion C5 = Criterion.builder()
        .shortDescription("Beide Methoden sind korrekt übersetzt.")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H1_1_1.class.getMethod("t1_t3_1")))
            .requirePass(JUnitTestRef.ofMethod(() -> H1_1_1.class.getMethod("t1_t3_2")))
            .requirePass(JUnitTestRef.ofMethod(() -> H1_1_1.class.getMethod("t2_t4_1")))
            .requirePass(JUnitTestRef.ofMethod(() -> H1_1_2.class.getMethod("t5_1")))
            .requirePass(JUnitTestRef.ofMethod(() -> H1_1_2.class.getMethod("t5_2")))
            .requirePass(JUnitTestRef.ofMethod(() -> H1_1_2.class.getMethod("t5_3")))

            .pointsPassedMax()
            .build()
        )
        .build();


    static Criterion C_H1_1 = Criterion.builder()
        .shortDescription("H1.1 | Erste Rekursion auf Zahlen")
        .addChildCriteria(C1, C2, C3, C4, C5)
        .build();

    static Criterion C6 = Criterion.builder()
        .shortDescription(String.format("Alle Bedingungen sind in %s korrekt übersetzt.", tt("understandable1")))
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H1_2_1.class.getMethod("t6_t8_1")))
            .requirePass(JUnitTestRef.ofMethod(() -> H1_2_1.class.getMethod("t6_t8_2")))
            .requirePass(JUnitTestRef.ofMethod(() -> H1_2_1.class.getMethod("t6_t8_3")))
            .pointsPassedMax()
            .build()
        )
        .build();

    static Criterion C7 = Criterion.builder()
        .shortDescription(String.format("Die rekursiven Aufrufe sind in %s korrekt übersetzt.", tt("understandable1")))
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H1_2_1.class.getMethod("t7_t9_1")))
            .requirePass(JUnitTestRef.ofMethod(() -> H1_2_1.class.getMethod("t7_t9_2")))
            .pointsPassedMax()
            .build()
        )
        .build();

    static Criterion C8 = Criterion.builder()
        .shortDescription(String.format("Alle Bedingungen sind in %s korrekt übersetzt.", tt("understandable2")))
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H1_2_2.class.getMethod("t6_t8_1")))
            .requirePass(JUnitTestRef.ofMethod(() -> H1_2_2.class.getMethod("t6_t8_2")))
            .requirePass(JUnitTestRef.ofMethod(() -> H1_2_2.class.getMethod("t6_t8_3")))
            .pointsPassedMax()
            .build()
        )
        .build();

    static Criterion C9 = Criterion.builder()
        .shortDescription(String.format("Die rekursiven Aufrufe sind in %s korrekt übersetzt.", tt("understandable2")))
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H1_2_2.class.getMethod("t7_t9_1")))
            .requirePass(JUnitTestRef.ofMethod(() -> H1_2_2.class.getMethod("t7_t9_2")))
            .pointsPassedMax()
            .build()
        )
        .build();

    static Criterion C10 = Criterion.builder()
        .shortDescription("Beide Methoden sind korrekt übersetzt.")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H1_2_1.class.getMethod("t6_t8_1")))
            .requirePass(JUnitTestRef.ofMethod(() -> H1_2_1.class.getMethod("t6_t8_2")))
            .requirePass(JUnitTestRef.ofMethod(() -> H1_2_1.class.getMethod("t6_t8_3")))
            .requirePass(JUnitTestRef.ofMethod(() -> H1_2_1.class.getMethod("t7_t9_1")))
            .requirePass(JUnitTestRef.ofMethod(() -> H1_2_1.class.getMethod("t7_t9_2")))
            .requirePass(JUnitTestRef.ofMethod(() -> H1_2_2.class.getMethod("t6_t8_1")))
            .requirePass(JUnitTestRef.ofMethod(() -> H1_2_2.class.getMethod("t6_t8_2")))
            .requirePass(JUnitTestRef.ofMethod(() -> H1_2_2.class.getMethod("t6_t8_3")))
            .requirePass(JUnitTestRef.ofMethod(() -> H1_2_2.class.getMethod("t7_t9_1")))
            .requirePass(JUnitTestRef.ofMethod(() -> H1_2_2.class.getMethod("t7_t9_2")))
            .requirePass(JUnitTestRef.ofMethod(() -> H1_2_1.class.getMethod("t10_1")))
            .requirePass(JUnitTestRef.ofMethod(() -> H1_2_1.class.getMethod("t10_2")))
            .requirePass(JUnitTestRef.ofMethod(() -> H1_2_2.class.getMethod("t10_1")))
            .requirePass(JUnitTestRef.ofMethod(() -> H1_2_2.class.getMethod("t10_2")))
            .pointsPassedMax()
            .build()
        )
        .build();

    static Criterion H1_2 = Criterion.builder()
        .shortDescription("H1.2 | Zweite Rekursion auf Zahlen")
        .addChildCriteria(C6, C7, C8, C9, C10)
        .build();

    static Criterion H1 = Criterion.builder()
        .shortDescription("H1 | Rekursion auf Zahlen")
        .addChildCriteria(C_H1_1, H1_2)
        .build();

    static Criterion C11 = Criterion.builder()
        .shortDescription("Methode \\<samp\\>transformArray1\\</samp\\> funktioniert korrekt.")
        .maxPoints(2)
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H2_1.class.getMethod("testRequirements")))
            .requirePass(JUnitTestRef.ofMethod(() -> H2_1.class.getMethod("t11_t13", Double[].class, Double[].class)))
            .pointsPassedMax()
            .build()
        )
        .build();

    static Criterion C12 = Criterion.builder()
        .shortDescription("Methode \\<samp\\>transformArray1\\</samp\\> ist korrekt.")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H2_1.class.getMethod("testRequirements")))
            .requirePass(JUnitTestRef.ofMethod(() -> H2_1.class.getMethod("t11_t13", Double[].class, Double[].class)))
            .pointsPassedMax()
            .build()
        )
        .build();

    static Criterion C13 = Criterion.builder()
        .shortDescription("Methode \\<samp\\>transformArray2\\</samp\\> funktioniert korrekt.")
        .maxPoints(2)
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H2_2.class.getMethod("testRequirements")))
            .requirePass(JUnitTestRef.ofMethod(() -> H2_2.class.getMethod("t11_t13", Double[].class, Double[].class)))
            .pointsPassedMax()
            .build()
        )
        .build();

    static Criterion C14 = Criterion.builder()
        .shortDescription("Methode \\<samp\\>transformArray2\\</samp\\> ist korrekt.")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H2_2.class.getMethod("testRequirements")))
            .requirePass(JUnitTestRef.ofMethod(() -> H2_2.class.getMethod("t11_t13", Double[].class, Double[].class)))
            .pointsPassedMax()
            .build()
        )
        .build();

    static Criterion H2 = Criterion.builder()
        .shortDescription("H2 | Iterativ/Rekursiv auf Arrays")
        .addChildCriteria(C11, C12, C13, C14)
        .build();

    static Criterion C15 = Criterion.builder()
        .shortDescription(String.format("%s funktioniert für korrekte Ausdrücke korrekt, wenn %s", tt("evaluate"), it("nextIndex = index + 2")))
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H3.class.getMethod("testRequirements")))
            .requirePass(JUnitTestRef.ofMethod(() -> H3_1.class.getMethod("t15", BracketExpressionData.class)))
            .pointsPassedMax()
            .build()
        )
        .build();

    static Criterion C16 = Criterion.builder()
        .shortDescription(String.format("%s funktioniert für korrekte Ausdrücke korrekt.", tt("evaluate")))
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H3.class.getMethod("testRequirements")))
            .requirePass(JUnitTestRef.ofMethod(() -> H3_1.class.getMethod("t15", BracketExpressionData.class)))
            .requirePass(JUnitTestRef.ofMethod(() -> H3_1.class.getMethod("t16", BracketExpressionData.class)))
            .pointsPassedMax()
            .build()
        )
        .build();

    static Criterion C17 = Criterion.builder()
        .shortDescription(String.format("%s funktioniert für inkorrekte Ausdrücke korrekt, wenn %s oder %s.", tt("evaluate"), it("nextIndex = index"), it("nextIndex = index + 1")))
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H3.class.getMethod("testRequirements")))
            .requirePass(JUnitTestRef.ofMethod(() -> H3_1.class.getMethod("t17", BracketExpressionData.class)))
            .pointsPassedMax()
            .build()
        )
        .build();

    static Criterion C18 = Criterion.builder()
        .shortDescription(String.format("%s funktioniert für inkorrekte Ausdrücke korrekt.", tt("evaluate")))
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H3.class.getMethod("testRequirements")))
            .requirePass(JUnitTestRef.ofMethod(() -> H3_1.class.getMethod("t18", BracketExpressionData.class)))
            .pointsPassedMax()
            .build()
        )
        .build();

    static Criterion C19 = Criterion.builder()
        .shortDescription(String.format("%s funktioniert korrekt.", tt("evaluate")))
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H3.class.getMethod("testRequirements")))
            .requirePass(JUnitTestRef.ofMethod(() -> H3_1.class.getMethod("t19", BracketExpressionData.class)))
            .requirePass(JUnitTestRef.ofMethod(() -> H3_1.class.getMethod("t15", BracketExpressionData.class)))
            .requirePass(JUnitTestRef.ofMethod(() -> H3_1.class.getMethod("t16", BracketExpressionData.class)))
            .requirePass(JUnitTestRef.ofMethod(() -> H3_1.class.getMethod("t17", BracketExpressionData.class)))
            .requirePass(JUnitTestRef.ofMethod(() -> H3_1.class.getMethod("t18", BracketExpressionData.class)))
            .pointsPassedMax()
            .build()
        )
        .build();


    static Criterion H3_1 = Criterion.builder()
        .shortDescription("H3.1 | Nicht-Disjunkte Klammerausdrücke")
        .addChildCriteria(C15, C16, C17, C18, C19)
        .build();

    static Criterion C22 = Criterion.builder()
        .shortDescription(String.format("%s funktioniert für korrekte disjunkte Ausdrücke korrekt.", tt("evaluate")))
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H3.class.getMethod("testRequirements")))
            .requirePass(JUnitTestRef.ofMethod(() -> H3_2.class.getMethod("t20", BracketExpressionData.class)))
            .pointsPassedMax()
            .build()
        )
        .build();

    static Criterion C23 = Criterion.builder()
        .shortDescription(String.format("%s funktioniert für inkorrekte disjunkte Ausdrücke korrekt.", tt("evaluate")))
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H3.class.getMethod("testRequirements")))
            .requirePass(JUnitTestRef.ofMethod(() -> H3_2.class.getMethod("t21", BracketExpressionData.class)))
            .pointsPassedMax()
            .build()
        )
        .build();

    static Criterion C24 = Criterion.builder()
        .shortDescription("%s funktioniert korrekt.".formatted(tt("evaluate")))
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H3.class.getMethod("testRequirements")))
            .requirePass(JUnitTestRef.ofMethod(() -> H3_2.class.getMethod("t20", BracketExpressionData.class)))
            .requirePass(JUnitTestRef.ofMethod(() -> H3_2.class.getMethod("t21", BracketExpressionData.class)))
            .pointsPassedMax()
            .build()
        )
        .build();

    static Criterion H3_2 = Criterion.builder()
        .shortDescription("H3.2 | Disjunkte Klammerausdrücke")
        .addChildCriteria(C22, C23, C24)
        .build();

    static Criterion H3 = Criterion.builder()
        .shortDescription("H3 |  Korrektheit von Klammerausdrücken")
        .addChildCriteria(H3_1, H3_2)
        .build();

    @Override
    public Rubric getRubric() {
        return Rubric.builder()
            .title("H06 | Strange Functions")
            .addChildCriteria(H1, H2, H3)
            .build();
    }
}

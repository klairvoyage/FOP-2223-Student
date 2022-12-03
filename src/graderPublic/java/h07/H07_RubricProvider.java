package h07;

import h07.h1.h1_1.ReduceDoubleArrayTest;
import h07.h1.h1_2.PairwiseDoubleArrayBinaryOperatorGivingArrayTest;
import h07.h1.h1_3.PairwiseDoubleArrayBinaryOperatorGivingScalarTest;
import h07.h2.h2_1.DoubleSumWithCoefficientsOpTest;
import h07.h2.h2_2.EuclideanNormTest;
import h07.h2.h2_3.DoubleMaxOfTwoTest;
import h07.h2.h2_4.ComposedDoubleBinaryOperatorTest;
import h07.h3.h3_1.DoubleSumWithCoefficientsOpAsLambdaTest;
import h07.h3.h3_2.EuclideanNormAsLambdaTest;
import h07.h3.h3_3.DoubleMaxOfTwoAsLambdaTest;
import h07.h3.h3_4.ComposedDoubleBinaryOperatorAsLambdaTest;
import h07.h4.h4_1.BuildOperatorTest;
import h07.h4.h4_2.BuildOperatorWithNewTest;
import h07.h4.h4_3.BuildOperatorWithLambdaTest;
import org.sourcegrade.jagr.api.rubric.*;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.regex.Pattern;

public class H07_RubricProvider implements RubricProvider {

    private static final Function<String, String> CODE_TAGIFY = s -> Pattern.compile("\\[\\[\\[(.+?)]]]")
        .matcher(s)
        .replaceAll(matchResult -> "\\\\<code\\\\>%s\\\\</code\\\\>".formatted(matchResult.group(1)));

    private static final BiFunction<String, Integer, Criterion> UNTESTED_CRITERION = (s, maxPoints) -> Criterion.builder()
        .shortDescription(CODE_TAGIFY.apply(s))
        .grader((testCycle, criterion) -> new GradeResult() {
            @Override
            public int getMinPoints() {
                return 0;
            }

            @Override
            public int getMaxPoints() {
                return maxPoints;
            }

            @Override
            public List<String> getComments() {
                return List.of("Not tested by public grader");
            }
        })
        .maxPoints(maxPoints)
        .build();

    private static final BiFunction<String, Callable<Method>, Criterion> DEFAULT_CRITERION = (s, methodCallable) ->
        Criterion.builder()
            .shortDescription(CODE_TAGIFY.apply(s))
            .grader(Grader.testAwareBuilder()
                .requirePass(JUnitTestRef.ofMethod(methodCallable))
                .pointsFailedMin()
                .pointsPassedMax()
                .build())
            .build();

    private static final Criterion CRITERION_H1_1 = Criterion
        .builder()
        .shortDescription(CODE_TAGIFY.apply("H1.1: Unäre Filter-Klasse auf \"Array von [[[double]]]\""))
        .addChildCriteria(
            DEFAULT_CRITERION.apply(
                "Die Methode liefert korrekterweise [[[null]]] zurück, sollte sie mit [[[null]]] aufgerufen werden.",
                () -> ReduceDoubleArrayTest.class.getMethod("testNullInput")
            ),
            UNTESTED_CRITERION.apply("Die Rückgabe der Methode hat die korrekte Länge.", 1),
            DEFAULT_CRITERION.apply(
                "Die Methode liefert korrekte Ergebnisse bei Verwendung verschiedener Operatoren.",
                () -> ReduceDoubleArrayTest.class.getMethod("testResult", String.class, String.class, String.class)
            ),
            UNTESTED_CRITERION.apply("Die Methode verändert das übergebene Array nicht.", 1)
        )
        .build();

    private static final Criterion CRITERION_H1_2 = Criterion
        .builder()
        .shortDescription(CODE_TAGIFY.apply("H1.2: Binäre Map-Klasse auf \"Array von [[[double]]]\""))
        .addChildCriteria(
            DEFAULT_CRITERION.apply(
                "Die Methode liefert korrekterweise [[[null]]] zurück, sollte einer ihrer Parameter ebenfalls [[[null]]] sein.",
                () -> PairwiseDoubleArrayBinaryOperatorGivingArrayTest.class.getMethod("testNullInput")
            ),
            UNTESTED_CRITERION.apply("Die Rückgabe der Methode hat die korrekte Länge.", 1),
            DEFAULT_CRITERION.apply(
                "Die Methode liefert korrekte Ergebnisse bei Verwendung verschiedener Operatoren.",
                () -> PairwiseDoubleArrayBinaryOperatorGivingArrayTest.class.getMethod("testResult", String.class, String.class, String.class, String.class)
            ),
            UNTESTED_CRITERION.apply("Die Methode verändert die übergebenen Arrays nicht.", 1)
        )
        .build();

    private static final Criterion CRITERION_H1_3 = Criterion
        .builder()
        .shortDescription(CODE_TAGIFY.apply("H1.3: Binäre Fold-Klasse auf \"Array von [[[double]]]\""))
        .addChildCriteria(
            DEFAULT_CRITERION.apply(
                "Die Methode liefert korrekte Ergebnisse bei Verwendung verschiedener Operatoren.",
                () -> PairwiseDoubleArrayBinaryOperatorGivingScalarTest.class.getMethod("testResult", String.class, String.class, String.class, String.class, String.class, String.class)
            ),
            DEFAULT_CRITERION.apply(
                "Die Methode verwendet keine Rekursion.",
                () -> PairwiseDoubleArrayBinaryOperatorGivingScalarTest.class.getMethod("checkRecursion", String.class, String.class, String.class, String.class, String.class)
            ),
            UNTESTED_CRITERION.apply("Die Methode verwendet lediglich eine Schleife.", 1)
        )
        .build();

    private static final Criterion CRITERION_H1 = Criterion
        .builder()
        .shortDescription(CODE_TAGIFY.apply("H1: Unäre und binäre Operatoren auf \"Array von [[[double]]]\" als Functional Interfaces"))
        .addChildCriteria(
            CRITERION_H1_1,
            CRITERION_H1_2,
            CRITERION_H1_3
        )
        .build();

    private static final Criterion CRITERION_H2_1 = Criterion
        .builder()
        .shortDescription(CODE_TAGIFY.apply("H2.1: Erste binäre Operatorklasse auf [[[double]]]"))
        .addChildCriteria(
            DEFAULT_CRITERION.apply(
                "Die Methode liefert korrekte Ergebnisse bei verschiedenen Eingabewerten.",
                () -> DoubleSumWithCoefficientsOpTest.class.getMethod("testResults", double.class, double.class, double.class, double.class, double.class)
            )
        )
        .build();

    private static final Criterion CRITERION_H2_2 = Criterion
        .builder()
        .shortDescription(CODE_TAGIFY.apply("H2.2: Zweite binäre Operatorklasse auf [[[double]]]"))
        .addChildCriteria(
            DEFAULT_CRITERION.apply(
                "Die Methode liefert korrekte Ergebnisse bei verschiedenen Eingabewerten.",
                () -> EuclideanNormTest.class.getMethod("testResults", double.class, double.class, double.class)
            )
        )
        .build();

    private static final Criterion CRITERION_H2_3 = Criterion
        .builder()
        .shortDescription(CODE_TAGIFY.apply("H2.3: Dritte binäre Operatorklasse auf [[[double]]]"))
        .addChildCriteria(
            DEFAULT_CRITERION.apply(
                "Die Methode liefert korrekte Ergebnisse bei verschiedenen Eingabewerten.",
                () -> DoubleMaxOfTwoTest.class.getMethod("testResults", double.class, double.class, double.class)
            )
        )
        .build();

    private static final Criterion CRITERION_H2_4 = Criterion
        .builder()
        .shortDescription(CODE_TAGIFY.apply("H2.4: Vierte binäre Operatorklasse auf [[[double]]]"))
        .addChildCriteria(
            DEFAULT_CRITERION.apply(
                "Die Methode liefert korrekte Ergebnisse bei verschiedenen Eingabewerten.",
                () -> ComposedDoubleBinaryOperatorTest.class.getMethod("testResults", String.class, String.class, String.class, double.class, double.class, double.class)
            )
        )
        .build();

    private static final Criterion CRITERION_H2 = Criterion
        .builder()
        .shortDescription(CODE_TAGIFY.apply("H2: Binäre Operatoren auf [[[double]]] als Functional Interfaces"))
        .addChildCriteria(
            CRITERION_H2_1,
            CRITERION_H2_2,
            CRITERION_H2_3,
            CRITERION_H2_4
        )
        .build();

    private static final Criterion CRITERION_H3_1 = Criterion
        .builder()
        .shortDescription("H3.1: Lambda-Ausdruck anstelle von DoubleSumWithCoefficientsOp")
        .addChildCriteria(
            DEFAULT_CRITERION.apply(
                "Die Methode liefert korrekte Ergebnisse bei verschiedenen Eingabewerten.",
                () -> DoubleSumWithCoefficientsOpAsLambdaTest.class.getMethod("testResults", double.class, double.class, double.class, double.class, double.class)
            ),
            DEFAULT_CRITERION.apply(
                "Die Methode liefert einen Lambda-Ausdruck in Standardform.",
                () -> DoubleSumWithCoefficientsOpAsLambdaTest.class.getMethod("testLambdaExpression")
            )
        )
        .build();

    private static final Criterion CRITERION_H3_2 = Criterion
        .builder()
        .shortDescription("H3.2: Lambda-Ausdruck anstelle von EuclideanNorm")
        .addChildCriteria(
            DEFAULT_CRITERION.apply(
                "Die Methode liefert korrekte Ergebnisse bei verschiedenen Eingabewerten.",
                () -> EuclideanNormAsLambdaTest.class.getMethod("testResults", double.class, double.class, double.class)
            ),
            DEFAULT_CRITERION.apply(
                "Die Methode liefert einen Lambda-Ausdruck in Standardform.",
                () -> EuclideanNormAsLambdaTest.class.getMethod("testLambdaExpression")
            )
        )
        .build();

    private static final Criterion CRITERION_H3_3 = Criterion
        .builder()
        .shortDescription("H3.3: Lambda-Ausdruck anstelle von DoubleMaxOfTwo")
        .addChildCriteria(
            DEFAULT_CRITERION.apply(
                "Die Methode liefert korrekte Ergebnisse bei verschiedenen Eingabewerten.",
                () -> DoubleMaxOfTwoAsLambdaTest.class.getMethod("testResults", double.class, double.class, double.class)
            ),
            DEFAULT_CRITERION.apply(
                "Die Methode liefert einen Lambda-Ausdruck in Kurzform.",
                () -> DoubleMaxOfTwoAsLambdaTest.class.getMethod("testLambdaExpression")
            ),
            DEFAULT_CRITERION.apply(
                "Die Methode verwendet im Falle von [[[false]]] eine Methodenreferenz auf die Methode max der Klasse Math.",
                () -> DoubleMaxOfTwoAsLambdaTest.class.getMethod("testMethodReference")
            ),
            UNTESTED_CRITERION.apply(
                "Die Methode verwendet im Falle von [[[true]]] einen Lambda-Ausdruck mit dem Bedingungsoperator \"<\".", 1)
        )
        .build();

    private static final Criterion CRITERION_H3_4 = Criterion
        .builder()
        .shortDescription("H3.4: Lambda-Ausdruck anstelle von ComposedDoubleBinaryOperator")
        .addChildCriteria(
            DEFAULT_CRITERION.apply(
                "Die Methode liefert korrekte Ergebnisse bei verschiedenen Eingabewerten.",
                () -> ComposedDoubleBinaryOperatorAsLambdaTest.class.getMethod("testResults", String.class, String.class, String.class, double.class, double.class, double.class)
            ),
            DEFAULT_CRITERION.apply(
                "Die Methode liefert einen Lambda-Ausdruck in Standardform.",
                () -> ComposedDoubleBinaryOperatorAsLambdaTest.class.getMethod("testLambdaExpression")
            )
        )
        .build();

    private static final Criterion CRITERION_H3 = Criterion
        .builder()
        .shortDescription("H3: Lambda-Ausdrücke in Kurzform und Standardform")
        .addChildCriteria(
            CRITERION_H3_1,
            CRITERION_H3_2,
            CRITERION_H3_3,
            CRITERION_H3_4
        )
        .build();

    private static final Criterion CRITERION_H4_1 = Criterion
        .builder()
        .shortDescription("H4.1: Die Methode buildOperator")
        .addChildCriteria(
            DEFAULT_CRITERION.apply(
                "Die Methode ruft in beiden Fällen die korrekte Methoden auf.",
                () -> BuildOperatorTest.class.getMethod("testMethodCalls")
            )
        )
        .build();

    private static final Criterion CRITERION_H4_2 = Criterion
        .builder()
        .shortDescription("H4.2: Operatoren mittels [[[new]]]")
        .addChildCriteria(
            DEFAULT_CRITERION.apply(
                "Die Methode liefert die korrekten Objekttypen.",
                () -> BuildOperatorWithNewTest.class.getMethod("testReturnTypes")
            ),
            DEFAULT_CRITERION.apply(
                "Die Methode verwendet einen [[[switch]]]-Block anstelle von [[[if]]]-Statements.",
                () -> BuildOperatorWithNewTest.class.getMethod("testSwitch")
            ),
            DEFAULT_CRITERION.apply(
                "Die Methode ruft alle vier Konstruktoren mittels [[[new]]] auf.",
                () -> BuildOperatorWithNewTest.class.getMethod("testUseOfNew")
            )
        )
        .build();

    private static final Criterion CRITERION_H4_3 = Criterion
        .builder()
        .shortDescription("H4.3: Operatoren mittels Lambda-Ausdrücken")
        .addChildCriteria(
            UNTESTED_CRITERION.apply("Die Methode besteht lediglich aus einem [[[return]]]-Statement.", 2),
            DEFAULT_CRITERION.apply(
                "Die Methode verwendet in ihrem [[[return]]]-Statement einen [[[switch]]]-Block.",
                () -> BuildOperatorWithLambdaTest.class.getMethod("testSwitch")
            ),
            DEFAULT_CRITERION.apply(
                "Die Methode erstellt keine neuen Objekte mittels [[[new]]].",
                () -> BuildOperatorWithLambdaTest.class.getMethod("checkForUseOfNew")
            ),
            DEFAULT_CRITERION.apply(
                "Die Methode hat einen [[[switch]]]-case für jeden Operator.",
                () -> BuildOperatorWithLambdaTest.class.getMethod("testReturnTypes")
            )
        )
        .build();

    private static final Criterion CRITERION_H4 = Criterion
        .builder()
        .shortDescription("H4: Das Bauen von Operatoren mit Hilfe der Klasse DoubleBinaryOperatorFactory")
        .addChildCriteria(
            CRITERION_H4_1,
            CRITERION_H4_2,
            CRITERION_H4_3
        )
        .build();

    public static final Rubric RUBRIC = Rubric.builder()
        .title("H07")
        .addChildCriteria(
            CRITERION_H1,
            CRITERION_H2,
            CRITERION_H3,
            CRITERION_H4
        )
        .build();

    @Override
    public Rubric getRubric() {
        return RUBRIC;
    }
}

package h02;

import h02.h1.h1_1.CountRobotsInPatternTest;
import h02.h1.h1_2.InitializeRobotsPatternTest;
import h02.h3.h3_1.NumberOfNullRobotsTest;
import h02.h3.h3_2.GenerateThreeDistinctRandomIndicesTest;
import h02.h3.h3_3.SortArrayTest;
import h02.h3.h3_4.SwapRobotsTest;
import h02.h3.h3_5.ReduceRobotArrayTest;
import h02.h4.LetRobotsMarchTest;
import org.sourcegrade.jagr.api.rubric.*;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;

public class H02_RubricProvider implements RubricProvider {

    private static final BiFunction<String, Callable<Method>, Criterion> DEFAULT_CRITERION = (s, methodCallable) ->
        Criterion.builder()
            .shortDescription(s)
            .grader(Grader.testAwareBuilder()
                .requirePass(JUnitTestRef.ofMethod(methodCallable))
                .pointsFailedMin()
                .pointsPassedMax()
                .build())
            .build();

    private static final Criterion CRITERION_H1_1 = Criterion
        .builder()
        .shortDescription("H1.1: Zählen von true in einem Array von Array von boolean")
        .addChildCriteria(
            DEFAULT_CRITERION.apply(
                "Methode zählt die Anzahl der Roboter für ein gegebenes Muster, welches in die Welt passt, korrekt.",
                () -> CountRobotsInPatternTest.class.getDeclaredMethod("testFittingPattern", String.class, int.class)
            ),
            DEFAULT_CRITERION.apply(
                "Methode zählt die Anzahl der Roboter für ein gegebenes Muster, welches nicht in die Welt passt, korrekt.",
                () -> CountRobotsInPatternTest.class.getDeclaredMethod("testUnFittingPattern", String.class, int.class)
            )
        )
        .build();

    private static final Criterion CRITERION_H1_2 = Criterion
        .builder()
        .shortDescription("H1.2: Erstellen eines Robot-Arrays mittels eines Patterns")
        .addChildCriteria(
            DEFAULT_CRITERION.apply(
                "Methode benutzt countRobotsInPattern mindestens einmal.",
                () -> InitializeRobotsPatternTest.class.getDeclaredMethod("testInvocationsOfCountOfRobotsInPattern", String.class)
            ),
            DEFAULT_CRITERION.apply(
                "Methode initialisiert die korrekte Anzahl von Robotern.",
                () -> InitializeRobotsPatternTest.class.getDeclaredMethod("testNumberOfRobotsWithFittingPattern", String.class, int.class)
            ),
            DEFAULT_CRITERION.apply(
                "Methode initialisiert die Roboter mit den korrekten Koordinaten.",
                () -> InitializeRobotsPatternTest.class.getDeclaredMethod("testCoordinatesWithFittingPattern", String.class)
            ),
            DEFAULT_CRITERION.apply(
                "Methode initialisiert die Roboter mit der korrekten Anzahl an Münzen.",
                () -> InitializeRobotsPatternTest.class.getDeclaredMethod("testCoinsWithFittingPattern", String.class)
            ),
            DEFAULT_CRITERION.apply(
                "Methode initialisiert die Roboter mit der korrekten Ausrichtung.",
                () -> InitializeRobotsPatternTest.class.getDeclaredMethod("testDirectionsWithFittingPattern", String.class)
            ),
            DEFAULT_CRITERION.apply(
                "Methode initialisiert die Roboter korrekt, wenn das Muster nicht in die Welt passt.",
                () -> InitializeRobotsPatternTest.class.getDeclaredMethod("testNotFittingPatterns", String.class, int.class)
            )
        )
        .build();

    private static final Criterion CRITERION_H1 = Criterion
        .builder()
        .shortDescription("H1: Erstellen eines Robot-Arrays")
        .addChildCriteria(CRITERION_H1_1, CRITERION_H1_2)
        .build();

    private static final Criterion CRITERION_H3_1 = Criterion
        .builder()
        .shortDescription("H3.1: Arraykomponenten gleich null")
        .addChildCriteria(
            DEFAULT_CRITERION.apply(
                "Die Anzahl der Komponenten mit Wert null wird korrekt gezählt.",
                () -> NumberOfNullRobotsTest.class.getDeclaredMethod("testNumberOfNullRobots", String.class)
            )
        )
        .build();

    private static final Criterion CRITERION_H3_2 = Criterion
        .builder()
        .shortDescription("H3.2: Drei (pseudo-)zufällige int-Werte")
        .addChildCriteria(
            DEFAULT_CRITERION.apply(
                "Alle erzeugten Arrays haben exakt die Länge 3.",
                () -> GenerateThreeDistinctRandomIndicesTest.class.getDeclaredMethod("testLength")
            ),
            DEFAULT_CRITERION.apply(
                "Die erzeugten Zahlen in den Arrays sind unterschiedlich.",
                () -> GenerateThreeDistinctRandomIndicesTest.class.getDeclaredMethod("testDissimilarityOfElements")
            ),
            DEFAULT_CRITERION.apply(
                "Alle Komponenten der Arrays sind größer oder gleich 0 und kleiner als die gegebene obere Schranke.",
                () -> GenerateThreeDistinctRandomIndicesTest.class.getDeclaredMethod("testBounds")
            ),
            DEFAULT_CRITERION.apply(
                "Die Rückgaben unterscheiden sich; es wird nicht jedes Mal das gleiche Objekt zurückgegeben.",
                () -> GenerateThreeDistinctRandomIndicesTest.class.getDeclaredMethod("testDissimilarityOfArrays")
            )
        )
        .build();

    private static final Criterion CRITERION_H3_3 = Criterion
        .builder()
        .shortDescription("H3.3: Sortierung eines 3-elementigen int-Arrays")
        .addChildCriteria(
            DEFAULT_CRITERION.apply(
                "Alle Arrays werden korrekt sortiert.",
                () -> SortArrayTest.class.getDeclaredMethod("testSorting", String.class)
            )
        )
        .build();

    private static final Criterion CRITERION_H3_4 = Criterion
        .builder()
        .shortDescription("H3.4: Vertauschen von Robotern")
        .addChildCriteria(
            DEFAULT_CRITERION.apply(
                "Das Vertauschen der Roboter funktioniert korrekt.",
                () -> SwapRobotsTest.class.getDeclaredMethod("testSwapping", String.class, int.class, int.class, int.class)
            )
        )
        .build();

    private static final Criterion CRITERION_H3_5 = Criterion
        .builder()
        .shortDescription("H3.5: Reduzieren eines Arrays")
        .addChildCriteria(
            DEFAULT_CRITERION.apply(
                "Die Methode reduziert die Arrays korrekt.",
                () -> ReduceRobotArrayTest.class.getDeclaredMethod("testSize", String.class, int.class)
            ),
            DEFAULT_CRITERION.apply(
                "Die Methode behält die Reihenfolge der Roboter bei.",
                () -> ReduceRobotArrayTest.class.getDeclaredMethod("testRobotsAfterResize", String.class, int.class)
            )
        )
        .build();

    private static final Criterion CRITERION_H3 = Criterion
        .builder()
        .shortDescription("H3: Hilfsmethoden für die Hauptschleife")
        .addChildCriteria(CRITERION_H3_1, CRITERION_H3_2, CRITERION_H3_3, CRITERION_H3_4, CRITERION_H3_5)
        .build();

    private static final Criterion CRITERION_H4 = Criterion
        .builder()
        .shortDescription("H4: Die Hauptschleife")
        .addChildCriteria(
            DEFAULT_CRITERION.apply(
                "Die Methode ruft die anderen in H3 implementierten Methoden auf.",
                () -> LetRobotsMarchTest.class.getDeclaredMethod("testUseOfMethods")
            ),
            DEFAULT_CRITERION.apply(
                "Die Methode funktioniert korrekt, auch dann, wenn alle Arraykomponenten null sind.",
                () -> LetRobotsMarchTest.class.getDeclaredMethod("testNullArray")
            ),
            DEFAULT_CRITERION.apply(
                "Die Methode wirft keine Exceptions.",
                () -> LetRobotsMarchTest.class.getDeclaredMethod("checkForExceptions", String.class)
            ),
            DEFAULT_CRITERION.apply(
                "Die Roboter führen keine weitere Aktion aus, als sich zu bewegen und Münzen abzulegen.",
                () -> LetRobotsMarchTest.class.getDeclaredMethod("checkActions", String.class)
            ),
            DEFAULT_CRITERION.apply(
                "Alle Roboter bewegen sich zum Ende.",
                () -> LetRobotsMarchTest.class.getDeclaredMethod("testAllRobotsReachEnd", String.class)
            ),
            DEFAULT_CRITERION.apply(
                "Alle Roboter legen die korrekte Anzahl von Münzen ab.",
                () -> LetRobotsMarchTest.class.getDeclaredMethod("testAllRobotsPutCoins", String.class)
            )
        )
        .build();

    private static final Rubric RUBRIC = Rubric.builder()
        .title("H02 - Let them march")
        .addChildCriteria(CRITERION_H1, CRITERION_H3, CRITERION_H4)
        .build();

    @Override
    public Rubric getRubric() {
        return RUBRIC;
    }
}

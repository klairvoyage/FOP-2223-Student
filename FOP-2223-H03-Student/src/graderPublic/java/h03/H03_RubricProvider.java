package h03;

import fopbot.Direction;
import h03.utils.ChildCollectionCriterionBuilder;
import h03.utils.OnePointCriterionBuilder;
import org.sourcegrade.jagr.api.rubric.*;
import org.sourcegrade.jagr.api.testing.TestCycle;

import java.util.List;

public class H03_RubricProvider implements RubricProvider {
    /*
     * Punktekriterien:
     * - H1.1 4 Punkte
     *      - Klassendeklaration korrekt 1
     *      - Attribute korrekt deklariert 1
     *      - Konstruktor korrekt deklariert und setzt Attribute korrekt 1
     *      - Konstruktor ruft super-Konstruktor korrekt auf 1
     * - H1.2 4 Punkte
     *      - offspring-Attribut korrekt deklariert 1
     *      - offspringIsInitialized Methode korrekt deklariert und implementiert 1
     *      - alle Getter korrekt implementiert 1
     *      - Methode initOffspring korrekt deklariert und implementiert 1
     * - H1.3 4 Punkte
     *      - addToXOfOffspring, addToYOfOffspring korrekt implementiert 1
     *      - addToNumberOfCoinsOfOffspring korrekt implementiert 1
     *      - addToDirectionOfOffspring besteht Standardfälle 1
     *      - addToDirectionOfOffspring besteht Randfälle 1
     * - H2 6 Punkte
     *      - directionAccu Attribut korrekt deklariert
     *         und getDirectionOfOffspring korrekt implementiert 1
     *      - Konstruktor korrekt deklariert und implementiert 1
     *      - initOffspring korrekt deklariert und implementiert 1
     *      - getDirectionFromAccu besteht Standardfälle 1
     *      - getDirectionFromAccu besteht Randfälle 1
     *      - addToDirectionOfOffspring korrekt implementiert 1
     * - H3.1 4 Punkte
     *     - robots Attribut korrekt deklariert 1
     *     - Konstruktor korrekt implementiert 1
     *     - getRobotByIndex korrekt implementiert 1
     *     - addToDirectionOfBothOffsprings korrekt implementiert 1
     * - H3.2 3 Punkte
     *     - ein zusätzlicher Aufruf mit negativem Parameter vorhanden 1
     *     - ein zusätzlicher Aufruf mit negativem directionAccu-Wert vorhanden 1
     *     - insgesamt mindestens 3 zusätzliche Aufrufe 1
     */

    // TODO: JavaDoc prüfen
    // TODO: investigate timeouts
    // TODO: bei Methoden, wo ein Punkt für Deklaration und Implementierung zusammen vergeben wird: Toleranz für Schreibfehler
    //  in der Deklaration einbauen

    @Override
    public Rubric getRubric() {
        Grader graderNoPublicTests = (testCycle, criterion) -> new GradeResult() {
            @Override
            public int getMinPoints() {
                return 0;
            }

            @Override
            public int getMaxPoints() {
                return 1;
            }

            @Override
            public List<String> getComments() {
                return List.of("Not tested by public grader");
            }
        };

        var H1_1_T1 = new OnePointCriterionBuilder("Die Klasse \"RobotWithOffspring\" wurde korrekt deklariert.",
            JUnitTestRef.ofMethod(() -> TutorTests_H1_1.class.getMethod("classRobotWithOffspringDeclaredCorrectly")));

        var H1_1_T2 = new OnePointCriterionBuilder(
            "Die Attribute \"numberOfColumnsOfWorld\" und \"numberOfRowsOfWorld\" wurden korrekt deklariert.",
            JUnitTestRef.ofMethod(() -> TutorTests_H1_1.class.getMethod("numberOfColumnsOfWorldDeclaredCorrectly")),
            JUnitTestRef.ofMethod(() -> TutorTests_H1_1.class.getMethod("numberOfRowsOfWorldDeclaredCorrectly")));

        var H1_1_T3 = new OnePointCriterionBuilder(
            "Der Konstruktor von \"RobotWithOffspring\" wurde korrekt deklariert und setzt die Attribute " +
                "\"numberOfColumnsOfWorld\" und \"numberOfRowsOfWorld\" korrekt.",
            JUnitTestRef.ofMethod(() -> TutorTests_H1_1.class.getMethod("constructorDeclaredCorrectly")),
            JUnitTestRef.ofMethod(() -> TutorTests_H1_1.class.getMethod("constructorSetsAttributesCorrectly", int.class,
                int.class)));

        var H1_1_T4 = new OnePointCriterionBuilder(
            "Der Konstruktor von \"RobotWithOffspring\" ruft den super-Konstruktor von \"Robot\" korrekt auf.",
            JUnitTestRef.ofMethod(() -> TutorTests_H1_1.class.getMethod("constructorCallsSuperConstructorCorrectly",
                int.class, int.class, Direction.class, int.class, int.class, int.class, TestCycle.class)));

        var H1_1 = new ChildCollectionCriterionBuilder("H1.1 | Abgeleitete Klasse, ihr Konstruktor und zusätzliche Attribute",
            H1_1_T1, H1_1_T2, H1_1_T3, H1_1_T4);

        var H1_2_T1 = new OnePointCriterionBuilder("Das Attribut \"offspring\" wurde korrekt deklariert.",
            JUnitTestRef.ofMethod(() -> TutorTests_H1_2.class.getMethod("offspringDeclaredCorrectly")));

        var H1_2_T2 = new OnePointCriterionBuilder("Die Methode \"initOffspring\" wurde korrekt deklariert und implementiert.",
            JUnitTestRef.ofMethod(() -> TutorTests_H1_2.class.getMethod("initOffspringDeclaredCorrectly")),
            JUnitTestRef.ofMethod(() -> TutorTests_H1_2.class.getMethod("initOffspringImplementedCorrectly", int.class,
                int.class, Direction.class, int.class)));

        var H1_2_T3 = new OnePointCriterionBuilder("Die get-Methoden für den offspring wurden korrekt deklariert und " +
            "implementiert.",
            JUnitTestRef.ofMethod(() -> TutorTests_H1_2.class.getMethod(
                "allOffspringGetterMethodsCorrectlyDeclaredAndImplemented", int.class, int.class, Direction.class, int.class)));

        var H1_2_T4 = new OnePointCriterionBuilder("Die Methode \"offspringIsInitialized\" wurde korrekt deklariert und " +
            "implementiert.",
            JUnitTestRef.ofMethod(() -> TutorTests_H1_2.class.getMethod("offspringIsInitializedDeclaredAndImplementedCorrectly")));

        var H1_2 = new ChildCollectionCriterionBuilder("H1.2 | Attribut vom Referenztyp und get-Methoden für dessen Attribute",
            H1_2_T1, H1_2_T2, H1_2_T3, H1_2_T4);

        var H1_3_T1 = new OnePointCriterionBuilder(" Die Methoden \"addToXOfOffspring\" und \"addToYOfOffspring\" wurden " +
            "korrekt deklariert und implementiert.",
            JUnitTestRef.ofMethod(() -> TutorTests_H1_3.class.getMethod(
                "addToXOfOffspringDeclaredAndImplementedCorrectly",
                int.class, int.class, Direction.class, int.class, int.class, int.class)),
            JUnitTestRef.ofMethod(() -> TutorTests_H1_3.class.getMethod(
                "addToYOfOffspringDeclaredAndImplementedCorrectly",
                int.class, int.class, Direction.class, int.class, int.class, int.class)));

        var H1_3_T2 = new OnePointCriterionBuilder("Die Methode \"addToNumberOfCoinsOfOffspring\" wurde korrekt deklariert und " +
            "implementiert.",
            JUnitTestRef.ofMethod(() -> TutorTests_H1_3.class.getMethod(
                "addToNumberOfCoinsOfOffspringDeclaredAndImplementedCorrectly",
                int.class, int.class, Direction.class, int.class, int.class, int.class)));

        var H1_3_T3 = new OnePointCriterionBuilder("Die Methode \"addToDirectionOfOffspring\" wurde korrekt deklariert und die " +
            "Implementierung besteht einfache Testfälle.",
            JUnitTestRef.ofMethod(() -> TutorTests_H1_3.class.getMethod(
                "addToDirectionOfOffspringDeclaredCorrectlyAndPassesBaseTests", int.class, int.class, Direction.class,
                int.class, int.class, Direction.class, TestCycle.class)));

        var H1_3_T4 = new OnePointCriterionBuilder("Methode \"addToDirectionOfOffspring\" wurde korrekt deklariert und die " +
            "Implementierung besteht komplexe und Rand-Testfälle.",
            JUnitTestRef.ofMethod(() -> TutorTests_H1_3.class.getMethod("addToDirectionOfOffspringDeclaredCorrectlyAndPassesAdvancedTests",
                int.class, int.class, Direction.class, int.class, int.class, Direction.class, TestCycle.class)));

        var H1_3 = new ChildCollectionCriterionBuilder("H1.3 | Attributwerte relativ zum momentanen Wert ändern",
            H1_3_T1, H1_3_T2, H1_3_T3, H1_3_T4);

        var H1 = new ChildCollectionCriterionBuilder("H1 | Roboter mit Abkömmling", H1_1, H1_2, H1_3);

        var H2_T1 = new OnePointCriterionBuilder("RobotWithOffspring2 deklariert das Attribut directionAccu korrekt.",
            JUnitTestRef.ofMethod(() -> TutorTests_H2.class.getMethod("directionAccuDeclaredCorrectly")));
        var H2_T2 = new OnePointCriterionBuilder("Der Konstruktor wird korrekt deklariert und ruft intern den super-Konstruktor auf.",
            JUnitTestRef.ofMethod(() -> TutorTests_H2.class.getMethod("testConstructor", TestCycle.class)));
        var H2_T3 = new OnePointCriterionBuilder("Die überschriebene Methode \"initOffspring\" ist korrekt implementiert.",
            JUnitTestRef.ofMethod(() -> TutorTests_H2.class.getMethod("testInitOffspring", TestCycle.class)));
        var H2_T4 = new OnePointCriterionBuilder("Methode \"getDirectionFromAccu\" funktioniert für normale Werte wie erwartet.",
            JUnitTestRef.ofMethod(() -> TutorTests_H2.class.getMethod("testGetDirectionFromAccu_normal", int.class, Direction.class)));
        var H2_T5 = Criterion.builder()
            .shortDescription("Methode \"getDirectionFromAccu\" funktioniert für kompliziertere Werte wie erwartet.")
            .grader(graderNoPublicTests)
            .build();
        var H2_T6 = new OnePointCriterionBuilder("Die überschriebene Methode \"addToDirectionOfOffspring\" wurde korrekt " +
            "implementiert.",
            JUnitTestRef.ofMethod(() -> TutorTests_H2.class.getMethod("testAddToDirectionOfOffspring",
                Direction.class, int.class, int.class, Direction.class)));

        var H2 = Criterion.builder()
            .shortDescription("H2 | Roboter mit überschriebenen Methoden")
            .addChildCriteria(H2_T1.build(), H2_T2.build(), H2_T3.build(), H2_T4.build(), H2_T5, H2_T6.build())
            .build();

        var H3_1_T1 = Criterion.builder()
            .shortDescription("Das Attribut robots ist korrekt deklariert.")
            .grader(graderNoPublicTests)
            .build();
        var H3_1_T2 = Criterion.builder()
            .shortDescription("Der Konstruktor ist korrekt implementiert.")
            .grader(graderNoPublicTests)
            .build();
        var H3_1_T3 = Criterion.builder()
            .shortDescription("Methode getRobotsByIndex ist korrekt implementiert.")
            .grader(graderNoPublicTests)
            .build();
        var H3_1_T4 = Criterion.builder()
            .shortDescription("Methode addToDirectionOfBothOffsprings ist korrekt implementiert.")
            .grader(graderNoPublicTests)
            .build();
        var H3_1 = Criterion.builder()
            .shortDescription("H3.1 | Klasse mit Robotern")
            .addChildCriteria(H3_1_T1, H3_1_T2, H3_1_T3, H3_1_T4)
            .build();

        var H3_2_T1 = Criterion.builder()
            .shortDescription("TwinRobots wird mindestens noch drei weitere Male getestet.")
            .grader(graderNoPublicTests)
            .build();
        var H3_2_T2 = Criterion.builder()
            .shortDescription("addToDirectionOfBothOffsprings wird mindestens einmal mit einer negativen Zahl als Parameter aufgerufen.")
            .grader(graderNoPublicTests)
            .build();
        var H3_2_T3 = Criterion.builder()
            .shortDescription("Einer der Testfälle läuft ab, während das directionAccu-Attribut von RobotWithOffspring2 negativ ist.")
            .grader(graderNoPublicTests)
            .build();
        var H3_2 = Criterion.builder()
            .shortDescription("H3.2 | Testen")
            .addChildCriteria(H3_2_T1, H3_2_T2, H3_2_T3)
            .build();

        var H3 = Criterion.builder()
            .shortDescription("H3 | Klasse mit Robotern und Tests")
            .addChildCriteria(H3_1, H3_2)
            .build();

        return Rubric.builder()
            .title("H03 | Ihr Upgrade in die First Class")
            .addChildCriteria(H1.build(), H2, H3)
            .build();
    }
}

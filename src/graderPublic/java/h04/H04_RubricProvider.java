package h04;

import fopbot.Direction;
import h04.student.CoinCollectionData;
import h04.student.RobotWithCoinTypesData;
import org.sourcegrade.jagr.api.rubric.*;

import static org.tudalgo.algoutils.tutor.general.stringify.HTML.tt;


public class H04_RubricProvider implements RubricProvider {

    ////////////////////////////////////////////////// H1.1

    public static final Criterion H1_1_T1 = Criterion.builder()
        .shortDescription("Interface " + tt("RobotWithReferenceState") + " ist mitsamt Methoden korrekt deklariert.")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.and(
                JUnitTestRef.ofMethod(() -> H1_1.class.getMethod("t1_1")),
                JUnitTestRef.ofMethod(() -> H1_1.class.getMethod("t1_2")),
                JUnitTestRef.ofMethod(() -> H1_1.class.getMethod("t1_3")),
                JUnitTestRef.ofMethod(() -> H1_1.class.getMethod("t1_4")),
                JUnitTestRef.ofMethod(() -> H1_1.class.getMethod("t1_5")),
                JUnitTestRef.ofMethod(() -> H1_1.class.getMethod("t1_6"))))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H1_1 = Criterion.builder()
        .shortDescription("H1.1 | Keine Referenz? Ist nicht egal!")
        .addChildCriteria(H1_1_T1)
        .build();

    ////////////////////////////////////////////////// H1.2

    public static final Criterion H1_2_T1 = Criterion.builder()
        .shortDescription("Enum " + tt("CoinType") + " ist korrekt.")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H1_2.class.getMethod("t2")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H1_2_T2 = Criterion.builder()
        .shortDescription("Interface " + tt("WithCoinTypes") + " ist mitsamt Methoden korrekt deklariert.")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.and(
                JUnitTestRef.ofMethod(() -> H1_2.class.getMethod("t3_1")),
                JUnitTestRef.ofMethod(() -> H1_2.class.getMethod("t3_2")),
                JUnitTestRef.ofMethod(() -> H1_2.class.getMethod("t3_3"))))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H1_2 = Criterion.builder()
        .shortDescription("H1.2 |  Keine Münzen? Ist nicht egal!")
        .addChildCriteria(H1_2_T1, H1_2_T2)
        .build();

    ////////////////////////////////////////////////// H2.1
    public static final Criterion H1 = Criterion.builder()
        .shortDescription("H1 | Zwei Interfaces")
        .addChildCriteria(H1_1, H1_2)
        .build();
    public static final Criterion H2_1_T1 = Criterion.builder()
        .shortDescription("Klasse " + tt("RobotWithCoinTypes") + " ist korrekt deklariert.")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H2_1.class.getMethod("t4")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();
    public static final Criterion H2_1_T2 = Criterion.builder()
        .shortDescription("Attribute " + tt("numberOfSilverCoins") + ", " + tt("numberOfBrassCoins") + ", " + tt("numberOfCopperCoins") + " sind " +
            "korrekt deklariert.")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H2_1.class.getMethod("t5")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();
    public static final Criterion H2_1_T3 = Criterion.builder()
        .shortDescription("Konstruktor ist korrekt.")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H2_1.class.getMethod("t6", RobotWithCoinTypesData.class)))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();
    public static final Criterion H2_1_T4 = Criterion.builder()
        .shortDescription("Methode " + tt("setNumberOfCoins") + " ist korrekt.")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H2_1.class.getMethod("t7", RobotWithCoinTypesData.class, int.class)))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();
    public static final Criterion H2_1_T5 = Criterion.builder()
        .shortDescription("Methode " + tt("getNumberOfCoinsOfType") + " ist korrekt.")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H2_1.class.getMethod("t8", RobotWithCoinTypesData.class)))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();
    public static final Criterion H2_1_T6 = Criterion.builder()
        .shortDescription("Methode " + tt("setNumberOfCoinsOfType") + " ist korrekt.")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H2_1.class.getMethod("t9", RobotWithCoinTypesData.class)))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();
    public static final Criterion H2_1_T7 = Criterion.builder()
        .shortDescription("Methode " + tt("putCoin") + " ist korrekt.")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H2_1.class.getMethod("t10", RobotWithCoinTypesData.class, RobotWithCoinTypesData.class)))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();
    public static final Criterion H2_1_T8 = Criterion.builder()
        .shortDescription("Methode " + tt("pickCoin") + " ist korrekt.")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H2_1.class.getMethod("t11", RobotWithCoinTypesData.class)))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    ////////////////////////////////////////////////// H2.2
    public static final Criterion H2_1 = Criterion.builder()
        .shortDescription("H2.1 | Keine Scheine? Ist mir egal!")
        .addChildCriteria(H2_1_T1, H2_1_T2, H2_1_T3, H2_1_T4, H2_1_T5, H2_1_T6, H2_1_T7, H2_1_T8)
        .build();
    public static final Criterion H2_2_T1 = Criterion.builder()
        .shortDescription("Klasse " + tt("RobotWithCoinTypesAndRefStateOne") + " ist korrekt deklariert.")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H2_2.class.getMethod("t12")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();
    public static final Criterion H2_2_T2 = Criterion.builder()
        .shortDescription("Attribute " + tt("ref{X,Y,Direction,NumberOfCoins}") + " sind korrekt deklariert.")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H2_2.class.getMethod("t13")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();
    public static final Criterion H2_2_T3 = Criterion.builder()
        .shortDescription("Konstruktor ist korrekt.")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H2_2.class.getMethod("t14", RobotWithCoinTypesData.class)))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();
    public static final Criterion H2_2_T4 = Criterion.builder()
        .shortDescription("Methode " + tt("setCurrentStateAsReferenceState") + " ist korrekt.")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H2_2.class.getMethod(
                "t15",
                RobotWithCoinTypesData.class,
                int.class,
                int.class,
                Direction.class,
                int.class,
                int.class,
                int.class
            )))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();
    public static final Criterion H2_2_T5 = Criterion.builder()
        .shortDescription("Methode " + tt("getDiffX") + " ist korrekt.")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H2_2.class.getMethod(
                "t16",
                RobotWithCoinTypesData.class,
                int.class,
                int.class
            )))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();
    public static final Criterion H2_2_T6 = Criterion.builder()
        .shortDescription("Methode " + tt("getDiffY") + " ist korrekt.")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H2_2.class.getMethod(
                "t17",
                RobotWithCoinTypesData.class,
                int.class,
                int.class
            )))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();
    public static final Criterion H2_2_T7 = Criterion.builder()
        .shortDescription("Methode " + tt("getDiffNumberOfCoins") + " ist korrekt.")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H2_2.class.getMethod("t18", RobotWithCoinTypesData.class, int.class, int.class, int.class, int.class)))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();
    public static final Criterion H2_2_T8 = Criterion.builder()
        .shortDescription("Methode " + tt("getDiffDirection") + " ist korrekt.")
        .maxPoints(2)
        .minPoints(0)
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H2_2.class.getMethod("t19", RobotWithCoinTypesData.class, Direction.class, Direction.class)))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();


    ////////////////////////////////////////////////// H2.3
    public static final Criterion H2_2 = Criterion.builder()
        .shortDescription("H2.2 | Keine Geld? Ist nicht egal!")
        .addChildCriteria(H2_2_T1, H2_2_T2, H2_2_T3, H2_2_T4, H2_2_T5, H2_2_T6, H2_2_T7, H2_2_T8)
        .build();
    public static final Criterion H2_3_T1 = Criterion.builder()
        .shortDescription("Klasse " + tt("RobotWithCoinTypesAndRefStateOne") + " ist korrekt deklariert.")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H2_3.class.getMethod("t20")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();
    public static final Criterion H2_3_T2 = Criterion.builder()
        .shortDescription("Attribut " + tt("refRobot") + " ist korrekt deklariert.")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H2_3.class.getMethod("t21")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();
    public static final Criterion H2_3_T3 = Criterion.builder()
        .shortDescription("Konstruktor ist korrekt.")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H2_3.class.getMethod("t22", RobotWithCoinTypesData.class)))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();
    public static final Criterion H2_3_T4 = Criterion.builder()
        .shortDescription("Methoden " + tt("setCurrentStateAsReferenceState,getDiff{X,Y,Direction,NumberOfCoins}") + " sind korrekt.")
        .minPoints(0)
        .maxPoints(2)
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.and(
                JUnitTestRef.ofMethod(() -> H2_3.class.getMethod(
                    "t23_1",
                    RobotWithCoinTypesData.class,
                    int.class,
                    int.class,
                    Direction.class,
                    int.class,
                    int.class,
                    int.class
                )),
                JUnitTestRef.ofMethod(() -> H2_3.class.getMethod(
                    "t23_2",
                    RobotWithCoinTypesData.class,
                    int.class,
                    int.class
                )),
                JUnitTestRef.ofMethod(() -> H2_3.class.getMethod(
                    "t23_3",
                    RobotWithCoinTypesData.class,
                    int.class,
                    int.class
                )),
                JUnitTestRef.ofMethod(() -> H2_3.class.getMethod(
                    "t23_4",
                    RobotWithCoinTypesData.class,
                    Direction.class,
                    Direction.class
                )),
                JUnitTestRef.ofMethod(() -> H2_3.class.getMethod(
                    "t23_5",
                    RobotWithCoinTypesData.class,
                    int.class,
                    int.class,
                    int.class,
                    int.class
                ))))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    ////////////////////////////////////////////////// H2.4
    public static final Criterion H2_3 = Criterion.builder()
        .shortDescription("H2.3 |  Roboter in Roboter? Ist mir egal!")
        .addChildCriteria(H2_3_T1, H2_3_T2, H2_3_T3, H2_3_T4)
        .build();
    public static final Criterion H2_4_T1 = Criterion.builder()
        .shortDescription("Klasse " + tt("CoinCollection") + " ist korrekt deklariert.")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H2_4.class.getMethod("t24")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();
    public static final Criterion H2_4_T2 = Criterion.builder()
        .shortDescription("Attribute " + tt("numberOf{Silver,Brass,Copper}Coins") + " sind korrekt deklariert.")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H2_4.class.getMethod("t25")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();
    public static final Criterion H2_4_T3 = Criterion.builder()
        .shortDescription("Konstruktor ist korrekt.")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> H2_4.class.getMethod(
                "t26",
                CoinCollectionData.class
            )))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();
    public static final Criterion H2_4_T4 = Criterion.builder()
        .shortDescription("Methoden " + tt("{get,set}NumberOfCoinsOfType") + " sind korrekt.")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.and(
                JUnitTestRef.ofMethod(() -> H2_4.class.getMethod(
                    "t27_1",
                    CoinCollectionData.class
                )),
                JUnitTestRef.ofMethod(() -> H2_4.class.getMethod(
                    "t27_2",
                    CoinCollectionData.class,
                    CoinCollectionData.class
                ))))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();
    public static final Criterion C28 = Criterion.builder()
        .shortDescription("Methoden " + tt("getNumberOf{Silver,Brass,Copper}Coins") + " sind korrekt.")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.and(
                JUnitTestRef.ofMethod(() -> H2_4.class.getMethod(
                    "t28",
                    CoinCollectionData.class
                ))))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();
    public static final Criterion H2_4_T6 = Criterion.builder()
        .shortDescription("Methoden " + tt("{insert,remove}Coin") + " sind korrekt.")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.and(
                JUnitTestRef.ofMethod(() -> H2_4.class.getMethod(
                    "t29_1",
                    CoinCollectionData.class,
                    CoinCollectionData.class
                )),
                JUnitTestRef.ofMethod(() -> H2_4.class.getMethod(
                    "t29_2",
                    CoinCollectionData.class,
                    CoinCollectionData.class
                ))))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    ////////////////////////////////////////////////// Tasks
    public static final Criterion H2_4 = Criterion.builder()
        .shortDescription("H2.4 | Klasse ohne Roboter? Ist mir egal!")
        .addChildCriteria(H2_4_T1, H2_4_T2, H2_4_T3, H2_4_T4, C28, H2_4_T6)
        .build();
    public static final Criterion H2 = Criterion.builder()
        .shortDescription("H2 | Implementierende Klassen")
        .addChildCriteria(H2_1, H2_2, H2_3, H2_4)
        .build();

    @Override
    public Rubric getRubric() {
        return Rubric.builder()
            .title("H04 | Roboter mit Senf")
            .addChildCriteria(H1, H2)
            .build();
    }


}

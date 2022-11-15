package h04;

import fopbot.World;
import h04.student.CoinCollectionData;
import h04.student.CoinCollectionStudent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junitpioneer.jupiter.json.JsonClasspathSource;
import org.junitpioneer.jupiter.json.Property;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import static h04.student.CoinCollectionStudent.*;
import static h04.student.CoinTypeStudent.*;
import static h04.student.More.linkToObject;
import static h04.student.WithCoinTypesStudent.*;
import static java.lang.Integer.max;
import static java.lang.Integer.min;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.*;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions3.*;
import static org.tudalgo.algoutils.tutor.general.match.BasicReflectionMatchers.sameType;
import static org.tudalgo.algoutils.tutor.general.reflections.Modifier.PRIVATE;
import static org.tudalgo.algoutils.tutor.general.reflections.Modifier.PUBLIC;

@TestForSubmission
@DisplayName("H2_4")
public class H2_4 {

    @BeforeEach
    public void setupWorld() {
        World.reset();
        World.setDelay(0);
        World.setSize(9, 9);
    }

    @Test
    @DisplayName("24 | Declaration")
    public void t24() {
        var type = linkToCoinCollection();
        assertCorrectModifiers(type, PUBLIC);
        assertCorrectSuperType(type, sameType(linkToObject()));
        assertCorrectInterfaces(type, sameType(linkToWithCoinTypes()));
    }


    @ParameterizedTest
    @JsonClasspathSource("h04/H2_4.json")
    @DisplayName("26 | Konstruktor")
    public void t26(
        @Property("coinCollectionBefore") CoinCollectionData coinCollectionData
    ) {
        assertCorrectModifiers(linkToCoinCollectionConstructor(), PUBLIC);
        var coinCollection = new CoinCollectionStudent(coinCollectionData, false);
        assertEquals(
            coinCollectionData.numberOfCopperCoins(),
            coinCollection.numberOfCopperCoins(),
            contextBuilder().add(coinCollection.context()).subject(linkToNumberOfCopperCoins()).build(),
            c -> "unexpected number of copper coins"
        );
        assertEquals(
            coinCollectionData.numberOfSilverCoins(),
            coinCollection.numberOfSilverCoins(),
            contextBuilder().add(coinCollection.context()).subject(linkToNumberOfSilverCoins()).build(),
            c -> "unexpected number of silver coins"
        );
        assertEquals(
            coinCollectionData.numberOfBrassCoins(),
            coinCollection.numberOfBrassCoins(),
            contextBuilder().add(coinCollection.context()).subject(linkToNumberOfBrassCoins()).build(),
            c -> "unexpected number of brass coins"
        );
    }

    @Test
    @DisplayName("25 | Attributes")
    public void t25() {
        assertCorrectModifiers(linkToNumberOfCopperCoins(), PRIVATE);
        assertCorrectModifiers(linkToNumberOfSilverCoins(), PRIVATE);
        assertCorrectModifiers(linkToNumberOfBrassCoins(), PRIVATE);
    }

    @ParameterizedTest
    @JsonClasspathSource("h04/H2_4.json")
    @DisplayName("28")
    public void t28(
        @Property("coinCollectionBefore") CoinCollectionData coinCollectionData
    ) {
        var coinCollection = new CoinCollectionStudent(coinCollectionData, true);
        assertEquals(
            coinCollectionData.numberOfCopperCoins(),
            coinCollection.getNumberOfCopperCoins(),
            contextBuilder().subject(linkToNumberOfCopperCoins()).add(context(coinCollectionData)).build(),
            c -> "unexpected number of copper coins"
        );
        assertEquals(
            coinCollectionData.numberOfSilverCoins(),
            coinCollection.getNumberOfSilverCoins(),
            contextBuilder().subject(linkToNumberOfSilverCoins()).add(context(coinCollectionData)).build(),
            c -> "unexpected number of silver coins"
        );
        assertEquals(
            coinCollectionData.numberOfBrassCoins(),
            coinCollection.getNumberOfBrassCoins(),
            contextBuilder().subject(linkToGetNumberOfBrassCoins()).add(context(coinCollectionData)).build(),
            c -> "unexpected number of brass coins"
        );
    }

    @ParameterizedTest
    @JsonClasspathSource("h04/H2_4.json")
    @DisplayName("27.1 | getNumberOfCoinsOfType()")
    public void t27_1(
        @Property("coinCollectionBefore") CoinCollectionData coinCollectionData
    ) {
        var coinCollection = new CoinCollectionStudent(coinCollectionData, true);
        var context = contextBuilder().add(coinCollection.context()).subject(linkToGetNumberOfCoinsOfType()).build();
        assertEquals(
            coinCollectionData.numberOfCopperCoins(),
            coinCollection.getNumberOfCoinsOfType(linkToCoinTypeCopper().constant()),
            context,
            c -> "unexpected number of copper coins"
        );
        assertEquals(
            coinCollectionData.numberOfSilverCoins(),
            coinCollection.getNumberOfCoinsOfType(linkToCoinTypeSilver().constant()),
            context,
            c -> "unexpected number of silver coins"
        );
        assertEquals(
            coinCollectionData.numberOfBrassCoins(),
            coinCollection.getNumberOfCoinsOfType(linkToCoinTypeBrass().constant()),
            context,
            c -> "unexpected number of brass coins"
        );
    }

    @ParameterizedTest
    @JsonClasspathSource("h04/H2_4.json")
    @DisplayName("27.2 | setNumberOfCoinsOfType()")
    public void t27_2(
        @Property("coinCollectionBefore") CoinCollectionData coinCollectionBefore,
        @Property("coinCollectionAfter") CoinCollectionData coinCollectionAfter
    ) {
        var coinCollection = new CoinCollectionStudent(coinCollectionBefore, true);
        coinCollection.setNumberOfCoinsOfType(linkToCoinTypeSilver().constant(), coinCollectionAfter.numberOfSilverCoins());
        coinCollection.setNumberOfCoinsOfType(linkToCoinTypeBrass().constant(), coinCollectionAfter.numberOfBrassCoins());
        coinCollection.setNumberOfCoinsOfType(linkToCoinTypeCopper().constant(), coinCollectionAfter.numberOfCopperCoins());
        var context = contextBuilder()
            .subject(linkToSetNumberOfCoinsOfType())
            .add("before", context(coinCollectionBefore))
            .add("after", context(coinCollectionAfter))
            .build();
        assertEquals(
            coinCollectionAfter.numberOfCopperCoins(),
            coinCollection.getNumberOfCoinsOfType(linkToCoinTypeCopper().constant()),
            context,
            c -> "unexpected number of copper coins"
        );
        assertEquals(
            coinCollectionAfter.numberOfSilverCoins(),
            coinCollection.getNumberOfCoinsOfType(linkToCoinTypeSilver().constant()),
            context,
            c -> "unexpected number of silver coins"
        );
        assertEquals(
            coinCollectionAfter.numberOfBrassCoins(),
            coinCollection.getNumberOfCoinsOfType(linkToCoinTypeBrass().constant()),
            context,
            c -> "unexpected number of brass coins"
        );
    }

    @ParameterizedTest
    @JsonClasspathSource("h04/H2_4.json")
    @DisplayName("9 | insertCoin()")
    public void t29_1(
        @Property("coinCollectionBefore") CoinCollectionData coinCollectionBefore,
        @Property("coinCollectionAfter") CoinCollectionData coinCollectionAfter
    ) {
        var coinCollection = new CoinCollectionStudent(coinCollectionBefore, true);
        var numberOfAdditionalSilverCoins = coinCollectionAfter.numberOfSilverCoins() - coinCollectionBefore.numberOfSilverCoins();
        var numberOfAdditionalBrassCoins = coinCollectionAfter.numberOfBrassCoins() - coinCollectionBefore.numberOfBrassCoins();
        var numberOfAdditionalCopperCoins = coinCollectionAfter.numberOfCopperCoins() - coinCollectionBefore.numberOfCopperCoins();
        for (var i = 0; i < numberOfAdditionalSilverCoins; i++) {
            coinCollection.insertCoin(linkToCoinTypeSilver().constant());
        }
        for (var i = 0; i < numberOfAdditionalBrassCoins; i++) {
            coinCollection.insertCoin(linkToCoinTypeBrass().constant());
        }
        for (var i = 0; i < numberOfAdditionalCopperCoins; i++) {
            coinCollection.insertCoin(linkToCoinTypeCopper().constant());
        }
        var context = contextBuilder()
            .subject(linkToInsertCoin())
            .add("before", context(coinCollectionBefore))
            .add("after", context(coinCollectionAfter))
            .build();
        assertEquals(
            max(coinCollectionBefore.numberOfCopperCoins(), coinCollectionAfter.numberOfCopperCoins()),
            coinCollection.getNumberOfCoinsOfType(linkToCoinTypeCopper().constant()),
            context,
            c -> "unexpected number of copper coins"
        );
        assertEquals(
            max(coinCollectionBefore.numberOfSilverCoins(), coinCollectionAfter.numberOfSilverCoins()),
            coinCollection.getNumberOfCoinsOfType(linkToCoinTypeSilver().constant()),
            context,
            c -> "unexpected number of silver coins"
        );
        assertEquals(
            max(coinCollectionBefore.numberOfBrassCoins(), coinCollectionAfter.numberOfBrassCoins()),
            coinCollection.getNumberOfCoinsOfType(linkToCoinTypeBrass().constant()),
            context,
            c -> "unexpected number of brass coins"
        );
    }

    @ParameterizedTest
    @JsonClasspathSource("h04/H2_4.json")
    @DisplayName("10 | removeCoin()")
    public void t29_2(
        @Property("coinCollectionBefore") CoinCollectionData coinCollectionBefore,
        @Property("coinCollectionAfter") CoinCollectionData coinCollectionAfter
    ) {
        var coinCollection = new CoinCollectionStudent(coinCollectionBefore, true);
        var numberOfLessSilverCoins = coinCollectionBefore.numberOfSilverCoins() - coinCollectionAfter.numberOfSilverCoins();
        var numberOfLessBrassCoins = coinCollectionBefore.numberOfBrassCoins() - coinCollectionAfter.numberOfBrassCoins();
        var numberOfLessCopperCoins = coinCollectionBefore.numberOfCopperCoins() - coinCollectionAfter.numberOfCopperCoins();
        for (var i = 0; i < numberOfLessSilverCoins; i++) {
            coinCollection.removeCoin(linkToCoinTypeSilver().constant());
        }
        for (var i = 0; i < numberOfLessBrassCoins; i++) {
            coinCollection.removeCoin(linkToCoinTypeBrass().constant());
        }
        for (var i = 0; i < numberOfLessCopperCoins; i++) {
            coinCollection.removeCoin(linkToCoinTypeCopper().constant());
        }
        var context = contextBuilder()
            .subject(linkToRemoveCoin())
            .add("before", context(coinCollectionBefore))
            .add("after", context(coinCollectionAfter))
            .build();
        assertEquals(
            min(coinCollectionBefore.numberOfCopperCoins(), coinCollectionAfter.numberOfCopperCoins()),
            coinCollection.getNumberOfCoinsOfType(linkToCoinTypeCopper().constant()),
            context,
            c -> "unexpected number of copper coins"
        );
        assertEquals(
            min(coinCollectionBefore.numberOfSilverCoins(), coinCollectionAfter.numberOfSilverCoins()),
            coinCollection.getNumberOfCoinsOfType(linkToCoinTypeSilver().constant()),
            context,
            c -> "unexpected number of silver coins"
        );
        assertEquals(
            min(coinCollectionBefore.numberOfBrassCoins(), coinCollectionAfter.numberOfBrassCoins()),
            coinCollection.getNumberOfCoinsOfType(linkToCoinTypeBrass().constant()),
            context,
            c -> "unexpected number of brass coins"
        );
    }
}

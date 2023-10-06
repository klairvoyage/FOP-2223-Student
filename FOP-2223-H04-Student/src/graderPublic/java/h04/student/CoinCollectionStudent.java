package h04.student;

import org.mockito.Mockito;
import org.tudalgo.algoutils.tutor.general.assertions.Assertions2;
import org.tudalgo.algoutils.tutor.general.assertions.Context;
import org.tudalgo.algoutils.tutor.general.reflections.ConstructorLink;
import org.tudalgo.algoutils.tutor.general.reflections.FieldLink;
import org.tudalgo.algoutils.tutor.general.reflections.MethodLink;
import org.tudalgo.algoutils.tutor.general.reflections.TypeLink;

import static h04.student.CoinTypeStudent.linkToCoinType;
import static h04.student.H01Student.linkToH01;
import static h04.student.More.linkToInt;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.tudalgo.algoutils.tutor.general.Messages.UNEXPECTED_EXCEPTION;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.callObject;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.contextBuilder;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions3.*;
import static org.tudalgo.algoutils.tutor.general.match.BasicReflectionMatchers.*;
import static org.tudalgo.algoutils.tutor.general.match.BasicStringMatchers.identical;
import static org.tudalgo.algoutils.tutor.general.reflections.Modifier.CLASS;

public class CoinCollectionStudent implements WithCoinTypesStudent {

    Context context;

    Object instance;

    public CoinCollectionStudent(CoinCollectionData data, boolean mock) {
        context = contextBuilder().subject(linkToCoinCollectionConstructor()).add(Assertions2.context(data)).build();
        if (mock) {
            instance = Mockito.mock(linkToCoinCollection().reflection(), CALLS_REAL_METHODS);
            numberOfCopperCoins(data.numberOfCopperCoins());
            numberOfBrassCoins(data.numberOfBrassCoins());
            numberOfSilverCoins(data.numberOfSilverCoins());
        } else {
            instance = callObject(
                () -> linkToCoinCollectionConstructor().invoke(data.numberOfSilverCoins(), data.numberOfBrassCoins(), data.numberOfCopperCoins()),
                context,
                r -> UNEXPECTED_EXCEPTION
            );
        }
    }

    public static TypeLink linkToCoinCollection() {
        return assertTypeExists(
            linkToH01(),
            identical("CoinCollection").and(hasModifiers(CLASS))
        );
    }

    public static FieldLink linkToNumberOfSilverCoins() {
        return assertFieldExists(
            linkToCoinCollection(),
            identical("numberOfSilverCoins").and(sameType(linkToInt()))
        );
    }

    public static FieldLink linkToNumberOfBrassCoins() {
        return assertFieldExists(
            linkToCoinCollection(),
            identical("numberOfBrassCoins").and(sameType(linkToInt()))
        );
    }

    public static FieldLink linkToNumberOfCopperCoins() {
        return assertFieldExists(
            linkToCoinCollection(),
            identical("numberOfCopperCoins").and(sameType(linkToInt()))
        );
    }

    public static ConstructorLink linkToCoinCollectionConstructor() {
        return assertConstructorExists(
            linkToCoinCollection(),
            sameTypes(
                linkToInt(),
                linkToInt(),
                linkToInt()
            )
        );
    }

    public static MethodLink linkToGetNumberOfSilverCoins() {
        return assertMethodExists(
            linkToCoinCollection(),
            identical("getNumberOfSilverCoins").and(sameTypes()).and(sameType(linkToInt()))
        );
    }

    public static MethodLink linkToGetNumberOfBrassCoins() {
        return assertMethodExists(
            linkToCoinCollection(),
            identical("getNumberOfBrassCoins").and(sameTypes()).and(sameType(linkToInt()))
        );
    }

    public static MethodLink linkToGetNumberOfCopperCoins() {
        return assertMethodExists(
            linkToCoinCollection(),
            identical("getNumberOfCopperCoins").and(sameTypes()).and(sameType(linkToInt()))
        );
    }

    public static MethodLink linkToInsertCoin() {
        return assertMethodExists(
            linkToCoinCollection(),
            identical("insertCoin").and(sameTypes(linkToCoinType()))
        );
    }

    public static MethodLink linkToRemoveCoin() {
        return assertMethodExists(
            linkToCoinCollection(),
            identical("removeCoin").and(sameTypes(linkToCoinType()))
        );
    }

    public void numberOfCopperCoins(int numberOfCopperCoins) {
        linkToNumberOfCopperCoins().set(instance, numberOfCopperCoins);
    }

    public void numberOfBrassCoins(int numberOfBrassCoins) {
        linkToNumberOfBrassCoins().set(instance, numberOfBrassCoins);
    }

    public void numberOfSilverCoins(int numberOfSilverCoins) {
        linkToNumberOfSilverCoins().set(instance, numberOfSilverCoins);
    }

    public int numberOfCopperCoins() {
        return linkToNumberOfCopperCoins().get(instance);
    }

    public int numberOfBrassCoins() {
        return linkToNumberOfBrassCoins().get(instance);
    }

    public int numberOfSilverCoins() {
        return linkToNumberOfSilverCoins().get(instance);
    }

    public int getNumberOfSilverCoins() {
        return callObject(
            () -> linkToGetNumberOfSilverCoins().invoke(instance),
            contextBuilder().add(context).subject(linkToGetNumberOfSilverCoins()).build(),
            r -> UNEXPECTED_EXCEPTION
        );
    }

    public int getNumberOfBrassCoins() {
        return callObject(
            () -> linkToGetNumberOfBrassCoins().invoke(instance),
            contextBuilder().add(context).subject(linkToGetNumberOfBrassCoins()).build(),
            r -> UNEXPECTED_EXCEPTION
        );
    }

    public int getNumberOfCopperCoins() {
        return callObject(
            () -> linkToGetNumberOfCopperCoins().invoke(instance),
            contextBuilder().add(context).subject(linkToGetNumberOfCopperCoins()).build(),
            r -> UNEXPECTED_EXCEPTION
        );
    }

    public void insertCoin(Object type) {
        callObject(
            () -> linkToInsertCoin().invoke(instance, type),
            contextBuilder().add(context).subject(linkToInsertCoin()).build(),
            r -> UNEXPECTED_EXCEPTION
        );
    }

    public void removeCoin(Object type) {
        callObject(
            () -> linkToRemoveCoin().invoke(instance, type),
            contextBuilder().add(context).subject(linkToRemoveCoin()).build(),
            r -> UNEXPECTED_EXCEPTION
        );
    }

    @Override
    public Context context() {
        return context;
    }

    @Override
    public Object instance() {
        return instance;
    }

}

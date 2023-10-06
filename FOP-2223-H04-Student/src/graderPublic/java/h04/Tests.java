package h04;

import org.tudalgo.algoutils.tutor.general.match.BasicStringMatchers;
import org.tudalgo.algoutils.tutor.general.match.Matcher;
import org.tudalgo.algoutils.tutor.general.match.MatcherFactories.StringMatcherFactory;
import org.tudalgo.algoutils.tutor.general.match.Stringifiable;

public class Tests {

    private static final StringMatcherFactory STRING_MATCHER_FACTORY = BasicStringMatchers::identical;

    public static <T extends Stringifiable> Matcher<T> stringMatcher(String string) {
        return STRING_MATCHER_FACTORY.matcher(string);
    }
}





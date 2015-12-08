package day5.part1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Predicate;

public class StringsContainedMatcher implements Predicate<String> {

    private static final Logger logger = LoggerFactory.getLogger(StringsContainedMatcher.class);

    private final String[] containsAnyOf;


    public StringsContainedMatcher(String... mustContainOneOf) {
        this.containsAnyOf = mustContainOneOf;
    }

    @Override public boolean test(String s) {
        logger.debug("testing {} to see if it contains any of {}", s, containsAnyOf);
        if (s == null) {
            return false;
        }

        for (String contained: containsAnyOf) {
            if (s.contains(contained)) {
                logger.debug("{} contains \"{}\"", s, contained);
                return true;
            }
        }
        logger.debug("{} did not contain any of {}", s, containsAnyOf);
        return false;
    }
}

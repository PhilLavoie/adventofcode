package day5.part1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Predicate;

import static util.iterables.StringIterable.iterableOf;

public class DoubledLetterMatcher implements Predicate<String> {

    private static Logger logger = LoggerFactory.getLogger(DoubledLetterMatcher.class);

    @Override public boolean test(String s) {
        logger.debug("testing {} for doubled letter count", s);
        if (null == s || s.length() < 2) {
            logger.debug("{} does not contain enough characters to match", s);
            return false;
        }

        char lastLetter = s.charAt(0);

        for (char letter: iterableOf(s.substring(1))) {
            if (letter == lastLetter) {
                logger.debug("{} contains \"{}{}\"", s, lastLetter, letter );
                return true;
            }
            lastLetter = letter;
        }

        logger.debug("{} does not contain any doubled letters", s);
        return false;
    }
}

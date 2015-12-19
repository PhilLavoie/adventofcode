package day5.part1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Predicate;

import static util.iterables.StringIterable.iterableOf;

public class VowelCountMatcher implements Predicate<String> {

    private static final Logger logger = LoggerFactory.getLogger(VowelCountMatcher.class);

    private static final char[] vowels = {'a', 'e', 'i', 'o', 'u'};

    private boolean isVowel(char character) {
        for (char vowel: vowels) {
            if (character == vowel) {
                return true;
            }
        }
        return false;
    }

    @Override public boolean test(String s) {
        logger.debug("testing {} for vowel count", s);
        if (null == s || s.length() < 3) {
            logger.debug("{} does not have enough characters to contain 3 vowels", s);
            return false;
        }

        int vowelCount = 0;
        for (char character: iterableOf(s)) {
            if (isVowel(character)) {
                vowelCount++;
                if (vowelCount == 3) {
                    logger.debug("found 3 vowels in {}", s);
                    return true;
                }
            }
        }

        logger.debug("could not find enough vowels in {}", s);
        return false;
    }
}

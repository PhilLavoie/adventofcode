package day5.part1;

import java.util.function.Predicate;

public class GoodStringMatcher implements Predicate<String> {



    private final static String[] invalidContainedStrings = {"ab", "cd", "pq", "xy"};


    @Override public boolean test(String s) {
        VowelCountMatcher vowelCountMatcher = new VowelCountMatcher();
        DoubledLetterMatcher doubledLetterMatcher = new DoubledLetterMatcher();
        StringsContainedMatcher stringsContainedMatcher =
            new StringsContainedMatcher(invalidContainedStrings);
        return vowelCountMatcher.test(s) && doubledLetterMatcher.test(s) && !stringsContainedMatcher
            .test(s);
    }
}

package day5.part2;

import java.util.function.Predicate;

public class GoodStringMatcher implements Predicate<String> {
    @Override public boolean test(String s) {
        return new NonOverlappingPairsMatcher().and(new SameLettersSeperatedByOneMatcher()).test(s);
    }
}

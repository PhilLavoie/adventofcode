package day5.part2;

import java.util.LinkedList;
import java.util.function.Predicate;

import static util.iterables.StringIterable.iterableOf;

public class SameLettersSeperatedByOneMatcher implements Predicate<String> {

    @Override public boolean test(String s) {
        if (s == null || s.length() < 3) {
            return false;
        }

        LinkedList<Character> window = new LinkedList<>();
        window.add(s.charAt(0));
        window.add(s.charAt(1));
        window.add(s.charAt(2));

        if (hasDuplicatesAtBothEnds(window)) {
            return true;
        }

        for (Character letter: iterableOf(s.substring(3))) {
            window.removeFirst();
            window.add(letter);

            if (hasDuplicatesAtBothEnds(window)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasDuplicatesAtBothEnds(LinkedList<Character> window) {
        return window.getFirst().equals(window.getLast());
    }
}

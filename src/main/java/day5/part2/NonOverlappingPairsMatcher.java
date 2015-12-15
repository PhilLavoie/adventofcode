package day5.part2;

import util.Pair;

import java.util.Comparator;
import java.util.TreeSet;
import java.util.function.Predicate;

import static util.StringIterable.iterableOf;

public class NonOverlappingPairsMatcher implements Predicate<String> {

    private final Comparator<Pair.Pair> comparator =
        (pair1, pair2) -> {
            char left1 = pair1.getLeft();
            char left2 = pair2.getLeft();
            if (left1 != left2) {
                return left1 - left2;
            }
            return pair1.getRight() - pair2.getRight();
        };

    @Override public boolean test(String s) {
        if (s == null || s.length() < 4) {
            return false;
        }

        TreeSet<Pair.Pair> pairs = new TreeSet<>(comparator);

        Character firstChar = s.charAt(0);
        Character secondChar = s.charAt(1);

        Pair.Pair lastPair = new Pair.Pair(firstChar, secondChar);
        pairs.add(lastPair);

        boolean pairAlreadyOverlapped = false;

        for (Character character: iterableOf(s.substring(2))) {
            firstChar = secondChar;
            secondChar = character;

            Pair.Pair newPair = new Pair.Pair(firstChar, secondChar);
            //Skip overlapping pairs.
            if (0 == comparator.compare(lastPair, newPair)) {
                if (!pairAlreadyOverlapped) {
                    pairAlreadyOverlapped = true;
                    continue;
                } else {
                    //We have this situation: "aaaa"
                    return true;
                }
            }

            if (pairs.contains(newPair)) {
                return true;
            }
            pairs.add(newPair);
            lastPair = newPair;
        }

        return false;
    }
}

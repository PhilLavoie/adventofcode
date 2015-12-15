package day11;

import util.ValuePair;

import java.util.HashSet;
import java.util.function.Predicate;

import static com.google.common.base.Preconditions.checkNotNull;

public class NonOverlappingDifferentPairsValidator implements Predicate<char[]> {

    private boolean isPair(char left, char right) {
        return left == right;
    }

    //2 different pairs cannot overlap.
    @Override public boolean test(char[] chars) {
        checkNotNull(chars);

        if (chars.length < 4) {
            return false;
        }

        ValuePair<Character> firstPair = null;

        for (int i = 1; i < chars.length; i++) {
            //If it's not a pair then f off.
            if (!isPair(chars[i - 1], chars[i])) {
                continue;
            }
            ValuePair<Character> currentPair = new ValuePair<>(chars[i - 1], chars[i]);

            if (firstPair == null) {
                firstPair = currentPair;
                continue;
            }

            //If we have already seen this pair then move on.
            if (firstPair.equals(currentPair)) {
                continue;
            }

            return true;
        }
        return false;
    }

}

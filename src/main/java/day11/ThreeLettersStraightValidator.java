package day11;

import java.util.function.Predicate;

import static com.google.common.base.Preconditions.checkNotNull;

public class ThreeLettersStraightValidator implements Predicate<char[]> {
    @Override public boolean test(char[] chars) {
        checkNotNull(chars);

        if (chars.length < 3) {
            return false;
        }

        for (int i = 2; i < chars.length; i++) {
            if (isStraight(chars[i - 2], chars[i - 1], chars[i])) {
                return true;
            }
        }
        return false;
    }

    private boolean isStraight(char firstChar, char secondChar, char thirdChar) {
        return firstChar + 1 == secondChar && secondChar + 1 == thirdChar;
    }
}

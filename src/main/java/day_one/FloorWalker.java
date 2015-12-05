package day_one;

import java.util.Arrays;
import java.util.List;

public class FloorWalker {

    private static final List<Character> validCharacters = Arrays.asList('(', ')');

    private static boolean isValidChar(int character) {
        return validCharacters.contains((char) character);
    }

    private static void checkInput(String input) {
        final char[] invalidCharacter = new char[1]; //Only used when one is found.

        if (!input.chars().allMatch(character -> {
            if (!isValidChar(character)) {
                invalidCharacter[0] = (char) character;
                return false;
            }
            return true;
        })) {
            throw new IllegalArgumentException("invalid character: " + invalidCharacter);
        }
    }

    public FloorWalkingResult walk(String input) {
        final long[] floor = {0};
        final long[] firstBasementIndex = {-1};
        final boolean[] firstBasementIndexFound = {false};
        final long[] currentIndex = {1};
        checkInput(input);

        input.chars().forEach(character -> {
            if (character == '(') {
                floor[0]++;
            } else {
                floor[0]--;
                if (floor[0] < 0 && !firstBasementIndexFound[0]) {
                    firstBasementIndex[0] = currentIndex[0];
                    firstBasementIndexFound[0] = true;
                }
            }

            currentIndex[0]++;
        });

        return new FloorWalkingResult(floor[0], firstBasementIndex[0]);
    }
}

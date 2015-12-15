package day11;

import java.util.function.Predicate;

import static com.google.common.base.Preconditions.checkNotNull;

public class ValidCharactersValidator implements Predicate<char[]> {

    private final char[] invalidCharacters;

    public ValidCharactersValidator(char[] invalidCharacters) {
        this.invalidCharacters = invalidCharacters;
    }

    private boolean isValidCharacter(char character) {
        for (char invalidCharacter: invalidCharacters) {
            if (invalidCharacter == character) {
                return false;
            }
        }
        return true;
    }

    @Override public boolean test(char[] chars) {
        checkNotNull(chars);

        for (char character: chars) {
            if (!isValidCharacter(character)) {
                return false;
            }
        }
        return true;
    }
}

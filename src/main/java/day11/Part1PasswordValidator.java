package day11;

import java.util.function.Predicate;

public class Part1PasswordValidator implements Predicate<char[]>, PasswordValidator {
    private final ThreeLettersStraightValidator threeLettersStraightValidator;
    private final ValidCharactersValidator invalidCharactersValidator;
    private final NonOverlappingDifferentPairsValidator nonOverlappingDifferentPairsValidator;

    public Part1PasswordValidator(ThreeLettersStraightValidator threeLettersStraightValidator,
            ValidCharactersValidator invalidCharactersValidator,
            NonOverlappingDifferentPairsValidator nonOverlappingDifferentPairsValidator) {
        this.threeLettersStraightValidator = threeLettersStraightValidator;
        this.invalidCharactersValidator = invalidCharactersValidator;
        this.nonOverlappingDifferentPairsValidator = nonOverlappingDifferentPairsValidator;
    }

    @Override public boolean test(char[] chars) {
        return threeLettersStraightValidator.test(chars) && invalidCharactersValidator.test(chars)
                && nonOverlappingDifferentPairsValidator.test(chars);
    }

    @Override public boolean isValid(char[] password) {
        return test(password);
    }
}

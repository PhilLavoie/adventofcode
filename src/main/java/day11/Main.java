package day11;

import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        char[] initialPassword = {'h', 'e', 'p', 'x', 'c', 'r', 'r', 'q'};
        char[] invalidCharacters = {'i', 'o', 'l'};

        PasswordEnumerator passwordEnumerator = new PasswordEnumerator(initialPassword,
                new Part1PasswordValidator(new ThreeLettersStraightValidator(),
                        new ValidCharactersValidator(invalidCharacters), new NonOverlappingDifferentPairsValidator()));

        Iterator<char[]> passwordIterator = passwordEnumerator.iterator();

        String firstPassword =  new String(passwordIterator.next());
        System.out.println("First password is: " + firstPassword);

        String secondPassword = new String(passwordIterator.next());
        System.out.println("Second password is: " + secondPassword);
    }
}

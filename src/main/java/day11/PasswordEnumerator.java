package day11;

import java.util.Iterator;

public class PasswordEnumerator implements Iterable<String> {
    private final char[] firstPassword;
    private final PasswordValidator validator;

    public PasswordEnumerator(char[] firstPassword, PasswordValidator validator) {
        this.firstPassword = firstPassword;
        this.validator = validator;
    }


    @Override public Iterator<String> iterator() {
        return null;
    }

    private static class PassworIterator implements Iterator<String> {
        private char[] currentPassword;
        private final PasswordValidator validator;

        public PassworIterator(char[] firstPassword, PasswordValidator validator) {
            this.currentPassword = firstPassword;
            this.validator = validator;
        }

        @Override public boolean hasNext() {
            return true;
        }

        @Override public String next() {
            nextPassword();
            return new String(currentPassword);
        }

        private void nextPassword() {
            do {
                incrementPassword();
            } while (!validator.isValid(currentPassword));
        }

        private void incrementPassword(int index) {
            if (currentPassword[index] != 'z') {
                currentPassword[index]++;
            } else {
                currentPassword[index] = 'a';
                if (0 < index) {
                    incrementPassword(index - 1);
                }
            }
        }

        private void incrementPassword() {
            incrementPassword(currentPassword.length - 1);
        }
    }
}
